package hu.csanyzeg.master;

import java.util.ArrayList;


public class Matek {

    float beviz;
    float time = 0;
    float kimeno = 1;
    float difi = 0;
    float vizmennyiseg = 10;//m3 900 az optimális, a teljes vizmennyiség megkapásához hozzáadunk 890m3-t
    ArrayList<cso> pipe = new ArrayList<cso>();
    ArrayList<Float> szintek = new ArrayList<Float>();

    float min = 890;
    float max = 910;

    public Matek(float vizbe, float[] csok) {
        beviz = vizbe;
        int co = 0;
        for (float a : csok){
            co++; //kell hogy tudjuk melyik cső lesz, lusta voltam for-ba
            cso asd = new cso(a,co);
            pipe.add(asd);
        }
        setDifi();
    }

    public ArrayList<cso> getPipe() {
        return pipe;
    }

    public cso getcso(int i) {
        return getPipe().get(i);
    }

    public void setDifi()
    {
        difi = (max-min)/getPipe().size();
    }

    public float getVizmennyiseg(){
        return vizmennyiseg;
    }

    public float getOsszesKimeno()
    {
        float sum =0; //csak egy void ami visszaadja az összes csövön átmenő vízmennyiséget
        for (int i = 0; i < getPipe().size(); i++)
        {
            sum+=getcso(i).getKi();
        }
        return sum;
    }

    public float getKimeno()
    {
        return kimeno;
    }

    public ArrayList<Float> getSzintek() {
        return szintek;
    }

    public float getSzint(int index) {
        return getSzintek().get(index);
    }

    public float getBemeno()
    {
        return beviz; //csak egy void ami visszaadja a nyílt csöveken átmenő vízmennyiséget
    }

    public float getLegkisebb()
    {
        float max = getLegnagyobb();
        for (int i = 0; i < getPipe().size(); i++)
        {
            if(getPipe().get(i).getKi() < max) max = getPipe().get(i).getKi();
        }
        return max;
    }

    public float getAtlag()
    {
        return getOsszesKimeno()/pipe.size();
    }

    public float getDifi() {return difi;}

    public float getLegnagyobb()
    {
        float min = 0;
        for (int i = 0; i < getPipe().size(); i++)
        {
            if(getPipe().get(i).getKi() > min) min = getPipe().get(i).getKi();
        }
        return min;
    }

    public void setMin(float value)
    {
        min = value * 100;
    }
    //A teljes vízmennyiséget adjuk vissza, hiszen a setDifi ezekkel számol
    public void setMax(float value)
    {
        max = value * 100;
    }

    public void step(float deltaTime)
    {
        time+=deltaTime;
        vizmennyiseg += (deltaTime * beviz); //hozzáadjuk az eltelt idő alatt befolyt vizet
        /*if (beviz > getLegkisebb()) getcso(0).setOpen(true); //ha több víz folyik be, mint amennyit a legkisebb csap elbír, akkor nyíljon ki az első, mert ha kevesebb víz folyik be mint a legkisebb csap, akkor lemegy 8.9 alá
        if (getVizmennyiseg()> getSzint(1)) getcso(1).setOpen(true);*/
        setKimeno();
        vizmennyiseg += -1*(deltaTime*kimeno); // kivonjuk az eltelt idő alatt kifolyt vizet
        // Fontos hogy a vízmennyiség nem mehet 890 m3 alá, de 910m3 fölé sem! tehát van egy kellemes 20m3-nyi területünk floatban

    }

    public void setKimeno() {
        float sum =0; //csak egy void ami összegzi a nyílt csöveken átmenő vízmennyiséget
        for (int i = 0; i < pipe.size(); i++){
            if (getVizmennyiseg() + 890 > getSzint(i)) getcso(i).setOpen(true);
            else getcso(i).setOpen(false);
        }
        for (int i = 0; i < pipe.size(); i++) {
            if (getcso(i).isOpen()) sum += getcso(i).getKi();
        }
        this.kimeno = sum;
    }

    public void szintfeltoltes(){//ez fut le először, feltölti a szint tömböt
        float sum = 0;
        while (sum < max-min){
            szintek.add(sum + min);
            sum += difi;
            //itt a kettőt megcseréltem, mivel a 0-t is belekell rakni, mert ott is kikell nyitni már egy csapot
        }
    }

    public void szintfeltoltesSokadszorra(){//ez felülírja a szint tömbben lévő értékeket
        float sum = 0;
        int index = 0;
        while (sum < max-min){
            szintek.set(index++, sum + min);
            sum += difi;
        }
    }

    public void szintekNull()
    {//szintek nullázása
        for (int i = 0; i < szintek.size();i++)
        {
            szintek.set(i,0f);
        }
    }

    public void setBemeno(float vizbe)
    {
        beviz = vizbe;
    }

    public static void main(String[] args) {
        float[] csovek = {1,2,4,8};
        Matek m = new  Matek(2, csovek);
        System.out.println(m.getVizmennyiseg());
        for(int i = 0; i< 200; i++){
            m.step(0.1f);
            System.out.println(m.getVizmennyiseg());
        }
        System.out.println("asd");
    }
}

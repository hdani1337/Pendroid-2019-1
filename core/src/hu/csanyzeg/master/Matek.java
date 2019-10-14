package hu.csanyzeg.master;

import java.util.ArrayList;


public class Matek {

    float beviz;
    float time = 0;
    float kimeno = 1;
    float vizmennyiseg = 10;//m3 900 az optimális, a teljes vizmennyiség megkapásához hozzáadunk 890m3-t
    ArrayList<cso> pipe = new ArrayList<cso>();

    public Matek(float vizbe, float[] csok) {
        beviz = vizbe;
        int co = 0;
        for (float a : csok){
            co++; //kell hogy tudjuk melyik cső lesz, lusta voltam for-ba
            cso asd = new cso(a,co);
            pipe.add(asd);
        }
    }

    public ArrayList<cso> getPipe() {
        return pipe;
    }

    public cso getcso(int i) {
        return getPipe().get(i);
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

    public float getBemeno()
    {
        return beviz; //csak egy void ami visszaadja a nyílt csöveken átmenő vízmennyiséget
    }

    public float getLegkisebb()
    {
        float min = 32768;
        for (int i = 0; i < getPipe().size(); i++)
        {
            if(getPipe().get(i).getKi() < min) min = getPipe().get(i).getKi();
        }
        return min;
    }

    public void step(float deltaTime)
    {
        time+=deltaTime;
        vizmennyiseg += (deltaTime * beviz); //hozzáadjuk az eltelt idő alatt befolyt vizet
        if (beviz > getLegkisebb()) getcso(0).setOpen(true); //ha több víz folyik be, mint amennyit a legkisebb csap elbír, akkor nyíljon ki az első, mert ha kevesebb víz folyik be mint a legkisebb csap, akkor lemegy 8.9 alá
        if (getVizmennyiseg()> 11) getcso(1).setOpen(true);
        setKimeno();
        vizmennyiseg += -1*(deltaTime*kimeno); // kivonjuk az eltelt idő alatt kifolyt vizet
        // Fontos hogy a vízmennyiség nem mehet 890 m3 alá, de 910m3 fölé sem! tehát van egy kellemes 20m3-nyi területünk floatban

    }

    public void setKimeno() {
        float sum =0; //csak egy void ami összegzi a nyílt csöveken átmenő vízmennyiséget
        for (int i = 0; i < getPipe().size(); i++)
        {
            if(getcso(i).isOpen()) sum+=getcso(i).getKi();
        }
        this.kimeno = sum;
    }

    public void setBemeno(float vizbe)
    {
        beviz = vizbe;
    }

    public static void main(String[] args) {
        float[] csovek = {1,2,4,8};
        Matek m = new  Matek(2, csovek);
        System.out.println(m.getVizmennyiseg());
        for(int i = 0; i< 20; i++){
            m.step(0.1f);
            System.out.println(m.getVizmennyiseg());
        }
        System.out.println("asd");
    }
}

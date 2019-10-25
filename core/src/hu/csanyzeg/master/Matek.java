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
    //Kezdőértékek

    public Matek(float vizbe, float[] csok) {
        beviz = vizbe;
        int co = 0;
        for (float a : csok){
            co++; //kell hogy tudjuk melyik cső lesz, lusta voltam for-ba
            cso asd = new cso(a,co);
            pipe.add(asd);
        }
        bubbleSorting(pipe);
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
        setKimeno();
        vizmennyiseg += -1*(deltaTime*kimeno); // kivonjuk az eltelt idő alatt kifolyt vizet

    }

    public void bubbleSorting(ArrayList<cso> array)
    {//Sorbarendezi a csöveket buborékrendezés módszerrel
        for (int i = array.size() - 1; i >= 0; i--)
        {
            for (int j = 0; j < i; j++)
            {
                if (array.get(i).getKi() < array.get(j).getKi())
                {
                    cso temp = array.get(i);
                    array.set(i,array.get(j));
                    array.set(j,temp);
                }

            }
        }
    }

    public void setKimeno() {
        float sum =0; //csak egy void ami összegzi a nyílt csöveken átmenő vízmennyiséget
        for (int i = 0; i < pipe.size(); i++){
            if (getVizmennyiseg() + 889 > getSzint(i)) getcso(i).setOpen(true);
            else if (getVizmennyiseg() + 889 < ((getSzint(i)-min)*0.15)+min) getcso(i).setOpen(false);
        }
        for (int i = 0; i < pipe.size(); i++) {
            if (getcso(i).isOpen()) sum += getcso(i).getKi();
        }
        this.kimeno = sum;
    }

    public void szintfeltoltes(){//ez fut le először, feltölti a szint tömböt
        for (int i = 1; i <= getPipe().size();i++)
        {
            szintek.add(((max-min)/getPipe().size())*(i-0.2f)+ min);
        }
    }

    public void szintfeltoltesSokadszorra(){//ez felülírja a szint tömbben lévő értékeket
        for (int i = 1; i <= getPipe().size();i++)
        {
            szintek.set(i-1,((max-min)/getPipe().size())*(i-0.2f)+ min);
        }
    }

    public void setBemeno(float vizbe)
    {
        beviz = vizbe;
    }
}

package hu.csanyzeg.master;

import java.util.ArrayList;

public class Matek {

    float beviz;
    float time = 0;
    float kimeno ;
    float vizmennyiseg = 10;//m3
    ArrayList<cso> pipe = new ArrayList<cso>();

    public Matek(float vizbe, float[] kimeno) {
        beviz = vizbe;
        for (float a : kimeno){
            cso asd = new cso(a);
        }
    }

    public float getVizmennyiseg(){
        return vizmennyiseg;
    }

    public void step(float deltaTime){
        time+=deltaTime;
    }
/*
    public static float mennyiseg() {
        float valtozas = beviz * getTime - (kimeno * time);
        float sum = 900 + valtozas;
    }
*/
    public static void main(String[] args) {
        Matek m = new  Matek(2, new float[] {2,3, 1});
        for(int i = 0; i< 20; i++){
            m.step(0.1f);
            System.out.println(m.getVizmennyiseg());
        }
        System.out.println("asd");
    }
}

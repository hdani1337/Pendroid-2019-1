package hu.csanyzeg.master;

public class cso {

    int key;
    float ki;
    boolean isOpen = false;

    public cso(float asd, int k) {
        ki = asd;
        key = k;
    }

    public Float getKi() {
        return this.ki;
    }

    public String getKistring() {
        String s = Float.toString(getKi());
        return s;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        if(open){
            if (!isOpen){
                isOpen = true;
            }
        }
        else {
            if(isOpen){
                isOpen = false;
            }
        }
    }
}

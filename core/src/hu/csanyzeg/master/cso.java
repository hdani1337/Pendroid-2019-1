package hu.csanyzeg.master;

public class cso {


    int key;
    float ki;
    boolean isOpen = false;

    public cso(float asd, int k) {
        ki = asd;
        key = k;
        main(new String[]{"none"});
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
                //System.out.println(getKey() + "-as cso kinyílt");
            }
        }
        else {
            if(isOpen){
                isOpen = false;
                //System.out.println(getKey() + "-as cso záródott");
            }
        }
    }

    public void main(String[] args) {
    }
}

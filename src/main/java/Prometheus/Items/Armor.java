package Prometheus.Items;

public class Armor extends Item{

    //params
    private int defRating;
    private String type;


    //G&S
    public int getDefPwr() {
        return defRating;
    }
    public void setDefPwr(int defPwr) {
        this.defRating = defPwr;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }


    //constructor
    public Armor(String name, int quantity, int defPwr, String type) {
        super(name, quantity);
        this.defRating = defPwr;
        this.type = type;
    }

    //methods (special traits)
}

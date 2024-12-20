package Prometheus.Items;

public class Item {

    //params
    private String name;
    private int quantity;

    //G&S
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //constructor
    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}

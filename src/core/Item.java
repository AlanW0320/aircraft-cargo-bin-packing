package core;

//Represents an item to be packed into bins with a size/weight.
public class Item {
    private int id;          // Unique identifier for the item
    private double size;        // Size/weight of the item (must be <= bin capacity)

    // Constructor to create a new item
    public Item(int id, double size) {
        this.id = id;
        this.size = size;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public double getSize() {
        return size;
    }


    @Override
    public String toString() {
        return String.format("Item[%s, size=%.2f]", id, size);
    }
}
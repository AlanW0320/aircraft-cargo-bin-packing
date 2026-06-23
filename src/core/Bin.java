package core;

import java.util.ArrayList;
import java.util.List;

//Represents a bin/container that can hold items up to its capacity.=
public class Bin {
    private final double capacity;    // Maximum capacity of the bin
    private double currentLoad;      // Current total size of items in the bin
    private List<Item> items;        // List of items currently in the bin

    //Creates a new bin with specified capacity
    public Bin(double capacity) {
        this.capacity = capacity;
        this.currentLoad = 0;
        this.items = new ArrayList<>();
    }

    //add an item to the bin
    //return true if item was added successfully, false if not enough space
    public boolean addItem(Item item) {
        if (canFit(item)) {
            items.add(item);
            currentLoad += item.getSize();
            return true;
        }
        return false;
    }

    //Checks if an item can fit in this bin
    public boolean canFit(Item item) {
        return (currentLoad + item.getSize()) <= capacity;
    }

    //Calculates remaining space in the bin
    public double getRemainingSpace() {
        return capacity - currentLoad;
    }

    // Getters
    public double getCapacity() {
        return capacity;
    }

    public double getCurrentLoad() {
        return currentLoad;
    }

    public List<Item> getItems() {
        return new ArrayList<>(items); // Return copy to preserve encapsulation
    }

    @Override
    public String toString() {
        return String.format("Bin [capacity=%.2f, currentLoad=%.2f, items=%d]", 
                            capacity, currentLoad, items.size());
    }
}
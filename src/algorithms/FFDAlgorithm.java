package algorithms;

import core.Bin;
import core.Item;
import core.PackingSolution;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

public class FFDAlgorithm {
    private final int binCapacity;
    private final List<Item> items;

    public FFDAlgorithm(int binCapacity, List<Item> items) {
        this.binCapacity = binCapacity;
        this.items = new ArrayList<>(items); // Defensive copy
    }

    public PackingSolution pack() {
        long startTime = System.currentTimeMillis();
        
        List<Bin> bins = new ArrayList<>();
        // Make a copy of items to avoid modifying the original list
        List<Item> itemsToPack = new ArrayList<>(items);
        
        // Sort items in descending order
        Collections.sort(itemsToPack, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return Double.compare(o2.getSize(), o1.getSize());
            }
        });

        for (Item item : itemsToPack) {
            if (item.getSize() > binCapacity) {
                throw new IllegalArgumentException("Item size exceeds bin capacity");
            }

            boolean placed = false;
            
            // Try to place item in existing bins (first-fit)
            for (Bin bin : bins) {
                if (bin.canFit(item)) {
                    bin.addItem(item);
                    placed = true;
                    break;
                }
            }

            // If item wasn't placed, open new bin
            if (!placed) {
                Bin newBin = new Bin(binCapacity);
                newBin.addItem(item);
                bins.add(newBin);
            }
        }

        long endTime = System.currentTimeMillis();
        return new PackingSolution(bins, endTime - startTime);
    }
}
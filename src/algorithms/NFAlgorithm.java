package algorithms;

import core.Bin;
import core.Item;
import core.PackingSolution;
import java.util.ArrayList;
import java.util.List;

public class NFAlgorithm {
    private final int binCapacity;
    private final List<Item> items;

    public NFAlgorithm(int binCapacity, List<Item> items) {
        this.binCapacity = binCapacity;
        this.items = new ArrayList<>(items); // Defensive copy
    }

	public PackingSolution pack() {
        long startTime = System.currentTimeMillis();
        
        List<Bin> bins = new ArrayList<>();
        if (items.isEmpty()) {
            return new PackingSolution(bins, System.currentTimeMillis() - startTime);
        }

        // Start with first bin
        Bin currentBin = new Bin(binCapacity);
        bins.add(currentBin);

        for (Item item : items) {
            if (item.getSize() > binCapacity) {
                throw new IllegalArgumentException("Item size exceeds bin capacity");
            }

            // Try to add to current bin
            if (!currentBin.addItem(item)) {
                // If doesn't fit, create new bin and make it current
                currentBin = new Bin(binCapacity);
                currentBin.addItem(item);
                bins.add(currentBin);
            }
        }

        long endTime = System.currentTimeMillis();
        return new PackingSolution(bins, endTime - startTime);
    }
}
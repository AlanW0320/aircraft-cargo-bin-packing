package algorithms;

import core.Bin;
import core.Item;
import core.PackingSolution;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

public class BFDAlgorithm {
	private final int binCapacity;
	private final List<Item> items;
	
	public BFDAlgorithm(int binCapacity, List<Item> items) {
		this.binCapacity = binCapacity;
		this.items = new ArrayList<>(items);
	}
	
	public PackingSolution pack() {
        long startTime = System.currentTimeMillis();

        List<Bin> bins = new ArrayList<>();
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

            Bin bestBin = null;
            double minRemainingSpace = Double.MAX_VALUE;

            // Find the bin with the least remaining space after placing the item
            for (Bin bin : bins) {
                if (bin.canFit(item)) {
                    double remaining = bin.getCapacity() - bin.getCurrentLoad() - item.getSize();
                    if (remaining < minRemainingSpace) {
                        minRemainingSpace = remaining;
                        bestBin = bin;
                    }
                }
            }

            if (bestBin != null) {
                bestBin.addItem(item);
            } else {
                // No suitable bin, create new one
                Bin newBin = new Bin(binCapacity);
                newBin.addItem(item);
                bins.add(newBin);
            }
        }

        long endTime = System.currentTimeMillis();
        return new PackingSolution(bins, endTime - startTime);
    }

}
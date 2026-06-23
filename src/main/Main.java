package main;

import algorithms.NFAlgorithm;
import algorithms.FFDAlgorithm;
import core.Bin;
import core.Item;
import core.PackingSolution;
import datasets.DataFileLoader;
import exporter.BinSolution;

import java.util.*;

public class Main 
{

	public static void main (String[] args)
	{
		Scanner input = new Scanner(System.in);
		
		try 
		{
			//Select which Algorithms
			System.out.println("Please Select Algorithms:");
			System.out.println("1. First Fit Decreasing Algorithm");
			System.out.println("2. Next Fit Algorithm");
			int AlgorithmsChoice = Integer.parseInt(input.nextLine());
			
			//Select which Dataset
			System.out.println("\nPlease Select Dataset:");
			System.out.println("1. Small dataset"); //manual verification
			System.out.println("2. Large dataset"); //more than 100 items
			System.out.println("3. Edge dataset"); //a huge item in the dataset
			int DatasetChoice = Integer.parseInt(input.nextLine());
			
			//Set the Dataset file
			String filePath = switch(DatasetChoice)
			{
				case 1->"data/SmallDataset.txt";
				case 2->"data/LargeDataset.txt";
				case 3->"data/EdgeDataset.txt";
				default -> throw new IllegalArgumentException("Unexpected Dataset value!");
			};
			
			List<Item> items = DataFileLoader.loadFromFile(filePath); //load the data from the file
			int binCapacity = 100; //Fixed the maximum capacity
	         
			//To handling the error of empty dataset and exceeded item
			if (items.isEmpty()) 
			{
				throw new IllegalArgumentException("The dataset is empty.");
			}
			for (Item item : items) 
			{
				if (item.getSize() > binCapacity) 
				{
					throw new IllegalArgumentException("Item size exceeds bin capacity. Item: " + item.getSize() + "kg");
				}
			}

			//Set the Algorithms
			PackingSolution solution;
			if (AlgorithmsChoice == 1) 
			{
				FFDAlgorithm ffd = new FFDAlgorithm(binCapacity, items);
				solution = ffd.pack();
			} 
	        else if (AlgorithmsChoice == 2) 
	        {
	        	NFAlgorithm nf = new NFAlgorithm(binCapacity, items);
	            solution = nf.pack();
	        } 
	        else 
	        {
	        	throw new IllegalArgumentException("Invalid Algorithms Choice!");
	        }

	        // Display bin packing results
	        System.out.println("\n------ Bin Packing Results ------");
	        List<Bin> bins = solution.getBinsUsed();
	        System.out.println("Total Bins Used: " + bins.size());
	        System.out.println("Total Items Packed: " + solution.getTotalItemsPacked());
	        System.out.printf("Total Weight Packed: %.2fkg\n", solution.getTotalWeightPacked());
	        System.out.printf("Overall Utilization: %.2f%%\n", solution.getAverageUtilization() * 100);
	        System.out.println("Execution Time: " + solution.getComputationTime() + " ms");
	         
	    	System.out.println("\n------ Bin Used Visualization ------");
	    	printBins(bins);
	         
	        // Export the final result to file
	        BinSolution.exportToFile(bins, "BinSolutionOutput.txt");
	        System.out.println("\nSolution exported to BinSolutionOutput.txt");
		}
		catch(Exception e) 
		{
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	//Method to print how items store in bins
	private static void printBins(List<Bin> bins) {
	    for (int i = 0; i < bins.size(); i++) {
	        Bin bin = bins.get(i);
	        StringBuilder sb = new StringBuilder();
	        sb.append("Bin ").append(i + 1).append(": [");
	        
	        // Add all items in the bin
	        List<Item> items = bin.getItems();
	        for (int j = 0; j < items.size(); j++) {
	            sb.append((int)items.get(j).getSize());
	            if (j < items.size() - 1) {
	                sb.append(", ");
	            }
	        }
	        
	        sb.append("] (").append((int)bin.getCurrentLoad()).append("%)");
	        System.out.println(sb.toString());
	    }
	}
}

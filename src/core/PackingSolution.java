package core;

import java.util.List;

/*
  Represents a solution to the bin packing problem, tracking metrics and results.
  Evaluate how good the packing solution is.
 */
public class PackingSolution {
    private List<Bin> binsUsed;      // List of bins used in this solution
    private int totalItemsPacked;    // Total number of items packed
    private double totalWeightPacked; // Total weight/size of all packed items
    private long computationTime;    // Time taken to compute the solution (ms)

    /*
      Creates a new packing solution
      binsUsed =  List of bins used in the solution
      computationTime = Time taken to compute the solution in milliseconds
     */
    public PackingSolution(List<Bin> binsUsed, long computationTime) {
        this.binsUsed = binsUsed;
        this.computationTime = computationTime;
        calculateMetrics();
    }

    // Calculate various metrics about the solution
    private void calculateMetrics() {
        totalItemsPacked = 0;
        totalWeightPacked = 0;
        
        for (Bin bin : binsUsed) {
            totalItemsPacked += bin.getItems().size();
            totalWeightPacked += bin.getCurrentLoad();
        }
    }

    //Getter
    //Number of bins used in this solution
    public int getNumBinsUsed() {
        return binsUsed.size();
    }

    //Total number of items packed
    public int getTotalItemsPacked() {
        return totalItemsPacked;
    }

    //Total weight/size of all packed items
    public double getTotalWeightPacked() {
        return totalWeightPacked;
    }

    /*
     Calculates the average utilization of bins (0.0 to 1.0)
     return Average utilization percentage
     */
    public double getAverageUtilization() {
        if (binsUsed.isEmpty()) 
        	return 0;
        double totalCapacity = binsUsed.stream().mapToDouble(Bin::getCapacity).sum();
        return totalWeightPacked / totalCapacity;
    }

    //Computation time in milliseconds
    public long getComputationTime() {
        return computationTime;
    }

    //List of bins used 
    public List<Bin> getBinsUsed() {
        return List.copyOf(binsUsed);
    }

    @Override
    public String toString() {
        return String.format("PackingSolution [binsUsed=%d, itemsPacked=%d, utilization=%.2f%%, time=%dms]",
                getNumBinsUsed(), 
                getTotalItemsPacked(),
                getAverageUtilization() * 100,
                getComputationTime());
    }
}
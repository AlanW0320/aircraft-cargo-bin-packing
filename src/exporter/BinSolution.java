package exporter;


import core.Bin;
import core.Item;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BinSolution
{
    public static void exportToFile(List<Bin> bins, String filename) throws IOException 
    {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        int binNumber = 1;

        for (Bin bin : bins) 
        {
            writer.write("Bin " + binNumber++ + ":\n");
            for (Item item : bin.getItems()) 
            {
                writer.write("  Item " + item.getId() + ": " + item.getSize() + "\n");
                
            }
            writer.write("  *Total Load: "+ bin.getCurrentLoad() + "kg\n");
            writer.write("\n");
        }
        writer.close();
    }
}
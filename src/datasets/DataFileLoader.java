package datasets;

import core.Item;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataFileLoader 
{
    public static List<Item> loadFromFile(String filename) throws IOException 
    {
        List<Item> items = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        int id = 1;

        while ((line = br.readLine()) != null) 
        {
            line = line.trim();
            if (!line.isEmpty()) 
            {
                double size = Double.parseDouble(line);
                items.add(new Item(id++, size));
            }
        }

        br.close();
        return items;
    }
}
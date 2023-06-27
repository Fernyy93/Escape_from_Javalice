package src;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Set;
import java.io.FileReader;
import java.io.FileWriter;

/**
* Class that performs file input and output.
* 
* @author Alex Fernicola
* @version ver 1.0.13    
*/
public class FileIO
{
    /**
    * Default Constructor - empty
    */
    public FileIO()
    {
        
    }
    

    /**
    * Static method that reads the exits data in exits.txt into a HashMap. Static is chosen as an object of the FileIO class is not required.
    * A HashMap was chosen as the data structure to contain exit data as the direction and probabilities were identified as a key, value pair.
    * Most people would have made an ArrayList<String> for directions and an int[] array for probabilities but this solution only requires one data structure/collection.
    * The key is the compass direction of the exit.
    * The value is an integer array of probabilities associated with each exit. 
    * int[0] = Probability the exit exists. 
    * int[1] = Probability that the exit is the escape portal. 
    * int[2] = Probability room triggers police.
    * The value could have been an ArrayList however the size of the probabilities array never changes, and elements are never removed or added.
    * The probabilities need to be accessed by the Game according to the directional input by the player. The probabilities also need to be frequently updated. 
    * @param newFileName - A string containing the filename
    * @return HashMap - String is the compass direction (key), int[] (value) is an array of integers which represent the probabilities associated with each exit.
    */
    public static HashMap<String, int[]> readFile(String newFileName)
    {
        // predeclare the HashMap and the direction.
        HashMap<String, int[]> exits = new HashMap<>();
        String direction = "";
        
        // attempt to open the filename for reading.
        try
        {        
            FileReader reader = new FileReader(newFileName);
            int counter = 0;
            try
            {
                // open a scanner
                Scanner fileInput = new Scanner(reader);
                // read each line of the input file
                while (fileInput.hasNextLine())
                {
                    // counter to keep track of which line was read incorrectly if errors occur.
                    counter++;
                    // predeclare an empty integer array of dimensions 1x3.
                    int[] probabilities = new int[3];
                    // separate the contents of each line of the file by a comma.
                    String[] lineContents = fileInput.nextLine().split(",");
                    // The direction is the first element of the lineContents array
                    direction = lineContents[0];
                    
                    try
                    {
                        // the first element of the probabilies array is the probability that the exit exists.
                        probabilities[0] = Integer.parseInt(lineContents[1]);
                        // second element is the probability the exit is the escape portal.
                        probabilities[1] = Integer.parseInt(lineContents[2]);
                        // third and final element is the probability the exit triggers the magic police.
                        probabilities[2] = Integer.parseInt(lineContents[3]);
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error in direction probabilities on line " + counter + " skipping line." );
                        continue;
                    }
                    // insert the direction and probabilities array into the hashmap.                
                    exits.put(direction, probabilities);
                }
                // close the file
                fileInput.close();
            }
            finally
            {
                try
                {
                    reader.close();
                }
                catch (Exception e)
                {
                    System.out.println("Error in closing the file! Exiting...");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error in reading from file! Exiting....");
        }
        
        return exits;
    }

    /**
    * Method to write the outcome of the game to a file when the game ends.
    * @param filename: The filename that stores the outcome as a String
    * @param outcome: the outcome of the game. Win if the player escaped Javalice or a description of how the player lost the game. 
    */
    public static void writeFile(String filename, String outcome)
    {
        try
        {
            FileWriter writer = new FileWriter(filename);
            writer.append(outcome);
            writer.close();
        }   
        catch (Exception e)
        {
            System.out.println("Unable to open " + filename + " for writing");
        }
    }

    /**
    * Main method to test the functionality of the HashMap and how data within it is accessed.
    * @param args - String[] console arguments
    */
    public static void main(String[] args)
    {
        // main function to test FileIO functionality
        HashMap<String, int[]> directions = readFile("src/exits.txt");
        
        System.out.println(directions);
        Set<String> keys = directions.keySet();
        for (String key: keys)
        {
            System.out.println("Value of " + key + " is: " + Arrays.toString(directions.get(key)));
        }
        
        System.out.println("Probabilities of North");
        for (int i = 0; i < 3; i++)
        {
            System.out.println(directions.get("North")[i]);
        }
        System.out.println("Probability of North exit existing: " + directions.get("North")[0]);
        System.out.println("Probability of North exit being the exit: " + directions.get("North")[1]);
        System.out.println("Probability of North exit triggering magical police: " + directions.get("North")[2]);

        writeFile("src/Outcome.txt", "escaped javalice");
        
    }

}

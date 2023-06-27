package src;

import java.util.*;

/**
* Class contains a Room.
* A new room object is made every time the player chooses a valid direction.
* 
* @author Alex Fernicola
* @version ver 1.0.13    
*/
public class Room
{   
    
    
    private ArrayList<String> directions; // an arraylist of the valid directional inputs
    private MagicBox magicBox;
    /**
    * Default constructor which creates an object of class Room with no exits.
    */
    public Room()
    {
        this.magicBox = new MagicBox();
        this.directions = null; // need to set directions to empty initially
        
    }

    

    
    
    /**
    * Access method that returns the number of exits in the room. This is used to check if the room has no exits and the player has reached a dead-end.
    * @return An integer that represents the number of exits in the room.
    */
    public int getNumExits()
    {
        return this.directions.size();
    }

    public MagicBox getMagicBox()
    {
        return this.magicBox;
    }
    

    /**
    * This method generates the available exits and the valid directions.
    * @param HashMap<String, int[]>  - the exits hashmap to generate available exits from
    */
    public boolean generateExits(HashMap<String, int[]> exits)
    {
        boolean error = false;
        if (exits.isEmpty())
        {
            error = true;
            return error;
        }
        
        this.directions = new ArrayList();
        // generate a random number between 0 and 100 (inclusive)
        int rNorth = RNG.generateRandom(0,100);
        
        if (rNorth < exits.get("North")[0])
        {
            this.directions.add("North");
        }
    
        int rWest = RNG.generateRandom(0,100);
        if (rWest < exits.get("West")[0])
        {
            this.directions.add("West");
        }
        

        int rEast = RNG.generateRandom(0,100);
        if (rEast < exits.get("East")[0])
        {
            this.directions.add("East");
        }
        

        int rSouth = RNG.generateRandom(0,100);
        if (rSouth < exits.get("South")[0])
        {
            this.directions.add("South");
        }
        return error;
       
    }

    /**
    * This method adds the available exits and associated probabilities to a string buffer and displays them to the player. The method also adds a graphic of the room to the stringbuffer depending on the available exits.
    * @param exits - the HashMap<String, int[]> of the exits to display.
    * @return A stringbuffer containing the available exits and a graphic.
    */
    public String displayExits(HashMap<String, int[]> exits)
    {
        StringBuffer sb = new StringBuffer();
        for (String direction : this.directions)
        {
            sb.append(direction + ": " + "Probability of finding escape portal: " + exits.get(direction)[1] + " Probability of police: " + exits.get(direction)[2] + "\n");
        }
        sb.append("There are " + this.directions.size() + " portals");
        

        // if exits contains north
        // print |-|
        sb.append("\n");
        if (this.directions.contains("North"))
        {
            sb.append("     |     |     ");
            sb.append("\n");
            sb.append("     |     |     ");
            sb.append("\n");
            sb.append("     |     |     ");
            sb.append("\n");
            sb.append("     |     |     ");
            sb.append("\n");
        }
        if (!this.directions.contains("North"))
        {
            sb.append("     |-----|     ");
            sb.append("\n");
            sb.append("     |     |     ");
            sb.append("\n");
            sb.append("     |     |     ");
            sb.append("\n");
            sb.append("     |     |     ");
            sb.append("\n");
        }

        // if exits contains west and not east
        // print west
        if (this.directions.contains("West") && !this.directions.contains("East"))
        {
            
            sb.append("-----       ----|");
            sb.append("\n");
            sb.append("                |");
            sb.append("\n");
            sb.append("                |");
            sb.append("\n");
            sb.append("-----       ----|");
            sb.append("\n");
        }

        // if exits contains east and not west
        // print east
        if (this.directions.contains("East") && !this.directions.contains("West"))
        {
            
            sb.append("|----       -----");
            sb.append("\n");
            sb.append("|                ");
            sb.append("\n");
            sb.append("|                ");
            sb.append("\n");
            sb.append("|----       -----");
            sb.append("\n");
        }

        if (this.directions.contains("East") && this.directions.contains("West"))
        {
            sb.append("-----       -----");
            sb.append("\n");
            sb.append("                 ");
            sb.append("\n");
            sb.append("                 ");
            sb.append("\n");
            sb.append("-----       -----");
            sb.append("\n");
        }
        if (!this.directions.contains("East") && !this.directions.contains("West"))
        {
            sb.append("|----       ----|");
            sb.append("\n");
            sb.append("|               |");
            sb.append("\n");
            sb.append("|               |");
            sb.append("\n");
            sb.append("|----       ----|");
            sb.append("\n");
        }

        if (this.directions.contains("South"))
        {
            sb.append("     |     |     ");
            sb.append("\n");
            sb.append("     |     |     ");
            sb.append("\n");
            sb.append("     |     |     ");
            sb.append("\n");
            sb.append("     |     |     ");
            sb.append("\n");
        }
        else if (!this.directions.contains("South"))
        {
            sb.append("     |     |     ");
            sb.append("\n");
            sb.append("     |     |     ");
            sb.append("\n");
            sb.append("     |     |     ");
            sb.append("\n");
            sb.append("     |-----|     ");
            sb.append("\n");
        }
        // if exits contains east and west
        // print east and west

        // if exits contains south
        // print south
        return sb.toString();
    }

    /**
    * Access method to retrieve the valid directions for the next room.
    * @return ArrayList - containing the valid directional inputs.
    */
    public ArrayList<String> getDirections()
    {
        return this.directions;
    }

    /**
    * @param newDirections - ArrayList<String> of new directions.
    */
    public void setDirections(ArrayList<String> newDirections)
    {
        this.directions = newDirections;
    }
    
    /**
    * This method checks if the input direction is valid.
    * @param input - String, the direction the player chose to move in.
    * @return boolean - true if the input direction is valid. false if the input direction is not valid.
    */
    public boolean checkDirections(String input)
    {
        if (this.getDirections().contains(input))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

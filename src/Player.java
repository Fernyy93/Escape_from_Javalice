package src;

/**
* Class which stores the information about the player and inventory
*
* @author Alex Fernicola
* @version ver1.0.13
*/
public class Player
{
    private String name;
    private double numCoins;
    private int numCloaks;
    private int numJumpBacks;
   
    /**
    * Default constructor which creates the object of the class Player
    *
    */
    public Player()
    {
        this.name = "Unknown";
        this.numCoins = 10.0;
        this.numCloaks = 0;
        this.numJumpBacks = 3;
        
    }

    /**
    * Non-Default constructor which creates the object of the class Player.
    *
    * @param newName           Accepts the players name as a String.
    * 
    */
    public Player(String newName, int newCoins, int newCloaks, int newJumpBacks)
    {
        this.name = newName;
        this.numCoins = newCoins;
        this.numCloaks = newCloaks;
        this.numJumpBacks = newJumpBacks;
        
    }

    /**
    * Accessor method to get the name of the player
    * @return              The player name as a String
    */
    public String getName()
    {
        return this.name;
    }

    /**
    * Mutator method to set the name of the player as an input
    * @param message              Accepts the message displayed to the user as a string
    */
    public void setName(String message)
    {
        Input input = new Input();
        Validation validation = new Validation();
        
        String playerName = "";

        while (!validation.lengthWithinRange(playerName,3,12))
        {
            playerName = input.acceptStringInput(message);
            if (!validation.lengthWithinRange(playerName,3,12))
            {
                System.out.println("Name must be between 3-12 characters");
            }
        }
        
        this.name = playerName;
        
    }

    /**
    * Accessor method to get the number of coins the player has.
    * @return The number of coins the player has as an integer.
    */
    public double getNumCoins()
    {
        return numCoins;
    }

    /**
    * Mutator method to set the number of coins the player has.
    * @param newCoins - double, the new number of coins
    */
    public void setNumCoins(double newCoins)
    {
        this.numCoins = newCoins;
    }

    /**
    * Display method to display the number of coins the player has.
    * @return String - number of coins as a String.
    */
    public String displayCoins()
    {
        String sCoins = String.format("%.1f", this.getNumCoins());
        return "You have " + sCoins + " coins in your wallet";
    }    

    /**
    * Accessor method to retrieve the number of jump backs the player has left.
    * @return The number of jump backs as an Integer.
    */
    public int getNumJumpBacks()
    {
        return this.numJumpBacks;
    }

    /**
    * Mutator method to set the number of jumpbacks
    * @param newJumpBacks - int, new number of jumpbacks
    */
    public void setJumpBacks(int newJumpBacks)
    {
        this.numJumpBacks = newJumpBacks;
    }

    /**
    * Accessor method that returns the number of cloaks the player has.
    * @return The number of cloaks as an integer.
    */
    public int getNumCloaks()
    {
        return this.numCloaks;
    }

    /**
    * Mutator method that sets the number of cloaks the player has
    * @param newCloaks - int, the new number of cloaks the player has
    */
    public void setCloaks(int newCloaks)
    {
        this.numCloaks = newCloaks;
    }

    /**
    * This method calculates the number of coins found in the box as a random integer between 10 and 35. It then updates number of coins the player has.
    * @return The number of coins the player found in the box.
    */
    public int getCoinsBox()
    {
        //Random r = new Random();
        // generate random number between 10 and 35
        // rand.nextInt((max - min) + 1) + min;

        int coins = RNG.generateRandom(10,35);
        this.numCoins+=coins;
        return coins;
    }

    /**
    * Method to bribe the police.
    * @return a boolean true: successful bribe, false: unsuccessful bribe
    */
    public boolean bribe()
    {
        
        if (this.numCoins == 0)
        {
            return false;
        }
        else
        {
            //   double bribe = Math.random()*(1.5*this.numCoins - 0.5*this.numCoins) + 0.5*this.numCoins;
            double bribe = RNG.generateRandom(0.5*this.numCoins, 1.5*this.numCoins);
            System.out.print("The police demand ");
            System.out.printf("%.1f", bribe);
            System.out.println(" coins from you");
            if (this.numCoins >= bribe)
            {
                this.numCoins -= bribe;
                
                System.out.print("You now have ");
                System.out.printf("%.1f", this.getNumCoins());
                System.out.println(" coins remaining");
                
                return true; // bribe successful
            }
            else
            {
                // police don't acces the bribe
                return false;
            }
        }
        
    }

    /**
    * Method to increase the number of cloaks if the magic box contains a cloak. The max number of cloaks a player can 
    have is 3
    */
    public void increaseCloaks()
    {
        // this method is called by the MagicBox class. It increases the number of invisibility cloaks.
        // Then checks ensures the maximum number of cloaks is 3.
        this.numCloaks+=1;
        this.numCloaks = Math.min(numCloaks, 3);
        System.out.println("Player now has: " + this.numCloaks + " cloaks");
    }

    /**
    * Mutator method to decrease the number of jump backs available if the player jumps back.
    */
    public void useJumpBack()
    {
        this.numJumpBacks -= 1;
    }

    /**
    * Mutator method to decrease the number of cloaks available if the player uses a cloak.
    */
    public void useCloak()
    {
        this.numCloaks -= 1;
    }

    /**
    * Method to allow the player to input a standard y/n choice. This is used by a number of other methods.
    * @param message - a message displayed to the console.
    * @return boolean: true for yes, false for no. Continues to ask for input if input is unrecognised.
    */
    public boolean playerChoice(String message)
    {
        boolean choice = false;
        boolean yn = false;
        Input input = new Input();
        String action = "";
        while (!yn)
        {
            action = input.acceptStringInput(message);
                                  
            
            if (action.equals("y") || action.equals("yes") || action.equals("Yes"))
            {
                yn = true;
                choice = true;
                
               
            }
            else if (action.equals("n") || action.equals("no") || action.equals("No"))
            {
                yn = true;
                choice = false;
                
                
            }
            else
            {
                System.out.println("Unrecognised input, enter y/n");
                
            }
        }
        return choice;
    }

    /**
    * Method that allows the player to choose a direction to move in.
    * @param message - a message displayed to the console.
    * @return the direction as a string.
    */
    public String chooseDirection(String message)
    {
        
        Input input = new Input();
        String choice = "";
        
        boolean yn = true;
        while (yn)
        {   
            choice = input.acceptStringInput(message);
            if (choice.equals("North") || choice.equals("N") || choice.equals("n") || choice.equals("north"))
            {
                choice = "North";
                yn = false;
            }
            else if (choice.equals("South") || choice.equals("S") || choice.equals("s") || choice.equals("south"))
            {
                choice = "South";
                yn = false;
            }
            else if (choice.equals("East") || choice.equals("E") || choice.equals("e") || choice.equals("east"))
            {
                choice = "East";
                yn = false;
            }
            else if (choice.equals("West") || choice.equals("west") || choice.equals("w") || choice.equals("West"))
            {
                choice = "West";
                yn = false;
            }
            else
            {
                System.out.println("You don't see that exit here");
            }
           
        }
        System.out.println("direction entered was: " + choice);
        return choice;
    }
    
    
}

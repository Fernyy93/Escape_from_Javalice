package src;

import java.util.*;

/**
* Class the Game class
* Contains the player, the outcome, if player landed in jail in the previous room, the exits HashMap, and input and output file names.
* 
* @author Alex Fernicola
* @version ver 1.0.13

* Video link: https://youtu.be/Hry9E4tSTzA
*/
public class Game
{   
    private Room currentRoom;
    private Room previousRoom;
    private ArrayList<Room> roomList;
    private Player player;
    private boolean gameOver;
    private boolean outcome;
    private boolean jail;    
    private HashMap<String, int[]> exits;
    
    public static final String EXITS_FILE = "src/exits.txt";
    
    public static final String OUTCOME_FILE = "src/Outcome.txt";
    
    
    
    /**
    * Default constructor which creates an object of class Game.
    */
    public Game()
    {
        this.roomList = new ArrayList<Room>();
        this.currentRoom = null;
        this.previousRoom = null;
        this.outcome = false;
        this.jail = false;
        this.gameOver = false;
        this.exits = new HashMap<String, int[]>();
        this.player = new Player();
        
    }

    /**
    * Access method for the current Room
    * @return Room - the current room
    */
    public Room getCurrentRoom()
    {
        return this.currentRoom;
    }

    /**
    * Mutator method for the room object. Used to set current room to previous room.
    * @param newRoom - new Room object
    */
    public void setCurrentRoom(Room newRoom)
    {
        this.currentRoom = newRoom;
    }

    /**
    * Accessor method for the previous {@link Room}
    * @return Room - the previous room.
    */
    public Room getPreviousRoom()
    {
        return this.previousRoom;
    }

    /**
    * Mutator method for the previous room
    * @param newPreviousRoom - the new previous room
    */
    public void setPreviousRoom(Room newPreviousRoom)
    {
        this.previousRoom = newPreviousRoom;
    }

    /**
    * Access method for the Room List
    * @return ArrayList<Room> - the room list.
    */
    public ArrayList<Room> getRoomList()
    {
        return this.roomList;
    }

    /**
    * Accessor method to get the number of rooms in the Room List
    * @return int - The number of rooms in the room list.
    */
    public int getNumRooms()
    {
        return this.getRoomList().size();
    }

    /**
    * Method creates a new room and adds it to the Room List
    */
    public void createRoom()
    {
        this.getRoomList().add(new Room());
    }

    /**
    * Access method for the Player object.
    * @return The player object.
    */
    public Player getPlayer()
    {
        return this.player;
    }

    /**
    * Mutator method for the player object
    * @param newPlayer, the player object.
    */
    public void setPlayer(Player newPlayer)
    {
        this.player = newPlayer;
    }

    /**
    * Accessor method to access the exits HashMap.
    * @return HashMap<String, int[]> - The exits HashMap.
    */
    public HashMap<String, int[]> getExits()
    {
        return this.exits;
    }

    /**
    * Mutator method to set the initial state of the exits hashmap by calling the readFile method
    * @param filename - the exits.txt file.
    */
    public void setExits(String filename)
    {
        this.exits = FileIO.readFile(filename);
    }
    
    /**
    * Accessor method to check if the game is over.
    * @return boolean true: game is over, false: game is not over.
    */
    public boolean getGameOver()
    {
        return this.gameOver;
    }

    /**
    * Mutator method to set the status of gameOver
    * @param newGameOver boolean: true: game is over, false: game is not over.
    */
    public void setGameOver(boolean newGameOver)
    {
        this.gameOver = newGameOver;
    }

    /**
    * Accessor method to get the game outcome
    * @return boolean true: game won, false: game lost.
    */
    public boolean getOutcome()
    {
        return this.outcome;
    }

    /**
    * Mutator method to set the outcome of the game.
    * @param newOutcome. boolean: true: game won. false: game lost.
    */
    public void setOutcome(boolean newOutcome)
    {
        this.outcome = newOutcome;
    }

    /**
    * Accessor method to check if player escaped from jail after going to the previous room.
    * @return boolean jail: true: player escaped jail. false: player was not taken to jail.
    */
    public boolean getJail()
    {
        return jail;
    }

    /**
    * Mutator method to set whether or not the player was taken to jail in the current room.
    * @param newJail - boolean: true: player was taken to jail, false: player was not taken to jail.
    */
    public void setJail(boolean newJail)
    {
        this.jail = newJail;
    }

    

    /**
    * Method to check if the chosen direction leads to the escape portal.
    * @param direction - String, the direction the player chose to move in.
    * @return Boolean: true: player found the escape portal. false: The exit chosen was not the escape portal.
    */
    private boolean checkExitPortal(String direction)
    {
        
        int pExit = exits.get(direction)[1];
        int rExit = RNG.generateRandom(0,100);
        if (rExit < pExit)
        {
            this.setGameOver(true);
            System.out.println("You feel air rushing through the portal as you approach it. The smell of the air is that of a foreign land... you step through the portal and are free.");
            FileIO.writeFile(OUTCOME_FILE, "Escaped Javalice");
            return true;
        }
        else
        {
            return false;
        }
    }

    

    

   

    /**
    * This method checks if the room in the chosen direction triggers the magic police.
    * @param direction - the direction the player chose to move in as a String.
    * @return boolean - true: police appear, false: police don't appear.
    */
    private boolean checkPolice(String direction)
    {
        // get the probability of police for the chosen direction
        int pPolice = this.exits.get(direction)[2];
        // generate random number between 0 and 100
        int rPolice = RNG.generateRandom(0,100);
        if (rPolice < pPolice)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
    * This method updates the probabilities of exit and magic police for the chosen direction if the chosen direction did not lead to the escape portal.
    * @param direction - A String of the direction the player chose to move in.
    * @param exits - The exits HashMap<>, passing the exits HashMap into this method ensures the exits hashmap in the game object is updated.
    */
    private void updateProbabilities(String direction, HashMap<String, int[]> exits)
    {
        // check if the player has not just started the game or if they haven't found the exit portal.
        if (!direction.isEmpty())
        {
            // flip a coin to determine if the probability is increased or decreased for each the exit or police
            boolean increaseExit = RNG.coinFlip();
            boolean increasePolice = RNG.coinFlip();

            // determine how much to increase or decrease each probability
            int deltaExit = RNG.generateRandom(1,5);
            int deltaPolice = RNG.generateRandom(1,5);

            // any changes made to temp will change the int[] array within exits in memory.
            int[] temp = exits.get(direction);
            if (increaseExit)
            {
                
                //System.out.println("Increasing exit probability by: " + deltaExit);
                temp[1] = Math.min((temp[1] + deltaExit),100); 
                
                
            }
            else if (!increaseExit)
            {
                //System.out.println("Decreasing exit probability by: " + deltaExit);
                temp[1] = Math.max(0,temp[1] - deltaExit); 
            }

            if (increasePolice)
            {
                //System.out.println("Increasing police probability by: " + deltaPolice);
                temp[2] = Math.min((temp[2] + deltaPolice),100);
            }
            else if (!increasePolice)
            {
                //System.out.println("Decreasing police probability by: " + deltaPolice);
                temp[2] = Math.max(0,temp[2] - deltaPolice);
            }
            //System.out.println(Arrays.toString(temp));
            
            //System.out.println(Arrays.toString(exits.get(direction)));
        }
    }

    /**
    * This method checks if the player hit a dead end. It returns true if the player used a jump back (or room was not a dead-end). 
    * Returns false if the room was a dead-end and the player ran out.
    * @params Room - the current room.
    * @return boolean - true: the player jumped back 
    */
    private boolean checkDeadEnd(Player player)
    {
        boolean trapped = false;
        if (player.getNumJumpBacks() > 0)
        {
            String message = ("You find yourself trapped and with no escape, will you jump back through an emergency portal? (y/n)");                
            boolean jump = player.playerChoice(message);
            
            if (jump)
            {
                System.out.println("You open an emergency portal and escape to another room");
                player.useJumpBack();
                
            }
            else if (!jump)
            {
                System.out.println("You feel that escape from this doomed land is hopeless....");
                
                FileIO.writeFile(OUTCOME_FILE, "gave up");
                trapped = true;
                
            }
            
        }
        else
        {
            System.out.println("You don't have enough magical power to move any further. You collapse with exhaustion and await your fate.");
            FileIO.writeFile(OUTCOME_FILE, "Reached a dead end or landed in jail without any jump backs remaining.");
            
            trapped = true;
        }
        return trapped;
    }

    /**
    * This method allows the player to use a cloak or bribe the police.
    */
    private void policeEncounter(Player player)
    {
        String message = "";
        boolean cloak = false;

        System.out.println("As you pass through the portal you hear a shout 'Its the Wizard! Seize him!'");
        if (player.getNumCloaks() > 0)
        {
            message = "Will you use an invisibility cloak? (enter y/n)";
            cloak  = player.playerChoice(message);
            if (cloak)
            {
                System.out.println("You use an invisibility cloak and escape the police undetected");
                player.useCloak();
            }
            else if (!cloak)
            {
                System.out.println("You decide to try your luck with a bribe");
            }
        }
        if (!cloak)
        {
            System.out.println("The magic police rush to your location and surround you, with magic supressing hand-cuffs at the ready, there is no escape but perhaps they will accept a bribe?");
            System.out.println(player.displayCoins());
            message = ("Will you attempt to bribe the police? (enter y/n)");
            // check if bribe is successful
            boolean bribeChoice = player.playerChoice(message);
            boolean bribeSuccess = false;
            if (bribeChoice)
            {
                bribeSuccess = player.bribe();
                if (bribeSuccess)
                {
                    System.out.println("The magic police accept your bribe and pretend they didn't see you, you better go through another portal before they change their mind....");
                }
                else
                {
                    System.out.println("The magic police look at your coins then grunt in disgust, they arrest you.");
                }

            }
            // if bribe unsuccessful or player chose not to bribe, allow player to jump back
            if (!bribeChoice || !bribeSuccess)
            {
                // check if player has jumpbacks remaining
                System.out.println();
                if (player.getNumJumpBacks() > 0)
                {
                    System.out.println("As the police throw you in jail you feel you have just enough magic left to open an emergency portal...");
                    message = ("Will you open an emergency portal? (y/n)");
                    boolean escape = player.playerChoice(message);
                    if (escape)
                    {
                        System.out.println("You open an emergency portal and escape the jail");
                        player.useJumpBack();
                        this.setPreviousRoom(this.getCurrentRoom());
                        this.setJail(true); // 
                    }
                    else if (!escape)
                    {
                        System.out.println("You feel that escape from this doomed land is hopeless....");
                        FileIO.writeFile(OUTCOME_FILE, "Thrown in jail");
                        this.setGameOver(true);
                        
                    }                
                }
                else if (player.getNumJumpBacks() <= 0)
                {
                    System.out.println("The jailers lock the cell as you realise escape from this doomed land is hopeless...");
                    this.setGameOver(true);
                    FileIO.writeFile(OUTCOME_FILE, "Thrown in jail");
                }
            }
        }
    }
        
    

    

    /**
    * This method sets up the game and contains the main game loop.
    */
    private void startGame()
    {


        String message = "Welcome to Escape from Javalice, please enter your name (must be between 3-12 characters)";


        this.setExits(EXITS_FILE);
        
        // player could also be part of the Game class
        Player player1 = new Player();        
        player1.setName(message);
        this.setPlayer(player1);


        String direction = "";
        while(!this.gameOver)
        {   
            // create the first room, set the current room to the last room in the room list.
            this.createRoom();
            this.setCurrentRoom(this.getRoomList().get(this.getNumRooms() - 1));
            // check if the number of rooms is more than 2, then set previous room to the second last room
            if (this.getNumRooms() >= 2)
            {
                this.setPreviousRoom(this.getRoomList().get(this.getNumRooms() - 2));
            }
            // check if the player escaped jail the previous turn
            if (this.getJail() && (this.getPreviousRoom() != null))
            {
                // if escaped jail, go back to the previous room with the same exits available
                // the current room is now the previous room.
                this.setCurrentRoom(this.getPreviousRoom());
            }
            else
            {   
                // else, the newest room is the current room, generate the exits available
                try
                {
                    this.getCurrentRoom().generateExits(this.getExits());
                }
                catch (NullPointerException npe)
                {
                    System.out.println("Error in reading file into exits HashMap, aborting game");
                    this.gameOver = true;
                    continue;
                } 
            }
            this.setJail(false); // reset 
        
            // update probabilities only if the player entered a direction
            
            this.updateProbabilities(direction, this.getExits());
            System.out.println("You see these portals:");
            System.out.println(this.getCurrentRoom().displayExits(this.getExits()));
            

            // if number of exits is 0, the player has to use a jump back
            // if jump back, generate new exits
            // could add this to a checkDeadEnd method
            if (this.getCurrentRoom().getNumExits() == 0)
            {
                System.out.println("You teleported to a room with no exits, you've hit a dead-end");
                
                if (this.checkDeadEnd(this.getPlayer()))
                {
                    this.setGameOver(true);
                    continue;
                }
                else
                {
                    this.createRoom();
                    this.setCurrentRoom(this.getRoomList().get(this.getNumRooms() - 1));
                    this.getCurrentRoom().generateExits(this.getExits());
                    System.out.println(this.getCurrentRoom().displayExits(this.getExits()));
                }
            }

            message = "Enter a direction to move in";
            String choice = "";
            
            boolean exists = false;
            // this could be in a chooseDirection method
            while (!exists)
            {
                //choice = input.acceptStringInput(message);
                direction = this.getPlayer().chooseDirection(message);
                System.out.println("Checking if " + direction + " exists");
                exists = this.getCurrentRoom().checkDirections(direction);                
                if (!exists)
                {
                    System.out.println("You don't see that exit here");

                    System.out.println(this.getCurrentRoom().displayExits(this.getExits()));
                }
                

            }

            // first check if the direction is the exit
            // this could be an output of the chooseDirection Method            
            if (this.checkExitPortal(direction))
            {
                continue;
            }
            
            System.out.println("Previous direction entered " + direction);
            
            // this could be the output of the chooseDirection method
            // first check if the box exits
            if (this.getCurrentRoom().getMagicBox().checkBox())
            {
                System.out.println("You found a magic box!");
                message = "Will you open it? (enter y/n)";
                boolean open = this.getPlayer().playerChoice(message);
                
                if (open)
                {
                    this.getCurrentRoom().getMagicBox().openBox(this.getPlayer(),this.getExits());                    
                }
                else if (!open)
                {
                    System.out.println("You choose to ignore the box");
                }
            }

            // then check if the police arrive            
            if (this.checkPolice(direction))
            {
                this.policeEncounter(this.getPlayer());
            }
        }
    }

    /**
    * main method - executes the startGame method.
    * @param args - Optional commandline arguments (unused).
    */
    public static void main(String[] args)
    {
        (new Game()).startGame();
    }

}

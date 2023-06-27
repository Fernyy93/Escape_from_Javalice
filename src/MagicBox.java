package src;

import java.util.*;

/**
* Class that contains the Magic Box (What's in the box today?)
* 
* @author Alex Fernicola
* @version ver 1.0.13    
*/
public class MagicBox
{
    /**
    * The default constructor - is empty.
    */
    public MagicBox()
    {

    }


    /**
    * This method checks if the magic box exists in the room, the magic box has a 1/2 chance to appear.
    * @return a boolean true: box exists. false: box does not exist. 
    */
    public boolean checkBox()
    {   
        Random coin = new Random();
        return coin.nextBoolean();
    }

    /**
    * This method is called if the player chooses to open the box. The contents of the box are based on probability.
    * @param player1, the object of the player class. It is used to increase the number of coins or invisibility cloaks depending on the contents of the box.
    * @param exits, the exits HashMap, it is passed to increase the probability of the magic police occuring if the alarm is raised.
    */
    public void openBox(Player player1, HashMap<String, int[]> exits)
    {
        
        // this method is called if the player chooses to open the box
        
        if (player1 != null)
        {
            int res = RNG.generateRandom(0,100);
            if (res < 30)
            {
                System.out.println("You found coins");
                int nCoins = player1.getCoinsBox();
                System.out.println("You found " + nCoins + " coins in the box");
                System.out.println(player1.displayCoins());
            }
            else if (res < 55)
            {
                if (!exits.isEmpty())
                {
                    System.out.println("Alarm raised!");
                    // get the keys (directions) from exits
                    Set<String> keys = exits.keySet();
                    // for each direction increase the police probability
                    for (String key: keys)
                    {
                        // again, use pass by reference to increase the probability of police
                        int[] temp = exits.get(key);
                        // increase the probability of finding police for each exit by 3%, maximum of 100%
                        temp[2] = Math.min((temp[2] + 3),100);
                    }

                } 
                else
                {
                    System.out.println("Error reading exits HashMap");
                }

            }
            else if (res < 70)
            {
                System.out.println("You found an invisibility cloak");
                player1.increaseCloaks();
            }
            else
            {
                System.out.println("You found coal, perhaps you can use it to keep warm");
            }
        }
        else
        {
            // Schrodinger's magic box, the box neither exists or doesn't exist until the player enters the room.
            System.out.println("A magic box can't exist without a room or a player to open it");
        }
    }
}

package src;

import java.util.Random;

/**
* Class which is used to generate random numbers with high reusability of the class.
* 
* @author Alex Fernicola
* @version ver 1.0.13    
*/
public class RNG
{
    /**
    * Default constructor - is empty.
    */
    public RNG()
    {

    }
    

    /**
    * Static method to generate a random number within a range between min and max.
    * Method is static as it is not requried to generate an object of the RNG class.
    * @param min - An integer number that is the minimum of the range of random numbers.
    * @param max - An integer number that is the maxmimum of the range of random numbers.
    * @return A random integer between min and max.
    */
    public static int generateRandom(int min, int max)
    {
        
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }

    /**
    * Overloaded generateRandom method that accepts doubles instead of integers
    * @param min - A double number that is the minimum of the range of random numbers.
    * @param max - A double number that is the maximum of the range of random numbers.
    * @return double - Random double between min and max.
    */    
    public static double generateRandom(double min, double max)
    {
        Random r = new Random();
        return r.nextDouble(max - min + 1) + min;
    }

    
    /**
    * Static method to simulate the flipping of a coin.
    * @return boolean
    */
    public static boolean coinFlip()
    {
        Random r = new Random();
        return r.nextBoolean();
    }
}

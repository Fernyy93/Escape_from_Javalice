package src;

import java.util.Scanner;

/**
* Class to accept keyboard input from the player.
*
* @author Alex Fernicola
* @version ver 1.0.13    
*/
public class Input
{
    private final Scanner console; 
    /**
    * Default constructor - empty
    */
    public Input()
    {
        this.console = new Scanner(System.in);
    }
    
    /**
    * Method to accept a specific number of characters and output a string.
    * @param message: String message output to the screen. int length, number of characters required.
    * @param length - int, number of character to enter
    * @return A string of the character sequence entered.
    */
    public String acceptCharInput(String message, int length)
    {
        
        
        
        System.out.println(message);        
        StringBuilder stb = new StringBuilder(length);
    
        
        
        for (int i = 0; i < length; i++)
        {
            char c = this.console.next().charAt(0);            
            stb.insert(c,i);
           
            
        }
        
       
        String ret = stb.toString();        
        return ret;     
         

    }

    /**
    * Method to accept input of a double value.
    * @param message: String message output to the screen.
    * @return double the double value entered by the user.
    */
    public double acceptDoubleInput(String message)
    {
        
        System.out.println(message);
        double num = Double.parseDouble(this.console.nextLine());
        
        return num;
       
        
    }

    /**
    * Method to accept input of an integer value.
    * @param message: String message output to the screen.
    * @return int The integer value entered by the user.
    */
    public int acceptIntegerInput(String message)
    {
        //Scanner input = new Scanner(System.in);
        System.out.println(message);
        // use parseInt so the scanner goes to the next line;
        int num = Integer.parseInt(this.console.nextLine());
        
        return num;
    }
    
    /**
    * This method prompts the player to enter a string.
    * @param message - String: a message that is displayed to the command line that requests the player to input a String.
    * @return String - The player input as a string.
    */
    public String acceptStringInput(String message)
    {
        //Scanner input = new Scanner(System.in);
        System.out.println(message);
        String ret = this.console.nextLine();
        
        
        return ret;
    }

    /**
    * This method clears the scanner buffer.
    */
    public void clear()
    {
        //Scanner input = new Scanner(System.in);
        this.console.nextLine();
    }

    
}



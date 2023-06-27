package src;

/**
* Class to validate the inputs made by the player.
*
* @author Alex Fernicola
* @version ver 1.0.13    
*/
public class Validation
{
    
    /**
    * Default constructor, is empty
    */
    public Validation()
    {

    }

    /**
    * This method checks if the input the player entered was blank or a newline character.
    * @param input - the String entered by the player.
    * @return boolean - true: valid input, false: invalid input.
    */
    public boolean isBlank(String input)
    {
        if (input.isEmpty() || input.equals("\n"))
        {
            return true;
        }
        return false;
    }

    /**
    * This method checks if the input the player was of the required length
    * @param input - the String entered by the player.
    * @param start - Integer, the minimum character length
    * @param end - the maximum character length
    * @return boolean - true: valid input, false: invalid input.
    */
    public boolean lengthWithinRange(String input, int start, int end)
    {
        if ((start <= input.length()) && (input.length() <= end))
        {
            return true;
        }
        return false;
    }
}

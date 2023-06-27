package src;

public class Display
{
    /**
     * Display class to build up the display the player sees
     * Author: Alex Fernicola, Version 1.1.0
     */
    private StringBuffer display;


    public Display()
    {
        this.display = new StringBuffer();
    }

    public void setDisplay(StringBuffer display) {
        this.display = display;
    }

    public StringBuffer getDisplay() {
        return display;
    }

    public void addToDisplay(String str)
    {
        this.getDisplay().append(str);
    }

    public void clearDisplay()
    {
        this.getDisplay().setLength(0);
    }

    public void newLine()
    {
        this.getDisplay().append("\n");
    }

    public void display()
    {
        System.out.println(this.getDisplay());
    }

    public static void main(String[] args)
    {
        Display display1 = new Display();
        int numCoins = 10;
        int numCloaks = 3;
        int numJumpBacks = 3;
        String exits = "North, South, East, West";

        display1.addToDisplay("Coins: " + numCoins + " Cloaks: " + numCloaks + " Jumps: " + numJumpBacks);
        display1.newLine();
        display1.addToDisplay(exits);
        display1.display();
    }
}

import java.util.Scanner;
public class Player
{
    private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    private Ship[] ships;
    private Grid shipGrid;
    private Grid guessGrid;
    
    //constructor: intializes lists of ships and player grids
    public Player()
    {
        ships = new Ship[5];
        shipGrid = new Grid();
        guessGrid = new Grid();
        for(int i = 0; i < 5; i++)
        {
            ships[i] = new Ship(SHIP_LENGTHS[i]);
        }
    }
    //returns the number of hits on the grid
    public int returnHits()
    {
        int hits = 0;
        for(int i = 1; i < 11; i++)//rows
        {
            for(int j = 1; j < 11; j++)//columns
            {
                if(guessGrid.getStatus(i, j) == 1)
                {
                    hits++;
                }
            }
        }
        return hits;
    }
    //getter method that returns the ship grid
    public Grid getShips()
    {
        return shipGrid;
    }
    //getter method that returns the guess grid
    public Grid getGuesses()
    {
        return guessGrid;
    }
    //return true if all spaces guessed, false if not all guessed
    public boolean winCond()
    {
        int hits = 0;
        for(int i = 1; i < 11; i++)//rows
        {
            for(int j = 1; j < 11; j++)//columns
            {
                if(guessGrid.getStatus(i, j) == 1)
                {
                    hits++;
                }
            }
        }
        if(hits == 17)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * This method takes an input grid, checks if the guess is a hit on this input grid, then marks it as a miss or hit on the guess grid of the current player
     * 
     * @param g input grid
     */
    public void playerGuess(Grid g)//input opponent grid: in this case, the computer ship grid
    {
        boolean hit = false;
        Scanner scanner = new Scanner(System.in);
        boolean condition = true;
        int r = 1;
        int c = 1;
        while(condition)
        {
            System.out.println("");
            System.out.println("Please input your row guess from 1 to 10, inclusive");
            r = scanner.nextInt();
            System.out.println("Please input your column guess from 1 to 10, inclusive");
            c = scanner.nextInt();
            if(r >= 1 && r <= 10 && c >= 1 && c <= 10)
            {
                condition = false;
            }
            else
            {
                System.out.println("That guess is not valid, try again! :(");
            }
        }
        if(g.hasShip(r, c))
        {
            guessGrid.markHit(r, c);
            hit = true;
        }
        else
        {
            guessGrid.markMiss(r, c);
            hit = false;
        }
    }
    /**
     * This method takes an input grid, creates a randomized guess, and marks the location on the guess grid as a hit or miss based upon whether there's a ship there on the input grid
     * 
     * @param g input grid
     */
    public void computerGuess(Grid g)//input opponent grid: in this case, the player ship grid
    {
        boolean condition = true;
        boolean hit = false;
        while(condition)
        {
            int r = Randomizer.nextInt(1, 10);
            int c = Randomizer.nextInt(1, 10);
            if(!guessGrid.alreadyGuessed(r, c))
            {
                condition = false;
                if(g.hasShip(r, c))
                {
                    guessGrid.markHit(r, c);
                    hit = true;
                }
                else
                {
                    guessGrid.markMiss(r, c);
                }
            }
        }
    }
    /**
     * This method sets up all of the computer ships on the grid
     */
    public void computerSetup()
    {
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < 5; i++)
        {
            int rowCoord = Randomizer.nextInt(1, 10);
            int colCoord = Randomizer.nextInt(1, 10);
            int direc = Randomizer.nextInt(0, 1);
            ships[i].setLocation(rowCoord, colCoord);
            ships[i].setDirection(direc);
            if(shipGrid.validShip(ships[i]))
            {
                shipGrid.addShip(ships[i]);
            }
            else
            {
                i--;
            }
        }
    }
    
    //asks user for ship locations and puts them on ship grid
    public void playerSetup()
    {
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < 5; i++)
        {
            shipGrid.printShips();
            System.out.println("");
            System.out.println("Enter a row number for the " + SHIP_LENGTHS[i] + " long ship: ");
            int rowCoord = scanner.nextInt();
            System.out.println("Enter a column number for the " + SHIP_LENGTHS[i] + " long ship: ");
            int colCoord = scanner.nextInt();
            System.out.println("Enter a 1 if you want the ship vertical, a 0 if you want it horizontal: ");
            int direc = scanner.nextInt();
            ships[i].setLocation(rowCoord, colCoord);
            ships[i].setDirection(direc);
            if(shipGrid.validShip(ships[i]) && rowCoord <= 10 && rowCoord >= 1 && colCoord <= 10 && colCoord >= 1 && (direc == 1 || direc == 0))
            {
                shipGrid.addShip(ships[i]);
            }
            else
            {
                System.out.println("That is not a valid location, try again! :)");
                i--;
            }
        }
        shipGrid.printShips();
    }
}

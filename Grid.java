public class Grid
{
    private Location[][] grid;
    
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;
    
    public Grid()
    {
        grid = new Location[NUM_ROWS][NUM_COLS];
        for(int i = 0; i < NUM_ROWS; i++)
        {
            for(int j = 0; j < NUM_COLS; j++)
            {
                grid[i][j] = new Location(i, j);
            }
        }
    }
    //marks location with hit
    public void markHit(int row, int col)
    {
        grid[row - 1][col - 1].markHit();
    }
    //marks location with miss
    public void markMiss(int row, int col)
    {
        grid[row - 1][col - 1].markMiss();
    }
    //sets location with chosen status
    public void setStatus(int row, int col, int status)
    {
        grid[row - 1][col - 1].setStatus(status);
    }
    //gets status of certain location
    public int getStatus(int row, int col)
    {
        return grid[row - 1][col - 1].getStatus();
    }
    //checks if location is already guessed
    public boolean alreadyGuessed(int row, int col)
    {
        return !grid[row - 1][col - 1].isUnguessed();
    }
    //sets location to have ship
    public void setShip(int row, int col, boolean val)
    {
        grid[row - 1][col - 1].setShip(val);
    }
    //checks if location has ship
    public boolean hasShip(int row, int col)
    {
        return grid[row - 1][col - 1].hasShip();
    }
    //pulls the location object at the location
    public Location get(int row, int col)
    {
        return grid[row - 1][col - 1];
    }
    //gets number of rows
    public int numRows()
    {
        return grid.length;
    }
    //gets number of columns
    public int numCols()
    {
        return grid[0].length;
    }
    //prints status of each space
    public void printStatus()
    {
        System.out.println(" ");
        System.out.print(" ");
        for(int i = 0; i < NUM_COLS; i++)
        {
            System.out.print(" ");
            System.out.print(i+1);
        }
        for(int i = 0; i < NUM_ROWS; i++)
        {
            System.out.println("");
            System.out.print((char)(i + 65));
            for(int j = 0; j < NUM_COLS; j++)
            {
                System.out.print(" ");
                if(grid[i][j].getStatus() == 1)
                {
                    System.out.print("X");
                }
                else if (grid[i][j].getStatus() == 2)
                {
                    System.out.print("O");
                }
                else
                {
                    System.out.print("-");
                }
            }
        }
    }
    //prints where ships are
    public void printShips()
    {
        System.out.println(" ");
        System.out.print(" ");
        for(int i = 0; i < NUM_COLS; i++)
        {
            System.out.print(" ");
            System.out.print(i+1);
        }
        for(int i = 0; i < NUM_ROWS; i++)
        {
            System.out.println("");
            System.out.print((char)(i + 65));
            for(int j = 0; j < NUM_COLS; j++)
            {
                System.out.print(" ");
                if(grid[i][j].hasShip())
                {
                    System.out.print("X");
                }
                else
                {
                    System.out.print("-");
                }
            }
        }
    }
    //checks if a ship can be placed at its location
    public boolean validShip(Ship s)
    {
        boolean ret = true;
        if(s.getDirection() == 1)
        {
            if(s.getRow() > (NUM_ROWS / 2))
            {
                for(int i = 0; i < s.getLength(); i++)
                {
                    if(grid[s.getRow() - 1 - i][s.getCol() - 1].hasShip())
                    {
                        ret = false;
                    }
                }
            }
            else
            {
                for(int i = 0; i < s.getLength(); i++)
                {
                    if(grid[s.getRow() - 1 + i][s.getCol() - 1].hasShip())
                    {
                        ret = false;
                    }
                }
            }
        }
        else if(s.getDirection() == 0)
        {
            if(s.getCol() > (NUM_COLS / 2))
            {
                for(int i = 0; i < s.getLength(); i++)
                {
                    if(grid[s.getRow() - 1][s.getCol() - 1 - i].hasShip())
                    {
                        ret = false;
                    }
                }
            }
            else
            {
                for(int i = 0; i < s.getLength(); i++)
                {
                    if(grid[s.getRow() - 1][s.getCol() - 1 + i].hasShip())
                    {
                        ret = false;
                    }
                }
            }
        }
        return ret;
    }
    
    //adds a ship at the location on the grid
    public void addShip(Ship s)
    {
        if(s.getDirection() == 1)//if ship is vertical
        {
            int tempLength = s.getLength();
            int tempPos = s.getRow() - 1;
            if(s.getRow() > (NUM_ROWS / 2))//in bottom half of grid, pointed up
            {
                boolean condition = true;
                while(condition)
                {
                    grid[tempPos][s.getCol() - 1].setShip(true);
                    tempPos--;
                    tempLength--;
                    if(tempPos == -1 || tempLength == 0)
                    {
                        condition = false;
                    }
                }
                if(tempLength > 0)
                {
                    boolean cond = true;
                    tempPos = s.getRow();
                    while(cond)
                    {
                        grid[tempPos][s.getCol() - 1].setShip(true);
                        tempPos++;
                        tempLength--;
                        if(tempPos == NUM_ROWS || tempLength == 0)
                        {
                            cond = false;
                        }
                    }
                }
            }
            else //in top half of the grid, pointed down
            {
                boolean condition = true;
                while(condition)
                {
                    grid[tempPos][s.getCol() - 1].setShip(true);
                    tempPos++;
                    tempLength--;
                    if(tempPos == NUM_ROWS || tempLength == 0)
                    {
                        condition = false;
                    }
                }
                if(tempLength > 0)
                {
                    boolean cond = true;
                    tempPos = s.getRow() - 2;
                    while(cond)
                    {
                        grid[tempPos][s.getCol() - 1].setShip(true);
                        tempPos--;
                        tempLength--;
                        if(tempPos == -1 || tempLength == 0)
                        {
                            cond = false;
                        }
                    }
                }
            }
        }
        else if(s.getDirection() == 0)
        {
            int tempLength = s.getLength();
            int tempPos = s.getCol() - 1;
            if(s.getCol() > (NUM_COLS / 2)) //in right half of grid, pointed left
            {
                boolean condition = true;
                while(condition)
                {
                    grid[s.getRow() - 1][tempPos].setShip(true);
                    tempPos--;
                    tempLength--;
                    if(tempPos == -1 || tempLength == 0)
                    {
                        condition = false;
                    }
                }
                if(tempLength > 0)
                {
                    boolean cond = true;
                    tempPos = s.getCol();
                    while(cond)
                    {
                        grid[s.getRow() - 1][tempPos].setShip(true);
                        tempPos++;
                        tempLength--;
                        if(tempPos == NUM_COLS || tempLength == 0)
                        {
                            cond = false;
                        }
                    }
                }
            }
            else // in left half of grid, pointed right
            {
                boolean condition = true;
                while(condition)
                {
                    grid[s.getRow() - 1][tempPos].setShip(true);
                    tempPos++;
                    tempLength--;
                    if(tempPos == NUM_COLS || tempLength == 0)
                    {
                        condition = false;
                    }
                }
                if(tempLength > 0)
                {
                    boolean cond = true;
                    tempPos = s.getCol() - 2;
                    while(cond)
                    {
                        grid[s.getRow() - 1][tempPos].setShip(true);
                        tempPos--;
                        tempLength--;
                        if(tempPos == -1 || tempLength == 0)
                        {
                            cond = false;
                        }
                    }
                }
            }
        }
    }
    
}

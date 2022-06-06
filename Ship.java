public class Ship
{
    private int row = -5;
    private int col = -5;
    private int length = -5;
    private int direction = UNSET; //0 is horizontal, 1 is vertical
    
    public static final int UNSET = -1;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    
    
    //Constructor: creating ship and setting length
    public Ship(int length)
    {
        this.length = length;
    }
    //checking if location is set
    public boolean isLocationSet()
    {
        return(row > 0 || col > 0);
    }
    //checking if direction is set
    public boolean isDirectionSet()
    {
        return(direction >= 0);
    }
    //setting location: setting the row and column
    public void setLocation(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
    //setting the direction as an integer
    public void setDirection(int direction)
    {
        this.direction = direction;
    }
    //row getter method
    public int getRow()
    {
        return row;
    }
    //column getter method
    public int getCol()
    {
        return col;
    }
    //length getter method
    public int getLength()
    {
        return length;
    }
    //direction getter method: returns integer value
    public int getDirection()
    {
        return direction;
    }
    //turns direction integer to string
    private String directionToString()
    {
        if(direction == HORIZONTAL)
        {
            return "HORIZONTAL";
        }
        else if(direction == VERTICAL)
        {
            return "VERTICAL";
        }
        else
        {
            return "UNSET";
        }
    }
    //to string method: includes direction, length, row, and column
    public String toString()
    {
        return "Ship is at row: " + getRow() + " and column: " + getCol() + ". Ship is " + getLength() + " spaces long and is " + directionToString();
    }
}

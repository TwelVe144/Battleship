public class Battleship extends ConsoleProgram
{
    public void run()
    {
        Player hooman = new Player();
        Player compooter = new Player();
        boolean turn = true;//true means hooman turn, false means compooter turn
        
        compooter.computerSetup();
        hooman.playerSetup();
        
        boolean condition = true;
        
        while(condition)
        {
            if(turn)
            {
                System.out.println("");
                System.out.println("Player guess board:");
                hooman.getGuesses().printStatus();
                System.out.println("");
                hooman.playerGuess(compooter.getShips());
                System.out.println("Player guess board with guess:");
                hooman.getGuesses().printStatus();
                System.out.println("");
                System.out.println("You have hit " + hooman.returnHits() + " ship parts! You have " + (17-hooman.returnHits()) + " ship parts left to hit!");
                turn = false;
            }
            else
            {
                System.out.println("");
                System.out.println("Player ships board:");
                hooman.getShips().printShips();
                compooter.computerGuess(hooman.getShips());
                System.out.println("");
                System.out.println("Computer guessed");
                System.out.println("Computer guess board:");
                compooter.getGuesses().printStatus();
                turn = true;
            }
            if(hooman.winCond() || compooter.winCond())
            {
                condition = false;
            }
        }
        if(hooman.winCond())
        {
            System.out.println("");
            System.out.println("You win! Congragulations! You beat the computer! The computer only hit " + compooter.returnHits() + " ship parts! What a score!");
        }
        else
        {
            System.out.println("");
            System.out.println("Computer wins! Try again next time :(");
        }
    }
}

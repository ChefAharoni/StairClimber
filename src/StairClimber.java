/*
You have been asked to find the number of different ways to climb an n-stair staircase. You are
allowed to move up 1, 2, or 3 stairs at a time. You must also output how to climb all n stairs.
The number of stairs will be between 1 and 39, inclusive, supplied as a command line argument.
A staircase with 39 stairs can be ascended in a tremendous number of ways. If the staircase
were to have 40 stairs, the total count would overflow an integer!
The output for a staircase with 3 stairs is seen below. Note that the ways must be sorted by the
order of the steps, starting with small steps. The first output contains only 1s in the solution.
The last output contains just a single 3.

$ java StairClimber 3
4 ways to climb 3 stairs.
1. [1, 1, 1]
2. [1, 2]
3. [2, 1]
4. [3]
 */

public class StairClimber
{
    private final int STAIRS;
    private int nWays;

    public StairClimber(int stairs)
    {
        this.STAIRS = stairs;
    }

    public void climb()
    {
        // input is valid from main
        System.out.println("I got: " + this.STAIRS + " stairs");
    }

    public void printSolutions()
    {
        System.out.println(nWays + (nWays > 1 ? " ways" : " way") + " to climb " +
                STAIRS + (STAIRS > 1 ? " stairs." : "stair."));

        // loop over the num of solutions and print each in brackets
    }


    public static void main(String[] args)
    {
        // check for valid amt of input
        if (args.length != 1)
        {
            System.err.println("Usage: java StairClimber <number of stairs>");
            System.exit(1);
        }

        int stairs = 0;
        try
        {
            stairs = Integer.parseInt(args[0]);
            if (stairs < 1)
            {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException iae)
        {
            System.err.println("Error: " +
                    "Number of stairs must be a positive integer.");
            System.exit(1);
        }

        StairClimber climber = new StairClimber(stairs);
        climber.climb();
        climber.printSolutions();

        System.exit(0);
    }
}
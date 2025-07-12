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
    private int solCounter;

    public StairClimber(int stairs)
    {
        this.STAIRS = stairs;
//        this.nWays = 0;
    }

    private int countWays(int stairs)
    {

        if (stairs == 0)
        {
            return 1;
        }

        if (stairs < 0) {
            return 0;
        }

        return countWays(stairs - 1 ) +
                countWays(stairs - 2 ) +
                countWays(stairs - 3);
    }

    public void climb()
    {
        int totalWays = countWays(STAIRS);
        printSolutions(totalWays);
    }

    public void printSolutions(int totalWays)
    {
        System.out.println(totalWays
                + (totalWays > 1 ? " ways" : " way")
                + " to climb "
                + STAIRS
                + (STAIRS > 1 ? " stairs." : " stair.")
        );

        // determine how much space we need for right-aligning the index
        // calculated from the total amount of ways
        int maxIndexWidth = String.valueOf(totalWays).length();
        int[] buffer = new int[STAIRS]; // Max possible path length
        climb(STAIRS, buffer, 0, maxIndexWidth);
    }

    private void printPath(int[] path, int len) {
        System.out.print("[");
        for (int i = 0; i < len; i++)
        {
            System.out.print(path[i]);
            if (i < len - 1)
            {
                System.out.print(", ");
            }
        }
        System.out.print("]\n");
    }


    private void climb(int stairs, int[] buf, int len, int width)
    {
        // Worked with my father on this solution

        // This optimization uses buffers instead of ArrayLists
        // This saves all the ArrayList operations that are very costly
        // These ArrayList operations were pointed out by JProfile

        if (stairs == 0)
        {
//            solCounter++;
            System.out.printf("%" + width + "d. ", ++solCounter);
            printPath(buf, len);
            return;
        }

        if (stairs >= 1)
        {
            buf[len] = 1;
            climb(stairs - 1, buf, len+1, width);
        }

        if (stairs >= 2)
        {
            buf[len] = 2;
            climb(stairs -2, buf, len+1, width);
        }

        if (stairs >= 3)
        {
            buf[len] = 3;
            climb(stairs - 3, buf, len+1, width);
        }
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

        System.exit(0);
    }
}
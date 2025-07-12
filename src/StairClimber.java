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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StairClimber
{
    private final int STAIRS;
    private int nWays;
    private final List<int[]> opts; // options

    public StairClimber(int stairs)
    {
        this.STAIRS = stairs;
        this.opts = new ArrayList<>();
    }

    public void climb()
    {
        int[] buffer = new int[STAIRS];
        climb(STAIRS, buffer, 0);
    }


    private void climb(int stairs, int[] buf, int len)
    {
        // Worked with my father on the solution
        // he now officially hates Java and says this is a kids' language
        // it was much more fun in C where we could do everything

        // This optimization uses buffers instead of ArrayLists
        // This saves all the ArrayList operations that are very costly
        // These ArrayList operations were pointed out by JProfile

        if (stairs == 0)
        {
            nWays++;
            opts.add(Arrays.copyOf(buf, len));
            return;
        }

        if (stairs >= 1)
        {
            buf[len] = 1;
            climb(stairs - 1, buf, len+1);
        }

        if (stairs >= 2)
        {
            buf[len] = 2;
            climb(stairs -2, buf, len+1);
        }

        if (stairs >= 3)
        {
            buf[len] = 3;
            climb(stairs - 3, buf, len+1);
        }
    }

    public void printSolutions() {
        System.out.println(nWays
                + (nWays > 1 ? " ways" : " way")
                + " to climb "
                + STAIRS
                + (STAIRS > 1 ? " stairs." : " stair.")
        );

        // determine how much space we need for right-aligning the index
        int maxIndexWidth = String.valueOf(opts.size()).length();

        for (int idx = 0; idx < opts.size(); idx++) {
            int[] steps = opts.get(idx);

            // "%Nd" means an integer right-aligned in a field of width N
            System.out.printf(
                    "%" + maxIndexWidth + "d. %s%n",
                    idx + 1,
                    Arrays.toString(steps)
            );
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
        climber.printSolutions();

        System.exit(0);
    }
}
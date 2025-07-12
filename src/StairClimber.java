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
import java.util.List;

public class StairClimber
{
    private final int STAIRS;
    private int nWays;
    private final List<ArrayList<Integer>> opts; // options

    public StairClimber(int stairs)
    {
        this.STAIRS = stairs;
        this.opts = new ArrayList<>();
    }

    public void climb()
    {
        ArrayList<Integer> initSteps = new ArrayList<>();
        climb(STAIRS, initSteps);
    }

    private void climb(int stairs, ArrayList<Integer> steps)
    {
        if (stairs == 0)
        {
            nWays++;
            opts.add(steps);
            return;
        }

        if (stairs >= 1)
        {
            ArrayList<Integer> stepsResult = new ArrayList<>(steps);
            stepsResult.add(1);
            climb(stairs - 1, stepsResult );
        }

        if (stairs >= 2)
        {
            ArrayList<Integer> stepsResult = new ArrayList<>(steps);
            stepsResult.add(2);
            climb(stairs - 2, stepsResult );
        }

        if (stairs >= 3)
        {
            ArrayList<Integer> stepsResult = new ArrayList<>(steps);
            stepsResult.add(3);
            climb(stairs - 3, stepsResult );
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
            List<Integer> steps = opts.get(idx);
            // "%Nd" means an integer right-aligned in a field of width N
            System.out.printf(
                    "%" + maxIndexWidth + "d. %s%n",
                    idx + 1,
                    steps
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
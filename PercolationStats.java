/**
 * Created by parameshpradeep on 9/6/17.
 */
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        int iterations = Integer.parseInt(args[1]);
        String type = args[2];
        int openCells;
        double probability[] = new double[iterations];
        double time[] = new double[iterations];
        double totalTime = 0;
        double val ;
        int index = 0;
        double mean;
        int currIt = 0;
        boolean speedMan = false;
        if(type.equals("fast"))
            speedMan = true;

        while(currIt<iterations){
            openCells = 0;
            Percolation P = new Percolation(size);
            P.fast = speedMan;
            Stopwatch s = new Stopwatch();
            while (!P.percolates()) {
                int a = StdRandom.uniform(P.gridL);
                int b = StdRandom.uniform(P.gridL);
                if (!P.isOpen(a, b)) {
                    P.open(a, b);
                    openCells++;
                }
            }
            currIt++;
            val = (openCells / (float) (size * size));
            probability[index] = val;
            time[index++] = s.elapsedTime();
            totalTime +=time[index-1];
        }
        mean = StdStats.mean(probability);
        System.out.println("mean threshold="+mean);
        System.out.println("std dev="+StdStats.stddev(probability));
        System.out.println("time="+totalTime);
        System.out.println("mean time="+StdStats.mean(time));
        System.out.println("stddev time="+StdStats.stddev(time));
    }
}



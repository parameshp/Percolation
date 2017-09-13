import java.util.Scanner;
import edu.princeton.cs.algs4.*;

/**
 * Created by parameshpradeep on 9/6/17.
 */
public class PercolationVisualizer {
    public  static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int g = s.nextInt();
        Percolation P = new Percolation(g);
        P.visualize = true;
        StdDraw.filledSquare(0.5, 0.5, 0.5);
        int i,j;
        while(s.hasNext()){
            i = s.nextInt();
            j = s.nextInt();
            P.open(i,j);
        }
    }
}

import edu.princeton.cs.algs4.*;

import java.util.Scanner;

/**
 * Created by parameshpradeep on 9/8/17.
 */
public class Percolation {
    int grid[];
    int gridSize;
    int gridL;
    boolean perc = false, visualize = false;
    boolean fast = false;
    float squareLength, halfsLength;
    int isOpen = 1;
    QuickFindUF quickFind;
    WeightedQuickUnionUF WFind;
    int indexTop;
    int indexBottom = oneDimension(0,0);
    int isBlocked = 0;
    public Percolation(int n){
        gridL = n;

        indexTop = oneDimension(0,(gridL-1));

        squareLength = 1/(float)gridL;

        halfsLength = squareLength/2;
        WFind = new WeightedQuickUnionUF(n*n);
        quickFind = new QuickFindUF(n*n);
        gridSize = n*n;
        grid = new int[gridSize];
        for(int i = 0; i < gridSize; i++){
            grid[i] = isBlocked;
        }
    }
    public void open(int x, int y){
        int index = oneDimension(x,y);
        grid[index] = isOpen;
        if(visualize) {
            if(index>=indexTop)
                StdDraw.setPenColor(StdDraw.BLUE);
            else{
                StdDraw.show();
                StdDraw.pause(100);
                StdDraw.setPenColor(StdDraw.WHITE);
            }
            StdDraw.filledSquare(halfsLength + x*squareLength, halfsLength + y *squareLength, 0.96*halfsLength);
        }
        checkSurroundings(x,y);
    }
    public boolean isOpen(int x, int y){
        int index = oneDimension(x,y);
        if(grid[index]==isBlocked)
            return false;
        if((index>0)&&(index<gridSize))
            if(grid[index]!=isBlocked)
                return true;
        return false;
    }

    public boolean isFull(int x, int y){
        int index = oneDimension(x,y);
        if((index>indexTop)&&(isOpen(x,y)))
            return true;
        for(int i = 0; i < gridL; i++){
            if(!fast) {
                if(quickFind.connected(index,indexTop+i))
                return true;
            }
            else if(fast==true){
                if(WFind.connected(index,indexTop+i))
                    return true;
            }
        }
        return false;
    }

    public int oneDimension(int x, int y){
        return (gridL*y) + x;
    }

    public void checkSurroundings(int x, int y){
        int adjL = gridL*y + (x-1);
        int adjR = gridL*y + (x+1);
        int adjUp = gridL*(y+1) + x;
        int adjDown = gridL*(y-1) + x;

        if((adjL>-1)&&(adjL<gridSize)&&(grid[adjL]!=isBlocked)){
            if((x-1)>=0){
                if(!fast)
                    quickFind.union(adjL,oneDimension(x,y));
                else
                    WFind.union(adjL,oneDimension(x,y));
            }
        }
        if((adjR>-1)&&(adjR<gridSize)&&(grid[adjR]!=isBlocked)){
            if((x+1)<(gridL)){
                if(!fast)
                    quickFind.union(adjR,oneDimension(x,y));
                else
                    WFind.union(adjR,oneDimension(x,y));
            }
        }
        if((adjUp>-1)&&(adjUp<gridSize)&&(grid[adjUp]!=isBlocked)){
            if((y+1)<(gridL)){
                if(!fast)
                    quickFind.union(adjUp,oneDimension(x,y));
                else
                    WFind.union(adjUp,oneDimension(x,y));
            }
        }
        if((adjDown>-1)&&(adjDown<gridSize)&&(grid[adjDown]!=isBlocked)){
            if((y-1)>=0){
                if(!fast)
                    quickFind.union(adjDown,oneDimension(x,y));
                else
                    WFind.union(adjDown,oneDimension(x,y));
            }
        }
        if((visualize)&&(isFull(x,y))){
            StdDraw.setPenColor(StdDraw.BLUE);
            for(int i = 0; i <gridSize; i++){
                if(!fast){
                if(quickFind.connected(i,oneDimension(x,y)))
                    StdDraw.filledSquare(halfsLength + (i%gridL)*squareLength, halfsLength + (i/gridL)*squareLength, 0.96*halfsLength);
                 }
                 else{
                    if(WFind.connected(i,oneDimension(x,y)))
                        StdDraw.filledSquare(halfsLength + (i%gridL)*squareLength, halfsLength + (i/gridL)*squareLength, 0.96*halfsLength);
                }
            }
        }
        if(isFull(x,y)){
            if(y==0)
                perc = true;
            else{
                for(int i = 0; i < gridL; i++)
                    if(!fast){
                        if(quickFind.connected(oneDimension(x,y),indexBottom+i)){
                            perc = true;
                        }
                    }
                    else{
                        if(WFind.connected(oneDimension(x,y),indexBottom+i)){
                            perc = true;
                    }
                }
            }
        }

    }

    public boolean percolates() {
    return perc;
    }
    public  static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int g = s.nextInt();
        Percolation P = new Percolation(g);
        int i,j;
        while(s.hasNext()){
            i = s.nextInt();
            j = s.nextInt();
            P.open(i,j);
        }
        if(P.percolates())
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}

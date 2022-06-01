package generator;
import java.util.List;
import java.util.Random;
import main.*;
import util.Cell;

/*
1.Let C be a list of cells, initially empty. Add one cell to C, at random.
2.Choose a cell from C, and carve a passage to any unvisited neighbor of that cell, adding that neighbor to C as well. If there are no unvisited neighbors, remove the cell from C.
3.Repeat #2 until C is empty.
*/


public class growingTree {
    private List<Cell> grid;
    private List<Cell> C;
    private Cell current;
    private Random rand = new Random();

    public growingTree(List<Cell> grid){
        this.grid = grid;
        current = grid.get(0);
        C.add(current);
    }

    //switch is for different methods for picking the cell from C, ideal can pick from same menu used for picking generation algorithm but idk how do so if to hard just go with any of them other than 0(bc its just backtracking)
    //we can add more if needed like a mix between first and random etc.

    private void create(int index){
        switch(index){
            case 0:
                current = C.get(C.size());
            case 1:
                current = C.get(rand.nextInt(C.size()));
            case 2:
                current = C.get(0);
            case 3:
                current = C.get((int)C.size()/2);
        }
        Cell next = current.getUnvisitedNeighbor(grid);
        if (next != null) {
            current.removeWalls(next);
            C.add(next);
        }
        else if(!C.isEmpty()){
            C.remove(current);
        }
    }
}

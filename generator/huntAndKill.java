package generator;
import java.util.List;

import main.*;
import util.Cell;


/*
1.Choose a starting location.
2.Perform a random walk, carving passages to unvisited neighbors, until the current cell has no unvisited neighbors.
3.Enter “hunt” mode, where you scan the grid looking for an unvisited cell that is adjacent to a visited cell. If found, carve a passage between the two and let the formerly unvisited cell be the new starting location.
4.Repeat steps 2 and 3 until the hunt mode scans the entire grid and finds no unvisited cells.
*/

public class huntAndKill {
    private List<Cell> grid;
    private List<Cell> unvisited;
    private Cell current;
    
    public huntAndKill(List<Cell> grid){
        this.grid = grid;
        current = grid.get(0);
        unvisited = grid;
    }

    private void create(){
        current.setVisited(true);
        Cell next = current.getUnvisitedNeighbor(grid);
        if (next != null) {
            current.removeWalls(next);
            current = next;
        }
        else{
            for(Cell c : unvisited){
                if(c.getVisitedNeighborsList(grid).size() != 0){
                    current = c;
                    current.removeWalls(current.getVisitedNeighbor(grid));
                    break;
                }
            }
        }
    }
}


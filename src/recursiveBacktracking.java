import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Stack;

/**
 * Recursive Backtracking map generation algorithm.
 *
 * Choose a starting point in the field.
 *
 * Randomly choose a wall at that point and carve a passage through to the adjacent cell,
 * but only if the adjacent cell has not been visited yet. This becomes the new current cell.
 *
 * If all adjacent cells have been visited, back up to the last cell that has uncarved walls and repeat.
 *
 * The algorithm ends when the process has backed all the way up to the starting point.
 *
 * @author Arnav Wadhwa, Brian Vesper
 */

public class recursiveBacktracking {
    Grid grid;
    HashSet<Cell> unvisited;
    Stack<Cell> visited;
    public recursiveBacktracking(int x, int y) {
        grid = new Grid(x, y);
        unvisited = new HashSet<Cell>();
        for(Cell c: grid) {
            unvisited.add(c);
        }
        visited = new Stack<>();
    }

    public recursiveBacktracking(int size) {
        grid = new Grid(size);
    }

    public void createMaze(int row, int col) {
        Cell current = grid.get(row, col);

        while(unvisited.size() != 0) {
            ArrayList<Direction> dir = grid.availbleNeighbors(current);
            if(dir.size() != 0) {
                Collections.shuffle(dir);
                visited.push(current);
                current.removeWall(dir.get(0));
                unvisited.remove(current);
                current.setVisited();
                current = current.neighbor(dir.get(0));
            } else {
                unvisited.remove(current);
                current = visited.pop();
            }
        }

    }
}

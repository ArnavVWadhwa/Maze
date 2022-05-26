import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Grid implements Iterable<Cell> {
    private Cell[][] cells;
    private int maxRow;
    private int maxCol;
    public Grid(int size){

    }
    public Grid(int row, int col){

    }

    @Override
    public Iterator iterator() {
        return null;
    }

    public Cell get(int row, int col) {
        return null;
    }

    public ArrayList<Direction> availbleNeighbors(Cell c) {
        ArrayList<Direction> dir = new ArrayList<>(Arrays.asList(Direction.values()));
        ArrayList<Direction> toRemove = new ArrayList<>();
        for(Direction d : dir) {

        }
        dir.removeAll(toRemove);
        return dir;
    }
}

package generator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import main.*;
import util.Cell;

import javax.swing.*;

/*
1.Let C be a list of cells, initially empty. Add one cell to C, at random.
2.Choose a cell from C, and carve a passage to any unvisited neighbor of that cell, adding that neighbor to C as well. If there are no unvisited neighbors, remove the cell from C.
3.Repeat #2 until C is empty.
*/


public class growingTree {
    private List<Cell> grid;
    private List<Cell> C = new ArrayList<>();
    private Cell current;
    private Random rand = new Random();

    public growingTree(List<Cell> grid, MazeGridPanel panel){
        this.grid = grid;
        current = grid.get(rand.nextInt(grid.size()));
        C.add(current);
        /**
         * This part is also taken from jaalsh to control how fast the maze generates so we can actually see it
         */
        final Timer timer = new Timer(Maze.speed, null);
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!grid.parallelStream().allMatch(Cell::isVisited)) {
                    create();
                } else {
                    current = null;
                    Maze.generated = true;
                    timer.stop();
                }
                panel.setCurrent(current);
                panel.repaint();
                timer.setDelay(Maze.speed);
            }
        });
        timer.start();
    }

    //switch is for different methods for picking the cell from C, ideal can pick from same menu used for picking generation algorithm but idk how do so if to hard just go with any of them other than 0(bc its just backtracking)
    //we can add more if needed like a mix between first and random etc.

    private void create(){
        current.setVisited(true);
        Cell next = current.getUnvisitedNeighbor(grid);
        if (next != null) {
            current.removeWalls(next);
            C.add(next);
            current = next;
        }
        else {
            C.remove(current);
            if (!C.isEmpty()) {
                current = C.get(rand.nextInt(C.size()));
            }
        }
    }
}

package generator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Stack;

import javax.swing.Timer;

import main.*;
import util.Cell;

/**
 * Recursive Backtracking map generation algorithm.
 *
 * Choose a starting point in the field.
 *
 * Randomly choose a wall at that point and carve a passage through to the adjacent util.cell,
 * but only if the adjacent util.cell has not been visited yet. This becomes the new current util.cell.
 *
 * If all adjacent cells have been visited, back up to the last util.cell that has uncarved walls and repeat.
 *
 * The algorithm ends when the process has backed all the way up to the starting point.
 *
 * @author Arnav Wadhwa, Brian Vesper, jaalsh
 */

public class recursiveBacktracking {
    private final List<Cell> grid;
    private final Stack<Cell> visited = new Stack<>();
    private Cell current;

    public recursiveBacktracking(List<Cell> grid, MazeGridPanel panel) {
        this.grid = grid;
        current = grid.get(0);
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

    private void create() {
        current.setVisited(true);
        Cell next = current.getUnvisitedNeighbor(grid);
        if (next != null) {
            visited.push(current);
            current.removeWalls(next);
            current = next;
        } else if (!visited.isEmpty()) {
            current = visited.pop();
        }
    }
}

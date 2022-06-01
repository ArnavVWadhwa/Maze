package generator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import main.*;
import util.Cell;

import javax.swing.*;


/*
1.Choose a starting location.
2.Perform a random walk, carving passages to unvisited neighbors, until the current cell has no unvisited neighbors.
3.Enter “hunt” mode, where you scan the grid looking for an unvisited cell that is adjacent to a visited cell. If found, carve a passage between the two and let the formerly unvisited cell be the new starting location.
4.Repeat steps 2 and 3 until the hunt mode scans the entire grid and finds no unvisited cells.
*/

public class huntAndKill {
    private List<Cell> grid;
//    private List<Cell> unvisited;
    private Cell current;
    
    public huntAndKill(List<Cell> grid, MazeGridPanel panel) {
        this.grid = grid;
        current = grid.get(0);
//        unvisited = grid;
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

    private void create(){
        current.setVisited(true);
        Cell next = current.getUnvisitedNeighbor(grid);
        if (next != null) {
            current.removeWalls(next);
            current = next;
        }
        else {
            List<Cell> visitWithUnvisitedNeighbors;
            visitWithUnvisitedNeighbors = grid.parallelStream().filter(c -> c.isVisited() && c.getUnvisitedNeighborsList(grid).size() > 0).collect(Collectors.toList());
            for (Cell c : visitWithUnvisitedNeighbors) {
                if (c != null) {
                    current = c;
                    break;
                }
            }
        }
    }
}


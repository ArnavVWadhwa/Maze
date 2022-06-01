package solver;

import main.Maze;
import main.MazeGridPanel;
import util.Cell;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class DijkstraSolve {

    private final List<Cell> grid;
    private final Queue<Cell> queue;
    private Cell current;

    public DijkstraSolve(List<Cell> grid, MazeGridPanel panel) {
        this.grid = grid;
        current = grid.get(0);
        current.setDistance(0);
        queue = new PriorityQueue<>(new DistanceComparator());
        queue.offer(current);
        final Timer timer = new Timer(Maze.speed, null);
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!current.equals(grid.get(grid.size() - 1))) {
                    solve();
                } else {
                    drawSolution();
                    Maze.solved = true;
                    timer.stop();
                }
                panel.setCurrent(current);
                panel.repaint();
                timer.setDelay(Maze.speed);
            }
        });
        timer.start();
    }
    //Note: Same as BFS, just using a priority queue instead
    private void solve() {
        current.setDeadEnd(true);
        current = queue.poll();
        List<Cell> adj = current.getMoveNeighbors(grid);
        for (Cell c : adj) {
            if (c.getDistance() == -1) {
                c.setDistance(current.getDistance() + 1);
                c.setParent(current);
                queue.offer(c);
            }
        }
    }

    private void drawSolution() {
        while(current != grid.get(0)) {
            current.setPath(true);
            current = current.getParent();
        }
    }

    private class DistanceComparator implements Comparator<Cell> {
        Cell goal = grid.get(grid.size() - 1);

        @Override
        public int compare(Cell o1, Cell o2) {
            if (distanceFromEnd(o1) > distanceFromEnd(o2)) {
                return 1;
            } else if (distanceFromEnd(o1) < distanceFromEnd(o2)) {
                return -1;
            } else {
                return 0;
            }
        }

        private double distanceFromEnd(Cell c) {
            return Math.sqrt(Math.pow(c.getX() - goal.getX(), 2) + Math.pow(c.getY() - goal.getY(), 2));
        }
    }
}

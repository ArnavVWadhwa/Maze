package solver;

import main.*;
import util.Cell;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSolve {

    private final Queue<Cell> queue;
    private final List<Cell> grid;
    private Cell current;

    public BFSolve(List<Cell> grid, MazeGridPanel panel) {
        this.grid = grid;
        current = grid.get(0);
        current.setDistance(0);
        queue = new LinkedList<>();
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
}

package solver;

import main.Maze;
import main.MazeGridPanel;
import util.Cell;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Stack;

public class DFSolve {

    private final Stack<Cell> path;
    private Cell current;
    private final List<Cell> grid;

    public DFSolve (List<Cell> grid, MazeGridPanel panel) {
        this.grid = grid;
        current = grid.get(0);
        path = new Stack<>();

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
        Cell next = current.getPathNeighbor(grid);
        if (next != null) {
            path.push(current);
            current = next;
        } else if (!path.isEmpty()) {
            current = path.pop();
        }
    }

    private void drawSolution() {
        while(!path.isEmpty()) {
            try {
                path.pop().setPath(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

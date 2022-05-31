package util;

import main.Maze;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class used to run and draw the maze, taken from jaalsh. We understand this.
 * @authors Arnav Wadhwa, Brian Vesper, jaalsh
 */
public class Cell {
    //TODO: Brian Add whatever you need for the rest of the generation methods and all of the solving ones.
    int x, y, distance;

    private Cell parent;

    private boolean visited = false;
    private boolean path = false;
    private boolean deadEnd = false;

    // north 0, south 1, east 2, west 3
    private boolean[] walls = {true, true, true, true};

    public boolean[] getWalls() {
        return walls;
    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.distance = -1;
    }

    public void removeWalls(Cell next) {
        // north 0, south 1, east 2, west 3
        int wallX = this.x - next.x;

        if (wallX == 1) {
            walls[3] = false;
            next.walls[2] = false;
        } else if (wallX == -1){
            walls[2] = false;
            next.walls[3] = false;
        }

        // north 0, south 1, east 2, west 3
        int wallY = this.x - next.x;

        if (wallY == 1) {
            walls[0] = false;
            next.walls[1] = false;
        } else if (wallY == -1){
            walls[1] = false;
            next.walls[0] = false;
        }
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isDeadEnd() {
        return deadEnd;
    }

    public void setDeadEnd(boolean deadEnd) {
        this.deadEnd = deadEnd;
    }

    public boolean isPath() {
        return path;
    }

    public void setPath(boolean path) {
        this.path = path;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Cell getParent() {
        return parent;
    }

    public void setParent(Cell parent) {
        this.parent = parent;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }


    private Cell checkNeighborInGridBounds(List<Cell> grid, Cell neighbor) {
        if (grid.contains(neighbor)) {
            return grid.get(grid.indexOf(neighbor));
        } else {
            return null;
        }
    }
    public Cell getUnvisitedNeighbor(List<Cell> grid) {
        List<Cell> unvisitedNeighbors = getUnvisitedNeighborsList(grid);

        if(unvisitedNeighbors.size() == 1) {
            return unvisitedNeighbors.get(0);
        }
        return randomNeighbor(unvisitedNeighbors);
    }

    private Cell randomNeighbor(List<Cell> unvisitedNeighbors) {
        if (unvisitedNeighbors.size() > 0) {
            return unvisitedNeighbors.get(new Random().nextInt(unvisitedNeighbors.size()));
        }
        return null;
    }

    public List<Cell> getUnvisitedNeighborsList(List<Cell> grid) {
        List<Cell> neighbors = new ArrayList<>(4);

        Cell north = checkNeighborInGridBounds(grid, new Cell(x, y - 1));
        Cell east = checkNeighborInGridBounds(grid, new Cell(x + 1, y));
        Cell south = checkNeighborInGridBounds(grid, new Cell(x, y + 1));
        Cell west = checkNeighborInGridBounds(grid, new Cell(x - 1, y));

        if (north != null) if(!north.visited) neighbors.add(north);
        if (east != null) if(!east.visited) neighbors.add(east);
        if (south != null)if(!south.visited) neighbors.add(south);
        if (west != null) if(!west.visited) neighbors.add(west);

        return neighbors;
    }

    private Cell checkCellInGrid(List<Cell> grid, Cell neighbor) {
        if (grid.contains(neighbor)) {
            return neighbor;
        } else {
            return null;
        }
    }

    /**
     * Full Credit for this method goes to jaalsh. We understand it though.
     */
    public void draw(Graphics g) {
        int x2 = x * Maze.W;
        int y2 = y * Maze.W;

        if(visited) {
            g.setColor(Color.MAGENTA);
            g.fillRect(x2, y2, Maze.W, Maze.W);
        }
        if(path) {
            g.setColor(Color.BLUE);
            g.fillRect(x2, y2, Maze.W, Maze.W);
        } else if (deadEnd) {
            g.setColor(Color.RED);
            g.fillRect(x2, y2, Maze.W, Maze.W);
        }
        // top 0, right 1, bottom 2, left 3
        // north 0, south 1, east 2, west 3
        g.setColor(Color.WHITE);
        if (walls[0]) {
            g.drawLine(x2, y2, x2+Maze.W, y2);
        }
        if (walls[1]) {
            g.drawLine(x2+Maze.W, y2+Maze.W, x2, y2+Maze.W);
        }
        if (walls[2]) {
            g.drawLine(x2+Maze.W, y2, x2+Maze.W, y2+Maze.W);
        }
        if (walls[3]) {
            g.drawLine(x2, y2+Maze.W, x2, y2);
        }
    }

    /**
     * Full Credit for this method goes to jaalsh. We understand it though.
     */
    public void displayAsColor(Graphics g, Color color) {
        int x2 = x * Maze.W;
        int y2 = y * Maze.W;
        g.setColor(color);
        g.fillRect(x2, y2, Maze.W, Maze.W);
    }

    @Override
    public String toString() {
        return String.format("X: %d, Y: %d", x, y);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cell other = (Cell) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
}

public class Cell {

    private boolean visited;
    int y;
    int x;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        visited = false;
    }
    public boolean hasValidNeighbor(Direction d) {
        if (d == Direction.N && y == 0)
            return false;
        else if (d == Direction.S && y == maxRow)
            return false;
        else if (d == Direction.W && x == 0)
            return false;
        else if (d == Direction.E && x == maxCol)
            return false;
        else if (c.hasValidNeighbor(d))
            return false;
        return this.neighbor(d).isVisited();
    }
    public Cell getRandomNeighbor() {
        return null;
    }


    public Cell neighbor(Direction directions) {
        return null;
    }

    public void removeWall(Direction direction) {
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited() {
        visited = true;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}

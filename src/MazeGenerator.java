public abstract class MazeGenerator {
    private Grid<> grid;

    public MazeGenerator(Grid<> grid) {
        this.grid = grid;
    }

    public abstract void createMaze(int row, int col);


}

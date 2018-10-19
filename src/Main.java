import CW.Maze;

public class Main {

    public static void main(String[] args) {
        Maze maze = new Maze(10, 15);
        maze.print();
        maze.serialGenerator();
        maze.print();
        maze.serialPathFinder();
    }
}

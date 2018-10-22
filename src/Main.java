import CW.Maze;
import CW.Generator;
//import CW.PathFinder;

public class Main {

    public static void main(String[] args) {
        Maze maze = new Maze(5, 5);
        maze.print();
        Generator.serialGenerator(maze);
        maze.print();
//        PathFinder.serialPathFinder(maze);
    }
}

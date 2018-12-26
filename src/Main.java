import CW.Maze;
import CW.Generator;

import CW.PathFinder;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Maze maze = new Maze(10, 15);
        //maze.print();

        Generator G = new Generator();
        //long res = G.startParallel(maze);
        long res = G.serialGenerator(maze);

        if (res >= 0) System.out.println("Время выполнения: " + res + "мс");
        else System.out.println("Код ошибки: " + res);
        maze.print();

        PathFinder PF = new PathFinder();
        res = PF.startParallel(maze);
        //res = PF.serialPathFinder(maze);

        if (res >= 0) System.out.println("Время выполнения: " + res + "мс");
        else System.out.println("Код ошибки: " + res);
        PF.printTrace(maze);

        //maze.print();

    }
}

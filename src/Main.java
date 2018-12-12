import CW.Maze;
import CW.Generator;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import CW.PathFinder;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Maze maze = new Maze(Integer.parseInt(args[0]), Integer.parseInt(args[0]));
        //maze.print();

        Generator G = new Generator();

        //long res = G.startParallel(maze);
        long res = G.serialGenerator(maze);

        if (res >= 0) System.out.println("Время выполнения: " + res + "мс");
        else System.out.println("Код ошибки: " + res);
        maze.print();

        PathFinder PF = new PathFinder();
        res = PF.serialPathFinder(maze, maze.getCellAt(0,0));

        if (res >= 0) System.out.println("Время выполнения: " + res + "мс");

    /* Пример многопоточности
        Runnable myRun1 = new ParallelTestClass();
        ((ParallelTestClass) myRun1).setTestChar('*');
        Thread thread1 = new Thread(myRun1);

        Runnable myRun2 = new ParallelTestClass();
        ((ParallelTestClass) myRun2).setTestChar('#');
        Thread thread2 = new Thread(myRun2);

        thread1.start();
        thread2.start();
    */
    }
}

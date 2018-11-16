import CW.Maze;
import CW.Generator;
import CW.ParallelTestClass;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
//import CW.PathFinder;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Maze maze = new Maze(Integer.parseInt(args[0]), Integer.parseInt(args[0]));

        switch (Integer.parseInt(args[1])){
            case 0:
                long startTime = System.currentTimeMillis();
                Generator.serialGenerator(maze);
                long endTime = System.currentTimeMillis();
                System.out.println("Время выполнения: " + (endTime - startTime) + "мс");
                //maze.print();
                break;
            case 1:
                Runnable parGen1 = new Generator();
                ((Generator) parGen1).setTargetMaze(maze);
                ((Generator) parGen1).setTargetMaze(maze);
                Thread thread1 = new Thread(parGen1);
                Thread thread2 = new Thread(parGen1);

                TimeUnit.MILLISECONDS.sleep(100);

                startTime = System.currentTimeMillis();

                thread1.start();
                thread2.start();

                thread1.join();
                thread2.join();
                endTime = System.currentTimeMillis();
                System.out.println("Время выполнения: " + (endTime - startTime) + "мс");
                //maze.print();
                break;
        }

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

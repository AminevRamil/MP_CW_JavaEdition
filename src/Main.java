import CW.Maze;
import CW.Generator;
import CW.ParallelTestClass;
//import CW.PathFinder;

public class Main {

    public static void main(String[] args) {
        Maze maze = new Maze(5, 5);
        maze.print();
//        Generator.serialGenerator(maze);
//        maze.print();
//        PathFinder.serialPathFinder(maze);

        Runnable parGen1 = new Generator();
        ((Generator) parGen1).setTargetMaze(maze);
        Thread thread1 = new Thread(parGen1);
        maze.print();




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

package CW;

import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class PathFinder {

    public long serialPathFinder(Maze maze) {
        System.out.println("Начало последовательного поиска пути");
        long start = System.currentTimeMillis();
        Cell currentCell = maze.getCellAt(0, 0);
        Cell exitCell = maze.getCellAt(maze.getSize_h() - 1, maze.getSize_w() - 1);
        Stack<Cell> path = new Stack<>();


        do {
            currentCell.visited = true;
            int numOfNeigbors = currentCell.isThereUnvisitedNeighborsPF();
            if (numOfNeigbors > 0) {
                path.push(currentCell);
                currentCell = currentCell.getUnvisitedNeighborPF();
            } else if (!path.empty()) {
                currentCell = path.peek();
                path.pop();
            } else {
                System.out.println("Пути нет");
                break;
            }
        } while (currentCell != exitCell);
        path.push(currentCell);

        long end = System.currentTimeMillis();
        track = path;
        return end - start;
    }

    static private volatile int workingThreads = 0;
    private Stack<Cell> track = null;
    static final private int MAX_THREADS = 4;
    private ExecutorService service = Executors.newFixedThreadPool(MAX_THREADS);
    private static final Semaphore SEMAPHORE_PRINT = new Semaphore(1, true);

    public long startParallel(Maze maze) {
        System.out.println("Начало параллельного поиска пути");
        long start = System.currentTimeMillis();

        final Cell staringCell = maze.getCellAt(0, 0);
        try {
            Stack<Cell> path = new Stack<>();
            service.submit(() -> parallelSeeker(maze, staringCell, path));

            service.awaitTermination(1000, TimeUnit.SECONDS);
            long end = System.currentTimeMillis();
            return end - start;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return -2;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -3;
        }
    }

    private void parallelSeeker(Maze maze, Cell startingCell, Stack<Cell> path){
        workingThreads++;
        Cell currentCell = startingCell;
        Cell exitCell = maze.getCellAt(maze.getSize_h() - 1, maze.getSize_w() - 1);
        do { currentCell.visited = true;
            int numOfNeighbors = currentCell.isThereUnvisitedNeighborsPF();
            if (numOfNeighbors > 1 && workingThreads <= MAX_THREADS && currentCell != exitCell) {
                numOfNeighbors--;
                final Cell newStartingCell = currentCell.getUnvisitedNeighborPF();
                newStartingCell.visited = true;
                path.push(currentCell);
                Stack<Cell> copyOfPath = (Stack<Cell>)path.clone();
                path.pop();
                service.submit(() -> parallelSeeker(maze, newStartingCell, copyOfPath)); }
            if (numOfNeighbors > 0) {
                path.push(currentCell);
                currentCell = currentCell.getUnvisitedNeighborPF();
            } else if (!path.empty()) {
                currentCell = path.peek();
                path.pop();
            } else {
                break;
            }
        } while (currentCell != exitCell && track == null); //Экспериментальная дополнение. Может вызывать зацикливание
        currentCell.visited = true;
        if (currentCell == exitCell) {
            path.push(currentCell);
            exitCell.visited = true;
            System.out.println("Путь найден потоком " + Thread.currentThread().getName());
            System.out.println("Длина пути: " + path.size());
            track = path;
            service.shutdown();}
        workingThreads--;
    }

    public void printTrace(Maze maze) {
        try {
            SEMAPHORE_PRINT.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < maze.getSize_h(); i++) {
            for (int j = 0; j < maze.getSize_w(); j++) {
                if (!track.contains(maze.getCellAt(i, j))) System.out.print("░");
                else {
                    switch (maze.getCellAt(i, j).wallCode()) {
                        case 0:
                            System.out.print('░');
                            break;
                        case 1:
                            System.out.print("╡");
                            break;
                        case 2:
                            System.out.print("╨");
                            break;
                        case 3:
                            System.out.print("╝");
                            break;
                        case 4:
                            System.out.print("╞");
                            break;
                        case 5:
                            System.out.print("═");
                            break;
                        case 6:
                            System.out.print("╚");
                            break;
                        case 7:
                            System.out.print("╩");
                            break;
                        case 8:
                            System.out.print("╥");
                            break;
                        case 9:
                            System.out.print("╗");
                            break;
                        case 10:
                            System.out.print("║");
                            break;
                        case 11:
                            System.out.print("╣");
                            break;
                        case 12:
                            System.out.print("╔");
                            break;
                        case 13:
                            System.out.print("╦");
                            break;
                        case 14:
                            System.out.print("╠");
                            break;
                        case 15:
                            System.out.print("╬");
                            break;
                    }
                }
            }
            System.out.println();
        }
        SEMAPHORE_PRINT.release();
    }
}

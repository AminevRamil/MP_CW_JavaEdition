package CW;

import java.util.Stack;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Generator{

    public long serialGenerator(Maze maze) {
        System.out.println("Начало генерации лабиринта");
        long start = System.currentTimeMillis();
        Cell currentCell = maze.getCellAt(0, 0);
        Cell startingCell = maze.getCellAt(0, 0);
        currentCell.visited = true;
        Stack<Cell> stack = new Stack<>();

        do if (currentCell.isThereUnvisitedNeighborsG() != 0) {
            stack.push(currentCell);
            Cell randNeighbor = currentCell.getUnvisitedNeighborG();
            currentCell.makePass(randNeighbor);
            randNeighbor.visited = true;
            currentCell = randNeighbor;
        } else if (!stack.empty()) {
            currentCell = stack.peek();
            stack.pop();
        }
        while (currentCell != startingCell);
        long end = System.currentTimeMillis();
        maze.makeAllUnvisited();
        System.out.println("Лабиринт сгенерирован");
        return end - start;
    }

    public long startParallel(Maze maze) {
        long start = System.currentTimeMillis();
        final Cell staringCell = maze.getCellAt(0,0);
        try {
            service.submit(() -> {
                try {
                    parallelGenerator(maze, staringCell);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            latch.await();
            long end = System.currentTimeMillis();
            return end - start;
        } catch (NullPointerException e){
            e.printStackTrace();
            return -2;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    static private volatile int workingThreads = 0;
    static final private int MAX_THREADS = 4;
    CountDownLatch latch = new CountDownLatch(0);
    private ExecutorService service = Executors.newFixedThreadPool(4);
    //добавить статичный пул потоков

    public void parallelGenerator(Maze maze, Cell startingCell) throws InterruptedException {
        //workingThreads++;
        //System.out.println("Начало генерации потоком " + Thread.currentThread().getName());
        latch.countDown();

        Cell currentCell = startingCell;
        currentCell.visited = true;
        Stack<Cell> stack = new Stack<>();
        int count = 0;

        do {
            int numOfNeighbors = currentCell.isThereUnvisitedNeighborsG();

            if (numOfNeighbors > 1 && workingThreads <= MAX_THREADS && count == 5) {

                final Cell newStartingCell = currentCell.getUnvisitedNeighborG();
                newStartingCell.visited = true;
                currentCell.makePass(newStartingCell);

                service.submit(() -> {
                    try {
                        parallelGenerator(maze, newStartingCell);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }

            if (numOfNeighbors != 0) {
                stack.push(currentCell);
                Cell randNeighbor = currentCell.getUnvisitedNeighborG();
                currentCell.makePass(randNeighbor);
                randNeighbor.visited = true;
                currentCell = randNeighbor;
            } else if (!stack.empty()) {
                currentCell = stack.peek();
                stack.pop();
            }

            if (count == 10) count = 0;
            else count++;

            //TimeUnit.SECONDS.sleep(1);
            //maze.print();
        } while (currentCell != startingCell);

        //workingThreads--;
        //System.out.println("Поток " + Thread.currentThread().getName() + " завершил работу");
    }
}


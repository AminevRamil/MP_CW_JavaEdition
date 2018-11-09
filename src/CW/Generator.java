package CW;

import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class Generator implements Runnable {

    public static void serialGenerator(Maze maze) {
        System.out.println("Начало генерации лабиринта");

        Cell currentCell = maze.getCellAt(0, 0);
        Cell startingCell = maze.getCellAt(0, 0);
        currentCell.visited = true;
        Stack<Cell> stack = new Stack<>();

        do if (currentCell.isThereUnvisitedNeighborsG()) {
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
        maze.makeAllUnvisited();
        System.out.println("Лабиринт сгенерирован");
    }

    private static Maze targetMaze = null;

    public void setTargetMaze(Maze maze) {
        targetMaze = maze;
    }

    private void parallelGenerator(Maze maze, int x0, int y0) throws InterruptedException {
        System.out.println("Начало генерации в точке (" + x0 + "," + y0 + ") потоком " + Thread.currentThread().getName());

        Cell currentCell = maze.getCellAt(0, 0);
        Cell startingCell = maze.getCellAt(0, 0);
        currentCell.visited = true;
        Stack<Cell> stack = new Stack<>();

        do {
            if (currentCell.isThereUnvisitedNeighborsG()) {
                stack.push(currentCell);
                Cell randNeighbor = currentCell.getUnvisitedNeighborG();
                currentCell.makePass(randNeighbor);
                randNeighbor.visited = true;
                currentCell = randNeighbor;
            } else if (!stack.empty()) {
                currentCell = stack.peek();
                stack.pop();
            }
//            TimeUnit.SECONDS.sleep(1);
//            maze.print();
        } while (currentCell != startingCell);


        System.out.println("Поток " + Thread.currentThread().getName() + " завершил работу");
    }

    @Override
    public void run(){
        try {
            parallelGenerator(targetMaze, 0, 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


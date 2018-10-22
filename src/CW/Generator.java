package CW;

import java.util.Stack;

public class Generator {

    public static void serialGenerator(Maze maze) {
        System.out.println("Начало генерации лабиринта");
        int size_h = maze.getSize_h();
        int size_w = maze.getSize_w();

        Cell currentCell = maze.getRandomCell();
        currentCell.visited = true;
        int unvisited = size_h * size_w - 1;
        Stack<Cell> stack = new Stack<>(); //инициализация?

        while (unvisited > 0) {
            if (currentCell.isThereUnvisitedNeighborsG()) {
                stack.push(currentCell);
                Cell randNeighbor = currentCell.getUnvisitedNeighborG();
                currentCell.makePass(randNeighbor);
                randNeighbor.visited = true;
                unvisited--;
                currentCell = randNeighbor;
            } else if (!stack.empty()) {
                currentCell = stack.peek();
                stack.pop();
            } else {
                System.out.println("What?");
                break;
            }
        }
        maze.makeAllUnvisited();
        System.out.println("Лабиринт сгенерирован");
    }
}


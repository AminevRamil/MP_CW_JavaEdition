package CW;

import java.util.Stack;

public class PathFinder {

    public long serialPathFinder(Maze maze, Cell startingCell) {
        System.out.println("Начало последовательного поиска пути");
        long start = System.currentTimeMillis();
        Cell currentCell = startingCell;
        currentCell.visited = true;
        Stack<Cell> stack = new Stack<>();


        do {
            if (currentCell.isThereUnvisitedNeighborsPF()) {
                stack.push(currentCell);
                currentCell = currentCell.getUnvisitedNeighborPF();
                currentCell.visited = true;
            } else if (!stack.empty()) {
                currentCell = stack.peek();
                stack.pop();
            } else {
                System.out.println("Пути нет");
                break;
            }
        } while (currentCell != startingCell);
        stack.push(currentCell);

        long end = System.currentTimeMillis();
        printTrace(maze, stack);
        return end - start;
    }

    void printTrace(Maze maze, Stack<Cell> track) {

        for (int i = 0; i < maze.getSize_h(); i++) {
            for (int j = 0; j < maze.getSize_w(); j++) {
                if (track.contains(maze.getCellAt(i, j))) System.out.print("■");
                else {
                    switch (maze.getCellAt(i, j).wallCode()) {
                        case 0:
                            System.out.print("░");
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
    }
}

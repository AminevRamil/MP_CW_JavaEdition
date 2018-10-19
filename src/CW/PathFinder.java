package CW;

import java.util.Stack;

public class PathFinder {

    public void serialPathFinder(Maze maze) {
        System.out.println("Начало последовательного поиска пути");
        Cell current = maze[0][0];
        current.visited = true;
        Stack<Cell> stack = new Stack<>();


        while (current != maze[size_h - 1][size_w - 1]) {
            if (current.isThereUnvisitedNeighborsPF()) {
                stack.push(current);
                current = current.getUnvisitedNeighborPF();
                current.visited = true;
            } else if (!stack.empty()) {
                current = stack.peek();
                stack.pop();
            } else {
                System.out.println("Пути нет");
                break;
            }
        }
        stack.push(current);
        System.out.println("Решение:" + stack.toString());
        //Придумать другой способ вывода лабиринта и его решения
    }

}

package CW;

import java.util.Stack;

public class Generator {

    public void serialGenerator(Maze maze) {
        System.out.println("Начало генерации лабиринта");
        int cur_i = (int) (Math.random() * size_h);
        int cur_j = (int) (Math.random() * size_w);
        System.out.printf("Начальная точка(%d,%d)\n", cur_i, cur_j);
        maze[cur_i][cur_j].visited = true;
        int unvisited = size_h * size_w - 1;
        Stack<Cell> stack = new Stack<>(); //инициализация?
        while (unvisited > 0) {
            if (maze[cur_i][cur_j].isThereUnvisitedNeighborsG()) {
                stack.push(maze[cur_i][cur_j]);
                Cell randNeighbor = maze[cur_i][cur_j].getUnvisitedNeighborG();
                maze[cur_i][cur_j].makePass(randNeighbor);
                randNeighbor.visited = true;
                unvisited--;
                cur_i = randNeighbor.coord_i;
                cur_j = randNeighbor.coord_j;
            } else if (!stack.empty()) {
                cur_i = stack.peek().coord_i;
                cur_j = stack.peek().coord_j;
                stack.pop();
            } else {
                this.print();
                System.out.println("What?");
                break;
            }
        }
        makeAllUnvisited();
        System.out.println("Лабиринт сгенерирован");
    }
}

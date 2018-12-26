package CW;

import java.util.Stack;
import java.util.concurrent.Semaphore;

public class Maze {
    private int size_h;
    private int size_w;
    private Cell maze[][];

    public Maze(int h, int w) {
        size_h = h;
        size_w = w;
        System.out.printf("Размер поля: [%d,%d]\n", size_h, size_w);

        maze = new Cell[size_h][size_w];

        for (int i = 0; i < size_h; i++) {
            for (int j = 0; j < size_w; j++) {
                maze[i][j] = new Cell();
            }
        }

        for (int i = 0; i < size_h - 1; i++) {
            for (int j = 0; j < size_w - 1; j++) {
                maze[i][j].addToRight(maze[i][j + 1]);
                maze[i][j].addToDown(maze[i + 1][j]);
            }
        }

        for (int i = 0; i < size_h - 1; i++){
            maze[i][size_w - 1].addToDown(maze[i + 1][size_w - 1]);
        }

        for (int j = 0; j < size_w - 1; j++){
            maze[size_h - 1][j].addToRight(maze[size_h - 1][j + 1]);
        }

        System.out.println("Лабиринт инициализирован");
    }

    int getSize_h(){
        return size_h;
    }

    int getSize_w(){
        return size_w;
    }

    public Cell getCellAt(int i, int j){
        return maze[i][j];
    }


    private static final Semaphore SEMAPHORE = new Semaphore(1, true);

    public void print() {
        try {
            SEMAPHORE.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < size_h; i++) {
            for (int j = 0; j < size_w; j++) {
                switch (maze[i][j].wallCode()) {
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
            System.out.println();
        }
        SEMAPHORE.release();
    }

    void makeAllUnvisited() {
        for (int i = 0; i < size_h; i++) {
            for (int j = 0; j < size_w; j++) {
                maze[i][j].visited = false;
            }
        }
    }

}
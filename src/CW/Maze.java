package CW;

import java.util.Stack;

public class Maze {
    private int size_h;
    private int size_w;
    private Cell maze[][];

    int getSize_h(){
        return size_h;
    }

    int getSize_w(){
        return size_w;
    }

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

        for (int i = 0; i < size_h; i++) {
            for (int j = 0; j < size_w; j++) {
                maze[i][j].init(i, j);
            }
        }

        for (int i = 1; i < size_h - 1; i++) {
            for (int j = 1; j < size_w - 1; j++) {
                maze[i][j].makePair(maze[i][j - 1]);
                maze[i][j].makePair(maze[i - 1][j]);
                maze[i][j].makePair(maze[i][j + 1]);
                maze[i][j].makePair(maze[i + 1][j]);
            }
        }

        for (int i = 0; i < size_h - 1; i++) {
            maze[i][0].makePair(maze[i + 1][0]);
            maze[i][w - 1].makePair(maze[i + 1][w - 1]);
        }

        for (int j = 0; j < size_w - 1; j++) {
            maze[0][j].makePair(maze[0][j + 1]);
            maze[h - 1][j].makePair(maze[h - 1][j + 1]);
        }
        System.out.println("Лабиринт инициализирован");
    }

    public void print() {
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
    }

    private void makeAllUnvisited() {
        for (int i = 0; i < size_h; i++) {
            for (int j = 0; j < size_w; j++) {
                maze[i][j].visited = false;
            }
        }
    }



    public void printPath(Stack<Cell> path) {
        char output[][] = new char[size_h][size_w];

        for (int i = 0; i < size_h; i++) {
            for (int j = 0; j < size_w; j++) {
                switch (maze[i][j].wallCode()) {
                    case 0:
                        output[i][j] = '░';
                        break;
                    case 1:
                        output[i][j] = '╡';
                        break;
                    case 2:
                        output[i][j] = '╨';
                        break;
                    case 3:
                        output[i][j] = '╝';
                        break;
                    case 4:
                        output[i][j] = '╞';
                        break;
                    case 5:
                        output[i][j] = '═';
                        break;
                    case 6:
                        output[i][j] = '╚';
                        break;
                    case 7:
                        output[i][j] = '╩';
                        break;
                    case 8:
                        output[i][j] = '╥';
                        break;
                    case 9:
                        output[i][j] = '╗';
                        break;
                    case 10:
                        output[i][j] = '║';
                        break;
                    case 11:
                        output[i][j] = '╣';
                        break;
                    case 12:
                        output[i][j] = '╔';
                        break;
                    case 13:
                        output[i][j] = '╦';
                        break;
                    case 14:
                        output[i][j] = '╠';
                        break;
                    case 15:
                        output[i][j] = '╬';
                        break;
                }
            }
        }

        int cnt = path.size();
        for (int i = 0; i < cnt; i++){
            switch(output[path.peek().coord_i][path.peek().coord_j]){
                case '╡':
                    output[path.peek().coord_i][path.peek().coord_j] = '╸';
                    break;
                case '╨':
                    output[path.peek().coord_i][path.peek().coord_j] = '╹';
                    break;
                case '╝':
                    output[path.peek().coord_i][path.peek().coord_j] = '┛';
                    break;
                case '╞':
                    output[path.peek().coord_i][path.peek().coord_j] = '╺';
                    break;
                case '═':
                    output[path.peek().coord_i][path.peek().coord_j] = '━';
                    break;
                case '╚':
                    output[path.peek().coord_i][path.peek().coord_j] = '┗';
                    break;
                case '╩':
                    output[path.peek().coord_i][path.peek().coord_j] = '┻';
                    break;
                case '╥':
                    output[path.peek().coord_i][path.peek().coord_j] = '╻';
                    break;
                case '╗':
                    output[path.peek().coord_i][path.peek().coord_j] = '┓';
                    break;
                case '║':
                    output[path.peek().coord_i][path.peek().coord_j] = '┃';
                    break;
                case '╣':
                    output[path.peek().coord_i][path.peek().coord_j] = '┫';
                    break;
                case '╔':
                    output[path.peek().coord_i][path.peek().coord_j] = '┏';
                    break;
                case '╦':
                    output[path.peek().coord_i][path.peek().coord_j] = '┳';
                    break;
                case '╠':
                    output[path.peek().coord_i][path.peek().coord_j] = '┣';
                    break;
                case '╬':
                    output[path.peek().coord_i][path.peek().coord_j] = '╋';
                    break;
            }
            path.pop();
        }

        for (int i = 0; i < size_h; i++) {
            for (int j = 0; j < size_w; j++) {
                System.out.print(output[i][j]);
            }
            System.out.println();
        }
    }
}

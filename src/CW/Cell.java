package CW;

public class Cell {
    int coord_i;
    int coord_j;
    boolean visited;
    Cell leftCell;
    Cell upCell;
    Cell rightCell;
    Cell downCell;
    boolean leftWall = true;
    boolean upWall = true;
    boolean rightWall = true;
    boolean downWall = true;

    void init(int i, int j) {
        coord_i = i;
        coord_j = j;
        visited = false;
        leftCell = null;
        upCell = null;
        rightCell = null;
        downCell = null;
        leftWall = true;
        upWall = true;
        rightWall = true;
        downWall = true;
    }

    void makePair(Cell cell2) {
        if (this.coord_i == cell2.coord_i) {
            if (this.coord_j < cell2.coord_j) {
                this.rightCell = cell2;
                cell2.leftCell = this;
            } else {
                this.leftCell = cell2;
                cell2.rightCell = this;
            }
        } else if (this.coord_j == cell2.coord_j) {
            if (this.coord_i < cell2.coord_i) {
                this.downCell = cell2;
                cell2.upCell = this;
            } else {
                this.upCell = cell2;
                cell2.downCell = this;
            }
        }
    }

    void makeWall(Cell cell2) {
        if (this.coord_i == cell2.coord_i) {
            if (this.coord_j < cell2.coord_j) {
                this.rightWall = true;
                cell2.leftWall = true;
            } else {
                this.leftWall = true;
                cell2.rightWall = true;
            }
        } else if (this.coord_j == cell2.coord_j) {
            if (this.coord_i < cell2.coord_i) {
                this.downWall = true;
                cell2.upWall = true;
            } else {
                this.upWall = true;
                cell2.downWall = true;
            }
        }
    }

    void makePass(Cell cell2) {
        if (this.coord_i == cell2.coord_i) {
            if (this.coord_j < cell2.coord_j) {
                this.rightWall = false;
                cell2.leftWall = false;
            } else {
                this.leftWall = false;
                cell2.rightWall = false;
            }
        } else if (this.coord_j == cell2.coord_j) {
            if (this.coord_i < cell2.coord_i) {
                this.downWall = false;
                cell2.upWall = false;
            } else {
                this.upWall = false;
                cell2.downWall = false;
            }
        }
    }

    int wallCode() {
        int code = 0;
        if (!leftWall) code += 1;
        if (!upWall) code += 2;
        if (!rightWall) code += 4;
        if (!downWall) code += 8;
        return code;
    }

    boolean isThereUnvisitedNeighborsG() { //Вариант функции для генератора лабиринтов
        boolean yes = false;
        if (leftCell != null) yes = yes || !leftCell.visited;
        if (upCell != null) yes = yes || !upCell.visited;
        if (rightCell != null) yes = yes || !rightCell.visited;
        if (downCell != null) yes = yes || !downCell.visited;
        return yes;
    }

    boolean isThereUnvisitedNeighborsPF() { //Вариант функции для поиска пути
        boolean yes = false;
        if (leftCell != null) yes = yes || (!leftCell.visited && !leftWall); //обдумать порядок действий
        if (upCell != null) yes = yes || (!upCell.visited && !upWall);
        if (rightCell != null) yes = yes || (!rightCell.visited && !rightWall);
        if (downCell != null) yes = yes || (!downCell.visited && !downWall);
        return yes;
    }

    Cell getUnvisitedNeighborG() {
        int code = 0;
        if ((leftCell != null) && !leftCell.visited) code += 1;
        if ((upCell != null) && !upCell.visited) code += 2;
        if ((rightCell != null) && !rightCell.visited) code += 4;
        if ((downCell != null) && !downCell.visited) code += 8;

        switch (code) {
            case 0:
                return null;
            case 1:
                return leftCell;
            case 2:
                return upCell;
            case 3:
                switch ((int) (Math.random() * 2)) {
                    case 0:
                        return leftCell;
                    case 1:
                        return upCell;
                }
                break;
            case 4:
                return rightCell;
            case 5:
                switch ((int) (Math.random() * 2)) {
                    case 0:
                        return leftCell;
                    case 1:
                        return rightCell;
                }
                break;
            case 6:
                switch ((int) (Math.random() * 2)) {
                    case 0:
                        return upCell;
                    case 1:
                        return rightCell;
                }
                break;
            case 7:
                switch ((int) (Math.random() * 3)) {
                    case 0:
                        return leftCell;
                    case 1:
                        return upCell;
                    case 2:
                        return rightCell;
                }
                break;
            case 8:
                return downCell;
            case 9:
                switch ((int) (Math.random() * 2)) {
                    case 0:
                        return leftCell;
                    case 1:
                        return downCell;
                }
                break;
            case 10:
                switch ((int) (Math.random() * 2)) {
                    case 0:
                        return upCell;
                    case 1:
                        return downCell;
                }
                break;
            case 11:
                switch ((int) (Math.random() * 3)) {
                    case 0:
                        return leftCell;
                    case 1:
                        return upCell;
                    case 2:
                        return downCell;
                }
                break;
            case 12:
                switch ((int) (Math.random() * 2)) {
                    case 0:
                        return rightCell;
                    case 1:
                        return downCell;
                }
                break;
            case 13:
                switch ((int) (Math.random() * 3)) {
                    case 0:
                        return leftCell;
                    case 1:
                        return rightCell;
                    case 2:
                        return downCell;
                }
                break;
            case 14:
                switch ((int) (Math.random() * 3)) {
                    case 0:
                        return upCell;
                    case 1:
                        return rightCell;
                    case 2:
                        return downCell;
                }
                break;
            case 15:
                switch ((int) (Math.random() * 4)) {
                    case 0:
                        return leftCell;
                    case 1:
                        return upCell;
                    case 2:
                        return rightCell;
                    case 3:
                        return downCell;
                }
                break;
        }
        return null;
    }

    Cell getUnvisitedNeighborPF() {
        int code = 0;
        if ((leftCell != null) && !leftCell.visited && !leftWall) code += 1;
        if ((upCell != null) && !upCell.visited && !upWall) code += 2;
        if ((rightCell != null) && !rightCell.visited && !rightWall) code += 4;
        if ((downCell != null) && !downCell.visited && !downWall) code += 8;

        switch (code) {
            case 0:
                return null;
            case 1:
                return leftCell;
            case 2:
                return upCell;
            case 3:
                switch ((int) (Math.random() * 2)) {
                    case 0:
                        return leftCell;
                    case 1:
                        return upCell;
                }
                break;
            case 4:
                return rightCell;
            case 5:
                switch ((int) (Math.random() * 2)) {
                    case 0:
                        return leftCell;
                    case 1:
                        return rightCell;
                }
                break;
            case 6:
                switch ((int) (Math.random() * 2)) {
                    case 0:
                        return upCell;
                    case 1:
                        return rightCell;
                }
                break;
            case 7:
                switch ((int) (Math.random() * 3)) {
                    case 0:
                        return leftCell;
                    case 1:
                        return upCell;
                    case 2:
                        return rightCell;
                }
                break;
            case 8:
                return downCell;
            case 9:
                switch ((int) (Math.random() * 2)) {
                    case 0:
                        return leftCell;
                    case 1:
                        return downCell;
                }
                break;
            case 10:
                switch ((int) (Math.random() * 2)) {
                    case 0:
                        return upCell;
                    case 1:
                        return downCell;
                }
                break;
            case 11:
                switch ((int) (Math.random() * 3)) {
                    case 0:
                        return leftCell;
                    case 1:
                        return upCell;
                    case 2:
                        return downCell;
                }
                break;
            case 12:
                switch ((int) (Math.random() * 2)) {
                    case 0:
                        return rightCell;
                    case 1:
                        return downCell;
                }
                break;
            case 13:
                switch ((int) (Math.random() * 3)) {
                    case 0:
                        return leftCell;
                    case 1:
                        return rightCell;
                    case 2:
                        return downCell;
                }
                break;
            case 14:
                switch ((int) (Math.random() * 3)) {
                    case 0:
                        return upCell;
                    case 1:
                        return rightCell;
                    case 2:
                        return downCell;
                }
                break;
            case 15:
                switch ((int) (Math.random() * 4)) {
                    case 0:
                        return leftCell;
                    case 1:
                        return upCell;
                    case 2:
                        return rightCell;
                    case 3:
                        return downCell;
                }
                break;
        }
        return null;
    }

    @Override
    public String toString() {
        return "(" + coord_i + "," + coord_j + ")";
    }


}

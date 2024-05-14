package src.com.game.tetris.classes;

import java.util.Arrays;

public class Board {
    
    // ATTRIBUTES
    private int[][] grid;
    private int rows;
    private int cols;

    private Piece currentPiece;

    // CONSTRUCTOR
    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new int[rows][cols];
    }

    // METHODS

    public void setCurrentPiece(Piece piece) {
        this.currentPiece = piece;
    }

    public Piece getCurrentPiece() {
        return this.currentPiece;
    }

    public void placePiece(int posX, int posY) {
        int[][] shape = currentPiece.getShape();

        for (int i=0;i<shape.length;i++) {
            for (int j=0;j<shape[i].length;j++) {
                if (shape[i][j] != 0) {
                    grid[posX+i][posY+j] = 1;
                }
            }
        }
    }

    private boolean isOccupied(int posX, int posY) {
        return grid[posX][posY] != 0;
    }

    public boolean moveDown() {
        if (currentPiece != null) {
            int posX = currentPiece.getPosX();
            int posY = currentPiece.getPosY();
            if (!isOccupied(posX, posY+1)) {
                currentPiece.moveDown();
                return true;
            }
        }
        return false;
    }

    public boolean moveLeft() {
        if (currentPiece != null) {
            int posX = currentPiece.getPosX();
            int posY = currentPiece.getPosY();
            if (!isOccupied(posX-1, posY)) {
                currentPiece.moveLeft();
                return true;
            }
        }
        return false;
    }

    public boolean moveRight() {
        if (currentPiece != null) {
            int posX = currentPiece.getPosX();
            int posY = currentPiece.getPosY();
            if (!isOccupied(posX+1, posY)) {
                placePiece(posX+1, posY);
                return true;
            }
        }
        return false;
    }

    public void removeCompleteLines() {
        for (int i = rows-1;i >= 0;i--) {
            boolean isComplete = true;
            for (int j=0;j<cols;j++) {
                if (grid[i][j] == 0) {
                    isComplete = false;
                    break;
                }
            }
            if (isComplete) {
                for (int k = i;k>0;k--) {
                    System.arraycopy(grid[k-1], 0, grid[k], 0, cols);
                }
                Arrays.fill(grid[0], 0);
                i++;
            }
        }
    }
}
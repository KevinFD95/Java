package src.com.game.tetris.classes;

import java.awt.Color;

public class Piece {

    // ATTRIBUTES
    private int[][] shape;
    private int posX;
    private int posY;
    private Color color;

    // CONSTRUCTOR
    public Piece(int[][] shape, int posX, int posY, Color color) {
        this.shape = shape;
        this.posX = posX;
        this.posY = posY;
        this.color = color;
    }

    // METHODS

    public void moveDown() {
        posY++;
    }

    public void moveLeft() {
        posX--;
    }

    public void moveRight() {
        posX++;
    }

    public void rotateClockWise() {
        int[][] rotatedShape = new int[shape[0].length][shape.length];

        for (int i=0;i<shape.length;i++) {
            for (int j=0;j<shape[0].length;j++) {
                rotatedShape[j][shape.length-1-i] = shape[i][j];
            }
        }

        shape = rotatedShape;
    }

    public void rotateCounterClockWise() {
        int[][] rotatedShape = new int[shape[0].length][shape.length];

        for (int i=0;i<shape.length;i++) {
            for (int j=0;j<shape[0].length;j++) {
                rotatedShape[shape[0].length-1-j][i] = shape[i][j];
            }
        }

        shape = rotatedShape;
    }

    public int[][] getShape() {
        return this.shape;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public Color getColor() {
        return this.color;
    }
}
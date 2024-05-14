package src.com.game.tetris.classes.PieceClasses;

import java.awt.Color;
import src.com.game.tetris.classes.Piece;

public class TPiece extends Piece {

    // CONSTRUCTOR
    public TPiece(int posX, int posY) {
        super(new int[][] {
            {0, 1, 0},
            {1, 1, 1}
        }, posX, posY, Color.MAGENTA);
    }
}
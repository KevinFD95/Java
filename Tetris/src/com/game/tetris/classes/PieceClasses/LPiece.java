package src.com.game.tetris.classes.PieceClasses;

import java.awt.Color;
import src.com.game.tetris.classes.Piece;

public class LPiece extends Piece {
    
    // CONSTRUCTOR
    public LPiece(int posX, int posY) {
        super(new int[][] {
            {1, 0},
            {1, 0},
            {1, 1}
        }, posX, posY, Color.ORANGE);
    }
}
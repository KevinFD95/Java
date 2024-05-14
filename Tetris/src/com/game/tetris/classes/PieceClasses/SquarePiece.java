package src.com.game.tetris.classes.PieceClasses;

import java.awt.Color;
import src.com.game.tetris.classes.Piece;

public class SquarePiece extends Piece {
    
    // CONSTRUCTOR
    public SquarePiece(int posX, int posY) {
        super(new int[][] {
            {1, 1},
            {1, 1}
        }, posX, posY, Color.YELLOW);
    }
}
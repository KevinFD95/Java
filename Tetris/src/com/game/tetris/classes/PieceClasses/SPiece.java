package src.com.game.tetris.classes.PieceClasses;

import java.awt.Color;
import src.com.game.tetris.classes.Piece;

public class SPiece extends Piece {
    
    // CONSTRUCTOR
    public SPiece(int posX, int posY) {
        super(new int[][] {
            {0, 1, 1},
            {1, 1, 0}
        }, posX, posY, Color.GREEN);
    }
}
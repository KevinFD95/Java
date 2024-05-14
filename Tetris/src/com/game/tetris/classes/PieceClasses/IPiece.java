package src.com.game.tetris.classes.PieceClasses;

import java.awt.Color;
import src.com.game.tetris.classes.Piece;

public class IPiece extends Piece {
    
    // CONSTRUCTOR
    public IPiece(int posX, int posY) {
        super(new int[][] {
            {1},
            {1},
            {1},
            {1}
        }, posX, posY, Color.CYAN);
    }
}
package classes;

import java.awt.*;

public class Score extends Rectangle {

    private static int GAME_WIDTH;
    private static int GAME_HEIGHT;
    protected int player1;
    protected int player2;

    public Score(int GAME_WIDTH, int GAME_HEIGHT) {
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Ink free", Font.BOLD, 60));
        g.drawLine((GAME_WIDTH / 2), 0, (GAME_WIDTH/2), GAME_HEIGHT);

        g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (GAME_WIDTH/2) - 85, 50);
        g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (GAME_WIDTH/2) + 15, 50);
    }
}
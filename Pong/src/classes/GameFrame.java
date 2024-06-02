package classes;

import java.awt.Color;

import javax.swing.*;

public class GameFrame extends JFrame {

    private TitlePanel titlePanel;
    private GamePanel gamePanel;

    public GameFrame() {

        titlePanel = new TitlePanel(this);
        gamePanel = new GamePanel();
        
        this.setTitle("Pong Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        showTitlePanel();

        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.BLACK);
    }

    public void showTitlePanel() {
        this.getContentPane().removeAll();
        this.add(titlePanel);
        this.revalidate();
        this.repaint();
        this.setLocationRelativeTo(null);
    }

    public void startGame() {
        this.getContentPane().removeAll();
        this.getContentPane();
        this.add(gamePanel);
        gamePanel.requestFocusInWindow();
        gamePanel.startGame();
        this.revalidate();
        this.repaint();
    }
}
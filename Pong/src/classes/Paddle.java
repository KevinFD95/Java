package classes;

import java.awt.*;
import java.awt.event.*;

public class Paddle extends Rectangle {

    private int id;
    private int yVelocity;
    protected int speed = 5;
    private PausePanel pausePanel;

    public Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;
    }

    public void keyPressed(KeyEvent e) {
        switch(id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-speed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(speed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    pausePanel = new PausePanel();
                    pausePanel.showMenu();
                }
            break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_I) {
                    setYDirection(-speed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_K) {
                    setYDirection(speed);
                    move();
                }
            break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch(id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                    move();
                } 
            break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_I) {
                    setYDirection(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_K) {
                    setYDirection(0);
                    move();
                }
            break;
        }
    }

    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }

    public void move() {
        y = y + yVelocity;
    }

    public void draw(Graphics g) {

        if (id == 1) {
            g.setColor(Color.BLUE);
        }
        else {
            g.setColor(Color.RED);
        }
        g.fillRect(x, y, width, height);
    }
    
}
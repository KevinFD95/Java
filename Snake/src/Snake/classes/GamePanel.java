package Snake.classes;

import javax.swing.JPanel;
import javax.swing.*;
import java.util.Random;

import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener {
    
    // GAME BOARD AND CELL SIZE
    private static final int SCREEN_WIDTH = 750;
    private static final int SCREEN_HEIGHT = 750;
    private static final int UNIT_SIZE = 25;

    // GAME CELLS AMOUNT & COORDS
    private static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    private final int x[] = new int[GAME_UNITS];
    private final int y[] = new int[GAME_UNITS];

    // SNAKE & ENTITIES VARIABLES
    private int bodyParts;
    private char direction;
    private int applesEaten;
    private int appleX;
    private int appleY;
    
    // GAME VARIABLES
    private boolean running = false;
    private boolean paused = false;
    private static int DELAY;
    private Timer timer;
    private Random random;

    public GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        initGame();
    }

    private void initGame() {
        bodyParts = 2;
        applesEaten = 0;
        direction = 'R';
        newApple();
        running = true;
        paused = false;
        DELAY = 500;

        for (int i=0;i<bodyParts;i++) {
            x[i] = 0;
            y[i] = 0;
        }

        if (timer != null) {
            timer.stop();
        }

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void startGame() {
        initGame();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        if (running) {

            /* for (int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) { // Optional Grid
                g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
            } */

            if (!paused) {
                g.setColor(Color.RED);
                g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
    
                for (int i=0;i<bodyParts;i++) {
                    if (i == 0) {
                        g.setColor(Color.GREEN);
                        g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                    }
                    else {
                        g.setColor(new Color(45,180,0));
                        g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                    }
                }
                showScore(g);
            }  
            else {
                showPaused(g);
            }
        }
        else {
            gameOver(g);
        }
    }

    public void newApple() {
        boolean appleOnSnake;
        do {
            appleOnSnake = false;
            appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
            appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;

            // CHECK IF THE APPLE IS ON THE SNAKE
            for (int i=0;i<bodyParts;i++) {
                if (x[i] == appleX && y[i] == appleY) {
                    appleOnSnake = true;
                    break;
                }
            }
        } while (appleOnSnake);
    }

    public void move() {
        for (int i=bodyParts;i>0;i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
            break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
            break;
            case 'L':
            x[0] = x[0] - UNIT_SIZE;
            break;
            case 'R':
            x[0] = x[0] + UNIT_SIZE;
            break;
        }
    }

    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;

            // INCREASE SPEED GAME WITH SCORE
            if ((applesEaten % 10) == 0 && DELAY >= 20) {
                DELAY -= 10;
                timer.setDelay(DELAY);
            }
            
            // System.out.println(DELAY); // SHOWS NEW SPEED
            newApple();
        }
    }

    public void checkCollisions() {
        // CHECKS IF HEAD COLLIDES WITH BODY
        for (int i=bodyParts;i>0;i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }

        // CHECKS IF HEAD TOUCHES LEFT BORDER
        if (x[0] < 0) {
            running = false;
        }

        // CHECKS IF HEAD TOUCHES RIGHT BORDER
        if (x[0] > SCREEN_WIDTH) {
            running = false;
        }

        // CHECKS IF HEAD TOUCHES TOP BORDER
        if (y[0] < 0) {
            running = false;
        }

        // CHECKS IF HEAD TOUCHES BOTTOM BORDER
        if (y[0] > SCREEN_HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    public void showScore(Graphics g) {
        // SCORE TEXT
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
    }
    
    public void showPaused (Graphics g) {
        showScore(g);

        // PAUSED TEXT
        paused = true;
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Paused", (SCREEN_WIDTH - metrics2.stringWidth("Paused"))/2, SCREEN_HEIGHT/2);
    }

    public void gameOver(Graphics g) {
        showScore(g);
        
        // GAME OVER TEXT
        paused = false;
        running = false;
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                break;
                case KeyEvent.VK_S:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                break;
                case KeyEvent.VK_A:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                break;
                case KeyEvent.VK_D:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                break;
                case KeyEvent.VK_ESCAPE:
                    if (running && !paused) {
                        paused = true;
                        running = false;
                        timer.stop();
                        showPaused(getGraphics());
                    }
                    else if (paused) {
                        paused = false;
                        running = true;
                        timer.start();
                    }
                    else if (!running && !paused) {
                        startGame();
                    }
                break;
            }
        }
    }
}
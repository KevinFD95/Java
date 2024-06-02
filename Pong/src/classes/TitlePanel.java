package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TitlePanel  extends JPanel {

    private JButton startButton;
    private JButton exitButton;

    public TitlePanel(GameFrame gameFrame) {
        this.setPreferredSize(GamePanel.SCREEN_SIZE);
        this.setBackground(Color.BLACK);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Pong Game", JLabel.CENTER);
        titleLabel.setFont(new Font("Ink Free", Font.BOLD, 75));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton = new JButton("Start");
        customizeButton(startButton, Color.WHITE);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.startGame();
            }
        });

        exitButton = new JButton("Exit");
        customizeButton(exitButton, Color.WHITE);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.add(Box.createVerticalGlue());
        this.add(titleLabel);
        this.add(Box.createVerticalGlue());
        this.add(startButton);
        this.add(Box.createVerticalBox());
        this.add(exitButton);
        this.add(Box.createVerticalGlue());
    }

    private void customizeButton(JButton button, Color color) {
        button.setFont(new Font("Ink Free", Font.BOLD, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setForeground(color);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(color);
            }
        });
    }

}
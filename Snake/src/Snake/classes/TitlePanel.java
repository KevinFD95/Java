package Snake.classes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class TitlePanel extends JPanel {
    
    public TitlePanel(GameFrame gameFrame) {
        this.setPreferredSize(new Dimension(750, 750));
        this.setBackground(Color.BLACK);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Snake Game");
        titleLabel.setFont(new Font("Ink Free", Font.BOLD, 75));
        titleLabel.setForeground(Color.GREEN);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton playButton = new JButton("Play");
        customizeButton(playButton, Color.GREEN);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.startGame();
            }
        });

        JButton exitButton = new JButton("Exit");
        customizeButton(exitButton, Color.RED);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.add(Box.createVerticalGlue());
        this.add(titleLabel);
        this.add(Box.createRigidArea(new Dimension(0, 50)));
        this.add(playButton);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
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
                button.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(color);
            }
        });
    }
}
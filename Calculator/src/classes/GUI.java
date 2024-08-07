package classes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class GUI extends JFrame {

    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] operatorButtons;
    private JButton equalsButton;
    private JButton clearButton;

    private Font font = new Font("Arial", Font.BOLD, 24);

    private double num1;
    private double num2;
    private String operator;

    private Calculator calculator;

    public GUI() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 500));
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setPreferredSize(new Dimension(50, 50));
        display.setFont(font);
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4,4));

        numberButtons = new JButton[10];
        for (int i=0 ; i<10 ; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(font);
            numberButtons[i].setPreferredSize(new Dimension(100, 100));
            numberButtons[i].setBackground(Color.ORANGE);
            numberButtons[i].setBorder(new LineBorder(Color.BLACK));

            int number = i;
            numberButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    display.setText(display.getText() + number);
                }
            });
            buttonPanel.add(numberButtons[i]);
        }

        operatorButtons = new JButton[4];
        operatorButtons[0] = new JButton("+");
        operatorButtons[1] = new JButton("-");
        operatorButtons[2] = new JButton("*");
        operatorButtons[3] = new JButton("/");

        for (int i=0 ; i<4 ; i++) {
            int index = i;
            operatorButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    num1 = Double.parseDouble(display.getText());
                    operator = operatorButtons[index].getText();
                    display.setText(display.getText() + " " + operator + " ");
                }
            });
            buttonPanel.add(operatorButtons[i]);
        }

        equalsButton = new JButton("=");
        equalsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] parts = display.getText().split(" ");
                if (parts.length == 3) {
                    num1 = Double.parseDouble(parts[0]);
                    operator = parts[1];
                    num2 = Double.parseDouble(parts[2]);

                    double result = 0;
                    calculator = new Calculator(num1, num2);

                    switch (operator) {
                        case "+":
                            result = calculator.plus();
                            break;
                        case "-":
                            result = calculator.minus();
                            break;
                        case "*":
                            result = calculator.multi();
                            break;
                        case "/":
                            result = calculator.split();
                            break;
                    }
                    display.setText(display.getText() + " = " + result);
                } else {
                    display.setText("Error");
                }
                
            }
        });
        buttonPanel.add(equalsButton);

        clearButton = new JButton("C");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display.setText("");
                num1 = 0;
                num2 = 0;
                operator = "";
            }
        });
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
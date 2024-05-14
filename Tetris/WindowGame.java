import javax.swing.JFrame;

public class WindowGame {

    public static final int WIDTH = 445;
    public static final int HEIGH = 640;

    private Board board;
    private JFrame window;
    //private Title title;

    public WindowGame() {
        window = new JFrame("Tetris");
        window.setSize(WIDTH, HEIGH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(false);

        board = new Board();
        //title = new Title(this);

        window.addKeyListener(board);
        //window.addKeyListener(title);

        window.add(board);
        //window.add(title);
        window.setVisible(true);
    }

    /*public void startTetris() {
        window.remove(title);
        window.addMouseMotionListener(board);
        window.addMouseListener(board);
        window.add(board);
        board.startGame();
        window.revalidate();
    }*/

    public static void main(String[] args) {
        new WindowGame();
    }
}
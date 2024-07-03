package classes;

public class AI {
    
    private Paddle paddle;
    private Ball ball;

    public AI(Paddle paddle, Ball ball) {
        this.paddle = paddle;
        this.ball = ball;
    }

    public void move() {
        if (ball.y + ball.height < paddle.y + (paddle.height/2)) {
            paddle.setYDirection(-paddle.speed);
        }
        else if (ball.y > paddle.y + (paddle.height/2)) {
            paddle.setYDirection(paddle.speed);
        }
        else {
            paddle.setYDirection(0);
        }
        paddle.move();
    }
}
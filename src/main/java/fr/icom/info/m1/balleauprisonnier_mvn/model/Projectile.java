package fr.icom.info.m1.balleauprisonnier_mvn.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;

public class Projectile {

    double x;
    double y;
    double angle;
    double speedX;
    double speedY;

    Sprite sprite;

    GraphicsContext graphicsContext;

    javafx.scene.image.Image ball;

    String side;




    public Projectile(GraphicsContext gc, double x, double y , double angle, double speed, String side){

        this.graphicsContext = gc;
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.speedX = speed;
        this.speedY = speed;
        this.side = side;
        ball = new javafx.scene.image.Image("assets/ball.png");
    }

    public void display()
    {
        move();
        graphicsContext.save(); // saves the current state on stack, including the current transform
        rotate(graphicsContext, angle, x + ball.getWidth() / 2, y + ball.getHeight() / 2);
        graphicsContext.drawImage(ball, x, y);
        graphicsContext.restore(); // back to original state (before rotation)
    }

    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    void move(){
        int rotation;
        if(side.equals("top")){
            rotation = 90;
        }
        else{
            rotation = 270;
        }
        // Collision with the walls
        if (x < 0 || x > 600 - ball.getWidth()) {
            angle = -angle;
        }
        if (y < 0 || y > 600 - ball.getHeight()) {
            // give the ball to the closest player
            String side = y < 0 ? "top" : "bottom";
            Field.getInstance().getClosestPlayer(x, y, side).setBall(true);
        }
        final double[] vector = new double[2];
        vector[0] = Math.cos(Math.toRadians(angle+rotation));
        vector[1] = Math.sin(Math.toRadians(angle+rotation));
        x += vector[0] * speedX;
        y += vector[1] * speedY;
    }

    public void updatePosition(double x, double y, double angle){
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public void setSpeed(double speed){
        this.speedX = speed;
        this.speedY = speed;
    }

    public void setAngle(double angle){
        this.angle = angle;
    }
}

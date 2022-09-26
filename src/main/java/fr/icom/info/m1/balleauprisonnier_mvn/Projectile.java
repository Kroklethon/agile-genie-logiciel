package fr.icom.info.m1.balleauprisonnier_fx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;

public class Projectile {

    double x;
    double y;
    double angle;
    double speed;

    Sprite sprite;

    GraphicsContext graphicsContext;

    javafx.scene.image.Image ball;




    public Projectile(GraphicsContext gc, double x, double y , double angle, double speed){

        this.graphicsContext = gc;
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.speed = speed;
        ball = new javafx.scene.image.Image("assets/ball.png");
    }

    void display()
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
        final double[] vector = new double[2];
        vector[0] = Math.cos(Math.toRadians(angle+90));
        vector[1] = Math.sin(Math.toRadians(angle+90));
        System.out.println("vector[0] = " + vector[0] + " vector[1] = " + vector[1]);
        x += vector[0] * speed;
        y += vector[1] * speed;
    }
}

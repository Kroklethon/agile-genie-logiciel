package fr.icom.info.m1.balleauprisonnier_mvn.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class AIPlayer implements Player{

    private double x;       // position horizontale du joueur
	private double y; 	  // position verticale du joueur
	private double angle = 90; // rotation du joueur, devrait toujour être en 0 et 180
	private double step;    // pas d'un joueur
	private String side;
	String playerColor;
	  
    // On une image globale du joueur 
    Image directionArrow;
    public Sprite sprite;
    ImageView PlayerDirectionArrow;
    
    GraphicsContext graphicsContext;
    boolean hasTheBall = false;
    Projectile ball;
    /**
     * Constructeur du Joueur
     * 
     * @param gc ContextGraphic dans lequel on va afficher le joueur
     * @param color couleur du joueur
     * @param yInit position verticale
     */

    public AIPlayer(GraphicsContext gc, String color, int xInit, int yInit, String side){
        // Tous les joueurs commencent au centre du canvas, 
	    x = xInit;               
	    y = yInit;
	    graphicsContext = gc;
	    playerColor=color;
		this.side = side;
	    angle = 0;
        if(side=="top"){
        	directionArrow = new Image("assets/PlayerArrowDown.png");
		}
		else{
			directionArrow = new Image("assets/PlayerArrowUp.png");
		}
        
        PlayerDirectionArrow = new ImageView();
        PlayerDirectionArrow.setImage(directionArrow);
        PlayerDirectionArrow.setFitWidth(10);
        PlayerDirectionArrow.setPreserveRatio(true);
        PlayerDirectionArrow.setSmooth(true);
        PlayerDirectionArrow.setCache(true);

        Image tilesheetImage = new Image("assets/orc.png");
        sprite = new Sprite(tilesheetImage, 0,0, Duration.seconds(.2), side);
        sprite.setX(x);
        sprite.setY(y);
        step = 2;	
    }
    public void display()
    {
        graphicsContext.save(); // saves the current state on stack, including the current transform
        rotate(graphicsContext, angle, x + directionArrow.getWidth() / 2, y + directionArrow.getHeight() / 2);
        graphicsContext.drawImage(directionArrow, x, y);
        graphicsContext.restore(); // back to original state (before rotation)
        if(ball != null){
          ball.updatePosition(x, y, angle);
        }
    }

    public void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
    
    /**
     *  Deplacement du joueur vers la gauche, on cantonne le joueur sur le plateau de jeu
     */
   
    public void moveLeft()
    {	    
      if (x > 10 && x < 520) 
      {
          spriteAnimate();
          x -= step;
      }
    }

    /**
     *  Deplacement du joueur vers la droite
     */
    public void moveRight()
    {
      if (x > 10 && x < 520) 
      {
          spriteAnimate();
          x += step;
      }
    }

    
    /**
     *  Rotation du joueur vers la gauche
     */
    public void turnLeft()
    {
      if (angle > 0 && angle < 180) 
      {
          angle += 1;
      }
      else {
          angle += 1;
      }

    }

    
    /**
     *  Rotation du joueur vers la droite
     */
    public void turnRight()
    {
      if (angle > 0 && angle < 180) 
      {
          angle -=1;
      }
      else {
          angle -= 1;
      }
    }


    public Projectile shoot(){
        sprite.playShoot();
        ball.setSpeed(3);
        if(side == "bottom"){
            ball.setAngle(angle);
        }
        else{
            ball.setAngle(angle+ 180);
        }
        Projectile tmpball = ball;
        ball = null;
        return tmpball;
    }

    public void spriteAnimate(){
          //System.out.println("Animating sprite");
        if(!sprite.isRunning) {sprite.playContinuously();}
        sprite.setX(x);
        sprite.setY(y);
    }

    public void setBall(Projectile ball){
        this.ball = ball;
      }
    public Projectile getBall(){
        return ball;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getAngle(){
        return angle;
    }

    public Sprite getSprite(){
        return sprite;
    }

    public String getPlayerColor(){
        return playerColor;
    }
}


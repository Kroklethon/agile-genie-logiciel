package fr.icom.info.m1.balleauprisonnier_mvn.model;


import javafx.scene.canvas.GraphicsContext;

/**
 * 
 * Classe gerant un joueur
 *
 */
public interface Player 
{
	  /**
	   *  Affichage du joueur
	   */
	  public void display();

	

	  public void rotate(GraphicsContext gc, double angle, double px, double py);
	  
	  /**
	   *  Deplacement du joueur vers la gauche, on cantonne le joueur sur le plateau de jeu
	   */
	 
	  public void moveLeft();

	  /**
	   *  Deplacement du joueur vers la droite
	   */
	  public void moveRight();

	  
	  /**
	   *  Rotation du joueur vers la gauche
	   */
	  public void turnLeft();

	  
	  /**
	   *  Rotation du joueur vers la droite
	   */
	  public void turnRight();


	  public Projectile shoot();

	  void spriteAnimate();

	  public void setBall(Projectile ball);

	  public Projectile getBall();

	  public double getX();

	  public double getY();

	  public double getAngle();

	  public Sprite getSprite();

	  public String getPlayerColor();

	  public String getSide();
}

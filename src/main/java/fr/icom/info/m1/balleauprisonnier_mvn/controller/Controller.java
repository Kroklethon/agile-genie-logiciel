package fr.icom.info.m1.balleauprisonnier_mvn.controller;


import java.util.ArrayList;

import fr.icom.info.m1.balleauprisonnier_mvn.model.*;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * Classe gerant le terrain de jeu.
 * 
 */
public class Controller{
	ArrayList<String> input = new ArrayList<String>();
	Field field = Field.getInstance();

	public Controller() {
			GraphicsContext gc = field.getGc();
			field.setOnKeyPressed(
					new EventHandler<KeyEvent>() {
						public void handle(KeyEvent e) {
							String code = e.getCode().toString();
							// only add once... prevent duplicates
							if (!input.contains(code))
								input.add(code);
						}
					});

			/**
			 * Event Listener du clavier
			 * quand une touche est relachee on l'enleve de la liste d'input
			 *
			 */
			field.setOnKeyReleased(
					new EventHandler<KeyEvent>() {
						public void handle(KeyEvent e) {
							String code = e.getCode().toString();
							input.remove(code);
						}
					});

			/**
			 *
			 * Boucle principale du jeu
			 *
			 * handle() est appelee a chaque rafraichissement de frame
			 * soit environ 60 fois par seconde.
			 *
			 */
			new AnimationTimer() {
				Player[] joueurs = field.getJoueursHumain();
				Player[] joueursIA = field.getJoueursIA();

				
				int width = field.width();
				int height = field.height();

				Projectile projectile = field.getProjectile();
				public void handle(long currentNanoTime) {
					// On nettoie le canvas a chaque frame
					gc.setFill(Color.LIGHTGRAY);
					gc.fillRect(0, 0, width, height);

					// Deplacement et affichage des joueurs
					for (int i = 0; i < joueurs.length; i++) {
						if(joueurs[i] != null){
							if (i == 0 && input.contains("LEFT")) {
								joueurs[i].moveLeft();
							}
							if (i == 0 && input.contains("RIGHT")) {
								joueurs[i].moveRight();
							}
							if (i == 0 && input.contains("UP")) {
								joueurs[i].turnLeft();
							}
							if (i == 0 && input.contains("DOWN")) {
								joueurs[i].turnRight();
							}
							if (i == 1 && input.contains("Q")) {
								joueurs[i].moveLeft();
							}
							if (i == 1 && input.contains("D")) {
								joueurs[i].moveRight();
							}
							if (i == 1 && input.contains("Z")) {
								joueurs[i].turnLeft();
							}
							if (i == 1 && input.contains("S")) {
								joueurs[i].turnRight();
							}
							if (i == 1 && input.contains("SPACE") && joueurs[i].getBall() != null) {
								projectile = joueurs[i].shoot();
							}
							if (i == 0 && input.contains("ENTER") && joueurs[i].getBall() != null) {
								projectile = joueurs[i].shoot();
							}


							if (projectile != null) {
								projectile.display();
							}
							for(Player joueur : joueursIA) {
								if( joueur != null){
									if(projectile.collide(joueur) && projectile.getLastOwner() != joueur && joueur.getBall() == null) {
										// On detruit le joueur
										field.destroyPlayer(joueur);
									}
								}
							}
							for(Player joueur : joueurs) {
								if( joueur != null){
									if(projectile.collide(joueur) && projectile.getLastOwner() != joueur && joueur.getBall() == null) {
										// On detruit le joueur
										field.destroyPlayer(joueur);
									}
								}
							}

							if(joueursIA[i] != null) {
								joueursIA[i].display();
							}
							if(joueurs[i] != null) {
								joueurs[i].display();
							}
							
						}
					}
				}
			}.start(); // On lance la boucle de rafraichissement

	}
}
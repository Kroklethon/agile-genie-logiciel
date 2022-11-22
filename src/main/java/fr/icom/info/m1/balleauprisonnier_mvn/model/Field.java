package fr.icom.info.m1.balleauprisonnier_mvn.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Classe gerant le terrain de jeu.
 *
 */
public class Field extends Canvas {

    private static Field instance;
    /** Joueurs */
    Player[] joueurs = new Player[2];
    Player [] joueurs2 = new Player[2];

    Projectile projectile;
    /** Couleurs possibles */
    String[] colorMap = new String[] {"blue", "green", "orange", "purple", "yellow"};
    /** Tableau traçant les evenements */


    final GraphicsContext gc;
    final int width = 600;
    final int height = 600;

    /**
     * Canvas dans lequel on va dessiner le jeu.
     *
     * @param scene Scene principale du jeu a laquelle on va ajouter notre Canvas
     * @param w largeur du canvas
     * @param h hauteur du canvas
     */
    private Field(int w, int h)
    {
        super(w, h);

        /** permet de capturer le focus et donc les evenements clavier et souris */
        this.setFocusTraversable(true);

        gc = this.getGraphicsContext2D();

        /** On initialise le terrain de jeu */
        joueurs[0] = new HumanPlayer(gc, colorMap[0], w/2, h-50, "bottom");
        joueurs[0].display();

        joueurs[1] = new AIPlayer(gc, colorMap[0], w/2, 20, "top");
        joueurs[1].display();

        joueurs2[0] = new AIPlayer(gc,colorMap[1], w/3, 20, "top");
        joueurs2[0].display();

        joueurs[0].setBall(true);
        projectile = joueurs[0].getBall();
    }

    public static Field getInstance(){
        if(instance == null){
            instance = new Field(600, 600);
        }
        return instance;
    }

    public GraphicsContext getGc(){
        return gc;
    }

    public Player[] getJoueurs() {
        return joueurs;
    }
    public Player[] getJoueurs2(){
        return joueurs2;
    }

    public int width() {
        return width;
    }
    public int height(){
        return height;
    }

    public Projectile getProjectile() {
        return projectile;
    }
}

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
    Player [] joueursTop = new Player[2];

    Player[] joueursHumain = new Player[2];
    Player[] joueursIA = new Player[2];


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
        joueurs[0] = new HumanPlayer(gc, colorMap[0], w/3, h-100, "bottom");
        joueurs[0].display();

        joueurs[1] = new AIPlayer(gc, colorMap[0], 2*w/3, h-100, "bottom");
        joueurs[1].display();

        joueursTop[1] = new AIPlayer(gc, colorMap[1], 2*w/3, 20, "top");
        joueursTop[1].display();

        joueursTop[0] = new HumanPlayer(gc,colorMap[1], w/3, 20, "top");
        joueursTop[0].display();

        joueurs[0].setBall(new Projectile(gc, joueurs[0].getX(), joueurs[0].getY(), joueurs[0].getAngle(), 0,  joueurs[0].getPlayerColor()));
        projectile = joueurs[0].getBall();

        for(Player p : joueursTop){
            if(p instanceof HumanPlayer){
                joueursHumain[0] = p;
            }
        }
        for(Player p : joueurs){
            if(p instanceof HumanPlayer){
                joueursHumain[1] = p;
            }
        }
        for(Player p : joueursTop){
            if(p instanceof AIPlayer){
                joueursIA[0] = p;
            }
        }
        for(Player p : joueurs){
            if(p instanceof AIPlayer){
                joueursIA[1] = p;
            }
        }
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
    public Player[] getjoueursTop(){
        return joueursTop;
    }

    public Player[] getJoueursHumain() {
        return joueursHumain;
    }

    public Player[] getJoueursIA() {
        return joueursIA;
    }

    public int width() {
        return width;
    }
    public int height(){
        return height;
    }

    public void setProjectile(Projectile projectile) {
        this.projectile = projectile;
    }

    public Projectile getProjectile() {
        return projectile;
    }

    public Player getClosestPlayer(double x, double y, String side){
        float distanceMin = -1;
        Player closestPlayer = null;
        if(side.equals("top")){
            for (Player p : Field.instance.getjoueursTop()) {
                if(p != null){
                    float distance = (float) Math.sqrt(Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2));
                    if(distanceMin == -1 || distance < distanceMin){
                        distanceMin = distance;
                        closestPlayer = p;
                    }
                }
            }
        }
        else{
            for (Player p : Field.instance.getJoueurs()) {
                if(p != null){
                    float distance = (float) Math.sqrt(Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2));
                    if(distanceMin == -1 || distance < distanceMin){
                        distanceMin = distance;
                        closestPlayer = p;
                    }
                }
            }
        }
        return closestPlayer;
    }

    public double distance(Player p1, Player p2){
        return (float) Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));   
    }

    public void destroyPlayer(Player p){
        for(int i = 0; i < joueursHumain.length; i++){
            if(joueursHumain[i] == p){
                joueursHumain[i].getSprite().setVisible(false);
                joueursHumain[i] = null;
            }
        }
        for(int i = 0; i < joueursIA.length; i++){
            if(joueursIA[i] == p){
                joueursIA[i].getSprite().setVisible(false);
                joueursIA[i] = null;
            }
        }
        for(int i = 0; i < joueurs.length; i++){
            if(joueurs[i] == p){
                joueurs[i].getSprite().setVisible(false);
                joueurs[i] = null;
            }
        }
        for(int i = 0; i < joueursTop.length; i++){
            if(joueursTop[i] == p){
                joueursTop[i].getSprite().setVisible(false);
                joueursTop[i] = null;
            }
        }
    }
}

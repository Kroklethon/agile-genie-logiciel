package fr.icom.info.m1.balleauprisonnier_mvn;

import javafx.scene.canvas.GraphicsContext;

public class HumanPlayer extends Player{

    public HumanPlayer(GraphicsContext gc, String color, int xInit, int yInit, String side){
        super(gc, color, xInit,yInit,side);
    }
}
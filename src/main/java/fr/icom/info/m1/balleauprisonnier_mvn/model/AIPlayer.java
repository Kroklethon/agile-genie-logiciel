package fr.icom.info.m1.balleauprisonnier_mvn.model;

import javafx.scene.canvas.GraphicsContext;

public class AIPlayer extends Player{
    public AIPlayer(GraphicsContext gc, String color, int xInit, int yInit, String side){
        super(gc, color, xInit,yInit,side);
    }
}


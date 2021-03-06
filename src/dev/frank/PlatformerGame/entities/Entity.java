/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.entities;

import dev.frank.PlatformerGame.Handler;
import java.awt.Rectangle;

/**
 *
 * @author Frank
 */
public abstract class Entity {
    
    public static final int DEFAULT_HEALTH = 10;
    protected Handler handler;
    protected float x,y; //float for smooth movement
    protected int width, height;
    protected int health;
    protected Rectangle bounds; //will be our bounding box
    

    public Entity(Handler handler, float x, float y, int width, int heigth) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = heigth;
        health = DEFAULT_HEALTH;
        
        bounds = new Rectangle(0,0, width, height); //upper left hand corener of entitiy image
        //width and height of actual entitiy   
    }
    

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    
}

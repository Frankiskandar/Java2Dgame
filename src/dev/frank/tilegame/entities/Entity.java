/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.tilegame.entities;

import dev.frank.tilegame.Game;
import java.awt.Graphics;

/**
 *
 * @author Frank
 */
public abstract class Entity {
    
    protected Game game;
    protected float x,y; //float for smooth movement
    protected int width, height;
    

    public Entity(Game game, float x, float y, int width, int heigth) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = heigth;
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
    
//    public void entityTest(){
//        Entity e = new Entity();
//        int num = 5;
//        e.setX(5);
//        assertEquals(num, e.getX(), "number doesnt match")
//    }
//    
    
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
}

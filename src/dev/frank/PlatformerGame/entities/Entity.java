/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.entities;

import dev.frank.PlatformerGame.Game;
import dev.frank.PlatformerGame.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Frank
 */
public abstract class Entity {
    
    protected Handler handler;
    protected float x,y; //float for smooth movement
    protected int width, height;
    protected Rectangle bounds; //will be our bounding box
    

    public Entity(Handler handler, float x, float y, int width, int heigth) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = heigth;
        
        bounds = new Rectangle(0,0, width, height); //upper left hand corener of entitiy image
        //width and height of actual entitiy
        
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
    
    
    //to return bounding rectangle
    //or the area around this entity
    //cover area collision of entity
    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset),bounds.width, bounds.height );
    }
    
    //to check collision
    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        // if 2 rectangle is collapsing and there is collision between these 2 entities, return true
        for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
            //dont check itself
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset,yOffset)))
                return true;
        }
        return false;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author Frank
 */
public class Animation {
    
    private int speed;
    private int index;
    private long lastTime , timer;
    private BufferedImage[] frames;
    
    public Animation(int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0; //to show the first image
        timer = 0;
        lastTime = System.currentTimeMillis(); //mili sec that has passed
    }
    
    public void tick() {
        //adding to timer the amount of milis that has passed since the last tick() method called
        // and this tck method called
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        
        if(timer > speed) {
            index++;
            timer = 0;
            if(index >= frames.length) // so it doesnt go out of bound
                index= 0;
        }
    }
    
    public BufferedImage getCurrentFrame() {
        return frames[index];
    }
    
}

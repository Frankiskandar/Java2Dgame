/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.tilegame.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author Frank
 */
public class Assets {
    
    private static final int width = 32, height = 32;
    
    public static BufferedImage player, dirt, grass, stone, tree, alien, sandmid;
    
    public static void init() {
        
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
        SpriteSheet enemy = new SpriteSheet(ImageLoader.loadImage("/textures/p1_spritesheet.png"));
        SpriteSheet ground = new SpriteSheet(ImageLoader.loadImage("/textures/tiles_spritesheet.png"));
        
        player = sheet.crop(0,0, width, height);
        dirt = sheet.crop(width, 0, width, height);
        grass = sheet.crop(width*2, 0, width, height);
        stone = sheet.crop(width*3, 0, width,height);
        tree = sheet.crop(0,height,width,height);
        alien = enemy.crop(0, 0, 72, 97);
        sandmid = ground.crop(288, 576, 70, 70);
        
    }
}

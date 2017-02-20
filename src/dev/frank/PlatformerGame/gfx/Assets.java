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
public class Assets {
    
    private static final int width = 32, height = 32;
    
    public static BufferedImage dirt, grass, stone, tree, alien, sandmid;
    
    //walking animation down of player
    public static BufferedImage[] player_down, player_up, player_left, player_right;
    public static BufferedImage[] alien_jump,alien_down, alien_left, alien_right, alien_stand;
    
    public static void init() {
        
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
        SpriteSheet alien_green = new SpriteSheet(ImageLoader.loadImage("/textures/p1_spritesheet.png"));
        SpriteSheet ground = new SpriteSheet(ImageLoader.loadImage("/textures/tiles_spritesheet.png"));
        
        player_down = new BufferedImage[2];
        player_up = new BufferedImage[2];
        player_left = new BufferedImage[2];
        player_right = new BufferedImage[2];
        
        player_down[0] = sheet.crop(width * 4, 0, width, height);
        player_down[1] = sheet.crop(width * 5, 0, width, height);
        player_up[0] = sheet.crop(width * 6, 0, width, height);
        player_up[1] = sheet.crop(width * 7, 0, width, height);
        player_right[0] = sheet.crop(width * 4, height, width, height);
        player_right[1] = sheet.crop(width * 5, height, width, height);
        player_left[0] = sheet.crop(width * 6, height, width, height);
        player_left[1] = sheet.crop(width * 7, height, width, height);
        
        
        //GREEN ALIEN PLAYER 1
        alien_stand = new BufferedImage[1];
        alien_jump = new BufferedImage[1];
        alien_down = new BufferedImage[1];
        alien_right = new BufferedImage[11];
        alien_left = new BufferedImage[11];

        alien_stand[0] = alien_green.crop(67, 196, 66, 92);
        
        alien_jump[0] = alien_green.crop(438, 93, 67, 94);
        
        alien_down[0] = alien_green.crop(438, 93, 67, 94);
        
        alien_right[0] = alien_green.crop(0,0 , 72, 97);
        alien_right[1] = alien_green.crop(73,0 , 72, 97);
        alien_right[2] = alien_green.crop(146,0 , 72, 97);
        alien_right[3] = alien_green.crop(0,98 , 72, 97);
        alien_right[4] = alien_green.crop(73,98 , 72, 97);
        alien_right[5] = alien_green.crop(146,98 , 72, 97);
        alien_right[6] = alien_green.crop(219,0 , 72, 97);
        alien_right[7] = alien_green.crop(292,0 , 72, 97);
        alien_right[8] = alien_green.crop(219,98 , 72, 97);
        alien_right[9] = alien_green.crop(365,0 , 72, 97);
        alien_right[10] = alien_green.crop(292,98 , 72, 97);
        
        alien_left[0] = alien_green.crop(0,0 , 72, 97);
        alien_left[1] = alien_green.crop(73,0 , 72, 97);
        alien_left[2] = alien_green.crop(146,0 , 72, 97);
        alien_left[3] = alien_green.crop(0,98 , 72, 97);
        alien_left[4] = alien_green.crop(73,98 , 72, 97);
        alien_left[5] = alien_green.crop(146,98 , 72, 97);
        alien_left[6] = alien_green.crop(219,0 , 72, 97);
        alien_left[7] = alien_green.crop(292,0 , 72, 97);
        alien_left[8] = alien_green.crop(219,98 , 72, 97);
        alien_left[9] = alien_green.crop(365,0 , 72, 97);
        alien_left[10] = alien_green.crop(292,98 , 72, 97);
        
            
        //player = sheet.crop(0,0, width, height);
        dirt = sheet.crop(width, 0, width, height);
        grass = sheet.crop(width*2, 0, width, height);
        stone = sheet.crop(width*3, 0, width,height);
        tree = sheet.crop(0,height,width,height);
        alien = alien_green.crop(0, 0, 72, 97);
        sandmid = ground.crop(288, 576, 70, 70);
        
    }
}
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
    
    public static BufferedImage dirt, grass, stone, tree,rock, alien, sandmid, exitSign, rightSign;
    
    //projectile
    public static BufferedImage fireball;
    
    //empty tile
    public static BufferedImage emptyTile, solidEmptyTile;
    
    //grass
    public static BufferedImage grassLeft, grassMid, grassRight;
    
    //water
    public static BufferedImage water;
    
    //box
    public static BufferedImage brownBox;
    
    //decoration
    public static BufferedImage bush;
    
    //walking animation down of player
    public static BufferedImage[] player_down, player_up, player_left, player_right;
    public static BufferedImage[] alien_jump,alien_down, alien_left, alien_right, alien_stand;
    public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;
    public static BufferedImage[] btn_start, btn_quit;
    
    
    public static void init() {
        
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
        
        SpriteSheet alien_green = new SpriteSheet(ImageLoader.loadImage("/textures/p1_spritesheet.png"));
        SpriteSheet ground = new SpriteSheet(ImageLoader.loadImage("/textures/tiles_spritesheet.png"));
        SpriteSheet item = new SpriteSheet(ImageLoader.loadImage("/textures/items_spritesheet.png"));
        SpriteSheet play_button1 = new SpriteSheet(ImageLoader.loadImage("/textures/play_button1.png"));
        SpriteSheet play_button2 = new SpriteSheet(ImageLoader.loadImage("/textures/play_button2.png"));
        
        //start button
        btn_start = new BufferedImage[2];
        btn_start[0] = play_button1.crop(0, 0, 190, 49);
	btn_start[1] = play_button2.crop(0, 0, 190, 45);
        
        SpriteSheet quit_button1 = new SpriteSheet(ImageLoader.loadImage("/textures/quit_button1.png"));
        SpriteSheet quit_button2 = new SpriteSheet(ImageLoader.loadImage("/textures/quit_button2.png"));
        
        btn_quit = new BufferedImage[2];
        btn_quit[0] = quit_button1.crop(0, 0, 190, 49);
	btn_quit[1] = quit_button2.crop(0, 0, 190, 45);
        
        
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
        
        zombie_down = new BufferedImage[2];
        zombie_up = new BufferedImage[2];
        zombie_left = new BufferedImage[2];
        zombie_right = new BufferedImage[2];

        zombie_down[0] = sheet.crop(width * 4, height * 2, width, height);
        zombie_down[1] = sheet.crop(width * 5, height * 2, width, height);
        zombie_up[0] = sheet.crop(width * 6, height * 2, width, height);
        zombie_up[1] = sheet.crop(width * 7, height * 2, width, height);
        zombie_right[0] = sheet.crop(width * 4, height * 3, width, height);
        zombie_right[1] = sheet.crop(width * 5, height * 3, width, height);
        zombie_left[0] = sheet.crop(width * 6, height * 3, width, height);
        zombie_left[1] = sheet.crop(width * 7, height * 3, width, height);
        
        
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
        //end alien green
            
        //player = sheet.crop(0,0, width, height);
        dirt = sheet.crop(width, 0, width, height);
        grass = sheet.crop(width*2, 0, width, height);
        stone = sheet.crop(width*3, 0, width,height);
        tree = sheet.crop(0,0,width,height*2);
        rock = sheet.crop(0, height * 2, width, height);
        alien = alien_green.crop(0, 0, 72, 97);
        sandmid = ground.crop(288, 576, 70, 70);
        
        //grass
        grassLeft = ground.crop(504, 648, 70, 70);
        grassMid = ground.crop(504, 576, 70, 70);
        grassRight = ground.crop(504, 504, 70, 70);
        
        
        //water
        water = ground.crop(432, 576, 70, 70);
        
        //object
        exitSign = ground.crop(288, 360, 70, 70);
        rightSign = ground.crop(288, 216, 70, 70);
        
        //emptytile
        emptyTile = alien_green.crop(286, 208, 32, 32);
        solidEmptyTile = alien_green.crop(286, 208, 32, 32);
        
        //box
        brownBox = ground.crop(0, 864, 70, 70);
        
        //decoration
        bush = item.crop(346, 144, 70, 70);
        
        //projectile
        fireball = item.crop(0, 435, 70, 70);
        
    }
}

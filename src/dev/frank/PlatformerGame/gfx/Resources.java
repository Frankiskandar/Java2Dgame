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
public class Resources {
    
    private static final int width = 32, height = 32;
    
    public static BufferedImage exitSign, rightSign;
    
    //projectile
    public static BufferedImage fireball;
    
    //empty tile
    public static BufferedImage emptyTile, solidEmptyTile;
    
    //ground
    public static BufferedImage grassLeft, grassMid, grassRight, grassCenter;
    public static BufferedImage stoneLeft, stoneMid, stoneRight, stoneCenter;
    public static BufferedImage snowLeft, snowMid, snowRight, snowCenter;
    
    //water
    public static BufferedImage water, lava;
    
    //box
    public static BufferedImage brownBox;
    
    //decoration
    public static BufferedImage bush;
    public static BufferedImage mushroomRed;
    public static BufferedImage mushroomBrown;
    public static BufferedImage cactus;
    public static BufferedImage windowTop;
    public static BufferedImage windowBottom;
    
    //hud
    public static BufferedImage heart;
    
    //walking animation of player
    public static BufferedImage[] alien_jump,alien_down, alien_left, alien_right, alien_stand, alien_stand_left;
    public static BufferedImage[] alien_jump_left;
    
    //menu buttons
    public static BufferedImage[] btn_start, btn_quit, btn_return;
    public static BufferedImage[] btn_level1, btn_level2;
    
    //enemy
    //spider
    public static BufferedImage spider_idle_right[], spider_dead_right[];
    public static BufferedImage spider_idle_left[], spider_dead_left[];
    public static BufferedImage spider_walk_left[], spider_walk_right[];
    
    //spinner
    public static BufferedImage spinner[];
    
    public static void init() {
              
        SpriteSheet ground = new SpriteSheet(ImageLoader.loadImage("/textures/tiles_spritesheet.png"));
        SpriteSheet item = new SpriteSheet(ImageLoader.loadImage("/textures/item/items_spritesheet.png"));
        SpriteSheet hud = new SpriteSheet(ImageLoader.loadImage("/textures/hud/hud_spritesheet.png"));
     
        //buttons
        //start button
        SpriteSheet play_button1 = new SpriteSheet(ImageLoader.loadImage("/textures/button/play_button1.png"));
        SpriteSheet play_button2 = new SpriteSheet(ImageLoader.loadImage("/textures/button/play_button2.png"));
        btn_start = new BufferedImage[2];
        btn_start[0] = play_button1.crop(0, 0, 190, 49);
	btn_start[1] = play_button2.crop(0, 0, 190, 45);
 
        //quit button
        SpriteSheet quit_button1 = new SpriteSheet(ImageLoader.loadImage("/textures/button/quit_button1.png"));
        SpriteSheet quit_button2 = new SpriteSheet(ImageLoader.loadImage("/textures/button/quit_button2.png"));
        btn_quit = new BufferedImage[2];
        btn_quit[0] = quit_button1.crop(0, 0, 190, 49);
	btn_quit[1] = quit_button2.crop(0, 0, 190, 45);
        
        //return button
        SpriteSheet return_button1 = new SpriteSheet(ImageLoader.loadImage("/textures/button/return_button1.png"));
        SpriteSheet return_button2 = new SpriteSheet(ImageLoader.loadImage("/textures/button/return_button2.png"));
        btn_return = new BufferedImage[2];
        btn_return[0] = return_button1.crop(0, 0, 190, 49);
        btn_return[1] = return_button2.crop(0, 0, 190, 45);
        
        //level 1 button
        SpriteSheet level1_button1 = new SpriteSheet(ImageLoader.loadImage("/textures/button/level1_button1.png"));
        SpriteSheet level1_button2 = new SpriteSheet(ImageLoader.loadImage("/textures/button/level1_button2.png"));
        btn_level1 = new BufferedImage[2];
        btn_level1[0] = level1_button1.crop(0, 0, 190, 49);
        btn_level1[1] = level1_button2.crop(0, 0, 190, 45);
        
        //level 2 button
        SpriteSheet level2_button1 = new SpriteSheet(ImageLoader.loadImage("/textures/button/level2_button1.png"));
        SpriteSheet level2_button2 = new SpriteSheet(ImageLoader.loadImage("/textures/button/level2_button2.png"));
        btn_level2 = new BufferedImage[2];
        btn_level2[0] = level2_button1.crop(0, 0, 190, 49);
        btn_level2[1] = level2_button2.crop(0, 0, 190, 45);
               
        //GREEN ALIEN PLAYER 1
        SpriteSheet alien_green = new SpriteSheet(ImageLoader.loadImage("/textures/player/p1_spritesheet.png"));
        SpriteSheet alien_green_left = new SpriteSheet(ImageLoader.loadImage("/textures/player/p1left_spritesheet.png"));
        SpriteSheet alien_jump_left1 = new SpriteSheet(ImageLoader.loadImage("/textures/player/p1_jump_left.png"));
        
        SpriteSheet alien_green_left1 = new SpriteSheet(ImageLoader.loadImage("/textures/player/p1_walk1_left.png"));
        SpriteSheet alien_green_left2 = new SpriteSheet(ImageLoader.loadImage("/textures/player/p1_walk2_left.png"));
        SpriteSheet alien_green_left3 = new SpriteSheet(ImageLoader.loadImage("/textures/player/p1_walk3_left.png"));
        SpriteSheet alien_green_left4 = new SpriteSheet(ImageLoader.loadImage("/textures/player/p1_walk4_left.png"));
        SpriteSheet alien_green_left5 = new SpriteSheet(ImageLoader.loadImage("/textures/player/p1_walk5_left.png"));
        SpriteSheet alien_green_left6 = new SpriteSheet(ImageLoader.loadImage("/textures/player/p1_walk6_left.png"));
        SpriteSheet alien_green_left7 = new SpriteSheet(ImageLoader.loadImage("/textures/player/p1_walk7_left.png"));
        SpriteSheet alien_green_left8 = new SpriteSheet(ImageLoader.loadImage("/textures/player/p1_walk8_left.png"));
        SpriteSheet alien_green_left9 = new SpriteSheet(ImageLoader.loadImage("/textures/player/p1_walk9_left.png"));
        SpriteSheet alien_green_left10 = new SpriteSheet(ImageLoader.loadImage("/textures/player/p1_walk10_left.png"));
        
        alien_stand = new BufferedImage[1];
        alien_stand_left = new BufferedImage[1];
        alien_jump = new BufferedImage[1];
        alien_jump_left = new BufferedImage[1];
        alien_down = new BufferedImage[1];
        alien_right = new BufferedImage[11];
        alien_left = new BufferedImage[10];

        alien_stand[0] = alien_green.crop(67, 196, 66, 92);
        alien_stand_left[0] = alien_green_left.crop(377, 196, 65, 89);
        
        alien_jump[0] = alien_green.crop(438, 93, 67, 94);
        alien_jump_left[0] = alien_jump_left1.crop(0, 0, 67, 94);
        
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
        
        alien_left[0] = alien_green_left1.crop(0,0 , 72, 97);
        alien_left[1] = alien_green_left2.crop(0,0 , 72, 97);
        alien_left[2] = alien_green_left3.crop(0,0 , 72, 97);
        alien_left[3] = alien_green_left4.crop(0,0 , 72, 97);
        alien_left[4] = alien_green_left5.crop(0,0 , 72, 97);
        alien_left[5] = alien_green_left6.crop(0,0 , 72, 97);
        alien_left[6] = alien_green_left7.crop(0,0 , 72, 97);
        alien_left[7] = alien_green_left8.crop(0,0 , 72, 97);
        alien_left[8] = alien_green_left9.crop(0,0 , 72, 97);
        alien_left[9] = alien_green_left10.crop(0,0 , 72, 97);

        
        
        //spider enemy
        SpriteSheet enemies = new SpriteSheet(ImageLoader.loadImage("/textures/enemy/enemies.png"));
        SpriteSheet spider_walk_right1 = new SpriteSheet(ImageLoader.loadImage("/textures/enemy/spider_walk_right1.png"));
        SpriteSheet spider_walk_right2 = new SpriteSheet(ImageLoader.loadImage("/textures/enemy/spider_walk_right2.png"));
        SpriteSheet spider_dead_right_sheet = new SpriteSheet(ImageLoader.loadImage("/textures/enemy/spider_dead_right.png"));
        SpriteSheet spider_idle_right_sheet = new SpriteSheet(ImageLoader.loadImage("/textures/enemy/spider_idle_right.png"));
        spider_idle_right = new BufferedImage[2];
        spider_idle_left = new BufferedImage[2];
        spider_walk_right = new BufferedImage[4];
        spider_walk_left = new BufferedImage[4];
        spider_dead_right = new BufferedImage[2];
        spider_dead_left = new BufferedImage[2];
        
        
        spider_idle_right[0] = spider_idle_right_sheet.crop(0, 0, 71, 45);
        spider_idle_right[1] = spider_idle_right_sheet.crop(0, 0, 71, 45);
        
        spider_idle_left[0] = enemies.crop(0, 326, 71, 45);
        spider_idle_left[1] = enemies.crop(0, 326, 71, 45);
        
        spider_walk_right[0] = spider_walk_right1.crop(0, 0, 72, 51);
        spider_walk_right[1] = spider_walk_right2.crop(0, 0, 77, 53);
        spider_walk_right[2] = spider_walk_right1.crop(0, 0, 72, 51);
        spider_walk_right[3] = spider_walk_right2.crop(0, 0, 77, 53);
        
        spider_walk_left[0] = enemies.crop(0, 90, 72, 51);
        spider_walk_left[1] = enemies.crop(0, 37, 77, 53);
        spider_walk_left[2] = enemies.crop(0, 90, 72, 51);
        spider_walk_left[3] = enemies.crop(0, 37, 77, 53);
        
        spider_dead_right[0] = spider_dead_right_sheet.crop(0, 0, 69, 51);
        spider_dead_right[1] = spider_dead_right_sheet.crop(0, 0, 69, 51);
        
        spider_dead_left[0] = enemies.crop(71, 282, 69, 51);
        spider_dead_left[1] = enemies.crop(71, 282, 69, 51);
        
        spinner = new BufferedImage[4];
        spinner[0] = enemies.crop(134, 372, 63, 62);
        spinner[1] = enemies.crop(196, 65, 61, 61);
        spinner[2] = enemies.crop(134, 372, 63, 62);
        spinner[3] = enemies.crop(196, 65, 61, 61);
        
        
        //grass
        grassLeft = ground.crop(504, 648, 70, 70);
        grassMid = ground.crop(504, 576, 70, 70);
        grassRight = ground.crop(504, 504, 70, 70);
        grassCenter = ground.crop(576, 864, 70, 70);
        
        //stone
        stoneLeft = ground.crop(72, 504, 70, 70);
        stoneMid = ground.crop(72, 432, 70, 70);
        stoneRight = ground.crop(72, 360, 70, 70);
        stoneCenter = ground.crop(144, 576, 70, 70);
        
        //snow
        snowLeft = ground.crop(144, 864, 70, 70);
        snowMid = ground.crop(144, 792, 70, 70);
        snowRight = ground.crop(144, 720, 70, 70);
        snowCenter = ground.crop(720, 864, 70, 70);
       
        //water
        water = ground.crop(432, 576, 70, 70);
        lava = ground.crop(432, 792, 70, 70);
        
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
        mushroomRed = item.crop(72, 219, 70, 70);
        mushroomBrown = item.crop(72, 291, 70, 70);
        cactus = item.crop(360, 216, 70, 70);
        
        SpriteSheet topWindow = new SpriteSheet(ImageLoader.loadImage("/textures/windowTop.png"));
        SpriteSheet bottomWindow = new SpriteSheet(ImageLoader.loadImage("/textures/windowBottom.png"));
        windowTop = topWindow.crop(0, 0, 70, 70);
        windowBottom = bottomWindow.crop(0, 0, 70, 70);

        
        
        //projectile
        fireball = item.crop(0, 435, 70, 70);
        
        //hud
        heart = hud.crop(0, 94, 53, 45);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.state;

import dev.frank.PlatformerGame.Game;
import dev.frank.PlatformerGame.Handler;
import dev.frank.PlatformerGame.entities.creatures.Creature;
import dev.frank.PlatformerGame.entities.creatures.Spider;
import dev.frank.PlatformerGame.entities.creatures.Player;
import dev.frank.PlatformerGame.entities.creatures.Spinner;
import dev.frank.PlatformerGame.gfx.Resources;
import dev.frank.PlatformerGame.gfx.ImageLoader;
import dev.frank.PlatformerGame.music.Music;
import static dev.frank.PlatformerGame.state.LevelTwo.LEVEL2_DEAD_Y_COORDINATE;
import dev.frank.PlatformerGame.tiles.Tile;
import dev.frank.PlatformerGame.worlds.World;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Frank
 */
public class LevelOne extends State {
    
    private Player player;
    private World world;
    private BufferedImage background;
    private Spider[] spider;
    public static final int ENEMY_NUMBER = 6;
    public static int LEVEL1_DEAD_Y_COORDINATE = 1300;
    public ArrayList<Spider> spider_array;
    public static final int PLAYER_SPAWN_X = 136, PLAYER_SPAWN_Y = 643;
    public static final float EXIT_X_POSITION = 8127;
    public static final float EXIT_Y_POSITION = 195;
    int show_instruction = 0;
    
    public LevelOne(Handler handler) { 
        super(handler);
        //load map from text file
        world = new World(handler, "res/worlds/world1ext.txt");
        handler.setWorld(world);
        
        background = ImageLoader.loadImage("/textures/bg_level1.png");
        player = new Player(handler,PLAYER_SPAWN_X,PLAYER_SPAWN_Y);
        spider_array = new ArrayList<>();
        spider = new Spider[ENEMY_NUMBER];
        spider[0] = new Spider(handler, 640, 643, 0);
        spider[1] = new Spider(handler, 2859, 451, 1);
        spider[2] = new Spider(handler, 4415, 387, 2);
        spider[3] = new Spider(handler, 5019, 643, 3);
        spider[4] = new Spider(handler, 6075, 343, 4);
        spider[5] = new Spider(handler, 7163, 643, 5);
          
        for (Spider e : spider) {
            spider_array.add(e);
        }
        
        Music.loop("bgm_level1"); 

    }

    @Override
    public void tick() {
        
        world.tick();
        boolean aimPlayer = false;
        
        for (Spider e : spider) {
            if (CheckPlayerNearby(player, e)) {
                aimPlayer = true;
            }
        }
        
        player.tick(spider_array, null);
        
        for (Spider e : spider) {
            if(aimPlayer) {
                e.tick(player);
            }
            //will stop if i dont include this
            else {
                e.tick(player);
            } 
        }
        
        //if the player at the finish position, change the state to finish state
        if (Math.abs(player.getX() - EXIT_X_POSITION) < 20 && Math.abs(player.getY() - EXIT_Y_POSITION) < 20) {
                State.setState(new FinishState(handler));
                Music.stop("bgm_level1");
                Music.loop("bgm_tropics");  
            }
        
        
        if(player.getY() > LEVEL2_DEAD_Y_COORDINATE) {
            player.dead = true;                     
        }
        
//        
//        for (Enemy e : enemy) {
//            if (e.dead)
//            {
//                enemies.remove(e);
//            }
//        }
         //has getinpu and move() in player
        
        //move forward automatically
        //game.getGameCamera().move(1, 0); // add 1 to x and y offset every tick 
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(background, 0, 0, null);
        world.render(g);
        player.render(g);
        for (Spider e : spider) {
            //System.out.println("enemy's health: "+ e.health);
            e.render(g);
        }
 
        //System.out.println(player.getX()+ " " + player.getY());
        //System.out.println("player's health= "+player.health);
        
        // draw heart (player's health) on the top left corner
        for (int i = 0; i < player.health; i++) {
            g.drawImage(Resources.heart, 60 * (i + 1) - 55, 25, 50, 50, null);
        }
        
        //if player is dead change the state to gameover state
        if (player.dead) {
            Music.stop("bgm_level1");
            Music.loop("bgm_tropics");
            State.setState(new GameOverState(handler));
        }
        if (show_instruction < 400){
            g.setFont(new Font("Helvetica", Font.BOLD, 30));
            g.setColor(Color.BLACK);
            g.drawString("Move With Arrow Keys and F to Shoot", 200, 190);
            show_instruction++;
        }  
        
    }
    
    // to check if player is nearby
    public boolean CheckPlayerNearby(Creature player, Spider e) {
        if (Math.abs(player.getX() - e.getX()) < 200 && Math.abs(player.getY() - e.getY()) < 20) {
            e.playerInLineofSight = Math.abs(player.getX() - e.getX()) >= 20;
        } else {
            e.playerInLineofSight = false;
        }

        return e.playerInLineofSight;
    }
     
}
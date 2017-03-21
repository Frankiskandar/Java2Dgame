/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.state;

import dev.frank.PlatformerGame.Game;
import dev.frank.PlatformerGame.Handler;
import dev.frank.PlatformerGame.entities.creatures.Creature;
import dev.frank.PlatformerGame.entities.creatures.Player;
import dev.frank.PlatformerGame.entities.creatures.Spinner;
import dev.frank.PlatformerGame.gfx.Assets;
import dev.frank.PlatformerGame.gfx.ImageLoader;
import dev.frank.PlatformerGame.music.Music;
import static dev.frank.PlatformerGame.state.Game2State.LEVEL2_DEAD_Y_COORDINATE;
import dev.frank.PlatformerGame.tiles.Tile;
import dev.frank.PlatformerGame.worlds.World;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Frank
 */
public class GameState extends State {
    
    private Player player;
    private World world;
    private BufferedImage bg;
    public static int LEVEL1_DEAD_Y_COORDINATE = 1300;
    public static final int PLAYER_SPAWN_X = 121, PLAYER_SPAWN_Y = 831;
    public static final float EXIT_X_POSITION = 2367;
    public static final float EXIT_Y_POSITION = 269;
 //   private Handler handler;
    
    public GameState(Handler handler) { 
        super(handler);
        world = new World(handler, "res/worlds/world1.txt");
        handler.setWorld(world);
        
        bg = ImageLoader.loadImage("/textures/bg_level1.png");
        player = new Player(handler,PLAYER_SPAWN_X,PLAYER_SPAWN_Y);
        
        Music.loop("bgm_level1"); 

    }

    @Override
    public void tick() {
        
        world.tick();   
        player.tick();
      
        //if the player at the finish position, change the state to finish state
        if (Math.abs(player.getX() - EXIT_X_POSITION) < 20 && Math.abs(player.getY() - EXIT_Y_POSITION) < 20) {
                State.setState(new FinishState(handler));
                Music.stop("bgm_level1");
                Music.loop("bgm_tropics");  
            }
        
        if(player.getY() > LEVEL2_DEAD_Y_COORDINATE) {
            player.dead = true;                     
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(bg, 0, 0, null);
        world.render(g);
        player.render(g);
        
 
        System.out.println(player.getX()+ " " + player.getY());
        System.out.println("player's health= "+player.health);
        //g.drawImage(Assets.exitSign, (int) (EXIT_X_POSITION - handler.getGameCamera().getxOffset()), (int) (EXIT_Y_POSITION - handler.getGameCamera().getyOffset()), 100, 100, null);
        // draw heart (player's health) on the top left corner
        for (int i = 0; i < player.health; i++) {
            g.drawImage(Assets.heart, 60 * (i + 1) - 55, 25, 50, 50, null);
        }
        g.drawImage(Assets.enemy_test, 697, 1155, 100, 100, null);
        
        //if player is dead change the state to gameover state
        if (player.dead) {
            Music.stop("bgm_level1");
            Music.loop("bgm_tropics");
            State.setState(new GameOverState(handler));
        }
        
    }

}

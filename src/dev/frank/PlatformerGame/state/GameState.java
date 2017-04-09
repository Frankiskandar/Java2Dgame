/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.state;

import dev.frank.PlatformerGame.Game;
import dev.frank.PlatformerGame.Handler;
import dev.frank.PlatformerGame.entities.creatures.Creature;
import dev.frank.PlatformerGame.entities.creatures.Enemy;
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
    private Enemy[] enemy;
    public static final int ENEMY_NUMBER = 4;
    public static int LEVEL1_DEAD_Y_COORDINATE = 1300;
    public ArrayList<Enemy> enemies;
    public static final int PLAYER_SPAWN_X = 121, PLAYER_SPAWN_Y = 831;
    public static final float EXIT_X_POSITION = 2367;
    public static final float EXIT_Y_POSITION = 269;
 //   private Handler handler;
    
    public GameState(Handler handler) { 
        super(handler);
        //load map from text file
        world = new World(handler, "res/worlds/world1.txt");
        handler.setWorld(world);
        
        bg = ImageLoader.loadImage("/textures/bg_level1.png");
        player = new Player(handler,PLAYER_SPAWN_X,PLAYER_SPAWN_Y);
        enemies = new ArrayList<>();
        enemy = new Enemy[ENEMY_NUMBER];
        enemy[0] = new Enemy(handler, 683, 1155, 0);
        enemy[1] = new Enemy(handler, 2245, 707, 1);
        enemy[2] = new Enemy(handler, 125, 579, 2);
        enemy[3] = new Enemy(handler, 848, 323, 3);
          
        for (Enemy e : enemy) {
            enemies.add(e);
        }
        
        Music.loop("bgm_level1"); 

    }

    @Override
    public void tick() {
        
        world.tick();
        boolean aimPlayer = false;
        
        for (Enemy e : enemy) {
            if (CheckPlayerNearby(player, e)) {
                aimPlayer = true;
            }
        }
        
        player.tick(enemies, null);
        
        for (Enemy e : enemy) {
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
        
        //buat maju doank tembak2an
        //game.getGameCamera().move(1, 0); // add 1 to x and y offset every tick 
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(bg, 0, 0, null);
        world.render(g);
        player.render(g);
        for (Enemy e : enemy) {
            System.out.println("enemy's health: "+ e.health);
            e.render(g);
        }
 
        System.out.println(player.getX()+ " " + player.getY());
        System.out.println("player's health= "+player.health);
        //g.drawImage(Assets.exitSign, (int) (EXIT_X_POSITION - handler.getGameCamera().getxOffset()), (int) (EXIT_Y_POSITION - handler.getGameCamera().getyOffset()), 100, 100, null);
        // draw heart (player's health) on the top left corner
        for (int i = 0; i < player.health; i++) {
            g.drawImage(Assets.heart, 60 * (i + 1) - 55, 25, 50, 50, null);
        }
        
        //if player is dead change the state to gameover state
        if (player.dead) {
            Music.stop("bgm_level1");
            Music.loop("bgm_tropics");
            State.setState(new GameOverState(handler));
        }
        
    }
    
    
    public boolean CheckPlayerNearby(Creature player, Enemy e) {
        if (Math.abs(player.getX() - e.getX()) < 200 && Math.abs(player.getY() - e.getY()) < 20) {
            e.playerInLineofSight = Math.abs(player.getX() - e.getX()) >= 20;
        } else {
            e.playerInLineofSight = false;
        }

        return e.playerInLineofSight;
    }
     
}

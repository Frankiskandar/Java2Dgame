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
import dev.frank.PlatformerGame.entities.statics.Tree;
import dev.frank.PlatformerGame.gfx.Assets;
import dev.frank.PlatformerGame.gfx.ImageLoader;
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
    public ArrayList<Enemy> enemies;
    
    public static final int ENEMY_SPINNER_NUMBER = 1;
    private Spinner[] spinner;
    public ArrayList<Spinner> spinners;
    
    
    
    public static final int PLAYER_SPAWN_X = 121, PLAYER_SPAWN_Y = 831;
    public static final float EXIT_X_POSITION = 2409;
    public static final float EXIT_Y_POSITION = 259;
 //   private Handler handler;
    
    public GameState(Handler handler) { 
        super(handler);
        world = new World(handler, "res/worlds/world1.txt");
        handler.setWorld(world);
        
        bg = ImageLoader.loadImage("/textures/bg_level1.png");
        player = new Player(handler,PLAYER_SPAWN_X,PLAYER_SPAWN_Y);
        //tree = new Tree(handler, 100,200) {};
        enemies = new ArrayList<>();
        enemy = new Enemy[ENEMY_NUMBER];
        enemy[0] = new Enemy(handler, 683, 1155, 0);
        enemy[1] = new Enemy(handler, 2245, 707, 1);
        enemy[2] = new Enemy(handler, 125, 579, 2);
        enemy[3] = new Enemy(handler, 848, 323, 3);
        
        //spinner test
        spinners = new ArrayList<>();
        spinner = new Spinner[ENEMY_SPINNER_NUMBER];
        spinner[0] = new Spinner(handler, 1000, 1155,0);
        for (Spinner s : spinner) {
            spinners.add(s);
        }
           
        for (Enemy e : enemy) {
            enemies.add(e);
        }
        
//        for (Enemy e: enemy) {
//            e.tick(player);
//        }
        
        // adding 100 and 200 to offset
        //boleh nih buat maju doank
        //game.getGameCamera().move(0, 0);
//  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tick() {
        
        
        //tree.tick();
        world.tick();
        boolean aimPlayer = false;
        
        for (Enemy e : enemy) {
            if (aimForPlayer(player, e)) {
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
        
        //remove this later
        for (Spinner s : spinner) {
            s.tick(player);
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        //spinner
        for (Spinner s: spinner) {
            s.render(g);
        }
        
        System.out.println(player.getX()+ " " + player.getY());
        System.out.println("player's health= "+player.health);
        g.drawImage(Assets.exitSign, (int) (EXIT_X_POSITION - handler.getGameCamera().getxOffset()), (int) (EXIT_Y_POSITION - handler.getGameCamera().getyOffset()), 100, 100, null);
        // draw heart
        for (int i = 0; i < player.health; i++) {
            g.drawImage(Assets.heart, 60 * (i + 1) - 55, 25, 50, 50, null);
        }
        
        
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean aimForPlayer(Creature player, Enemy e) {
        if (Math.abs(player.getX() - e.getX()) < 200 && Math.abs(player.getY() - e.getY()) < 20) {
            e.aimPlayer = Math.abs(player.getX() - e.getX()) >= 20;
        } else {
            e.aimPlayer = false;
        }

        return e.aimPlayer;
    }
     
}

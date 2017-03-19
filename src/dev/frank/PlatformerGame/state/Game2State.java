/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.state;

import dev.frank.PlatformerGame.Handler;
import dev.frank.PlatformerGame.entities.creatures.Creature;
import dev.frank.PlatformerGame.entities.creatures.Enemy;
import dev.frank.PlatformerGame.entities.creatures.Player;
import dev.frank.PlatformerGame.entities.creatures.Spinner;
import dev.frank.PlatformerGame.gfx.Assets;
import dev.frank.PlatformerGame.gfx.ImageLoader;
import dev.frank.PlatformerGame.music.Music;
import dev.frank.PlatformerGame.worlds.World;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Frank
 */
public class Game2State extends State {
    
    private Player player;
    private World world;
    private BufferedImage bg;
    private Enemy[] enemy;
    public static final int ENEMY_NUMBER = 3;
    public ArrayList<Enemy> enemies;
    
    public static final int ENEMY_SPINNER_NUMBER = 7;
    private Spinner[] spinner;
    public ArrayList<Spinner> spinners;
    public static int LEVEL2_DEAD_Y_COORDINATE = 1300;
       
    public static final int PLAYER_SPAWN_X = 100, PLAYER_SPAWN_Y = 579;
    public static final float EXIT_X_POSITION = 4990;
    public static final float EXIT_Y_POSITION = 643;

    public Game2State(Handler handler) {
        super(handler);
        world = new World(handler, "res/worlds/world2.txt");
        handler.setWorld(world);
        
        bg = ImageLoader.loadImage("/textures/bg_level2.png");
        player = new Player(handler,PLAYER_SPAWN_X,PLAYER_SPAWN_Y);
        enemies = new ArrayList<>();
        enemy = new Enemy[ENEMY_NUMBER];
        enemy[0] = new Enemy(handler, 643, 643, 0);
        //enemy[1] = new Enemy(handler, 2668, 451, 1);
        enemy[1] = new Enemy(handler, 4859, 643, 2);
        enemy[2] = new Enemy(handler, 848, 323, 3);
        
        //spinner test
        spinners = new ArrayList<>();
        spinner = new Spinner[ENEMY_SPINNER_NUMBER];
        spinner[0] = new Spinner(handler, 709, 387, 0);
        spinner[1] = new Spinner(handler, 2551, 451, 0);
        spinner[2] = new Spinner(handler, 2770, 451, 0);
        spinner[3] = new Spinner(handler, 1444, 323, 0);
        spinner[4] = new Spinner(handler, 838, 515, 1);
        spinner[5] = new Spinner(handler, 3707, 323, 1);
        spinner[6] = new Spinner(handler, 2178, 451, 2);
        
        Music.loop("bgm_castle");
        
        for (Spinner s : spinner) {
            spinners.add(s);
        }
           
        for (Enemy e : enemy) {
            enemies.add(e);
        }
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
        
        //remove this later
        for (Spinner s : spinner) {
            s.tick(player);
        }
        
        if (Math.abs(player.getX() - EXIT_X_POSITION) < 20 && Math.abs(player.getY() - EXIT_Y_POSITION) < 20) {
                State.setState(new FinishState(handler));
                Music.stop("bgm_castle");
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
        //g.drawImage(Assets.exitSign, (int) (EXIT_X_POSITION - handler.getGameCamera().getxOffset()), (int) (EXIT_Y_POSITION - handler.getGameCamera().getyOffset()), 100, 100, null);
        // draw heart
        for (int i = 0; i < player.health; i++) {
            g.drawImage(Assets.heart, 60 * (i + 1) - 55, 25, 50, 50, null);
        }
        
        //if player is dead change the state to gameover state
        if (player.dead) {
            Music.stop("bgm_castle");
            Music.loop("bgm_tropics");
            State.setState(new GameOverState(handler));
        }
        
    }
    
    //check if player is nearby
    public boolean CheckPlayerNearby(Creature player, Enemy e) {
        if (Math.abs(player.getX() - e.getX()) < 200 && Math.abs(player.getY() - e.getY()) < 20) {
            e.aimPlayer = Math.abs(player.getX() - e.getX()) >= 20;
        } else {
            e.aimPlayer = false;
        }

        return e.aimPlayer;
    }
    
}

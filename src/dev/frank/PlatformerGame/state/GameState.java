/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.state;

import dev.frank.PlatformerGame.Game;
import dev.frank.PlatformerGame.Handler;
import dev.frank.PlatformerGame.entities.creatures.Player;
import dev.frank.PlatformerGame.entities.statics.Tree;
import dev.frank.PlatformerGame.gfx.Assets;
import dev.frank.PlatformerGame.gfx.ImageLoader;
import dev.frank.PlatformerGame.tiles.Tile;
import dev.frank.PlatformerGame.worlds.World;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Frank
 */
public class GameState extends State {
    
    private Player player;
    private World world;
    private BufferedImage bg;
    //private Tree tree;
 //   private Handler handler;
    
    public GameState(Handler handler) { 
        super(handler);
        world = new World(handler, "res/worlds/world1.txt");
        handler.setWorld(world);
        
        bg = ImageLoader.loadImage("/textures/bg_level1.png");
      //  player = new Player(handler,100,100);
        //tree = new Tree(handler, 100,200) {};

        
        // adding 100 and 200 to offset
        //boleh nih buat maju doank
        //game.getGameCamera().move(0, 0);
//  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tick() {
        world.tick();
       // player.tick(); //has getinpu and move() in player
       // tree.tick();
        //buat maju doank tembak2an
        //game.getGameCamera().move(1, 0); // add 1 to x and y offset every tick 
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(bg, 0, 0, null);
        world.render(g);
     // player.render(g);
    //  tree.render(g);
     // Tile.tiles[0].render(g, 0, 0); // draw grass on 0,0
        
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
}

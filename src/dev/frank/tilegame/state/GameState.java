/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.tilegame.state;

import dev.frank.tilegame.Game;
import dev.frank.tilegame.entities.creatures.Player;
import dev.frank.tilegame.gfx.Assets;
import dev.frank.tilegame.tiles.Tile;
import dev.frank.tilegame.worlds.World;
import java.awt.Graphics;

/**
 *
 * @author Frank
 */
public class GameState extends State {
    
    private Player player;
    private World world;
    
    public GameState(Game game) {
        super(game);
        player = new Player(game,100,100);
        world = new World(game, "res/worlds/world1.txt");
        
        // adding 100 and 200 to offset
        //boleh nih buat maju doank
        //game.getGameCamera().move(0, 0);
//  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tick() {
        world.tick();
        player.tick(); //has getinpu and move() in player
        //buat maju doank tembak2an
        //game.getGameCamera().move(1, 0); // add 1 to x and y offset every tick 
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics g) {
      world.render(g);
      player.render(g);
     // Tile.tiles[0].render(g, 0, 0); // draw grass on 0,0
        
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
}

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
import java.awt.Graphics;

/**
 *
 * @author Frank
 */
public class GameState extends State {
    
    private Player player;
    
    public GameState(Game game) {
        super(game);
        player = new Player(game,100,100);
        
//  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tick() {
        player.tick();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics g) {
      player.render(g);
      Tile.tiles[0].render(g, 0, 0);
        
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
}

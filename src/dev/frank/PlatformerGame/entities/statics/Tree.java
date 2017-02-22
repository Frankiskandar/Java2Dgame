/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.entities.statics;

import dev.frank.PlatformerGame.Handler;
import dev.frank.PlatformerGame.gfx.Assets;
import dev.frank.PlatformerGame.tiles.Tile;
import java.awt.Graphics;

/**
 *
 * @author Frank
 */
public abstract class Tree extends StaticEntity {

    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH , Tile.TILEHEIGHT * 2);
    }
    
    @Override
    public void tick() {
        
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
}

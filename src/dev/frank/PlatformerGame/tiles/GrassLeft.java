/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.tiles;

import dev.frank.PlatformerGame.gfx.Resources;

/**
 *
 * @author Frank
 */
public class GrassLeft extends Tile {
    
    public GrassLeft(int id) {
        super(Resources.grassLeft, id);
    }


    //dont want player to walk through it
    @Override
    public boolean isSolid() {
        return true;
    }
    
}

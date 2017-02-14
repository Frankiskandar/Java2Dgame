/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.tilegame.tiles;

import dev.frank.tilegame.gfx.Assets;
import java.awt.image.BufferedImage;

/**
 *
 * @author Frank
 */
public class SandMid extends Tile {

    public SandMid(int id) {
        super(Assets.sandmid, id);
    }
    

    
    //dont want player to walk through it
    @Override
    public boolean isSolid() {
        return true;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.tiles.decorations;

import dev.frank.PlatformerGame.gfx.Assets;
import dev.frank.PlatformerGame.tiles.Tile;
import java.awt.image.BufferedImage;

/**
 *
 * @author Frank
 */
public class BrownBox extends Tile {

    public BrownBox(int id) {
        super(Assets.brownBox, id);
    }
    
    //dont want player to walk through it
    @Override
    public boolean isSolid() {
        return true;
    
    }
}

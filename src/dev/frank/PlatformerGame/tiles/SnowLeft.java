/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.tiles;

import dev.frank.PlatformerGame.gfx.Resources;
import java.awt.image.BufferedImage;

/**
 *
 * @author Frank
 */
public class SnowLeft extends Tile {

    public SnowLeft(int id) {
        super(Resources.snowLeft, id);
    }
    
    @Override
    public boolean isSolid() {
        return true;
    }
    
}

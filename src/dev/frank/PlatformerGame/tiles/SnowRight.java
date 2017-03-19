/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.tiles;

import dev.frank.PlatformerGame.gfx.Assets;
import java.awt.image.BufferedImage;

/**
 *
 * @author Frank
 */
public class SnowRight extends Tile {

    public SnowRight(int id) {
        super(Assets.snowRight, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
    
}

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
public class GrassCenter extends Tile {

    public GrassCenter(int id) {
        super(Assets.grassCenter, id);
    }

    //dont want player to walk through it
    @Override
    public boolean isSolid() {
        return true;
    }
    
}

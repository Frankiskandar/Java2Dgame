/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author Frank
 */
public class SpriteSheet {
    //to crop image
    private BufferedImage sheet;
    
    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }
    
    public BufferedImage crop(int x, int y, int width, int height) {
        //to crop from an image
        return sheet.getSubimage(x, y, width, height);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.uinterface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Frank
 */
public class UIImageButton extends UIObject {
    
    private BufferedImage[] images;
    private ClickListener clickListener;

    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clickListener) {
        super(x, y, width, height);
        this.images = images;
        this.clickListener = clickListener;   
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        if (hovering)// if the mouse is on the button, show this image
            g.drawImage(images[1], (int) x, (int) y, width, height, null);
        else
            g.drawImage(images[0], (int) x, (int) y, width, height, null);
    }

    @Override
    public void onClick() {
        clickListener.onClick();
    }
    
    
}

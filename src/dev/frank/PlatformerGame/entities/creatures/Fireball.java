/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.entities.creatures;

import dev.frank.PlatformerGame.Handler;
import dev.frank.PlatformerGame.gfx.Resources;
import java.awt.Graphics;

/**
 *
 * @author Frank
 */
//fireball projectile class
public class Fireball extends Projectile {
    //player attacks using fireballs
    
    public static final int FIREBALL_WIDTH = 40;
    public static final int FIREBALL_HEIGHT = 40;
    public static final int FIREBALL_SPEED = 6;

    public Fireball(Handler handler, float x, float y, boolean facingRight) {
        super(handler, x, y, FIREBALL_WIDTH, FIREBALL_HEIGHT);
        this.facingRight = facingRight;
        bounds.x = 10;
        this.projectileSpeed = FIREBALL_SPEED;
    }
    
    @Override
    public void render(Graphics g) {
        if (facingRight) {
                g.drawImage(Resources.fireball, (int) (x - handler.getGameCamera().getxOffset() + 30), (int) (y - handler.getGameCamera().getyOffset() + 10), width, height, null);
        } else {
                g.drawImage(Resources.fireball, (int) (x - handler.getGameCamera().getxOffset() + 10), (int) (y - handler.getGameCamera().getyOffset() + 10), width, height, null);
        }     
    }
     
}

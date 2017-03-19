/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.entities.creatures;

import dev.frank.PlatformerGame.Handler;

/**
 *
 * @author Frank
 */
//fireball projectile class
public class Fireball extends Projectile {
    
    public static final int DEFAULT_FIREBALL_WIDTH = 40;
    public static final int DEFAULT_FIREBALL_HEIGHT = 40;
    public static final int DEFAULT_FIREBALL_SPEED = 6;
    //public static final int FIREBALL_DAMAGE = 10;

    public Fireball(Handler handler, float x, float y, boolean facingRight) {
        super(handler, x, y, DEFAULT_FIREBALL_WIDTH, DEFAULT_FIREBALL_HEIGHT, true);
        this.facingRight = facingRight;
        bounds.x = 10; //play with this
        this.projectileSpeed = DEFAULT_FIREBALL_SPEED;
    }
     
}

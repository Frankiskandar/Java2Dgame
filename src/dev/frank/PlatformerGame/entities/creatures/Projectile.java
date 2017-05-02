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
//fireball projectile
public class Projectile extends Creature {
    
    public int projectileSpeed = 8, distance = 0;

    boolean remove = false, facingRight = false;
    boolean hitEnemy = false;

    public Projectile(Handler handler, float x, float y, int width, int heigth) {
        super(handler, x, y, width, heigth);
    }
    
    public void render(Graphics g) {   
    }
    
    public void tick() {
        if (facingRight) { //if facing right direction
            xMove = projectileSpeed;
        } else { //if facing left direction
            xMove = -projectileSpeed;
        }
        distance += projectileSpeed;
        move();
    }
    
    
}

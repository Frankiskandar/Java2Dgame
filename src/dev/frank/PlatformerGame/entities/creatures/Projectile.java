/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.entities.creatures;

import dev.frank.PlatformerGame.Handler;
import dev.frank.PlatformerGame.gfx.Assets;
import java.awt.Graphics;

/**
 *
 * @author Frank
 */
public class Projectile extends Creature {
    
    public int projectileSpeed = 8, distance = 0;

    boolean remove = false, isRight = false, first = false;
    boolean restart = false, isNormal = false, hitEnemy = false;

    public static final int PLAYER1 = 0, PLAYER2 = 1, ENEMY = 2;

    public Projectile(Handler handler, float x, float y, int width, int heigth, boolean isNormal) {
        super(handler, x, y, width, heigth);
        this.isNormal = isNormal;
    }
    
    public void render(Graphics g, int time, int type) {
        if (isRight) {
                g.drawImage(Assets.fireball, (int) (x - handler.getGameCamera().getxOffset() + 30), (int) (y - handler.getGameCamera().getyOffset() + 10), width, height, null);
        } else {
                g.drawImage(Assets.fireball, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset() + 50), width, height, null);
        }     
    }
    
    public void tick() {
        if (isRight) { //if facing right direction
            xMove = projectileSpeed;
        } else { //if facing left direction
            xMove = -projectileSpeed;
        }
        distance += projectileSpeed;
        move();
    }
    
    
}

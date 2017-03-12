/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.entities.creatures;

import dev.frank.PlatformerGame.Handler;
import dev.frank.PlatformerGame.gfx.Animation;
import dev.frank.PlatformerGame.gfx.Assets;
import dev.frank.PlatformerGame.state.State;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author Frank
 */
public class Enemy extends Creature {
    
    public static final int ENEMY_WIDTH = 50;
    public static final int ENEMY_HEIGHT = 50;
    
    public boolean dead = false, deadAni = false, first = false, restart = false;
    boolean isRight = false, attack = false, hitRight = false, hitByPlayer = false;
    boolean magicalBulletHit = false, normalBulletHit = false, hitLeft = false;
    boolean autoRight = true;
    boolean autoLeft = false;
    int preTime, action = 0;
//    final int IDLE = 0, MOVELEFT = 1, MOVERIGHT = 2;
    final float MOVESPEED = 1.0f, JUMPSPEED = 10f;
//    Random random = new Random();
    int time = 0, i = 0;
    int tracker = 0;
    public boolean firstCall = true, notDraw = false, aimPlayer = false, deadTimeSet = false;
    int id, deadTime = 0;
//    boolean isAmmo = false, pickedByPlayer = false, isHeart = false, isMagicalAmmo = false;
//    boolean isDraw = false, hitByMagicalBullet = false, hitByRobot = false;
    
    private Animation animIdleLeft, animIdleRight, animWalkRight, animWalkLeft, animDead;

    public Enemy(Handler handler, float x, float y, int id) {
        super(handler, x, y, ENEMY_WIDTH, ENEMY_HEIGHT);
        this.id = id;
        health = 100;
        
        bounds.x = 32;
        bounds.y = 32;
        bounds.width = 30;
        bounds.height = 15;
        
        animIdleLeft = new Animation(500, Assets.spider_idle_left);
        animIdleRight = new Animation(500, Assets.spider_idle_right);
        animWalkRight = new Animation(500, Assets.spider_walk_right);
        animWalkLeft = new Animation(500, Assets.spider_walk_left);
        animDead = new Animation(500, Assets.spider_dead_right);
    }
    
    public void resetPosition() {
        
    }
    
    
    public void tick(Creature player) {
        
        animIdleLeft.tick();
        animIdleRight.tick();
        animWalkRight.tick();
        animWalkLeft.tick();
        animDead.tick();
        
        attack = true;
        if (health <= 0) {
            dead = true;
            if (!deadTimeSet) {
                deadTime = time;
                deadTimeSet = true;
        }

    }
        
        System.out.println(dead);
        //will cause rendering problem
        //if the enemy is dead, it cant attack
        if (dead) {
            attack = false;
            return;
        }

        
        //if player is not in line of sight, auto move back and forth
        if (!aimPlayer) {
            if (autoRight) {
                xMove = MOVESPEED;
                tracker++;
                if(tracker == 120) {
                   autoRight = false;
                   autoLeft = true;
                   tracker = 0;          
                }
            }

            if (autoLeft) {
                xMove = -MOVESPEED;
                tracker++;
                if(tracker == 120) {
                   autoLeft = false;
                   autoRight = true;
                   tracker = 0;          
                }
            } 
        }
        
        //the enemy will walk toward player if the player in its line of sight
        if (aimPlayer) {
            if (isRight && x > player.getX()) {
                xMove = -MOVESPEED;
                isRight = false;
            } else if (!isRight && x < player.getX()) {
                xMove = MOVESPEED;
                isRight = true;
            } else if (isRight) {
                xMove = MOVESPEED;
            } else {
                xMove = -MOVESPEED;
            }
        }
        
        if (attack) {
            //works
            if (isRight) {
                if (x > player.getX() - 55 && x < player.getX() + 15 && y > player.getY() - 60 && y < player.getY() + 60) {
                    if (!hitRight) {
                        hitRight = true;
                        player.health -= 1;
                    }
                } else {
                    hitRight = false;
                }

            } else if (x > player.getX() - 15 && x < player.getX() + 55 && y > player.getY() - 60 && y < player.getY() + 60) {
                if (!hitLeft) {
                    hitLeft = true;
                    player.health -= 1;
                }
            } else {
                hitLeft = false;
            }
        }
    //gravity
    yMove = JUMPSPEED;
    move();
        
    }
    

    public void stop() {
        xMove = yMove = 0;
    }
    
    public void render(Graphics g) {
        
        //if enemy is dead
        if (dead) {
            g.drawImage(animDead.getCurrentFrame(),(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), null);  
        } else { // if enemy is still alive
            g.setColor(Color.white);
            g.fillRect((int) (x - handler.getGameCamera().getxOffset() + 15), (int) (y - handler.getGameCamera().getyOffset() -15 ), 50, 15); //draws healthbar outline
            g.setColor(Color.red);
            g.fillRect((int) (x - handler.getGameCamera().getxOffset() + 15), (int) (y - handler.getGameCamera().getyOffset() - 15), health / 2, 15); //draws health
            g.drawImage(getCurrentAnimationFrame(),(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), null);
        }        
    }
    
    
    private BufferedImage getCurrentAnimationFrame() {
        //if we are moving to the left
        if (xMove < 0) {
            return animWalkLeft.getCurrentFrame();
        } else if (xMove > 0) {
            return animWalkRight.getCurrentFrame();     
        } else if (dead) {
            return animDead.getCurrentFrame();
//        } else if (yMove = 0) {
//            return animIdleRight.getCurrentFrame();
//        } else if (fall) {
//            return animDown.getCurrentFrame();
        } else {
            return animIdleRight.getCurrentFrame();
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.entities.creatures;

import dev.frank.PlatformerGame.Handler;
import dev.frank.PlatformerGame.gfx.Animation;
import dev.frank.PlatformerGame.gfx.Resources;
import dev.frank.PlatformerGame.music.Music;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Frank
 */
public class Spinner extends Creature {
    
    public static final int ENEMY_WIDTH = 50;
    public static final int ENEMY_HEIGHT = 50;
    public boolean dead = false;
    boolean facingRight = false, attack = false, hitRight = false;
    boolean hitLeft = false;
    boolean autoRight = true;
    boolean autoLeft = false;
    final float VERTICAL_SPEED = 4.0f;
    final float HORIZONTAL_SPEED = 3.0f;
    int tracker = 0;
    public boolean playerInLineofSight = false;
    int spinner_id;
    
    private Animation animation;

    public Spinner(Handler handler, float x, float y, int id) {
        super(handler, x, y, ENEMY_WIDTH, ENEMY_HEIGHT);
        this.spinner_id = id;
        health = 100;
        
        bounds.x = 14;
        bounds.y = 10;
        bounds.width = 35;
        bounds.height = 48;
        
        animation = new Animation(100, Resources.spinner);
        
    }
    
    public void tick(Creature player) {
        
        animation.tick();
        
        attack = true;
        if (health <= 0) {
            dead = true;
    }
        
        //System.out.println(dead);
        //if the enemy is dead, it cant attack
        // but this will not apply to spinner
        if (dead) {
            attack = false;
            return;
        }

       
        if (!playerInLineofSight) {
            //horizontal spinner
            if (spinner_id==1) {
                if (autoRight) {
                    xMove = HORIZONTAL_SPEED;
                    tracker++;
                    if(tracker == 200) {
                       autoRight = false;
                       autoLeft = true;
                       tracker = 0;          
                    }
                }

                if (autoLeft) {
                    xMove = -HORIZONTAL_SPEED;
                    tracker++;
                    if(tracker == 200) {
                       autoLeft = false;
                       autoRight = true;
                       tracker = 0;          
                    }
                }
                
            }
            
            else if (spinner_id == 2) {
                if (autoRight) {
                    xMove = HORIZONTAL_SPEED;
                    tracker++;
                    if(tracker == 350) {
                       autoRight = false;
                       autoLeft = true;
                       tracker = 0;          
                    }
                }

                if (autoLeft) {
                    xMove = -HORIZONTAL_SPEED;
                    tracker++;
                    if(tracker == 350) {
                       autoLeft = false;
                       autoRight = true;
                       tracker = 0;          
                    }
                }
            }
            else { // vertical spinner
                if (autoRight) {
                    yMove = VERTICAL_SPEED;
                    tracker++;
                    if(tracker == 200) {
                       autoRight = false;
                       autoLeft = true;
                       tracker = 0;          
                    }
                }

                if (autoLeft) {
                    yMove = -VERTICAL_SPEED;
                    tracker++;
                    if(tracker == 200) {
                       autoLeft = false;
                       autoRight = true;
                       tracker = 0;          
                    }
                }
            }
        }
        
        
        if (attack) {
            //player gets hit by spinner
            if (facingRight) {
                if (x > player.getX() - 55 && x < player.getX() + 15 && y > player.getY() - 60 && y < player.getY() + 60) {
                    if (!hitRight) {
                        hitRight = true;
                        player.health -= 1;
                        Music.play("playerhit");
                    }
                } else {
                    hitRight = false;
                }

            } else if (x > player.getX() - 15 && x < player.getX() + 55 && y > player.getY() - 60 && y < player.getY() + 60) {
                if (!hitLeft) {
                    hitLeft = true;
                    player.health -= 1;
                    Music.play("playerhit");
                }
            } else {
                hitLeft = false;
            }
        }
    move();        
    }
    
    public void stop() {
        xMove = 0;
        yMove = 0;
    }
    
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(),(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), null);
//        //collision testing
//        g.setColor(Color.red);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), 
//                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);
    }
    
    private BufferedImage getCurrentAnimationFrame() {      
        return animation.getCurrentFrame();
    }
    
}

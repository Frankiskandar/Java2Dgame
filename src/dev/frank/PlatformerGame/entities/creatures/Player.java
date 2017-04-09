/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.entities.creatures;

import dev.frank.PlatformerGame.Game;
import dev.frank.PlatformerGame.Handler;
import dev.frank.PlatformerGame.entities.Entity;
import dev.frank.PlatformerGame.gfx.Animation;
import dev.frank.PlatformerGame.gfx.Assets;
import dev.frank.PlatformerGame.music.Music;
import dev.frank.PlatformerGame.state.FinishState;
import dev.frank.PlatformerGame.state.Game2State;
import dev.frank.PlatformerGame.state.GameOverState;
import dev.frank.PlatformerGame.state.GameState;
import dev.frank.PlatformerGame.state.State;
import dev.frank.PlatformerGame.tiles.Tile;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author Frank
 */
public class Player extends Creature{
    
    final float FIREBALLSPEED = 5f;
    int forward = 0;
    boolean facingRight = true, shootAnimation = false, firstShot = false, fireBallShot = false;
    public boolean jumping = false, falling = false, dead = false;
    float JUMPSPEED = 10; 
    int jumpTimer = 0; // timer to let player jump again after some time
    Timer timer;
    int preTime, time;
    public boolean played = false;
    
    ArrayList<Fireball> FireballArray = new ArrayList<>();
    //ANIMATIONS
    private Animation animDown, animUp, animRight, animLeft, animStand, animUpLeft;
    private Animation animDownLeft, animStandLeft;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGTH);
        
        //customize this to change the bounding rectangle
        bounds.x = 16; // to the right
        bounds.y = 0; //  px down
        bounds.width = 32;
        bounds.height = 60;
        
        //Animations
        animDown = new Animation(500, Assets.alien_down);
        animUp = new Animation(500, Assets.alien_jump);
        animLeft = new Animation(100, Assets.alien_left);
        animRight = new Animation(100, Assets.alien_right);
        animStand = new Animation(500, Assets.alien_stand);
        animUpLeft = new Animation(500, Assets.alien_jump_left);
        animDownLeft = new Animation(500, Assets.alien_jump_left);
        animStandLeft = new Animation(500, Assets.alien_stand_left);
        
    }

    public void tick(ArrayList<Enemy> enemies, Player player) {
        //Animations
        animDown.tick(); //to update index var
        animUp.tick();
        animLeft.tick();
        animRight.tick();
        animStand.tick();
        animUpLeft.tick();
        animDownLeft.tick();
        animStandLeft.tick();

        //Movement
        getInput(enemies, player);
        move(); //from creature class
        
        //every time we tick after move
        //we want to center it on this player
        handler.getGameCamera().centerOnEntity(this);

        
    }
    

    private void getInput(ArrayList<Enemy> enemies, Player player) {
        xMove = 0;

        if(handler.getKeyManager().up) {
            //yMove = -speed;
            if (!jumping && !falling) { // if he is not currently jumping and falling
                //player cant jump if he is current in the air or falling
                //but if he is currently not jumping, then he can jump, therefor, set jumping boolean to true
                Music.play("jump");
                jumping = true;
                falling = false;
            }         
        }
        // if he is eligible to jump then, add speed to ymove
        if (jumping) {
            jumpTimer++;
            yMove = -JUMPSPEED;
            if (jumpTimer >= 24) {
                jumpTimer = 0;
                jumping = false;
                falling = true;
            }
        } else { //if user doesnt press up
            yMove = JUMPSPEED;
        }
        //end jumping mechanic
        
        if(handler.getKeyManager().left) {
            xMove = -speed;
            facingRight = false;
        }
            
        if(handler.getKeyManager().right) {
            xMove = speed;
            facingRight = true;
        // else yMove = speed;
        }
        

        int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
        if (collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
                || collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
            falling = false;
            if (!jumping && !played) {
                played = true;
                //landing
                
            }
        } else {
            played = false;
            falling = true;
        }
        
        //if the user presses shoot button
        if (handler.getKeyManager().attack && !firstShot) {
            Fireball fireball = new Fireball(handler, x, y, facingRight);
            FireballArray.add(fireball);
            fireBallShot = true;
            firstShot = true; //for attack delay
            Music.play("fire");
        }
        else {
            fireBallShot = false;
        }
        
        if (!handler.getKeyManager().attack) {
            firstShot = false;
            shootAnimation = false;
            
        }
        
        // to check if an enemy is hit/not
        for(Fireball b : FireballArray) {
            b.tick(); // update the fireball position
            for (Enemy e : enemies) { //check for every enemy
                if (Math.abs(b.getX() - e.getX()) < 45 && b.getY() > e.getY() - 60 && b.getY() < e.getY() + 60) {
                    if (!b.hitEnemy) {
                        b.hitEnemy = true;
                        e.health -= FIREBALL_DAMAGE;
                    }
                }           
            }
        }
   
        //if the fireball's distance is greater than 300 dont draw it, remove it from the game
        //dont update if it hits the wall
        for(int i = 0; i < FireballArray.size(); i++) {
            if (FireballArray.get(i).distance > 300 || FireballArray.get(i).hitWall) {
                FireballArray.remove(i);
            }       
        }
        //if player's health = 0 
        if(this.health==0) {
            dead = true;
        }
    }
    
    public void stop() {
        xMove = 0;
        yMove = 0;
        falling = jumping = false;
    }
    
    public void render(Graphics g) {
        g.drawImage( getCurrentAnimationFrame() ,(int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        
        for (Fireball b : FireballArray) {
            b.render(g, Projectile.PLAYER1);
        }
        
        if (handler.getKeyManager().attack) {
            if (!firstShot) {
                firstShot = true;
            }
        }

        //collision testing
//        g.setColor(Color.red);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), 
//                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private BufferedImage getCurrentAnimationFrame() {
        if (xMove < 0 && !jumping && !falling) {
            return animLeft.getCurrentFrame();
        } else if (xMove > 0 && !jumping && !falling) {
            return animRight.getCurrentFrame();     
        } else if (yMove < 0 && facingRight ) {
            return animUp.getCurrentFrame();
        } else if (yMove < 0 && !facingRight ) {
            return animUpLeft.getCurrentFrame();
        } else if (falling & facingRight) {
            return animDown.getCurrentFrame();
        } else if (falling & !facingRight) {
            return animDownLeft.getCurrentFrame();
        } else if (facingRight) {
            return animStand.getCurrentFrame();
        } else 
            return animStandLeft.getCurrentFrame();
    }
}

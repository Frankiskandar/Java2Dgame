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
import dev.frank.PlatformerGame.tiles.Tile;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Frank
 */
public class Player extends Creature{
    
    public boolean jump = false;
    public boolean fall = false;
    public boolean played = false;
    int jumpTimer= 0;
    float jumpspeed =10;
    
    //ANIMATIONS
    private Animation animDown, animUp, animRight, animLeft, animStand;
    //Attack timer
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
    
    
    
    //private Game game; // need to access Game object

    public Player(Handler handler, float x, float y) {
        super(handler, x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGTH);
        
        //customize this to change the bounding rectangle
        bounds.x = 16; // to the right
        bounds.y = 32; // 32 px down
        bounds.width = 32;
        bounds.height = 32;
        
        //Animations
        animDown = new Animation(500, Assets.alien_down);
        animUp = new Animation(500, Assets.alien_jump);
        animLeft = new Animation(100, Assets.alien_left);
        animRight = new Animation(100, Assets.alien_right);
        animStand = new Animation(500, Assets.alien_stand);
        
    }

    @Override
    public void tick() {
        //Animations
        animDown.tick(); //to update index var
        animUp.tick();
        animLeft.tick();
        animRight.tick();
        
        //Movement
        getInput();
        move(); //from creature class
        
        //every time we tick after move
        //we want to center it on this player
        handler.getGameCamera().centerOnEntity(this);
        
        //Attack
        checkAttacks();
        
//        if(game.getKeyManager().up)
//            y -=3; // to move a player up we have to substract from y position
//        if(game.getKeyManager().down)
//            y +=3;
//        if(game.getKeyManager().left)
//            x -=3;
//        if(game.getKeyManager().right)
//            x +=3;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void checkAttacks() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCooldown)
            return;
        
        //else will frun below code
        

        
        attackTimer = 0;

        
    }
    
    @Override
    public void die() {
        System.out.println("You Lose");
    }

    private void getInput() {
        xMove = 0;
        //yMove = 0; 
        //for gravity
        // yMove = 5;

        if(handler.getKeyManager().up) {
            //yMove = -speed;
            if (!jump && !fall) { // kalo 2-2nya false
                jump = true;
                fall = false;
            }
            
            
        }
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;
        // else yMove = speed;
        
        if (jump) {
            jumpTimer++;
            yMove = -jumpspeed;
            if (jumpTimer >= 24) {
                jumpTimer = 0;
                jump = false;
                fall = true;
            }
        } else { //if user doesnt press up
            yMove = jumpspeed;
        }
                int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
        if (collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
                || collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
            fall = false;
            if (!jump && !played) {
                played = true;
                //JukeBox.play("land");
            }
        } else {
            played = false;
            fall = true;
        }
        
        
             
        
    }
    
    @Override
    public void render(Graphics g) {
        //width and height from Creature
       // g.drawImage(Assets.player,(int) x,(int) y, width, height, null);
        g.drawImage( getCurrentAnimationFrame() ,(int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        //collision testing
//        g.setColor(Color.red);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), 
//                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private BufferedImage getCurrentAnimationFrame() {
        //if we are moving to the left
        if (xMove < 0) {
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            return animRight.getCurrentFrame();     
        } else if (yMove < 0) {
            return animUp.getCurrentFrame();
//        } else if (yMove > 0) {
//            return animDown.getCurrentFrame();
        } else {
            return animStand.getCurrentFrame();
        }
    }
    
    
}

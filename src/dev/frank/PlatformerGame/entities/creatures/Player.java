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
import dev.frank.PlatformerGame.state.MenuState;
import dev.frank.PlatformerGame.state.State;
import dev.frank.PlatformerGame.tiles.Tile;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author Frank
 */
public class Player extends Creature{
    
    boolean facingRight = true; 
    boolean firstShot = false;
    boolean fireBallShot = false;
    public boolean jumping = false;
    boolean falling = false;
    public boolean dead = false;
    int JUMP_HEIGHT = 25;
    float JUMP_SPEED = 10; 
    int jumpTimer = 0; // timer to let player jump again after some time
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
        animDown = new Animation(500, Resources.alien_down);
        animUp = new Animation(500, Resources.alien_jump);
        animLeft = new Animation(100, Resources.alien_left);
        animRight = new Animation(100, Resources.alien_right);
        animStand = new Animation(500, Resources.alien_stand);
        animUpLeft = new Animation(500, Resources.alien_jump_left);
        animDownLeft = new Animation(500, Resources.alien_jump_left);
        animStandLeft = new Animation(500, Resources.alien_stand_left);
        
    }

    public void tick(ArrayList<Spider> enemies, Player player) {
        //Animations
        animDown.tick();
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
        handler.getGameCamera().centerOnPlayer(this);      
    }
    

    private void getInput(ArrayList<Spider> enemies, Player player) {
        xMove = 0;

        if(handler.getKeyboardInputManager().up) {
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
            yMove = -JUMP_SPEED;
            if (jumpTimer >= JUMP_HEIGHT) {
                jumpTimer = 0;
                jumping = false;
                falling = true;
            }
        } else { //if user doesnt press up
            yMove = JUMP_SPEED;
        }
        //end jumping mechanic
        
        if(handler.getKeyboardInputManager().left) {
            xMove = -speed;
            facingRight = false;
        }
            
        if(handler.getKeyboardInputManager().right) {
            xMove = speed;
            facingRight = true;
        }
        
        int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
        if (collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
                || collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
            falling = false;
        } else {
            falling = true;
        }
        
        //if the user presses shoot button
        if (handler.getKeyboardInputManager().attack && !firstShot) {
            Fireball fireball = new Fireball(handler, x, y, facingRight);
            FireballArray.add(fireball);
            fireBallShot = true;
            firstShot = true; //for attack delay
            Music.play("fire");
        }
        else {
            fireBallShot = false;
        }
        
        if (!handler.getKeyboardInputManager().attack) {
            firstShot = false;
        }
        
        if (handler.getKeyboardInputManager().restart) {
                State.setState(new MenuState(handler));
                Music.stop("bgm_level1");
                Music.stop("bgm_castle");
                Music.loop("bgm_tropics");  
        }
        
        // to check if an enemy is hit/not
        for(Fireball b : FireballArray) {
            b.tick(); // update the fireball position
            for (Spider e : enemies) { //check for every enemy, if they are hit by fireballs                
                if (Math.abs(b.getX() - e.getX()) < 45 && b.getY() > e.getY() - 60 && b.getY() < e.getY() + 60) {
                    if (!b.hitEnemy) {
                        b.hitEnemy = true;
                        e.hitByPlayer = true;
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
    
    
    public void render(Graphics g) {
        //draw animation
        g.drawImage( getCurrentAnimationFrame() ,(int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        
        //Draw fireball
        for (Fireball b : FireballArray) {
            b.render(g);
        }
        //collision testing, uncomment to see bounding box
//        g.setColor(Color.red);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), 
//                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);
        
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

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
import dev.frank.PlatformerGame.state.FinishState;
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
    
    public int numOfNormalBullet = 25, numOfMagicalBullet = 5;
    
    final float BULLETSPEED = 5f;
    int forward = 0;
    boolean isRight = true, shootAni = false, firstShoot = false, normalBulletShoot = false;
    public boolean jump = false, fall = false, flag = true, dead = false, attackAni = false;
    ;
    float jumpspeed = 10; 
    int jumpTimer = 0; // timer to let player jumpt again after some time
    Timer timer;
    int preTime, time;
    public boolean played = false;
    
    ArrayList<Fireball> FireballArray = new ArrayList<>();
    //ANIMATIONS
    private Animation animDown, animUp, animRight, animLeft, animStand;
    //Attack timer
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
       
    //private Game game; // need to access Game object

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
        
    }

    public void tick(ArrayList<Enemy> enemies, Player player) {
        //Animations
        animDown.tick(); //to update index var
        animUp.tick();
        animLeft.tick();
        animRight.tick();
        
        //if the player at the finish position, change the state to finish state
        if (Math.abs(x - GameState.EXIT_X_POSITION) < 20 && Math.abs(y - GameState.EXIT_Y_POSITION) < 20) {
                State.setState(new FinishState(handler));
            }
        
        //Movement
        getInput(enemies, player);
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
        
        //else will run below code

        attackTimer = 0;
    }
    
    public void die() {
        System.out.println("You Lose");
    }

    private void getInput(ArrayList<Enemy> enemies, Player player) {
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
//        if(handler.getKeyManager().down)
//            yMove = speed;
        if(handler.getKeyManager().left) {
            xMove = -speed;
            isRight = false;
        }
            
        if(handler.getKeyManager().right) {
            xMove = speed;
            isRight = true;
        // else yMove = speed;
        }
        
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

            }
        } else {
            played = false;
            fall = true;
        }
        
        //if the user presses shoot button
        if (handler.getKeyManager().attack && !firstShoot) {
            Fireball fireball = new Fireball(handler, x, y, isRight);
            FireballArray.add(fireball);
            normalBulletShoot = true;
            firstShoot = true;
        }
        else {
            normalBulletShoot = false;
        }
        
        if (!handler.getKeyManager().attack) {
            firstShoot = false;
            shootAni = false;
            
        }
        
        // to check if enemy is hit/not
        for(Fireball b : FireballArray) {
            b.tick(); // update the fireball position
            for (Enemy e : enemies) {
                if (Math.abs(b.getX() - e.getX()) < 45 && b.getY() > e.getY() - 60 && b.getY() < e.getY() + 60) {
                    if (!b.hitEnemy) {
                        b.hitEnemy = true;
                        e.health -= FIREBALL_DAMAGE;
                    }
                }           
            }
        }
   
        //if the fireball's distance is greater than 300 dont draw it
        //dont draw if it hits the wall
        for(int i = 0; i < FireballArray.size(); i++) {
            if (FireballArray.get(i).distance > 300 || FireballArray.get(i).hitWall) {
                FireballArray.remove(i);
            }       
        }
        if(this.health==0) {
            dead = true;
        }
        
        //If the user falls to their death
        if(y > LEVEL1_DEAD_Y_COORDINATE) {
            dead = true;
        }
    }
    
    public void stop() {
        xMove = 0;
        yMove = 0;
        fall = jump = false;
    }
    
    public void render(Graphics g) {
        //width and height from Creature
       // g.drawImage(Assets.player,(int) x,(int) y, width, height, null);
        g.drawImage( getCurrentAnimationFrame() ,(int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        
        for (Fireball b : FireballArray) {
            b.render(g, time, Projectile.PLAYER1);
        }
        
        // if player is dead, set the state to gameover state
        if (dead) {
            State.setState(new GameOverState(handler));
        }
        
        if (handler.getKeyManager().attack) {
            if (!firstShoot) {
                firstShoot = true;
                preTime = time;
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
        //if we are moving to the left
        if (xMove < 0) {
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            return animRight.getCurrentFrame();     
        } else if (yMove < 0) {
            return animUp.getCurrentFrame();
        } else if (fall) {
            return animDown.getCurrentFrame();
        } else {
            return animStand.getCurrentFrame();
        }
    }
}

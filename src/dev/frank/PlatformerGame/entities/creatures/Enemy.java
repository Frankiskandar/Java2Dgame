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
    
    int preTime, action = 0;
    final int IDLE = 0, MOVELEFT = 1, MOVERIGHT = 2;
    final float MOVESPEED = 1.0f, JUMPSPEED = 10f;
    Random random = new Random();
    int time = 0, i = 0;
    public boolean firstCall = true, notDraw = false, aimPlayer = false, deadTimeSet = false;
    int id, deadTime = 0;
    boolean isAmmo = false, pickedByPlayer = false, isHeart = false, isMagicalAmmo = false;
    boolean isDraw = false, hitByMagicalBullet = false, hitByRobot = false;
    
    private Animation animIdleLeft, animIdleRight, animWalkRight, animWalkLeft, animDead;

    public Enemy(Handler handler, float x, float y, int id) {
        super(handler, x, y, ENEMY_WIDTH, ENEMY_HEIGHT);
        this.id = id;
        health = 100;
        
        bounds.x = 32;
        bounds.y = 32;
        bounds.width = 30;
        bounds.height = 12;
        
        animIdleLeft = new Animation(100, Assets.spider_idle_left);
        animIdleRight = new Animation(100, Assets.spider_idle_right);
        animWalkRight = new Animation(500, Assets.spider_walk_right);
        animWalkLeft = new Animation(500, Assets.spider_walk_left);
        animDead = new Animation(100, Assets.spider_dead_right);
    }
    
    public void resetPosition() {
        
    }
    
    public void doAction() {
        if (firstCall) {
            firstCall = false;
            //random
            getAction();
            action(action);
            attack = random.nextInt(5) != 1;
        }
    }
    
    public void tick(Creature player) {
        
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
//            if (pickedByPlayer) {
//                notDraw = true;
//                x = -100;
//                y = -100;
//            }
            //bug: mati pas musuh lg liat kanan
            attack = false;
            return;
        }
//        int timeElapse = time / 10;
//        //System.out.println(aimPlayer);
//        if (aimPlayer) {
//            if (isRight && x > player.getX()) {
//                action = MOVELEFT;
//            } else if (!isRight && x < player.getX()) {
//                action = MOVERIGHT;
//            } else if (isRight) {
//                action = MOVERIGHT;
//            } else {
//                action = MOVELEFT;
//            }
//            if (timeElapse % 3 == 0) {
//                if (!firstCall) {
//                    firstCall = true;
//                    attack = random.nextInt(10) != 1;
//                }
//            }
//            action(action);
//        } else if (id % 2 == 0) {
//            if (timeElapse % 2 == 0) {
//                doAction();
//            } else {
//                firstCall = true;
//            }
//        } else if (id % 3 == 0) {
//            if (timeElapse % 3 == 0) {
//                doAction();
//            } else {
//                firstCall = true;
//            }
//        } else if (timeElapse % 4 == 0) {
//            doAction();
//        } else {
//            firstCall = true;
//        }

        if (attack) {
            //works
            if (isRight) {
                if (x > player.getX() - 55 && x < player.getX() + 15 && y > player.getY() - 60 && y < player.getY() + 60) {
                    if (!hitRight) {
                        hitRight = true;
                        player.health -= 1;
                        //JukeBox.play("playerhit");
                    }
                } else {
                    hitRight = false;
                }

            } else if (x > player.getX() - 15 && x < player.getX() + 55 && y > player.getY() - 60 && y < player.getY() + 60) {
                if (!hitLeft) {
                    hitLeft = true;
                    player.health -= 1;
                    //JukeBox.play("playerhit");
                }
            } else {
                hitLeft = false;
            }

        }
    //xMove = 1;
    yMove = JUMPSPEED;
        
    //resetState();
    
    move();
        
    }
    
    public void resetState() {
        if (id == 0) {
            //musuh pertama di single
            if (x + xMove > 582) {
                xMove = 0;
                action = IDLE;
            }

            if (x + xMove < 132) {
                xMove = 0;
                action = IDLE;
            }
        }
    }
    
    public void action(int action) {

        switch (action) {
            case MOVELEFT:
                isRight = false;
                xMove = -MOVESPEED;
                break;
            case MOVERIGHT:
                isRight = true;
                xMove = MOVESPEED;
                break;
            case IDLE:
                xMove = 0;
                break;
            default:
                break;
        }
    }
    
    public void getAction() {

        action = random.nextInt(3);
    }

    public void stop() {
        xMove = yMove = 0;
    }
    
    public void render(Graphics g) {
        
        //this.time = time;
        
//        if (notDraw) {
//            return;
//        }
        
//        if (dead) {
//            stop();
//        }
        
        g.setColor(Color.white);
        g.fillRect((int) (x - handler.getGameCamera().getxOffset() + 30), (int) (y - handler.getGameCamera().getyOffset()), 50, 15); //draws healthbar outline
        g.setColor(Color.red);
        g.fillRect((int) (x - handler.getGameCamera().getxOffset() + 30), (int) (y - handler.getGameCamera().getyOffset()), health / 2, 15); //draws health
        
        //g.drawImage(getCurrentAnimationFrame(),(int) (x - handler.getGameCamera().getxOffset() + 30), (int) (y - handler.getGameCamera().getyOffset()+40), null);
        if (attack) {
            if (isRight) {
                animationAttackRight(g);
            } else {
                animationAttackLeft(g);
            }
        } else {
            if (action == MOVERIGHT) {
                animationMoveRight(g);
            }

            if (action == MOVELEFT) {
                animationMoveLeft(g);
            }
            if (action == IDLE) {
                if (isRight) {
                    animationIdleRight(g);
                } else {
                    animationIdleLeft(g);
                }
            }
        }
        if (dead) {
            animationDeadLeft(g);
        }
    }
    
    public void animationAttackRight(Graphics g) {
        g.drawImage(Assets.spider_walk_right[0], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    public void animationAttackLeft(Graphics g) {
        g.drawImage(Assets.spider_walk_left[0], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    public void animationMoveRight(Graphics g) {
        while(true){
            g.drawImage(Assets.spider_walk_right[0], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
            g.drawImage(Assets.spider_walk_right[1], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        }
        
    }

    public void animationMoveLeft(Graphics g) {
        g.drawImage(Assets.spider_walk_left[0], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    public void animationIdleRight(Graphics g) {
        g.drawImage(Assets.spider_idle_right[0], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    public void animationIdleLeft(Graphics g) {
        g.drawImage(Assets.spider_idle_left[0], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    public void animationDeadRight(Graphics g) {
        if (time / 2 - preTime / 2 < 2) {
            g.drawImage(Assets.spider_dead_right[0], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else {
            deadAni = true;
            g.drawImage(Assets.spider_walk_right[1], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        }
    }

    public void animationDeadLeft(Graphics g) {
        if (time / 2 - preTime / 2 < 2) {
            g.drawImage(Assets.spider_dead_left[0], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        } else {
            deadAni = true;
            g.drawImage(Assets.spider_dead_left[1], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        }
    }
    
    private BufferedImage getCurrentAnimationFrame() {
        //if we are moving to the left
        if (xMove < 0) {
            return animWalkLeft.getCurrentFrame();
        } else if (xMove > 0) {
            return animWalkRight.getCurrentFrame();     
//        } else if (yMove = 0) {
//            return animIdleRight.getCurrentFrame();
//        } else if (fall) {
//            return animDown.getCurrentFrame();
        } else {
            return animIdleRight.getCurrentFrame();
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.tilegame.entities.creatures;

import dev.frank.tilegame.Game;
import dev.frank.tilegame.gfx.Assets;
import java.awt.Graphics;

/**
 *
 * @author Frank
 */
public class Player extends Creature{
    
    //private Game game; // need to access Game object

    public Player(Game game, float x, float y) {
        super(game, x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGTH);
    }

    @Override
    public void tick() {
        getInput();
        move(); //from creature class
        
        //every time we tick after move
        //we want to center it on this player
        game.getGameCamera().centerOnEntity(this);
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

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if(game.getKeyManager().up)
            yMove = -speed;
        if(game.getKeyManager().down)
            yMove = speed;
        if(game.getKeyManager().left)
            xMove = -speed;
        if(game.getKeyManager().right)
            xMove = speed;
        
    }
    
    @Override
    public void render(Graphics g) {
        //width and height from Creature
       // g.drawImage(Assets.player,(int) x,(int) y, width, height, null);
        g.drawImage(Assets.alien,(int) (x - game.getGameCamera().getxOffset()),
                (int) (y - game.getGameCamera().getyOffset()), width, height, null);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

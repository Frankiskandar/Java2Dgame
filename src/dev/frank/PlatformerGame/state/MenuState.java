/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.state;

import dev.frank.PlatformerGame.Game;
import dev.frank.PlatformerGame.Handler;
import dev.frank.PlatformerGame.gfx.Assets;
import dev.frank.PlatformerGame.gfx.ImageLoader;
import dev.frank.PlatformerGame.ui.ClickListener;
import dev.frank.PlatformerGame.ui.UIImageButton;
import dev.frank.PlatformerGame.ui.UIManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Frank
 */
public class MenuState extends State {
    
    private UIManager uiManager;
    private BufferedImage bg;
    
    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        
        //background image
        bg = ImageLoader.loadImage("/textures/bg_menu.png");
        
        uiManager.addObject(new UIImageButton(445, 298, 128, 64, Assets.btn_start, new ClickListener(){

            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                State.setState(new GameModeState(handler));
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }));
        
        uiManager.addObject(new UIImageButton(445, 363, 128, 64, Assets.btn_quit, new ClickListener(){

            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                System.exit(0);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
        
        //temporarilty just go directly to the gameState,skip the menu state!
//        handler.getMouseManager().setUIManager(null);
//        State.setState(handler.getGame().gameState);
        
        //test if both right and left click pressed, set the state to game state
        if(handler.getMouseManager().isLeftPressed() && handler.getMouseManager().isRightPressed())
            State.setState(handler.getGame().gameState);
        System.out.println(handler.getMouseManager().getMouseX() + "   " + handler.getMouseManager().getMouseY());
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(bg, 0, 0, null);
        uiManager.render(g);
        //test
//        g.setColor(Color.RED);
//        g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 8, 8);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

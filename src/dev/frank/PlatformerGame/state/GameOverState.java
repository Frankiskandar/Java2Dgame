/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.state;

import dev.frank.PlatformerGame.Handler;
import dev.frank.PlatformerGame.gfx.Assets;
import dev.frank.PlatformerGame.gfx.ImageLoader;
import dev.frank.PlatformerGame.ui.ClickListener;
import dev.frank.PlatformerGame.ui.UIImageButton;
import dev.frank.PlatformerGame.ui.UIManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Frank
 */
public class GameOverState extends State {
    
    private UIManager uiManager;
    private BufferedImage bg;

    public GameOverState(Handler handler) {
        super(handler);
        
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        
        //background image
        bg = ImageLoader.loadImage("/textures/bg_menu.png");
        
        
        uiManager.addObject(new UIImageButton(462, 279, 150, 50, Assets.btn_return, new ClickListener(){

            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                State.setState(new MenuState(handler));
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }));
    }
    
    

    @Override
    public void tick() {
        uiManager.tick();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(bg, 0, 0, null);
        g.setFont(new Font("Franklin Gothic Heavy", Font.BOLD, 30));
        g.setColor(Color.BLACK);
        g.drawString("YOU ARE DEAD", 400, 200);
        uiManager.render(g);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

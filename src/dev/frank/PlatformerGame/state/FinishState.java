/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.state;

import dev.frank.PlatformerGame.Handler;
import dev.frank.PlatformerGame.gfx.Resources;
import dev.frank.PlatformerGame.gfx.ImageLoader;
import dev.frank.PlatformerGame.music.Music;
import dev.frank.PlatformerGame.uinterface.ClickListener;
import dev.frank.PlatformerGame.uinterface.UIImageButton;
import dev.frank.PlatformerGame.uinterface.UIManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Frank
 */
public class FinishState extends State {
    //this screen is shown when player finishes a level
    
    private UIManager uiManager;
    private BufferedImage background;
    
    public FinishState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseInputManager().setUIManager(uiManager);
        
        //background image
        background = ImageLoader.loadImage("/textures/bg_level1.png");
        
        
        uiManager.addObject(new UIImageButton(432, 279, 150, 50, Resources.btn_return, new ClickListener(){
            @Override
            public void onClick() {
                handler.getMouseInputManager().setUIManager(null);
                Music.play("click");
                State.setState(new MenuState(handler));
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
        
        //to print mouse x and y coordinate
        //System.out.println(handler.getMouseManager().getMouseX() + "   " + handler.getMouseManager().getMouseY());
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.setFont(new Font("Helvetica", Font.BOLD, 30));
        g.setColor(Color.BLACK);
        g.drawString("Congratulations! You Finished The Level ", 200, 200);
        uiManager.render(g);
        //test
//        g.setColor(Color.RED);
//        g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 8, 8);
    }
    
    
    
}

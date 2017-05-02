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
public class GameOverState extends State {
    
    private UIManager uiManager;
    private BufferedImage bg;

    public GameOverState(Handler handler) {
        super(handler);
        
        uiManager = new UIManager(handler);
        handler.getMouseInputManager().setUIManager(uiManager);
        
        //background image
        bg = ImageLoader.loadImage("/textures/bg_level1.png");
        
        
        uiManager.addObject(new UIImageButton(445, 279, 150, 50, Resources.btn_return, new ClickListener(){

            @Override
            public void onClick() {
                handler.getMouseInputManager().setUIManager(null);
                Music.play("click");
                State.setState(new MenuState(handler));
            }
        }));
    }
    
    

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(bg, 0, 0, null);
        g.setFont(new Font("TimesRoman", Font.BOLD, 30));
        g.setColor(Color.BLACK);
        g.drawString("YOU ARE DEAD", 400, 200);
        uiManager.render(g);
    }
    
}

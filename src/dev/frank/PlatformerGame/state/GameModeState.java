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
public class GameModeState extends State {
    
    private UIManager uiManager;
    private BufferedImage bg;

    public GameModeState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseInputManager().setUIManager(uiManager);
        
        bg = ImageLoader.loadImage("/textures/bg_level1.png");
        
        uiManager.addObject(new UIImageButton(435, 298, 150, 50, Resources.btn_level1, new ClickListener(){

            @Override
            public void onClick() {
                handler.getMouseInputManager().setUIManager(null);
                Music.play("click");
                Music.stop("bgm_tropics");
                //Music.loop("bgm_level1");               
                State.setState(new LevelOneState(handler));
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }));
        
        uiManager.addObject(new UIImageButton(435, 363, 150, 50, Resources.btn_level2, new ClickListener(){

            @Override
            public void onClick() {
                handler.getMouseInputManager().setUIManager(null);
                Music.play("click");
                Music.stop("bgm_tropics");
                //Music.loop("bgm_castle");
                State.setState(new LevelTwoState(handler));
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }));
        
        uiManager.addObject(new UIImageButton(435, 428, 150, 50, Resources.btn_return, new ClickListener(){

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
        //System.out.println(handler.getMouseManager().getMouseX() + "   " + handler.getMouseManager().getMouseY());
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(bg, 0, 0, null);
        g.setFont(new Font("TimesRoman", Font.BOLD, 30));
        g.setColor(Color.BLACK);
        g.drawString("SELECT GAME MODE", 350, 190);
        uiManager.render(g);
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame;

import dev.frank.PlatformerGame.display.Display;
import dev.frank.PlatformerGame.gfx.Assets;
import dev.frank.PlatformerGame.gfx.GameCamera;
import dev.frank.PlatformerGame.gfx.ImageLoader;
import dev.frank.PlatformerGame.gfx.SpriteSheet;
import dev.frank.PlatformerGame.input.KeyManager;
import dev.frank.PlatformerGame.state.GameState;
import dev.frank.PlatformerGame.state.MenuState;
import dev.frank.PlatformerGame.state.State;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Frank
 */
public class Game implements Runnable { //to run runnable
    
    private Display display;
    private int width, height;  
    public String title;
    
    private boolean running = false;
    private Thread thread;
    
    private BufferStrategy bs;//way for comp to draw things
    private Graphics g; // like a paintbrush
    
    //States
    private State gameState;
    private State menuState;
    
    //Input
    private KeyManager keyManager;
    
    //Camera
    private GameCamera gameCamera;
    
    //Handler
    private Handler handler;
//    private BufferedImage test;
//    private SpriteSheet sheet;
    
//    private BufferedImage testImage;
//    private BufferedImage testImage2;
    
    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();

    }
    
    private void init() {
        display = new Display(title, width, height);
        // adding keylistener to jframe, our keymanager extends key listener
        display.getFrame().addKeyListener(keyManager);
//        testImage = ImageLoader.loadImage("/textures/test.png");
//        testImage2 = ImageLoader.loadImage("/textures/test2.png");
//        test = ImageLoader.loadImage("/textures/sheet.png");
//        sheet = new SpriteSheet(test);
        Assets.init(); // check init in assets
        
        handler = new Handler(this); //takes game object
        gameCamera = new GameCamera(handler,0,0);
        
        
        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setState(gameState);
    }
    
//    int x = 0;
//    
   private void tick() {// update
        //x += 1;
       keyManager.tick();
       if(State.getState() != null)
           State.getState().tick();//RUN TICK() in game state which has tick() from player
    }
    
    public void render() {//draw
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){// if the canvas does not have bufferstrategy
            //we create one
            display.getCanvas().createBufferStrategy(3);
            return;        
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);
        //Draw here
//        g.drawImage(Assets.grass,x,10,null);
//        g.drawImage(Assets.tree, 200,200, null);
//        
//        g.drawImage(testImage, 20, 20, null);
//        g.drawImage(testImage2, 20, 20, null);
//        g.drawImage(sheet.crop(0, 0, 32, 32),5,5,null);
      //  g.drawImage(sheet.crop(32, 0, 32, 32),25,25,null);
        //if a state exists
        if(State.getState() != null) // if the current state is null
           State.getState().render(g);
        // end drawing
        bs.show();
        g.dispose();
    }
    
    public void run() {
        init(); //run method will call init method
        
        int fps = 60;
        double timePerTick = 1000000000/fps; //1 bil ns per sec / fps
        double delta = 0;
        long now;
        long lastTime = System.nanoTime(); //current time of our comp in nano
        long timer = 0;
        int ticks = 0;
        
        while(running) {
            now = System.nanoTime();
            
            // time elapsed / max time allowed
            // delta = how much time we have until we call tick and render
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime; // amount of time that has passed since above code
            lastTime = now;
            
            if(delta >= 1){
                tick();
                render();
                ticks++;
                delta--;
            }
            //to check 60 fps
            if(timer>= 1000000000) {
                System.out.println("Ticks and Frames: "+ ticks);
                ticks = 0;
                timer = 0;                
            }
        }//end while
        
        stop();
    }
    // player class needs access keymanager object
    // so other classes can access it too
    public KeyManager getKeyManager() {
        return keyManager;
    }
    
    public GameCamera getGameCamera() {
        return gameCamera;
    }
    
    
    //get height and width of our window
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public synchronized void start() {
        if(running)
            return;// ifthe game is already running
        running = true;
        thread = new Thread(this);
        thread.start(); // will run run() method above
    }
    
    //to close it properly
    public synchronized void stop() {
        if(!running)// if its still running
            return; //for safety
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

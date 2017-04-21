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
import dev.frank.PlatformerGame.input.MouseManager;
import dev.frank.PlatformerGame.music.Music;
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
//main class of the game
//it will start everything, hold all base code of the game
public class Game implements Runnable { //to run on a thread
    
    private Display display;
    private int width, height;// to access width and height easily
    public String title;
    
    private boolean running = false;
    private Thread thread;
    
    //way for computer to draw things to the screen, to prevent flickering in our game
    private BufferStrategy bs;
    
    //allows us to draw graphic to the screen
    private Graphics g; // like a paintbrush
    
    //States
    public State gameState;
    public State menuState;
    
    //Input
    private KeyManager keyManager;
    private MouseManager mouseManager;
    
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
        mouseManager = new MouseManager();

    }
    
    private void init() {
        
        //load all necessary music files
        Music.init();
        Music.load("/sound/fire.wav", "fire");
        Music.load("/sound/jump.wav", "jump");
        Music.load("/sound/playerhit.wav", "playerhit");
        Music.load("/sound/bgm_tropics.mp3", "bgm_tropics");
        Music.load("/sound/bgm_castle.mp3", "bgm_castle");
        Music.load("/sound/bgm_level1.mp3", "bgm_level1");
        Music.load("/sound/click1.wav", "click");
        Music.load("/sound/rollover2.wav", "rollover");
        
        
        display = new Display(title, width, height);
        // adding keylistener to jframe, our keymanager extends key listener
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        // to both jframe and canvas (whoever active)
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        
        Assets.init(); // check init in assets
        
        handler = new Handler(this); 
        gameCamera = new GameCamera(handler,0,0);
        
        //the game will show menu state when it runs
        menuState = new MenuState(handler);
        State.setState(menuState);
        Music.loop("bgm_tropics");
    }
       
   private void tick() {// update
       keyManager.tick();
       if(State.getState() != null)
           State.getState().tick();//RUN TICK() in game state which has tick() from player, enemy etc
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
        if(State.getState() != null) // if the current state is not null, draw
           State.getState().render(g);
        // end drawing
        bs.show();
        g.dispose();//make sure graphics objects get done properly
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
            //while running is true, we want to keep ticking and rendering all over again
            now = System.nanoTime();
            
            // time elapsed / max time allowed
            // delta = how much time we have until we call tick and render
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime; // amount of time that has passed since above code
            lastTime = now;
            
            if(delta >= 1){ // gameloop runs tick and render everytime
                tick();
                render();
                ticks++;
                delta--;
            }
            //to check 60 fps
            if(timer>= 1000000000) {
                //System.out.println("Ticks and Frames: "+ ticks);
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
    
    //need mouse manager from another class
    public MouseManager getMouseManager() {
        return mouseManager;
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
    //will be called in main method game.start()
    public synchronized void start() {
        //check if the code is already running
        if(running)
            return;// ifthe game is already running, dont do any of the code below
        running = true;
        thread = new Thread(this);
        thread.start(); // will run run() method above
    }
    
    //to close it properly
    public synchronized void stop() {
        if(!running)// if the game is already stop, we dont want to stop it again
            return; //for safety, dont do any of the code below
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
}

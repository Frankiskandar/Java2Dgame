/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame;

import dev.frank.PlatformerGame.screen.Screen;
import dev.frank.PlatformerGame.gfx.Resources;
import dev.frank.PlatformerGame.gfx.Camera;
import dev.frank.PlatformerGame.input.KeyboardInputManager;
import dev.frank.PlatformerGame.input.MouseInputManager;
import dev.frank.PlatformerGame.music.Music;
import dev.frank.PlatformerGame.state.MenuState;
import dev.frank.PlatformerGame.state.State;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Frank
 */
//main class of the game
//it will start everything, hold all base code of the game
public class Game implements Runnable { //to run on a thread
    
    private Screen screen;
    private int width, height;// to access width and height easily
    
    private boolean game_is_running = false;
    private Thread thread;
    
    //way for computer to draw things to the screen, to prevent flickering in our game
    private BufferStrategy bufferStrategy;
    
    //allows us to draw graphic to the screen
    private Graphics graphics; // like a paintbrush
    
    //Input
    private KeyboardInputManager keyboardInputManager;
    private MouseInputManager mouseInputManager;
    
    //Camera
    private Camera gameCamera;
    
    //Handler
    private Handler handler;
    
    public Game(int width, int height) {
        this.width = width;
        this.height = height;
        keyboardInputManager = new KeyboardInputManager();
        mouseInputManager = new MouseInputManager();
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
                
        screen = new Screen(width, height);
        // adding keylistener to jframe, our keymanager extends key listener
        screen.getFrame().addKeyListener(keyboardInputManager);
        screen.getFrame().addMouseListener(mouseInputManager);
        screen.getFrame().addMouseMotionListener(mouseInputManager);
        // to both jframe and canvas (whoever active)
        screen.getCanvas().addMouseListener(mouseInputManager);
        screen.getCanvas().addMouseMotionListener(mouseInputManager);
        
        Resources.init(); // call init in resources class
        
        handler = new Handler(this); 
        gameCamera = new Camera(handler,0,0);
        
        //the game will show menu state when it runs the first time
        State.setState(new MenuState(handler));
        Music.loop("bgm_tropics");
    }
       
   private void tick() {// update variables, positions etc
       keyboardInputManager.tick(); // will call tick from key manager and state
       if(State.getState() != null)
           State.getState().tick();//RUN TICK() in game state which has tick() from player, enemy etc
    }
    
    public void render() {//draw things on the screen
        bufferStrategy = screen.getCanvas().getBufferStrategy();
        if(bufferStrategy == null){// if the canvas does not have bufferstrategy
            //we create one
            screen.getCanvas().createBufferStrategy(3);
            return;
        }
        graphics = bufferStrategy.getDrawGraphics();
        //Draw state
        if(State.getState() != null) // if the current state is not null, draw
           State.getState().render(graphics);
        // end draw state
        bufferStrategy.show();
        graphics.dispose();//make sure graphics objects get done properly
    }
    
    public void run() {
        init(); //run method will call init method
        
        int framePerSecond = 60; // we want the game loop calls tick 60 times per sec
        double timePerTick = 1000000000/framePerSecond; //1 bil ns per sec / fps
        double timeLeft = 0; //how much time we have until we call tick and render
        long currentTime;
        long lastTime = System.nanoTime(); //current time of our comp in nano
        long timer = 0;
        
        while(game_is_running) {
            //while running is true, we want to keep ticking and rendering all over again
            currentTime = System.nanoTime();
            
            // time elapsed / max time allowed
            timeLeft += (currentTime - lastTime) / timePerTick;
            timer += currentTime - lastTime; // amount of time that has passed since above code
            lastTime = currentTime;
            
            if(timeLeft >= 1){ // gameloop runs tick and render everytime
                tick();
                render();
                timeLeft--;
            }
            //to check 60 fps
            if(timer>= 1000000000) {
                timer = 0;                
            }
        }//end while
        
        stop();
    }
    // player class needs access keymanager object
    // so other classes can access it too
    public KeyboardInputManager getKeyboardInputManager() {
        return keyboardInputManager;
    }
    
    //need mouse manager from another class
    public MouseInputManager getMouseInputManager() {
        return mouseInputManager;
    }
    
    public Camera getGameCamera() {
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
        if(game_is_running)
            return;// ifthe game is already running, dont do any of the code below
        game_is_running = true;
        thread = new Thread(this);
        thread.start(); // will run run() method above
    }
    
    //to close it properly
    public synchronized void stop() {
        if(!game_is_running)// if the game is already stop, we dont want to stop it again
            return; //for safety, dont do any of the code below
        game_is_running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
}

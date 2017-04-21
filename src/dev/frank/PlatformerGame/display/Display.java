/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.display;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Frank
 */
public class Display {
    
    
    //to get windows display to screen
    private JFrame frame;
    // to get images into jframe
    private Canvas canvas;// draw images to the canvas and put it on jframe
    private String title;
    private int width, height;
    
    //constructor
    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        
        createDisplay();
    }
    
    private void createDisplay() {
        frame = new JFrame(title);
        frame.setSize(width, height);
        //to make sure window closes down properly
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // limit ability for user to resize the window
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); //it will appear at the center 
        frame.setVisible(true); //jframe by default isnt visible
        
        //set canvas size
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        //make sure it stays at our width and height
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false); //must
        
        //add canvas to our jframe
        frame.add(canvas);
        frame.pack(); // resize our window to be able so see canvas fully
    }
    
    //to access our canvas from other class
    public Canvas getCanvas() {
        return canvas;
    }
      
    // to be able to access jframe outside of Display class
    public JFrame getFrame() {
        return frame;
    }
}

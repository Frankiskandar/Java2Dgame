/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Frank
 */
public class Tile {
    
    // static stuff
    
    
    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(10);
    public static Tile dirtTile = new DirtTile(99);
    public static Tile rockTile = new RockTile(98);
    public static Tile sandMid = new SandMid(5);
    
    //decoration
    public static Tile rightSign = new signRight(6);
    public static Tile bush = new Bush(9);
    
    //emptyTile
    public static Tile emptyTile = new EmptyTile(0);
    public static Tile solidEmptyTile = new SolidEmptyTile(4);
    
    //grass left mid right
    public static Tile grassLeft = new GrassLeft(1);
    public static Tile grassMid = new GrassMid(2);
    public static Tile grassRight = new GrassRight(3);
    
    //stone left mid right
    public static Tile stoneLeft = new StoneLeft(11);
    public static Tile stoneMid = new StoneMid(12);
    public static Tile stoneRight = new StoneRight(13);
    public static Tile stoneCenter = new StoneCenter(14);
        
    //water
    public static Tile water = new Water(7);
    public static Tile lava = new Lava(17);
    
    //box
    public static Tile brownBox = new BrownBox(8);

    //CLASS
    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
    
    protected BufferedImage texture;
    protected final int id;
    
    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;
        
        tiles[id] = this;
        
    }
    
    public void tick() {
        
    }
    
    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT,null);
    }
    
    public boolean isSolid() {
        return false;
    }
    
    public int getId() {
        return id;
    }
    
}

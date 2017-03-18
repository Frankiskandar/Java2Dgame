/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.worlds;

import dev.frank.PlatformerGame.Game;
import dev.frank.PlatformerGame.Handler;
import dev.frank.PlatformerGame.entities.creatures.Player;
import dev.frank.PlatformerGame.tiles.Tile;
import dev.frank.PlatformerGame.utils.Utils;
import java.awt.Graphics;

/**
 *
 * @author Frank
 */
public class World {
    
    //3 main things
    private Handler handler;
    //private Game game;
    private int width, height;
    private int spawnX, spawnY;
    //will hold bunch of tile id
    private int[][] tiles;
    
    public World(Handler handler, String path) { //path is the location of the file we want to load
        this.handler = handler;
      
        loadWorld(path);
    }
    
    public void tick() {
        
    }
    
    public void render(Graphics g) {
        
        //limit what tiles we actually draw on the screen
        //draw only user can see
        // max 0 so we dont get any error
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH ); //far left visible
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset()+handler.getWidth()) / Tile.TILEWIDTH+1); //far right
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT );
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset()+handler.getHeight()) / Tile.TILEHEIGHT+1);
        
        for(int y = yStart; y < yEnd; y++)
            for(int x = xStart;x < xEnd;x++) {
                getTile(x,y).render(g,(int) (x*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int) (y*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
                //subtracting where x and y offset are to the  screen where we rendering the tiles to
            }
    }
    
    // just to return tile for render method above
    public Tile getTile(int x, int y) { //x location and y location of that tile
        //make user x and y are greater than 0
        //and less than height and width of the map
        // ot player stands outside of the map, make it stand on a empty so no error
        if (x < 0 || y < 0|| x>= width || y >= height )
            return Tile.emptyTile;
        
        Tile t = Tile.tiles[tiles[x][y]];
        if(t==null)
            return Tile.emptyTile;
        return t;
    }
    
    private void loadWorld(String path) {      
        //load the map from text
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);
        
        tiles = new int[width][height];
        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++)
                tiles[x][y] = Utils.parseInt(tokens[(x+y *width)+4]);
        }

    }
    // to access world height and width from outside the class
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
}

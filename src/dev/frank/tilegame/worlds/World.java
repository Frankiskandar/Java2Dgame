/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.tilegame.worlds;

import dev.frank.tilegame.Game;
import dev.frank.tilegame.tiles.Tile;
import dev.frank.tilegame.utils.Utils;
import java.awt.Graphics;

/**
 *
 * @author Frank
 */
public class World {
    
    //3 main things
    private Game game;
    private int width, height;
    private int spawnX, spawnY;
    //will hold bunch of tile id
    private int[][] tiles;
    
    public World(Game game, String path) { //path is the location of the file we want to load
        this.game = game;
        loadWorld(path);
    }
    
    public void tick() {
        
    }
    
    public void render(Graphics g) {
        for(int y = 0; y < height; y++)
            for(int x = 0;x<width;x++) {
                getTile(x,y).render(g,(int) (x*Tile.TILEWIDTH - game.getGameCamera().getxOffset()),
                        (int) (y*Tile.TILEHEIGHT - game.getGameCamera().getyOffset()));
                //subtracting where x and y offset are to the  screen where we rendering the tiles to
            }
    }
    
    // just to return tile for render method above
    public Tile getTile(int x, int y) { //x location and y location of that tile
        Tile t = Tile.tiles[tiles[x][y]];
        if(t==null)
            return Tile.dirtTile;
        return t;
    }
    
    private void loadWorld(String path) {
//        //testing
//        width = 5;
//        height = 5;
//        tiles = new int[width][height]; // size 5 x 5
//        
//        for(int x =0;x<width;x++) {
//            for(int y =0;y<height;y++) {
//                tiles[x][y] = 0; // set every tile to be grass
//            }
//        }
//        
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
}

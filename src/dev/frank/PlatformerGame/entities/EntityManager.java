/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.entities;

import dev.frank.PlatformerGame.Handler;
import dev.frank.PlatformerGame.entities.creatures.Player;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Frank
 */
public class EntityManager {
    
    private Handler handler;
    private Player player;
    //to hold every entity  just like entity[] but no size
    private ArrayList<Entity> entities;
    private Comparator<Entity> renderSorter = new Comparator<Entity>() {
        
        //compare 2 entity which should be rendered first
        @Override
        public int compare(Entity a, Entity b) {
            if (a.getY() + a.getHeight() < b.getY() + b.getHeight() ) // a should be rendered before b, a has less y component
                    return -1;
            return 1; //a should be rendered after b
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
    
    public EntityManager(Handler handler, Player player) {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
        addEntity(player); // then we can remove player.render an tick inside tick and render
        
    }
    
    public void tick() {
        for (int i=0; i< entities.size(); i++) {
            Entity e = entities.get(i); // just like Entity e = entities[i]
            e.tick();
            if(!e.isActive())
                entities.remove(e);
        }

        entities.sort(renderSorter);
    }
    
    public void render(Graphics g) {
            for (Entity e : entities) {
            e.render(g);
        }
    }
    
    //take an entity and add it to array list so can be ticked and rendered
    public void addEntity(Entity e) {
        entities.add(e);
    }
    
    //Getters and Setters

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.frank.PlatformerGame.entities.statics;

import dev.frank.PlatformerGame.Handler;
import dev.frank.PlatformerGame.entities.Entity;

/**
 *
 * @author Frank
 */
//an entity that does not move around like a tree or a rock
public abstract class StaticEntity extends Entity {

    public StaticEntity(Handler handler, float x, float y, int width, int heigth) {
        super(handler, x, y, width, heigth);
    }
    
    
}

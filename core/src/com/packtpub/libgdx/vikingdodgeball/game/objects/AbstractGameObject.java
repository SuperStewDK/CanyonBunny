package com.packtpub.libgdx.vikingdodgeball.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Steffen on 21-04-2015.
 */
public abstract class AbstractGameObject {

    public Vector2 position;
    public Vector2 dimension;
    public Vector2 origin;
    public Vector2 scale;
    public float rotation;

    public AbstractGameObject(){
        position = new Vector2();
        dimension = new Vector2(1,1);
        origin = new Vector2();
        scale = new Vector2(1,1);
        rotation = 0;
    }

    public void update(float deltaTime){

    }

    public abstract void render(SpriteBatch batch);
}

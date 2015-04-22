package com.packtpub.libgdx.vikingdodgeball.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.Input.Keys;

/**
 * Created by benjaminmlynek on 16/04/15.
 */
public class WorldController extends InputAdapter {

    private static final String TAG = WorldController.class.getName();

    public Sprite[] testSprites;
    public int selectedSprite;
    private boolean aIsPressed;
    private boolean dIsPressed;
    private float rotation;
    private float randomY;
    private float randomX;

    public WorldController() {

        init();
    }

    private void init() {
        Gdx.input.setInputProcessor(this);
        initTestObjects();
    }

    public void update(float deltaTime) {

        handleDebugInput(deltaTime);

//        updateTestObjects(deltaTime);
    }

    private void initTestObjects() {

        // Create new array for 5 sprites
        testSprites = new Sprite[5];
        // Create empty POT-sized Pixmap with 8 bit RGBA pixel data
        int width = 32;
        int height = 32;
//        Pixmap pixmap = createProceduralPixmap(width, height);
        // Create a new texture from pixmap data
        Texture texture = new Texture("core/assets/viking_still.png");
        // Create new sprites using the just created texture
        for (int i = 0; i < testSprites.length; i++) {
            Sprite spr = new Sprite(texture);
            // Define sprite size to be 1m x 1m in game world
            spr.setSize(0.4f, 0.6f);
            // Set origin to sprite's center
            spr.setOrigin(spr.getWidth() / 2.5f, spr.getHeight() / 2.0f);
            // Calculate random position for sprite
            randomX = MathUtils.random(-2.0f, 2.0f);
            randomY = MathUtils.random(-2.0f, 2.0f);
            spr.setPosition(randomX, randomY);
            spr.setRotation(0);
            // Put new sprite into array
            testSprites[i] = spr;
        }
        // Set first sprite as selected one
        selectedSprite = 0;
    }

//    private Pixmap createProceduralPixmap(int width, int height) {
//
//        Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
//        // Fill square with red color at 50% opacity
//        pixmap.setColor(1, 0, 0, 0.5f);
//        pixmap.fill();
//        // Draw a yellow-colored X shape on square
//        pixmap.setColor(1, 1, 0, 1);
//        pixmap.drawLine(0, 0, width, height);
//        pixmap.drawLine(width, 0, 0, height);
//        // Draw a cyan-colored border around square
//        pixmap.setColor(0, 1, 1, 1);
//        pixmap.drawRectangle(0, 0, width, height);
//        return pixmap;
//    }

    // Kan bruges til statiske våben og våben der bliver kastet.
//    private void updateTestObjects(float deltaTime) {

    // Get current rotation from selected sprite
//        float rotation = testSprites[selectedSprite].getRotation();
    // Rotate sprite by 90 degrees per second
//        rotation += 90 * deltaTime;
    // Wrap around at 360 degrees
//        rotation %= 360;
    // Set new rotation value to selected sprite
//        testSprites[selectedSprite].setRotation(rotation);
//    }

    private void handleDebugInput(float deltaTime) {

        if (Gdx.app.getType() != Application.ApplicationType.Desktop) return;

        // Selected Sprite Controls
        float sprMoveSpeed = 2 * deltaTime;
        double angle = Math.toRadians(rotation);


        if (Gdx.input.isKeyPressed(Keys.A)) {

            aIsPressed = true;
            rotateSelectedSprite();
        }

        if (Gdx.input.isKeyPressed(Keys.D)) {

            dIsPressed = true;
            rotateSelectedSprite();
        }

        if (Gdx.input.isKeyPressed(Keys.W) && !Gdx.input.isKeyPressed(Keys.S)){
            moveSelectedSprite((float)(sprMoveSpeed*Math.cos(angle)),(float)(sprMoveSpeed*Math.sin(angle)));

        }


        if (Gdx.input.isKeyPressed(Keys.S) && !Gdx.input.isKeyPressed(Keys.W)){
            moveSelectedSprite((float)(-sprMoveSpeed*Math.cos(angle)),(float)(-sprMoveSpeed*Math.sin(angle)));
        }
    }

    private void moveSelectedSprite(float x, float y) {
        testSprites[selectedSprite].translate(x, y);
    }

    private void rotateSelectedSprite() {

        rotation = testSprites[selectedSprite].getRotation();


        if (aIsPressed) {
            rotation += 5;
            aIsPressed = false;
        }
        if (dIsPressed) {
            rotation -= 5;
            dIsPressed = false;
        }
        testSprites[selectedSprite].setRotation(rotation);

    }
        @Override
        public boolean keyUp (int keycode) {
        // Reset game world
            if (keycode == Keys.R) {
                init();
                Gdx.app.debug(TAG, "Game world resetted");
            }
         // Select next sprite
            else if (keycode == Keys.SPACE) {
                selectedSprite = (selectedSprite + 1) % testSprites.length;
                rotation = testSprites[selectedSprite].getRotation();
                Gdx.app.debug(TAG, "Sprite #" + selectedSprite + " selected");
            }
            return false;
        }
}

package com.alexaut.kroniax.screens;

import com.alexaut.kroniax.Application;
import com.alexaut.kroniax.game.Camera;
import com.alexaut.kroniax.game.Level;
import com.alexaut.kroniax.game.Player;
import com.alexaut.kroniax.game.tilemap.TileMap;
import com.alexaut.kroniax.game.tilemap.TileMapLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameScene implements Screen {

    final Application mApp;

    Camera mCamera;

    Level mLevel;
    Player mPlayer;

    boolean mStarted = false;

    TileMap mMap;

    public GameScene(Application app, String lvlPath) {
        mApp = app;

        mCamera = new Camera();

        try {
            mMap = new TileMapLoader().load(Gdx.files.internal(lvlPath));
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
            e1.printStackTrace();
            goBackToMenu();
        }

        // Load the level (properties, collision handler, renderer) and the
        // start values of the player
        try {
            mLevel = new Level(mMap);

            mPlayer = new Player(mLevel.getProperties());
        } catch (Exception e) {
            // Level not compatible
            goBackToMenu();
        }

    }

    @Override
    public void show() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
    }

    public void update(float deltaTime) {

        if (Gdx.input.isTouched())
            mStarted = true;

        if (!mStarted) {
            deltaTime = 0;
        }
        mLevel.update(deltaTime, mPlayer, mCamera);
        mPlayer.update(deltaTime);
        mCamera.update(mPlayer.getPosition(), mLevel.getProperties().fixedCamera);

        // Check collision
        mLevel.checkCollision(mPlayer);
        // Now check if the player collided
        if (!mPlayer.isAlive())
            goBackToMenu();

        if (Gdx.input.isKeyPressed(Input.Keys.F1))
            System.out.println("Frametime: " + 1.f / Gdx.graphics.getDeltaTime());

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            goBackToMenu();

    }

    @Override
    public void render(float delta) {
        // Call update first
        update(Gdx.graphics.getDeltaTime());

        // Clear screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Setup the sprite renderer
        SpriteBatch spriteBatch = mApp.getSpriteBatch();
        spriteBatch.begin();
        spriteBatch.setProjectionMatrix(mCamera.getTransform());
        // Setup the shape renderer
        ShapeRenderer shapeRenderer = mApp.getShapeRenderer();
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setProjectionMatrix(mCamera.getTransform());
        // Now render
        mLevel.render(spriteBatch, shapeRenderer, mCamera);
        spriteBatch.end();

        // Render Player
        // Setup ShapeRenderer
        shapeRenderer.setProjectionMatrix(mCamera.getTransform());
        mPlayer.render(shapeRenderer);
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        mCamera.updateViewport(width, height);
        mCamera.updateViewport(1280, 720);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        mLevel.dispose();
    }

    public void goBackToMenu() {
        mApp.getScreen().dispose();
        mApp.setScreen(mApp.getMenuScene());
    }

}

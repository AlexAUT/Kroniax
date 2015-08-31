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
            mMap = new TileMap();
        }

        // Load the level (properties, collision handler, renderer) and the
        // start values of the player
        try {
            mLevel = new Level(mMap);

            mPlayer = new Player(mLevel.getProperties());
        } catch (Exception e) {
            // Level not compatible
            System.out.println(e.getMessage());
            System.exit(-1);
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
        mLevel.update(deltaTime);
        mPlayer.update(deltaTime);
        mCamera.update(mPlayer.getPosition());

        // Check collision
        if (mLevel.checkCollision(mPlayer))
            System.exit(0);

        if (Gdx.input.isKeyPressed(Input.Keys.F1))
            System.out.println("Frametime: " + 1.f / Gdx.graphics.getDeltaTime());

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            mApp.setScreen(mApp.getMenuScene());

    }

    @Override
    public void render(float delta) {
        // Call update first
        update(Gdx.graphics.getDeltaTime());

        // Clear screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Now render
        SpriteBatch spriteBatch = mApp.getSpriteBatch();
        spriteBatch.begin();
        spriteBatch.setProjectionMatrix(mCamera.getTransform());
        mLevel.render(spriteBatch);
        spriteBatch.end();

        // Render Player
        // Setup ShapeRenderer
        ShapeRenderer shapeRenderer = mApp.getShapeRenderer();
        shapeRenderer.setProjectionMatrix(mCamera.getTransform());
        mPlayer.render(shapeRenderer);
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

}

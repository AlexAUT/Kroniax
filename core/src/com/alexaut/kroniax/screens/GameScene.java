package com.alexaut.kroniax.screens;

import com.alexaut.kroniax.Application;
import com.alexaut.kroniax.game.Camera;
import com.alexaut.kroniax.game.Level;
import com.alexaut.kroniax.game.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class GameScene implements Screen {

    final Application mApp;

    Camera mCamera;

    Level mLevel;
    Player mPlayer;

    public GameScene(Application app) {
        mApp = app;

        mCamera = new Camera();

        TiledMap levelMap = new TmxMapLoader().load("data/levels/official/level1.tmx");

        // Load the level (properties, collision handler, renderer) and the
        // start values of the player
        try {
            mLevel = new Level(levelMap);

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
        mLevel.update(deltaTime);
        mPlayer.update(deltaTime);
        mCamera.update(mPlayer.getPosition());

        // Check collision
        if (mLevel.checkCollision(mPlayer))
            System.exit(0);

        if (Gdx.input.isKeyPressed(Input.Keys.F1))
            System.out.println("Frametime: " + 1.f / Gdx.graphics.getDeltaTime());
    }

    @Override
    public void render(float delta) {
        // Call update first
        update(Gdx.graphics.getDeltaTime());

        // Clear screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Now render
        mLevel.render(mCamera);

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

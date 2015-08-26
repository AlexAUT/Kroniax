package com.alexaut.kroniax.screens;

import java.util.Iterator;

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

        Iterator<String> it = levelMap.getProperties().getKeys();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        try {
            mLevel = new Level(levelMap);

            mPlayer = new Player(mLevel.getProperties());
        } catch (IndexOutOfBoundsException e) {
            // Level not compatible

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
        // TODO Auto-generated method stub

    }

}

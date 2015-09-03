package com.alexaut.kroniax.screens;

import com.alexaut.kroniax.Application;
import com.alexaut.kroniax.game.Camera;
import com.alexaut.kroniax.game.GameController;
import com.alexaut.kroniax.game.Player;
import com.alexaut.kroniax.game.GameController.State;
import com.alexaut.kroniax.game.level.Level;
import com.alexaut.kroniax.game.tilemap.TileMap;
import com.alexaut.kroniax.game.tilemap.TileMapLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameScene implements Screen {

    final Application mApp;

    int mLvlNumber;

    GameController mGameController;

    Camera mCamera;

    Level mLevel;
    Player mPlayer;

    boolean mStarted = false;

    TileMap mMap;

    Music mMusic;

    public GameScene(Application app, int lvlNumber, Music music) {
        mApp = app;
        mLvlNumber = lvlNumber;

        mGameController = new GameController(app);
        Gdx.input.setInputProcessor(mGameController);

        mCamera = new Camera();

        try {
            mMap = new TileMapLoader().load(Gdx.files.internal("data/levels/official/level" + lvlNumber + ".kroniax"));
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

        mMusic = music;
        if (mMusic == null) {
            mMusic = Gdx.audio
                    .newMusic(Gdx.files.internal("data/music/GalaxyNewElectroHouseTechnobyMafiaFLairBeatz.ogg"));
            mMusic.setVolume(0.5f);
            mMusic.setLooping(true);
            mMusic.play();
        }

    }

    @Override
    public void show() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
    }

    public void update(float deltaTime) {
        mGameController.update(deltaTime);

        if (mGameController.isRunning()) {
            mLevel.update(deltaTime, mGameController, mPlayer, mCamera);
            mPlayer.update(deltaTime);

            // Check collision
            if (mLevel.checkCollision(mPlayer)) {
                mGameController.setState(State.CRASHED);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.F1))
                System.out.println("Frametime: " + 1.f / Gdx.graphics.getDeltaTime());
        }
        // Always update camera
        mCamera.update(mPlayer.getPosition(), mLevel.getProperties().fixedCamera);

        // Check if we have to go back to menu
        if (mGameController.getState() == State.BACK_TO_MENU)
            goBackToMenu();

        // Check if we have to set the player to the last checkpoint
        if (mGameController.getState() == State.RESET_TO_CHECKPOINT) {
            mPlayer.resetToCheckPoint();
            mGameController.setState(State.AT_START);
            mLevel.resetScripts();
        }

        if (mGameController.getState() == State.FINISHED)
            mApp.getProgressManager().finishedLevel(mLvlNumber);

        // Check if we need to load the next level
        if (mGameController.getState() == State.LOAD_NEXT_LEVEL) {
            Music cacheMusic = mMusic;
            mMusic = null;
            dispose();
            mApp.setScreen(new GameScene(mApp, mLvlNumber + 1, cacheMusic));

        }

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

        mGameController.render(shapeRenderer);
    }

    @Override
    public void resize(int width, int height) {
        mCamera.updateViewport(width, height);
        mCamera.updateViewport(1280, 720);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        mMusic.pause();
    }

    @Override
    public void resume() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        mMusic.play();
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        if (mMusic != null)
            mMusic.stop();
    }

    @Override
    public void dispose() {
        mLevel.dispose();
        if (mMusic != null)
            mMusic.dispose();
    }

    public void goBackToMenu() {
        mApp.getScreen().dispose();
        mApp.setScreen(mApp.getMenuScene());
    }

}

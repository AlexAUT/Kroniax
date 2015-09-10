package com.alexaut.kroniax;

import com.alexaut.kroniax.game.ProgressManager;
import com.alexaut.kroniax.screens.MenuScene;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Application extends Game {

    private SpriteBatch mSpriteBatch;
    private ShapeRenderer mShapeRenderer;

    private Skin mSkin;

    private ProgressManager mProgressManager;
    private Preferences mSettings;

    private MenuScene mMenuScene;

    @Override
    public void create() {
        mSpriteBatch = new SpriteBatch();
        mShapeRenderer = new ShapeRenderer();
        mSkin = new Skin(Gdx.files.internal("data/skins/menu.json"));
        mProgressManager = new ProgressManager();
        mSettings = Gdx.app.getPreferences("settings");

        mMenuScene = new MenuScene(this);

        setScreen(mMenuScene);
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        super.dispose();
        mSpriteBatch.dispose();
        mShapeRenderer.dispose();
        mMenuScene.dispose();
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        super.pause();
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        super.resume();
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        super.resize(width, height);
    }

    public SpriteBatch getSpriteBatch() {
        return mSpriteBatch;
    }

    public ShapeRenderer getShapeRenderer() {
        return mShapeRenderer;
    }

    public Skin getGuiSkin() {
        return mSkin;
    }

    public ProgressManager getProgressManager() {
        return mProgressManager;
    }

    public MenuScene getMenuScene() {
        return mMenuScene;
    }

    public void toogleMusicEnabled() {
        boolean current = mSettings.getBoolean("music", true);
        mSettings.putBoolean("music", !current);
        mSettings.flush();
        mMenuScene.updateMusicState();
    }

    public boolean isMusicEnabled() {
        return mSettings.getBoolean("music", true);
    }

}

package com.alexaut.kroniax.menu;

import com.alexaut.kroniax.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Gui {

    final Application mApp;

    private Stage mStage;
    private Skin mSkin;

    private MainLayer mMainLayer;
    private LevelSelectionLayer mLevelSelectionLayer;

    public Gui(Application app) {
        mApp = app;

        mStage = new Stage(new StretchViewport(1280, 720));
        Gdx.input.setInputProcessor(mStage);

        mSkin = new Skin(Gdx.files.internal("data/skins/menu.json"));

        mMainLayer = new MainLayer(this);
        mStage.addActor(mMainLayer);

        mLevelSelectionLayer = new LevelSelectionLayer(this);
        mStage.addActor(mLevelSelectionLayer);
    }

    public void update(float deltaTime) {
        mStage.act(deltaTime);
    }

    public void render() {
        mStage.draw();
    }

    public void dispose() {
        mStage.dispose();
    }

    public void updateViewport(int width, int height) {
        mStage.getViewport().update(width, height, true);
    }

    public Skin getSkin() {
        return mSkin;
    }

    public enum Layer {
        MAIN, LEVEL_SELECTION, SETTINGS
    }

    public Table getLayer(Layer layer) {
        switch (layer) {
        case MAIN:
            return mMainLayer;
        case LEVEL_SELECTION:
            return mLevelSelectionLayer;
        default:
            return null;
        }
    }
}

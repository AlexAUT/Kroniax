package com.alexaut.kroniax.menu;

import com.alexaut.kroniax.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Gui {

    final Application mApp;

    public enum Layer {
        MAIN, LEVEL_SELECTION, SETTINGS, CREDITS
    }

    private Layer mActiveLayer = Layer.MAIN;

    private Stage mStage;
    
    private Skin mSkin;

    private MainLayer mMainLayer;
    private LevelSelectionLayer mLevelSelectionLayer;
    private CreditsLayer mCreditsLayer;

    public Gui(Application app) {
        mApp = app;

        mStage = new Stage(new StretchViewport(1280, 720));

        mSkin = app.getGuiSkin();

        mMainLayer = new MainLayer(this);
        mStage.addActor(mMainLayer);

        mLevelSelectionLayer = new LevelSelectionLayer(this);
        mStage.addActor(mLevelSelectionLayer);

        mCreditsLayer = new CreditsLayer(this);
        mStage.addActor(mCreditsLayer);
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

    public Application getApplication() {
        return mApp;
    }

    public Skin getSkin() {
        return mSkin;
    }

    public Table getLayer(Layer layer) {
        switch (layer) {
        case MAIN:
            return mMainLayer;
        case LEVEL_SELECTION:
            return mLevelSelectionLayer;
        case CREDITS:
            return mCreditsLayer;
        default:
            return null;
        }
    }

    public void setActiveLayer(Layer active) {
        mActiveLayer = active;
    }

    private Table getActiveLayer() {
        switch (mActiveLayer) {
        case MAIN:
            return mMainLayer;
        case LEVEL_SELECTION:
            return mLevelSelectionLayer;
        case CREDITS:
            return mCreditsLayer;
        default:
            return null;
        }
    }

    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    public void show() {
        Gdx.input.setInputProcessor(mStage);
    }

    public void fadeActiveIn() {
        getActiveLayer().addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1)));
    }
}

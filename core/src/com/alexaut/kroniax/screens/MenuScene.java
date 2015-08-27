package com.alexaut.kroniax.screens;

import com.alexaut.kroniax.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class MenuScene implements Screen {

    private final Application mApp;

    private Stage mStage;
    private VisTable mRootTable;

    private VisTextButton mStartButton;

    public MenuScene(Application app) {
        mApp = app;

        VisUI.load();
        VisUI.setDefaultTitleAlign(Align.center);

        mStage = new Stage();
        Gdx.input.setInputProcessor(mStage);

        mRootTable = new VisTable();
        mRootTable.setFillParent(true);
        mStage.addActor(mRootTable);

        mStartButton = new VisTextButton("Play");
        mRootTable.add(mStartButton);
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(float delta) {
        mStage.act(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mStage.draw();

        // Debug drawing
        mRootTable.drawDebug(mApp.getShapeRenderer());

    }

    @Override
    public void resize(int width, int height) {
        mStage.getViewport().update(1280, 720);

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        mStage.dispose();
        VisUI.dispose();
    }
}

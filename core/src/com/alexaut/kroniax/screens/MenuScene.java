package com.alexaut.kroniax.screens;

import com.alexaut.kroniax.Application;
import com.alexaut.kroniax.menu.Gui;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class MenuScene implements Screen {

    private Gui mGui;

    public MenuScene(Application app) {

        mGui = new Gui(app);
    }

    @Override
    public void show() {
        mGui.show();
        mGui.fadeActiveIn();
    }

    @Override
    public void render(float delta) {
        mGui.update(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // mStage.getViewport().setCamera(mCamera);

        mGui.render();
    }

    @Override
    public void resize(int width, int height) {
        mGui.updateViewport(width, height);
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
        mGui.hide();
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        mGui.dispose();
    }
}

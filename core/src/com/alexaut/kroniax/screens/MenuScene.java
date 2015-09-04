package com.alexaut.kroniax.screens;

import com.alexaut.kroniax.Application;
import com.alexaut.kroniax.menu.Gui;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScene implements Screen {

    private Gui mGui;

    private Music mMusic;
    
    private SpriteBatch mSpriteBatch;

    public MenuScene(Application app) {
        mGui = new Gui(app);
        
        mSpriteBatch = app.getSpriteBatch();
    }

    @Override
    public void show() {
        mMusic = Gdx.audio.newMusic(Gdx.files.internal("data/music/PowerFight-ElectroTechnoBeat.ogg"));
        mMusic.setVolume(0.75f);
        mMusic.play();
        mMusic.setLooping(true);

        mGui.show();
        mGui.fadeActiveIn();
    }

    @Override
    public void render(float delta) {
        mGui.update(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // mStage.getViewport().setCamera(mCamera);
        
        mSpriteBatch.enableBlending();
        mSpriteBatch.begin();
        mGui.render(mSpriteBatch);
        mSpriteBatch.end();
        mSpriteBatch.disableBlending();
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
        mMusic.play();
    }

    @Override
    public void hide() {
        mGui.hide();
        mMusic.stop();
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        mGui.dispose();
        mMusic.dispose();
    }
}

package com.alexaut.kroniax.screens;

import com.alexaut.kroniax.Application;
import com.alexaut.kroniax.menu.Gui;
import com.alexaut.kroniax.menu.MenuBackground;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MenuScene implements Screen {
    
    private MenuBackground mBackground;
    private Gui mGui;

    private Music mMusic;
    
    private SpriteBatch mSpriteBatch;
    private ShapeRenderer mShapeRenderer;

    public MenuScene(Application app) {
        mBackground = new MenuBackground();
        mGui = new Gui(app);
        
        mSpriteBatch = app.getSpriteBatch();
        mShapeRenderer = app.getShapeRenderer();
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
        mBackground.update(Gdx.graphics.getDeltaTime());
        mGui.update(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // mStage.getViewport().setCamera(mCamera);
        
        mBackground.render(mSpriteBatch, mShapeRenderer);
        
        mSpriteBatch.begin();
        mGui.render(mSpriteBatch);
        mSpriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        mGui.updateViewport(width, height);
        mBackground.updateViewport(width, height);
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

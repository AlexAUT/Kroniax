package com.alexaut.kroniax;

import com.alexaut.kroniax.screens.GameScene;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Application extends Game {

    private SpriteBatch mSpriteBatch;
    private ShapeRenderer mShapeRenderer;

    @Override
    public void create() {

        mSpriteBatch = new SpriteBatch();
        mShapeRenderer = new ShapeRenderer();

        setScreen(new GameScene(this));
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        super.dispose();
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

}

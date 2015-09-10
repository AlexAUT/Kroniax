package com.alexaut.kroniax.game;

import com.alexaut.kroniax.Application;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameHUD {
    private float mTime;
    private int mTries;

    private BitmapFont mFont;

    private OrthographicCamera mCamera;

    public GameHUD(Application app) {
        mTime = 0;
        mTries = 0;

        mFont = app.getGuiSkin().getFont("default-font");

        mCamera = new OrthographicCamera(1280, 720);
        mCamera.position.set(640, 360, 0);
        mCamera.update();
    }

    public void addDeath() {
        mTries++;
    }

    public void update(float deltaTime) {
        mTime += deltaTime;
    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(mCamera.combined);
        spriteBatch.enableBlending();
        mFont.draw(spriteBatch, "Time: " + (int) (mTime * 10.f) / 10.f, 5, 30);
        mFont.draw(spriteBatch, "Deaths: " + mTries, 5, 60);
        spriteBatch.disableBlending();
    }
}

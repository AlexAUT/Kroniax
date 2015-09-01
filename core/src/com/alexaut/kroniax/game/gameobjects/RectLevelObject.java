package com.alexaut.kroniax.game.gameobjects;

import com.alexaut.kroniax.game.LevelObject;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class RectLevelObject extends LevelObject {

    private int mXPos, mYPos, mWidth, mHeight;

    public RectLevelObject(String type, int x, int y, int width, int height) {
        super(type);
        mWidth = width;
        mHeight = height;
        mXPos = x;
        mYPos = y - height;
    }

    @Override
    public boolean checkCollision(int x, int y) {
        if (x > mXPos && x < (mXPos + mWidth)) {
            if (y > mYPos && y > (mYPos + mHeight))
                return true;
        }

        return false;
    }

    @Override
    public void render(ShapeRenderer renderer) {
        renderer.setColor(getColor());
        renderer.rect(mXPos, mYPos, mWidth, mHeight);
    }

}

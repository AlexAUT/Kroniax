package com.alexaut.kroniax.game.gameobjects;

import com.alexaut.kroniax.game.level.LevelObject;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

public class RectLevelObject extends LevelObject {

    private int mXPos, mYPos, mWidth, mHeight;

    static Vector2 area = new Vector2();

    public RectLevelObject(String type, int x, int y, int width, int height) {
        super(type);
        mWidth = width;
        mHeight = height;
        mXPos = x;
        mYPos = y - height;
    }

    @Override
    public boolean checkCollision(Vector2 p1, Vector2 p2) {
        //Intersect left edge
        if (Intersector.intersectSegments(p1.x, p1.y, p2.x, p2.y, mXPos, mYPos, mXPos, mYPos + mHeight, null))
            return true;
        
        //Intersect right edge
        if (Intersector.intersectSegments(p1.x, p1.y, p2.x, p2.y, mXPos + mWidth, mYPos, mXPos + mWidth, mYPos + mHeight, null))
            return true;
        
        //Intersect top edge
        if (Intersector.intersectSegments(p1.x, p1.y, p2.x, p2.y, mXPos, mYPos + mHeight, mXPos + mWidth, mYPos + mHeight, null))
            return true;
        
        //Intersect bottom edge
        if (Intersector.intersectSegments(p1.x, p1.y, p2.x, p2.y, mXPos, mYPos, mXPos + mWidth, mYPos, null))
            return true;

        return false;
    }

    @Override
    public void render(ShapeRenderer renderer) {
        renderer.setColor(getColor());
        renderer.rect(mXPos, mYPos, mWidth, mHeight);
    }

}

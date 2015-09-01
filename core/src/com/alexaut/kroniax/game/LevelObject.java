package com.alexaut.kroniax.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class LevelObject {
    private String mType;

    private final Color mColor;

    protected LevelObject(String type) {
        mType = type;
        mColor = getColorFromType();
    }

    // Override these
    public boolean checkCollision(Vector2 p1, Vector2 p2) {
        return false;
    }

    public void render(ShapeRenderer renderer) {

    }

    public String getType() {
        return mType;
    }

    public Color getColor() {
        return mColor;
    }

    private Color getColorFromType() {
        if (mType.equalsIgnoreCase("finish"))
            return Color.GREEN;
        else if (mType.equalsIgnoreCase("speed_change"))
            return Color.CYAN;
        else if (mType.equalsIgnoreCase("gravity_change"))
            return Color.YELLOW;

        return Color.WHITE;
    }
}

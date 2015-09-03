package com.alexaut.kroniax.game.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PlayerScriptTimer {
    private float mValues[];
    private Color mColors[];

    private int mCurrentIndex;

    public PlayerScriptTimer() {
        mValues = new float[3];
        mColors = new Color[3];

        mCurrentIndex = 0;
    }

    public void reset() {
        mCurrentIndex = 0;
    }

    public void addTimer(float value, Color color) {
        if (mCurrentIndex < mValues.length) {
            mValues[mCurrentIndex] = value;
            mColors[mCurrentIndex] = color;
            mCurrentIndex++;
        }
    }

    public void render(ShapeRenderer renderer, Player player, PlayerShip ship) {
        float x = (ship.getCollisionPoints()[1].x - ship.getCollisionPoints()[2].x) * 0.5f;
        x += ship.getCollisionPoints()[1].x;
        float y = (ship.getCollisionPoints()[1].y - ship.getCollisionPoints()[2].y) * 0.5f;
        y += ship.getCollisionPoints()[1].y;

        float offsetX = (ship.getCollisionPoints()[0].x - player.getPosition().x);
        float offsetY = (ship.getCollisionPoints()[0].y - player.getPosition().y);

        for (int i = 0; i < mCurrentIndex; i++) {
            renderer.setColor(mColors[i]);
            renderer.arc(x + (i * offsetX), y + (i * offsetY), 12.f, 90.f, 360.f * mValues[i]);
        }
        System.out.println(mCurrentIndex);
    }
}

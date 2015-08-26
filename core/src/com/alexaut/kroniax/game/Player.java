package com.alexaut.kroniax.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;

public class Player {

    private Vector2 mPosition;
    private Vector2 mSize;

    private float mVelocity;
    private float mAngle;

    private float[] mPoints;

    public Player(MapProperties props) {

        mPosition = new Vector2();
        mPosition.x = Integer.parseInt(props.get("spawn_x", String.class)) * props.get("tilewidth", Integer.class);
        mPosition.y = Integer.parseInt(props.get("spawn_y", String.class)) * props.get("tileheight", Integer.class);

        System.out.println(mPosition.x + " " + mPosition.y);

        mSize = new Vector2(50, 35);
        mVelocity = 200.f;

        mPoints = new float[8];
    }

    public void update(float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.W))
            mAngle += deltaTime;
        if (Gdx.input.isKeyPressed(Input.Keys.S))
            mAngle -= deltaTime;

        final float cos = (float) Math.cos(mAngle);
        final float sin = (float) Math.sin(mAngle);
        mPosition.mulAdd(new Vector2(cos * mVelocity, sin * mVelocity), deltaTime);

        updatePoints();
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.WHITE);
        renderer.begin(ShapeType.Filled);

        renderer.triangle(mPoints[0], mPoints[1], mPoints[2], mPoints[3], mPoints[6], mPoints[7]);
        renderer.triangle(mPoints[0], mPoints[1], mPoints[4], mPoints[5], mPoints[6], mPoints[7]);

        renderer.end();
    }

    public void updatePoints() {
        final float cos = (float) Math.cos(mAngle);
        final float sin = (float) Math.sin(mAngle);

        final float halfXSize = mSize.x / 2.f;
        final float halfYSize = mSize.y / 2.f;

        // Calculate right point
        mPoints[0] = mPosition.x + (halfXSize * cos);
        mPoints[1] = mPosition.y + (halfXSize * sin);

        // Calculate left upper point
        mPoints[2] = mPosition.x - (halfXSize * cos) - (halfYSize * sin);
        mPoints[3] = mPosition.y + (halfYSize * cos) - (halfXSize * sin);

        // Calculate left bottom point
        mPoints[4] = mPosition.x - (halfXSize * cos) + (halfYSize * sin);
        mPoints[5] = mPosition.y - (halfYSize * cos) - (halfXSize * sin);

        // Optical point middle left
        mPoints[6] = mPosition.x - (halfXSize * cos / 2.f);
        mPoints[7] = mPosition.y - (halfXSize * sin / 2.f);
    }

    public Vector2 getPosition() {
        return mPosition;
    }

    public void setPosition(Vector2 position) {
        mPosition = position;
    }

}

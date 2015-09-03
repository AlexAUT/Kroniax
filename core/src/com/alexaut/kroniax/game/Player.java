package com.alexaut.kroniax.game;

import com.alexaut.kroniax.game.level.LevelProperties;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Player {

    private Vector2 mPosition;
    private Vector2 mSize;

    private float mVelocity;
    private float mAngle;
    private float mGravity;

    private float[] mPoints;
    private Vector2 mCollisionPoints[];
    private Vector2 mOldPosOfRightPoint;

    boolean mAlive;
    
    private Vector2 mCachePosition;
    private Vector2 mCacheSize;
    private float mCacheVelocity;
    private float mCacheAngle;
    private float mCacheGravity;

    public Player(LevelProperties props) {

        mPosition = new Vector2(props.spawn);

        mSize = new Vector2(50, 35);
        mVelocity = props.velocity;
        mGravity = props.gravity;

        mPoints = new float[8];
        mCollisionPoints = new Vector2[3];
        mCollisionPoints[0] = new Vector2();
        mCollisionPoints[1] = new Vector2();
        mCollisionPoints[2] = new Vector2();
        mOldPosOfRightPoint = new Vector2();

        mAlive = true;

        updatePoints();

        mOldPosOfRightPoint.set(mCollisionPoints[0]);
        
        //Cache start values for resetting the player
        mCachePosition = new Vector2(mPosition);
        mCacheSize = new Vector2(mSize);
        mCacheVelocity = mVelocity;
        mCacheAngle = mAngle;
        mCacheGravity = mGravity;
    }

    public void update(float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.W))
            mAngle += deltaTime;
        if (Gdx.input.isKeyPressed(Input.Keys.S))
            mAngle -= deltaTime;

        // Update angle with gravity and user input
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isTouched())
            mAngle += mGravity * deltaTime / 100.f;
        else
            mAngle -= mGravity * deltaTime / 100.f;

        final float cos = (float) Math.cos(mAngle);
        final float sin = (float) Math.sin(mAngle);
        mPosition.mulAdd(new Vector2(cos * mVelocity, sin * mVelocity), deltaTime);

        updatePoints();
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.WHITE);
        renderer.triangle(mPoints[0], mPoints[1], mPoints[2], mPoints[3], mPoints[6], mPoints[7]);
        renderer.triangle(mPoints[0], mPoints[1], mPoints[4], mPoints[5], mPoints[6], mPoints[7]);
    }

    public void updatePoints() {
        final float cos = (float) Math.cos(mAngle);
        final float sin = (float) Math.sin(mAngle);

        final float halfXSize = mSize.x / 2.f;
        final float halfYSize = mSize.y / 2.f;

        // Round the position, because we round the camera too
        // Otherwise the ship would jump pixels and not fly smooth
        final float pos_x = Math.round(mPosition.x);
        final float pos_y = Math.round(mPosition.y);

        // Calculate right point
        mPoints[0] = pos_x + (halfXSize * cos);
        mPoints[1] = pos_y + (halfXSize * sin);

        // Calculate left upper point
        mPoints[2] = pos_x - (halfXSize * cos) - (halfYSize * sin);
        mPoints[3] = pos_y + (halfYSize * cos) - (halfXSize * sin);

        // Calculate left bottom point
        mPoints[4] = pos_x - (halfXSize * cos) + (halfYSize * sin);
        mPoints[5] = pos_y - (halfYSize * cos) - (halfXSize * sin);

        // Optical point middle left
        mPoints[6] = pos_x - (halfXSize * cos / 2.f);
        mPoints[7] = pos_y - (halfXSize * sin / 2.f);

        // Update Collision points
        mOldPosOfRightPoint.set(mCollisionPoints[0]);
        mCollisionPoints[0].set(mPoints[0], mPoints[1]);
        mCollisionPoints[1].set(mPoints[2], mPoints[3]);
        mCollisionPoints[2].set(mPoints[4], mPoints[5]);
    }

    public Vector2 getPosition() {
        return mPosition;
    }

    public void setPosition(Vector2 position) {
        mPosition = position;
    }

    public Vector2[] getCollisionPoints() {
        return mCollisionPoints;
    }

    public Vector2 getOldRightPosition() {
        return mOldPosOfRightPoint;
    }

    public void setAlive(boolean alive) {
        mAlive = alive;
    }

    public void changeVelocity(float addition) {
        mVelocity += addition;
    }

    public void changeGravity(float addition) {
        mGravity += addition;
    }

    public boolean isAlive() {
        return mAlive;
    }
    
    public void addCheckPoint(float x, float y, float angle) {
        mCachePosition.set(x, y);
        mCacheSize.set(mSize);
        mCacheVelocity = mVelocity;
        mCacheAngle = angle;
        mCacheGravity = mGravity;
        System.out.println("Added checkpoint");
    }
    
    public void resetToCheckPoint() {
        mPosition.set(mCachePosition);
        mSize.set(mCacheSize);
        mVelocity = mCacheVelocity;
        mAngle = mCacheAngle;
        mGravity = mCacheGravity;
        //Update player model
        updatePoints();
        //We need to reset the old position hardcoded, otherwise it would collide infinite
        mOldPosOfRightPoint.set(mCollisionPoints[0]);
        //Set the play back again to live
        setAlive(true);
    }

}

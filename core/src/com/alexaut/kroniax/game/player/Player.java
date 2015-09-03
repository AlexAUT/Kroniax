package com.alexaut.kroniax.game.player;

import com.alexaut.kroniax.game.level.LevelProperties;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Player {

    private Vector2 mPosition;
    private Vector2 mSize;

    private float mVelocity;
    private float mAngle;
    private float mGravity;
    
    private PlayerShip mShip;

    
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
        
        mShip = new PlayerShip();
        mShip.updatePoints(this);
        
        // Cache start values for resetting the player
        mCachePosition = new Vector2(mPosition);
        mCacheSize = new Vector2(mSize);
        mCacheVelocity = mVelocity;
        mCacheAngle = mAngle;
        mCacheGravity = mGravity;
    }

    public void update(float deltaTime) {
        // Update angle with gravity and user input
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isTouched())
            mAngle += mGravity * deltaTime / 100.f;
        else
            mAngle -= mGravity * deltaTime / 100.f;

        final float cos = (float) Math.cos(mAngle);
        final float sin = (float) Math.sin(mAngle);
        mPosition.mulAdd(new Vector2(cos * mVelocity, sin * mVelocity), deltaTime);

        mShip.updatePoints(this);
    }

    public void render(ShapeRenderer renderer) {
        //Render the ship
        mShip.render(renderer);
    }

    public Vector2[] getCollisionPoints() {
        return mShip.getCollisionPoints();
    }

    public Vector2 getOldRightPosition() {
        return mShip.getOldRightPosition();
    }

    public void changeVelocity(float addition) {
        mVelocity += addition;
    }

    public void changeGravity(float addition) {
        mGravity += addition;
    }

    public void addCheckPoint(float x, float y, float angle) {
        mCachePosition.set(x, y);
        mCacheSize.set(mSize);
        mCacheVelocity = mVelocity;
        mCacheAngle = angle;
        mCacheGravity = mGravity;
    }

    public void resetToCheckPoint() {
        mPosition.set(mCachePosition);
        mSize.set(mCacheSize);
        mVelocity = mCacheVelocity;
        mAngle = mCacheAngle;
        mGravity = mCacheGravity;
        // Update player model
        mShip.updatePoints(this);
        // We need to reset the old position hardcoded, otherwise it would
        // collide infinite
        mShip.resetAfterCheckPoint();
    }
    
    public float getAngle() {
        return mAngle;
    }
    
    public void setPosition(Vector2 position) {
        mPosition = position;
    }
    
    public Vector2 getPosition() {
        return mPosition;
    }
    
    public Vector2 getSize() {
        return mSize;
    }

}

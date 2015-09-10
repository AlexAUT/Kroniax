package com.alexaut.kroniax.game.player;

import com.alexaut.kroniax.game.level.LevelProperties;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Player {

    private Vector2 mPosition;
    private Vector2 mSize;

    private Vector2 mVelocity;
    private float mGravity;

    private PlayerShip mShip;
    private PlayerScriptTimer mScriptTimers;

    private Vector2 mCachePosition;
    private Vector2 mCacheSize;
    private Vector2 mCacheVelocity;
    private float mCacheGravity;

    public Player(LevelProperties props) {

        mPosition = new Vector2(props.spawn);

        mSize = new Vector2(50, 35);
        mVelocity = new Vector2(props.velocity, 0.f);
        mGravity = props.gravity;

        mShip = new PlayerShip();
        mShip.updatePoints(this);

        mScriptTimers = new PlayerScriptTimer();

        // Cache start values for resetting the player
        mCachePosition = new Vector2(mPosition);
        mCacheSize = new Vector2(mSize);
        mCacheVelocity = new Vector2(mVelocity.x, 0.f);
        mCacheGravity = mGravity;
    }

    public void update(float deltaTime) {
        // Update angle with gravity and user input
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isTouched())
            mVelocity.y += mGravity * deltaTime;
        else
            mVelocity.y -= mGravity * deltaTime;

        mPosition.mulAdd(mVelocity, deltaTime);

        mShip.updatePoints(this);

        mScriptTimers.reset();
    }

    public void render(ShapeRenderer renderer) {
        // Render the ship
        mShip.render(renderer);
        mScriptTimers.render(renderer, this, mShip);
    }

    public Vector2[] getCollisionPoints() {
        return mShip.getCollisionPoints();
    }

    public Vector2 getOldRightPosition() {
        return mShip.getOldRightPosition();
    }

    public void changeVelocity(float addition) {
        mVelocity.x += addition;
    }

    public void changeGravity(float addition) {
        mGravity += addition;
    }

    public void changeSize(float increaseX, float increaseY) {
        mSize.add(increaseX, increaseY);
    }

    public void addCheckPoint(float x, float y, float yVelocity) {
        mCachePosition.set(x, y);
        mCacheSize.set(mSize);
        mCacheVelocity.set(mVelocity.x, 0);
        mCacheGravity = mGravity;
    }

    public void resetToCheckPoint() {
        mPosition.set(mCachePosition);
        mSize.set(mCacheSize);
        mVelocity.set(mCacheVelocity);
        mGravity = mCacheGravity;
        // Update player model
        mShip.updatePoints(this);
        // We need to reset the old position hardcoded, otherwise it would
        // collide infinite
        mShip.resetAfterCheckPoint();
        mScriptTimers.reset();
    }

    public Vector2 getVelocity() {
        return mVelocity;
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

    public void updateScriptTimer(float value, Color color) {
        mScriptTimers.addTimer(value, color);
    }
}

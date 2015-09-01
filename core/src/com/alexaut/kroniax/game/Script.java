package com.alexaut.kroniax.game;

public class Script {
    private boolean mRunning;

    public Script() {
        mRunning = false;
    }

    public boolean isAlive() {
        return mRunning;
    }

    public void start() {
        mRunning = true;
    }

    public void stop() {
        mRunning = false;
    }

    public void update(float deltaTime, Level level, Player player, Camera camera) {

    }
}

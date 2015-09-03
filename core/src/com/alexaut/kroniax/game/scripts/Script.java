package com.alexaut.kroniax.game.scripts;

import com.alexaut.kroniax.game.Camera;
import com.alexaut.kroniax.game.GameController;
import com.alexaut.kroniax.game.Player;
import com.alexaut.kroniax.game.level.Level;

public abstract class Script {
    private boolean mRunning;
    private boolean mStarted;

    public Script() {
        mRunning = false;
        mStarted = false;
    }

    public boolean isAlive() {
        return mRunning;
    }

    public void start() {
        mRunning = true;
        mStarted = true;
    }

    public void stop() {
        mRunning = false;
        mStarted = true;
    }

    public void reset() {
        mRunning = false;
        mStarted = false;
    }

    public boolean alreadyStarted() {
        return mStarted;
    }

    public abstract void update(float deltaTime, GameController gameController, Level level, Player player, Camera camera);
}

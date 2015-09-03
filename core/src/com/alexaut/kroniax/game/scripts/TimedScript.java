package com.alexaut.kroniax.game.scripts;

import com.alexaut.kroniax.game.Camera;
import com.alexaut.kroniax.game.Player;
import com.alexaut.kroniax.game.level.Level;

public abstract class TimedScript extends Script {
    public float mDuration;
    public float mElapsedTime;

    public TimedScript(float duration) {
        mDuration = duration;
        mElapsedTime = 0;
    }

    @Override
    public void update(float deltaTime, Level level, Player player, Camera camera) {
        float interp = 0;
        if (mDuration == 0)
            interp = 1;
        else if (mElapsedTime + deltaTime <= mDuration) {
            interp = deltaTime / mDuration;
        } else {
            interp = (mDuration - mElapsedTime) / mDuration;
        }

        mElapsedTime += deltaTime;
        updateWithInterp(interp, level, player, camera);
        if (mElapsedTime >= mDuration)
            stop();
    }

    @Override
    public void reset() {
        super.reset();
        mElapsedTime = 0;
    }

    protected abstract void updateWithInterp(float interp, Level level, Player player, Camera camera);
}

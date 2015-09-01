package com.alexaut.kroniax.game.scripts;

import com.alexaut.kroniax.game.Camera;
import com.alexaut.kroniax.game.Level;
import com.alexaut.kroniax.game.Player;

public class SpeedChangeScript extends TimedScript {

    float mValue;

    public SpeedChangeScript(float duration, float value) {
        super(duration);
        mValue = value;
    }

    @Override
    protected void updateWithInterp(float interp, Level level, Player player, Camera camera) {
        player.changeVelocity(mValue * interp);
    }
}

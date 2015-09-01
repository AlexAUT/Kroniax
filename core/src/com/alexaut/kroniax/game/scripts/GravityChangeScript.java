package com.alexaut.kroniax.game.scripts;

import com.alexaut.kroniax.game.Camera;
import com.alexaut.kroniax.game.Level;
import com.alexaut.kroniax.game.Player;

public class GravityChangeScript extends TimedScript {
    float mValue;

    public GravityChangeScript(float duration, float value) {
        super(duration);
        mValue = value;
    }

    @Override
    protected void updateWithInterp(float interp, Level level, Player player, Camera camera) {
        player.changeGravity(mValue * interp);
    }
}

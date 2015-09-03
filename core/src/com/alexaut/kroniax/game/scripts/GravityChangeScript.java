package com.alexaut.kroniax.game.scripts;

import com.alexaut.kroniax.game.Camera;
import com.alexaut.kroniax.game.GameController;
import com.alexaut.kroniax.game.level.Level;
import com.alexaut.kroniax.game.player.Player;

public class GravityChangeScript extends TimedScript {
    float mValue;

    public GravityChangeScript(float duration, float value) {
        super(duration);
        mValue = value;
    }

    @Override
    protected void updateWithInterp(float interp, GameController gameController, Level level, Player player,
            Camera camera) {
        player.changeGravity(mValue * interp);
    }
}

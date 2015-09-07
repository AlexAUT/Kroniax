package com.alexaut.kroniax.game.scripts;

import com.alexaut.kroniax.game.Camera;
import com.alexaut.kroniax.game.GameController;
import com.alexaut.kroniax.game.level.Level;
import com.alexaut.kroniax.game.player.Player;
import com.badlogic.gdx.graphics.Color;

public class SizeChangeScript extends TimedScript {
    float mValueX, mValueY;

    public SizeChangeScript(float duration, float value_x, float value_y) {
        super(duration);
        mValueX = value_x;
        mValueY = value_y;
    }

    @Override
    protected void updateWithInterp(float interp, GameController gameController, Level level, Player player,
            Camera camera) {
        player.changeSize(mValueX * interp, mValueY * interp);
        player.updateScriptTimer(getElapsedPercentage(), Color.YELLOW);
    }
}

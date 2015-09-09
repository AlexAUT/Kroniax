package com.alexaut.kroniax.game.scripts;

import com.alexaut.kroniax.game.Camera;
import com.alexaut.kroniax.game.GameController;
import com.alexaut.kroniax.game.level.Level;
import com.alexaut.kroniax.game.player.Player;
import com.badlogic.gdx.graphics.Color;

public class CameraRotateScript extends TimedScript {
        float mAngle;
        float mOffsetX, mOffsetY;

        public CameraRotateScript(float duration, float angle, float offset_x, float offset_y) {
            super(duration);
            mAngle = angle;
            mOffsetX = offset_x;
            mOffsetY = offset_y;
        }

        @Override
        protected void updateWithInterp(float interp, GameController gameController, Level level, Player player,
                Camera camera) {
            camera.getOrthoCamera().rotate(mAngle * interp);
            camera.changeOffset(mOffsetX * interp, mOffsetY * interp);

            player.updateScriptTimer(getElapsedPercentage(), Color.GOLD);
        }
}

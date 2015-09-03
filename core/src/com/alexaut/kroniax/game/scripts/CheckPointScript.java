package com.alexaut.kroniax.game.scripts;

import com.alexaut.kroniax.game.Camera;
import com.alexaut.kroniax.game.GameController;
import com.alexaut.kroniax.game.Player;
import com.alexaut.kroniax.game.level.Level;

public class CheckPointScript extends Script {
    float mStartX, mStartY, mStartAngle;

    public CheckPointScript(float start_x, float start_y, float start_angle) {
        super();

        mStartX = start_x;
        mStartY = start_y;
        mStartAngle = start_angle;
    }

    public void update(float deltaTime, GameController gameController, Level level, Player player, Camera camera) {
        // Convert startpos to pixels
        float x = mStartX * level.getProperties().tileSize.x;
        float y = (level.getProperties().tileCount.y - mStartY) * level.getProperties().tileSize.y;
        player.addCheckPoint(x, y, mStartAngle);

        // Signals that the event shouldnt get triggered again
        stop();
    }
}

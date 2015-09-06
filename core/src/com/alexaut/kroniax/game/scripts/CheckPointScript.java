package com.alexaut.kroniax.game.scripts;

import com.alexaut.kroniax.game.Camera;
import com.alexaut.kroniax.game.GameController;
import com.alexaut.kroniax.game.level.Level;
import com.alexaut.kroniax.game.player.Player;

public class CheckPointScript extends Script {
    float mStartX, mStartY, mStartYVelocity;

    public CheckPointScript(float start_x, float start_y, float start_y_velocity) {
        super();

        mStartX = start_x;
        mStartY = start_y;
        mStartYVelocity = start_y_velocity;
    }

    public void update(float deltaTime, GameController gameController, Level level, Player player, Camera camera) {
        // Convert startpos to pixels
        float x = mStartX * level.getProperties().tileSize.x;
        float y = (level.getProperties().tileCount.y - mStartY) * level.getProperties().tileSize.y;
        player.addCheckPoint(x, y, mStartYVelocity);

        // Signals that the event shouldn't get triggered again
        stop();
    }
}

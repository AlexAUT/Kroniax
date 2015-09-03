package com.alexaut.kroniax.game.scripts;

import com.alexaut.kroniax.game.Camera;
import com.alexaut.kroniax.game.GameController;
import com.alexaut.kroniax.game.Player;
import com.alexaut.kroniax.game.GameController.State;
import com.alexaut.kroniax.game.level.Level;

public class FinishScript extends Script {

    public FinishScript() {
        super();
    }

    public void update(float deltaTime, GameController gameController, Level level, Player player, Camera camera) {
        gameController.setState(State.FINISHED);

        // Signals that the event shouldn't get triggered again
        stop();
    }

}

package com.alexaut.kroniax.game.scripts;

import com.alexaut.kroniax.game.Camera;
import com.alexaut.kroniax.game.GameController;
import com.alexaut.kroniax.game.GameController.State;
import com.alexaut.kroniax.game.level.Level;
import com.alexaut.kroniax.game.player.Player;

public class TutorialScript extends Script {

    String mText;

    public TutorialScript(String text) {
        super();
        mText = text;
    }

    @Override
    public void update(float deltaTime, GameController gameController, Level level, Player player, Camera camera) {
        // Show message
        gameController.setModalText(mText);
        gameController.setState(State.SHOW_MODAL);

        stop();
    }
}

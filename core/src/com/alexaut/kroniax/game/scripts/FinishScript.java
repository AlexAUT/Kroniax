package com.alexaut.kroniax.game.scripts;

import com.alexaut.kroniax.game.Camera;
import com.alexaut.kroniax.game.Level;
import com.alexaut.kroniax.game.Player;
import com.alexaut.kroniax.game.Script;

public class FinishScript extends Script {

    public FinishScript() {
        super();
    }

    public void update(float deltaTime, Level level, Player player, Camera camera) {
        player.setAlive(false);
    }

}

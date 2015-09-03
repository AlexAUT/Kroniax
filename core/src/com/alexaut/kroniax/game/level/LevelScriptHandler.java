package com.alexaut.kroniax.game.level;

import java.util.ArrayList;

import com.alexaut.kroniax.game.Camera;
import com.alexaut.kroniax.game.GameController;
import com.alexaut.kroniax.game.Player;
import com.alexaut.kroniax.game.scripts.Script;
import com.badlogic.gdx.math.Vector2;

public class LevelScriptHandler {
    private ArrayList<Script> mScripts;

    public LevelScriptHandler(ArrayList<Script> scripts) {
        mScripts = scripts;
    }

    public void checkTriggers(ArrayList<LevelObject> objects, Player player) {
        // Make a ray cast from the old to the new position of the player
        // Then intersect it with the rect
        Vector2 oldPos = player.getOldRightPosition();
        Vector2 newPos = player.getCollisionPoints()[0];

        for (int i = 0; i < objects.size(); i++) {
            if (mScripts.get(i) != null && !mScripts.get(i).alreadyStarted()) {
                if (objects.get(i).checkCollision(oldPos, newPos)) {
                    mScripts.get(i).start();
                }
            }
        }
    }

    public void update(float deltaTime, GameController gameController, Level level, Player player, Camera camera) {
        for (Script script : mScripts) {
            if (script != null && script.isAlive())
                script.update(deltaTime, gameController, level, player, camera);
        }
    }
    
    public void resetScripts() {
        for(Script script : mScripts)
            script.reset();
    }
}

package com.alexaut.kroniax.game;

import java.util.ArrayList;

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
            if (objects.get(i).checkCollision(oldPos, newPos)) {
                if (mScripts.get(i) != null)
                    mScripts.get(i).start();
                System.out.println("Started script");
            }
        }
    }

    public void update(float deltaTime, Level level, Player player, Camera camera) {
        for (Script script : mScripts) {
            if (script != null && script.isAlive())
                script.update(deltaTime, level, player, camera);
        }
    }
}

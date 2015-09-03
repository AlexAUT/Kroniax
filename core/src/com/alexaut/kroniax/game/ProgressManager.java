package com.alexaut.kroniax.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class ProgressManager {
    private Preferences mUnlockedLevels;

    public ProgressManager() {
        mUnlockedLevels = Gdx.app.getPreferences("progress");
        if (!mUnlockedLevels.contains("unlocked_levels")) {
            mUnlockedLevels.putInteger("unlocked_levels", 1);
            mUnlockedLevels.flush();
        }
    }

    public int getUnlockedLevels() {
        return mUnlockedLevels.getInteger("unlocked_levels");
    }

    public void finishedLevel(int lvlNumber) {
        if (lvlNumber >= mUnlockedLevels.getInteger("unlocked_levels")) {
            mUnlockedLevels.putInteger("unlocked_levels", lvlNumber + 1);
            mUnlockedLevels.flush();
        }
    }
}

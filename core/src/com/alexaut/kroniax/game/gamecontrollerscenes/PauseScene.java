package com.alexaut.kroniax.game.gamecontrollerscenes;

import com.alexaut.kroniax.Application;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class PauseScene extends Table {
    public PauseScene(Application app) {
        setFillParent(true);

        Label label = new Label("Press Space or press Left to resume the game", app.getGuiSkin());
        add(label);

        row().padTop(150.f);
        label = new Label("Press Escape to go back to menu", app.getGuiSkin());
        add(label);

        pack();

        setColor(1, 1, 1, 0);
    }
}
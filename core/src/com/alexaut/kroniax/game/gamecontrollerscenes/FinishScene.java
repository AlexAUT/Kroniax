package com.alexaut.kroniax.game.gamecontrollerscenes;

import com.alexaut.kroniax.Application;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class FinishScene extends Table {

    public FinishScene(Application app) {
        setFillParent(true);

        Label label = new Label("You have beaten this level!", app.getGuiSkin());
        add(label);
        row().padTop(175.f);

        label = new Label("Press Space or click left to go to the next level", app.getGuiSkin());
        add(label);

        row().padTop(75.f);
        label = new Label("Press Escape to go back to menu", app.getGuiSkin());
        add(label);

        pack();

        setColor(1, 1, 1, 0);
    }
}

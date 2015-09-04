package com.alexaut.kroniax.game.gamecontrollerscenes;

import com.alexaut.kroniax.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class CrashedScene extends Table {
    public CrashedScene(Application app) {
        setFillParent(true);

        Label label1;
        Label label2;

        if (Gdx.app.getType() != ApplicationType.Android) {
            label1 = new Label("Press Space or press Left to start again from the last checkpoint", app.getGuiSkin());
            label2 = new Label("Press Escape to go back to menu", app.getGuiSkin());
        } else {
            label1 = new Label("Touch the screen to start again from the last checkpoint", app.getGuiSkin());
            label2 = new Label("Press the back button to go back to the menu", app.getGuiSkin());
        }

        add(new Label("You crashed into the wall", app.getGuiSkin()));
        row().padTop(150.f);
        add(label1);
        row().padTop(75.f);
        add(label2);

        pack();

        setColor(1, 1, 1, 0);
    }
}

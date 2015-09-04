package com.alexaut.kroniax.game.gamecontrollerscenes;

import com.alexaut.kroniax.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class FinishScene extends Table {

    public FinishScene(Application app) {
        setFillParent(true);

        Label label = new Label("You have beaten this level!", app.getGuiSkin());
        add(label);
        row().padTop(175.f);
        
        Label label1;
        Label label2;
        
        if(Gdx.app.getType() != ApplicationType.Android) {
            label1 = new Label("Press Space or click left to go to the next level", app.getGuiSkin());
            label2 = new Label("Press Escape to go back to menu", app.getGuiSkin());
        } else {
            label1 = new Label("Touch the screen to go to the next level", app.getGuiSkin());
            label2 = new Label("Press the back button to go back to the menu", app.getGuiSkin());
        }
        
        add(label1);
        row().padTop(75.f);
        add(label2);

        pack();

        setColor(1, 1, 1, 0);
    }
}

package com.alexaut.kroniax.game.gamecontrollerscenes;

import com.alexaut.kroniax.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class StartScene extends Table {

    public StartScene(Application app) {
        setFillParent(true);
        
        Label label1;
        Label label2;
        
        if(Gdx.app.getType() != ApplicationType.Android) {
            label1 = new Label("Press Space or press Left to start the game", app.getGuiSkin());
            label2 = new Label("Press Escape to pause the game", app.getGuiSkin());
        }
        else
        {
            label1 = new Label("Touch the screen to start the game", app.getGuiSkin());
            label2 = new Label("Press the back button to pause the game", app.getGuiSkin());
        }
        
        add(label1);
        row().padTop(150.f);
        add(label2);

        pack();
    }
}

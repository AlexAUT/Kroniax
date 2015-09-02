package com.alexaut.kroniax.game.gamecontrollerscenes;

import com.alexaut.kroniax.Application;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class StartScene extends Table {
    
    
    public StartScene(Application app) {
        setFillParent(true);
        
        Label label = new Label("Press Space or press Left to start the game", app.getGuiSkin());
        add(label);
        
        row().padTop(150.f);
        label = new Label("Press Escape to pause the game", app.getGuiSkin());
        add(label);
        
        pack();
    }
}

package com.alexaut.kroniax.game.gamecontrollerscenes;

import com.alexaut.kroniax.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ModalScene extends Table {
    
    private Label mText;
    
    public ModalScene(Application app) {
        setFillParent(true);

        mText = new Label("No Text set yet", app.getGuiSkin());
        mText.setWrap(true);
        add(mText).width(900);
        row().padTop(175.f);

        Label label1;

        if (Gdx.app.getType() != ApplicationType.Android) {
            label1 = new Label("Press Space or click left to resume the game", app.getGuiSkin());
        } else {
            label1 = new Label("Touch the screen to resume the game", app.getGuiSkin());
        }

        add(label1);

        pack();

        setColor(1, 1, 1, 0);
    }
    
    public void setText(String text) {
        mText.setText(text);
    }
}

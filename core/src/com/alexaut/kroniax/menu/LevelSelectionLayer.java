package com.alexaut.kroniax.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class LevelSelectionLayer extends Table {

    public LevelSelectionLayer(Gui gui) {
        super();
        setFillParent(true);
        setPosition(0, -720); // Shouldn't be on screen at the beginning

        TextButton level1 = new TextButton("1", gui.getSkin());
        add(level1);

        pack(); // Don't remove!
    }
}

package com.alexaut.kroniax.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class LevelSelectionLayer extends Table {

    public LevelSelectionLayer(Gui gui) {
        super();
        setFillParent(true);
        setPosition(0, -720); // Shouldn't be on screen at the beginning

        setupLevelGrid(gui);

        pack(); // Don't remove!
    }

    public void setupLevelGrid(Gui gui) {
        final int unlocked_levels = 16;
        final int rowSize = 4;

        for (int i = 1; i <= unlocked_levels; i++) {
            TextButton bt = new TextButton(Integer.toString(i), gui.getSkin());
            add(bt).pad(10.f);
            if (i != 0 && i % rowSize == 0)
                row();
        }
    }
}

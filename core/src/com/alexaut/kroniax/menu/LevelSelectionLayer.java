package com.alexaut.kroniax.menu;

import com.alexaut.kroniax.Application;
import com.alexaut.kroniax.menu.Gui.Layer;
import com.alexaut.kroniax.screens.GameScene;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class LevelSelectionLayer extends Table {

    public LevelSelectionLayer(final Gui gui) {
        super();
        setFillParent(true);
        setPosition(0, 720); // Shouldn't be on screen at the beginning

        setupLevelGrid(gui);

        // Add back button
        TextButton back = new TextButton("Back", gui.getSkin());
        back.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Get movement direction
                int direction = 720;
                if (gui.getLayer(Layer.MAIN).getY() > 0)
                    direction = -720;
                addAction(Actions.moveTo(0, direction, 1.5f, Interpolation.swingOut));
                gui.getLayer(Layer.MAIN).addAction(Actions.moveTo(0, 0, 1.5f, Interpolation.swingOut));
                gui.setActiveLayer(Layer.MAIN);
            }
        });

        add(back).colspan(getColumns()).padTop(75.f).width(300);

        pack(); // Don't remove!
    }

    public void setupLevelGrid(final Gui gui) {
        final int rowSize = 3;

        for (int i = 1; i <= 9; i++) {
            TextButton bt = new TextButton(Integer.toString(i), gui.getSkin());
            bt.setName(Integer.toString(i));

            // Callback to start the level
            bt.addListener(new ChangeListener() {

                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    int lvlNumber = Integer.parseInt(actor.getName());
                    gui.getApplication().setScreen(new GameScene(gui.getApplication(), lvlNumber, null));
                }
            });

            add(bt).pad(5.f).width(150).height(150);
            if (i != 0 && i % rowSize == 0)
                row();
        }
    }

    public void updateLevelButtons(Application app) {
        int unlockedLevels = app.getProgressManager().getUnlockedLevels();
        for (int i = 0; i < 9; i++) {
            int number = Integer.parseInt(getChildren().get(i).getName());
            getChildren().get(i).setVisible(number <= unlockedLevels);
        }
    }
}

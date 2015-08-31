package com.alexaut.kroniax.menu;

import com.alexaut.kroniax.menu.Gui.Layer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MainLayer extends Table {

    public MainLayer(final Gui gui) {
        super();
        setFillParent(true);

        // Start button
        TextButton bt = new TextButton("Play", gui.getSkin());
        // Show level selection Layer
        bt.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Get movement direction
                int direction = 720;
                if (gui.getLayer(Layer.LEVEL_SELECTION).getY() > 0)
                    direction = -720;
                addAction(Actions.moveTo(0, direction, 1.5f, Interpolation.swingOut));
                gui.getLayer(Layer.LEVEL_SELECTION).addAction(Actions.moveTo(0, 0, 1.5f, Interpolation.swingOut));
                gui.setActiveLayer(Layer.LEVEL_SELECTION);
            }
        });
        add(bt).width(450).height(100);
        row().padTop(50.f);

        // Credits button
        bt = new TextButton("Credits", gui.getSkin());
        // Show level selection Layer
        bt.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Get movement direction
                int direction = 1280;
                if (gui.getLayer(Layer.CREDITS).getX() > 0)
                    direction = -1280;
                addAction(Actions.moveTo(direction, 0, 1.5f, Interpolation.swingOut));
                gui.getLayer(Layer.CREDITS).addAction(Actions.moveTo(0, 0, 1.5f, Interpolation.swingOut));
                gui.setActiveLayer(Layer.CREDITS);
            }
        });
        add(bt).width(450).height(100);

        pack(); // Don't remove
    }
}

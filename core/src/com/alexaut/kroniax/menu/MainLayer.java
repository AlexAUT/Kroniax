package com.alexaut.kroniax.menu;

import com.alexaut.kroniax.menu.Gui.Layer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MainLayer extends Table {

    private TextButton mStart;

    public MainLayer(final Gui gui) {
        super();
        setFillParent(true);

        mStart = new TextButton("Play", gui.getSkin());
        // Show level selection Layer
        mStart.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                addAction(Actions.moveTo(0, 720, 1.5f, Interpolation.swingOut));
                gui.getLayer(Layer.LEVEL_SELECTION).addAction(Actions.moveTo(0, 0, 1.5f, Interpolation.swingOut));
            }
        });

        add(mStart);

        pack(); // Don't remove
    }
}

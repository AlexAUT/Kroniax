package com.alexaut.kroniax.menu;

import com.alexaut.kroniax.menu.Gui.Layer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class CreditsLayer extends Table {

    public CreditsLayer(final Gui gui) {
        super();
        setFillParent(true);
        setPosition(1280, 0);

        Label label = new Label("Programmed by Alexander Weinrauch", gui.getSkin());
        add(label);
        row().padTop(40.f);

        label = new Label("Special thanks to the creator of LibGDX", gui.getSkin());
        add(label);
        row();
        label = new Label("Mario Zechner!", gui.getSkin());
        add(label);

        row().padTop(40.f);
        label = new Label("Music created by MafiaFlairBeats", gui.getSkin());
        add(label);

        row().padTop(100.f);
        // Back button, add credits above!
        TextButton bt = new TextButton("Back", gui.getSkin());
        bt.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                int direction = 1280;
                if (gui.getLayer(Layer.MAIN).getX() > 0)
                    direction = -1280;
                addAction(Actions.moveTo(direction, 0, 1.5f, Interpolation.swingOut));
                gui.getLayer(Layer.MAIN).addAction(Actions.moveTo(0, 0, 1.5f, Interpolation.swingOut));
                gui.setActiveLayer(Layer.MAIN);
            }
        });
        add(bt);

        pack(); // Don't remove!
    }

}

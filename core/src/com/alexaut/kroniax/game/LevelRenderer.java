package com.alexaut.kroniax.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class LevelRenderer {

    TiledMapRenderer mMapRenderer;

    public LevelRenderer(TiledMap map) {
        mMapRenderer = new OrthogonalTiledMapRenderer(map);

    }

    public void render(Camera camera) {
        mMapRenderer.setView(camera.getOrthoCamera());
        mMapRenderer.render();
    }
}

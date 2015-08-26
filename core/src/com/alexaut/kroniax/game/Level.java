package com.alexaut.kroniax.game;

import com.badlogic.gdx.maps.tiled.TiledMap;

public class Level {

    LevelProperties mProperties;
    final LevelRenderer mRenderer;

    TiledMap mMap;

    public Level(TiledMap map) {
        // Load map
        mMap = map;
        // Setup the properties
        mProperties = new LevelProperties(map);
        // Setup Renderer
        mRenderer = new LevelRenderer(map);
    }

    public void update(float deltaTime) {

    }

    public void render(Camera camera) {
        mRenderer.render(camera);
    }

    public LevelProperties getProperties() {
        return mProperties;
    }

}

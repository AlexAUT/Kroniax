package com.alexaut.kroniax.game;

import com.badlogic.gdx.maps.tiled.TiledMap;

public class Level {

    LevelProperties mProperties;
    LevelCollisionHandler mCollisionHandler;
    final LevelRenderer mRenderer;

    TiledMap mMap;

    public Level(TiledMap map) throws Exception {
        // Load map
        mMap = map;
        // Setup the properties
        mProperties = new LevelProperties(map);
        // Setup the collision handler
        mCollisionHandler = new LevelCollisionHandler(map);
        // Setup the renderer
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

    public void dispose() {
        mMap.dispose();
    }
}

package com.alexaut.kroniax.game;

import com.alexaut.kroniax.game.tilemap.TileMap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Level {

    TileMap mMap;

    LevelProperties mProperties;
    LevelCollisionHandler mCollisionHandler;
    final LevelRenderer mRenderer;

    public Level(TileMap map) throws Exception {
        // Load map
        mMap = map;
        // Setup the properties
        mProperties = new LevelProperties(map);
        // Setup the collision handler
        mCollisionHandler = new LevelCollisionHandler(map, mProperties);
        // Setup the renderer
        mRenderer = new LevelRenderer(map);
    }

    public void update(float deltaTime) {

    }

    public boolean checkCollision(Player player) {
        return mCollisionHandler.collide(player);
    }

    public void render(SpriteBatch renderer, Camera camera) {
        mRenderer.render(renderer, camera);
    }

    public LevelProperties getProperties() {
        return mProperties;
    }

    public void dispose() {
        mMap.dispose();
    }
}

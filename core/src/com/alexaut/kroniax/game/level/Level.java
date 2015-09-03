package com.alexaut.kroniax.game.level;

import com.alexaut.kroniax.game.Camera;
import com.alexaut.kroniax.game.Player;
import com.alexaut.kroniax.game.tilemap.TileMap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Level {

    TileMap mMap;

    LevelProperties mProperties;
    LevelCollisionHandler mCollisionHandler;
    final LevelRenderer mRenderer;
    LevelScriptHandler mScriptHandler;

    public Level(TileMap map) throws Exception {
        // Load map
        mMap = map;
        // Setup the properties
        mProperties = new LevelProperties(map);
        // Setup the collision handler
        mCollisionHandler = new LevelCollisionHandler(map, mProperties);
        // Setup the renderer
        mRenderer = new LevelRenderer(map);
        // Setup the script handler
        mScriptHandler = new LevelScriptHandler(map.getScripts());
    }

    public void update(float deltaTime, Player player, Camera camera) {
        // Check collision with map objects
        mScriptHandler.checkTriggers(mMap.getLevelObjects(), player);
        // Update running scripts
        mScriptHandler.update(deltaTime, this, player, camera);
    }

    public void checkCollision(Player player) {
        mCollisionHandler.collide(player);
    }

    public void render(SpriteBatch spriteRenderer, ShapeRenderer shapeRenderer, Camera camera) {
        mRenderer.render(spriteRenderer, shapeRenderer, camera);
    }

    public LevelProperties getProperties() {
        return mProperties;
    }

    public void dispose() {
        mMap.dispose();
    }
}

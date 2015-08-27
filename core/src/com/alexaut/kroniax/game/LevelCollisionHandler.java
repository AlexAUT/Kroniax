package com.alexaut.kroniax.game;

import java.util.ArrayList;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class LevelCollisionHandler {

    ArrayList<TiledMapTileLayer> mCollisionLayers;

    LevelProperties mLevelProperties;

    public LevelCollisionHandler(TiledMap map, LevelProperties properties) throws Exception {
        mCollisionLayers = new ArrayList<TiledMapTileLayer>();
        mLevelProperties = properties;

        // Check for layers with the collision property
        for (MapLayer layer : map.getLayers()) {
            if (layer.getProperties().containsKey("collision")) {
                if (layer instanceof TiledMapTileLayer) {
                    mCollisionLayers.add((TiledMapTileLayer) layer);
                } else
                    throw new Exception("collision property is only allowed on tile layers!");
            }
        }

        System.out.println("Found " + mCollisionLayers.size() + " collision layers.");
    }

    public boolean collide(Player player) {
        for (TiledMapTileLayer layer : mCollisionLayers) {
            for (Vector2 point : player.getCollisionPoints()) {
                int x = (int) (point.x / mLevelProperties.tileSize.x);
                int y = (int) ((point.y / mLevelProperties.tileSize.y));
                // Check if this tile is not null => collision!
                if (layer.getCell(x, y) != null)
                    return true;
            }
        }

        return false;
    }

}

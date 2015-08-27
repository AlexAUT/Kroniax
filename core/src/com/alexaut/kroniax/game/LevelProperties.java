package com.alexaut.kroniax.game;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;

public class LevelProperties {
    // Map Properties
    public Vector2 tileSize;
    public Vector2 size;
    public Vector2 tileCount;

    public Vector2 spawn;
    public float velocity;
    public float gravity;

    public LevelProperties(TiledMap level) throws Exception {

        parseMapDetails(level.getProperties());

        for (MapLayer layer : level.getLayers()) {
            parseLayer(layer.getProperties());
        }

        checkEssentialValues();
    }

    private void parseMapDetails(MapProperties props) {
        // Search tilesize
        tileSize = new Vector2(props.get("tilewidth", Integer.class), props.get("tileheight", Integer.class));
        tileCount = new Vector2(props.get("width", Integer.class), props.get("height", Integer.class));
        size = new Vector2(tileCount.x * tileSize.x, tileCount.y * tileSize.y);
        System.out.println("TileCount: " + size.x + " | " + size.y);
    }

    private void parseLayer(MapProperties props) {
        // Spawn
        if (props.containsKey("spawn_x")) {
            float spawn_x = Integer.parseInt(props.get("spawn_x", String.class)) * tileSize.x;
            if (spawn == null)
                spawn = new Vector2(spawn_x, 0);
            else
                spawn.x = spawn_x;
        }
        if (props.containsKey("spawn_y")) {
            float spawn_y = (tileCount.y - Integer.parseInt(props.get("spawn_y", String.class))) * tileSize.y;
            if (spawn == null)
                spawn = new Vector2(0, spawn_y);
            else
                spawn.y = spawn_y;
        }
        if (props.containsKey("gravity")) {
            gravity = Float.parseFloat(props.get("gravity", String.class));
        }
        if (props.containsKey("velocity")) {
            velocity = Float.parseFloat(props.get("velocity", String.class));
        }
    }

    private void checkEssentialValues() throws Exception {
        if (spawn == null)
            throw new Exception("spawn_x and/or spawn_y are missing, they should be properties of a layer");
    }

}

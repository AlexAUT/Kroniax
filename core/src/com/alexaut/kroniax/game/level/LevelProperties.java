package com.alexaut.kroniax.game.level;

import com.alexaut.kroniax.game.tilemap.Properties;
import com.alexaut.kroniax.game.tilemap.TileMap;
import com.badlogic.gdx.math.Vector2;

public class LevelProperties {
    // Map Properties
    public Vector2 tileSize;
    public Vector2 size;
    public Vector2 tileCount;

    public Vector2 spawn;
    public float velocity;
    public float gravity;

    public boolean fixedCamera;

    public LevelProperties(TileMap level) throws Exception {
        parseMapDetails(level.getProperties());

        checkEssentialValues();
    }

    private void parseMapDetails(Properties props) {
        // Search tilesize
        tileSize = new Vector2(props.get("tilewidth"), props.get("tileheight"));
        tileCount = new Vector2(props.get("width"), props.get("height"));
        size = new Vector2(tileCount.x * tileSize.x, tileCount.y * tileSize.y);
        // Spawn
        if (props.hasProperty("spawn_x")) {
            float spawn_x = props.get("spawn_x") * tileSize.x;
            if (spawn == null)
                spawn = new Vector2(spawn_x, 0);
            else
                spawn.x = spawn_x;
        }
        if (props.hasProperty("spawn_y")) {
            float spawn_y = (tileCount.y - props.get("spawn_y")) * tileSize.y;
            if (spawn == null)
                spawn = new Vector2(0, spawn_y);
            else
                spawn.y = spawn_y;
        }
        if (props.hasProperty("gravity"))
            gravity = props.get("gravity");
        else
            gravity = 100.f;
        if (props.hasProperty("velocity"))
            velocity = props.get("velocity");
        else
            velocity = 300;

        fixedCamera = props.hasProperty("fixed_camera");
    }

    private void checkEssentialValues() throws Exception {
        if (spawn == null)
            throw new Exception("spawn_x and/or spawn_y are missing, they should be properties of a layer");
    }

}

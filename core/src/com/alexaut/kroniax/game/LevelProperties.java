package com.alexaut.kroniax.game;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;

public class LevelProperties {

    Vector2 mTileSize;

    public LevelProperties(TiledMap level) {

        parseMapDetails(level.getProperties());

    }

    private void parseMapDetails(MapProperties props) {
        // Search tilesize
        mTileSize = new Vector2(props.get("tilewidth", Integer.class), props.get("tileheight", Integer.class));
    }

}

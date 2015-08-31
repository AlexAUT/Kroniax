package com.alexaut.kroniax.game;

import java.util.ArrayList;

import com.alexaut.kroniax.game.tilemap.TileColumn;
import com.alexaut.kroniax.game.tilemap.TileLayer;
import com.alexaut.kroniax.game.tilemap.TileMap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LevelRenderer {

    TileMap mMap;

    public LevelRenderer(TileMap map) {
        mMap = map;

    }

    public void render(SpriteBatch renderer) {
        // Render tile layers
        int height = mMap.getHeight();

        for (TileLayer layer : mMap.getTileLayers()) {
            for (int x = 0; x < layer.getColumns().size(); x++) {
                TileColumn col = layer.getColumns().get(x);
                ArrayList<TextureRegion> tiles = col.getTiles();
                for (int i = 0; i < tiles.size(); i++) {
                    int row = (height - col.getStartRow() - i) * mMap.getTileHeight();
                    TextureRegion tile = col.getTile(col.getStartRow() + i);
                    if (tile != null)
                        renderer.draw(tile, x * mMap.getTileWidth(), row);
                }
            }
        }
    }
}

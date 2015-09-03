package com.alexaut.kroniax.game.level;

import java.util.ArrayList;

import com.alexaut.kroniax.game.Camera;
import com.alexaut.kroniax.game.tilemap.TileColumn;
import com.alexaut.kroniax.game.tilemap.TileLayer;
import com.alexaut.kroniax.game.tilemap.TileMap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class LevelRenderer {

    TileMap mMap;

    public LevelRenderer(TileMap map) {
        mMap = map;

    }

    public void render(SpriteBatch spriteRenderer, ShapeRenderer shapeRenderer, Camera camera) {
        // Check which columns are in the frustum
        int min = (int) ((camera.getOrthoCamera().position.x - camera.getOrthoCamera().viewportWidth / 2.f)
                / mMap.getTileWidth()) - 1;
        if (min < 0)
            min = 0;

        int max = (int) (min + (camera.getOrthoCamera().viewportWidth / mMap.getTileWidth())) + 3;
        if (max >= mMap.getWidth())
            max = mMap.getWidth() - 1;

        // Render tile layers
        int height = mMap.getHeight();
        for (TileLayer layer : mMap.getTileLayers()) {
            for (int x = min; x <= max; x++) {
                TileColumn col = layer.getColumn(x);
                if (col != null) {
                    ArrayList<TextureRegion> tiles = col.getTiles();
                    for (int i = 0; i < tiles.size(); i++) {
                        int row = (height - col.getStartRow() - i - 1) * mMap.getTileHeight();
                        TextureRegion tile = col.getTile(col.getStartRow() + i);
                        if (tile != null)
                            spriteRenderer.draw(tile, x * mMap.getTileWidth(), row);
                    }
                }
            }
        }

        // Draw Objects
        for (LevelObject obj : mMap.getLevelObjects())
            obj.render(shapeRenderer);
    }
}

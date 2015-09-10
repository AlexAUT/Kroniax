package com.alexaut.kroniax.menu;

import com.alexaut.kroniax.game.Camera;
import com.alexaut.kroniax.game.level.LevelRenderer;
import com.alexaut.kroniax.game.tilemap.TileMap;
import com.alexaut.kroniax.game.tilemap.TileMapLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class MenuBackground {
    private TileMap mMap;

    private LevelRenderer mRenderer;
    private Camera mCamera;
    private Vector2 mPosition;

    public MenuBackground() {
        int lvlNr = (int) ((Math.random() * 8) + 1);
        mMap = new TileMapLoader().load(Gdx.files.internal("data/levels/official/level" + lvlNr + ".kroniax"));
        mRenderer = new LevelRenderer(mMap);

        mCamera = new Camera();
        mCamera.updateViewport(1280, 720);
        mPosition = new Vector2(0, 0);
    }

    public void updateViewport(int width, int height) {
        mCamera.updateViewport(width, height);
        mCamera.updateViewport(1280, 720);
    }

    public void update(float deltaTime) {
        mPosition.add(800.f * deltaTime, 0);
        if (mPosition.x > mMap.getTileWidth() * mMap.getWidth())
            mPosition.set(0, 0);

        mCamera.update(mPosition, true);
    }

    public void dispose() {
        mMap.dispose();
    }

    public void render(SpriteBatch spriteRenderer, ShapeRenderer shapeRenderer) {
        // Setup the sprite renderer
        spriteRenderer.begin();
        spriteRenderer.setProjectionMatrix(mCamera.getTransform());
        // Setup the shape renderer
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setProjectionMatrix(mCamera.getTransform());
        // Now render
        mRenderer.render(spriteRenderer, shapeRenderer, mCamera);
        spriteRenderer.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.setColor(0, 0, 0, 0.5f);
        shapeRenderer.rect(mCamera.getOrthoCamera().position.x - 640, mCamera.getOrthoCamera().position.y - 360, 1280,
                720);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }
}

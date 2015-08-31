package com.alexaut.kroniax.game.tilemap;

import java.util.ArrayList;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tileset {
    private Texture mTexture;

    private int mFirstID, mLastID;
    private int mMargin, mSpacing;

    private ArrayList<TextureRegion> mTiles;

    public Tileset(FileHandle file, int firstID, int margin, int spacing, int tilewidth, int tileheight) {
        mTexture = new Texture(file);
        mFirstID = firstID;
        mMargin = margin;
        mSpacing = spacing;

        mTiles = new ArrayList<TextureRegion>();

        generateTiles(tilewidth, tileheight);

        mLastID = mFirstID + mTiles.size();
    }

    private void generateTiles(int tilewidth, int tileheight) {
        int stopWidth = mTexture.getWidth() - tilewidth;
        int stopHeight = mTexture.getHeight() - tileheight;

        for (int y = mMargin; y < stopHeight; y += tileheight + mSpacing) {
            for (int x = mMargin; x < stopWidth; x += tilewidth + mSpacing) {
                mTiles.add(new TextureRegion(mTexture, x, y, tilewidth, tileheight));
            }
        }

    }

    public int getFirstID() {
        return mFirstID;
    }

    public int getLastID() {
        return mLastID;
    }

    public TextureRegion getTile(int id) {
        int index = id - mFirstID;
        if (index < mTiles.size())
            return mTiles.get(index);
        else
            return null;
    }

    void dispose() {
        mTexture.dispose();
    }

}

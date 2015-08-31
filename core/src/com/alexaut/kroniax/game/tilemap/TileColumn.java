package com.alexaut.kroniax.game.tilemap;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileColumn {
    int mStartRow;
    int mEndRow;

    ArrayList<TextureRegion> mTiles;

    public TileColumn(int startValue) {
        mStartRow = startValue;
        mEndRow = -1; // -1 For safety

        mTiles = new ArrayList<TextureRegion>();
    }

    public void setStartRow(int row) {
        mStartRow = row;
        if (mTiles.size() > 0)
            mEndRow = mTiles.size() + mStartRow;
        else
            mEndRow = mStartRow - 1;
    }

    public void addTile(TextureRegion tile) {
        mTiles.add(tile);
        mEndRow = mStartRow + mTiles.size() - 1;
    }

    public TextureRegion getTile(int row) {
        if (row < mStartRow || row > mEndRow)
            return null;
        return mTiles.get(row - mStartRow);
    }

    public ArrayList<TextureRegion> getTiles() {
        return mTiles;
    }

    public int getStartRow() {
        return mStartRow;
    }
}

package com.alexaut.kroniax.game.tilemap;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileLayer {
    String mName;

    ArrayList<TileColumn> mColumns;
    Properties mProperties;

    public TileLayer(int width) {
        mColumns = new ArrayList<TileColumn>(width);
        mProperties = new Properties();
    }

    public void addColumn(int startValue) {
        mColumns.add(new TileColumn(startValue));
    }

    public void addTile(int x, TextureRegion tile) {
        if (x < mColumns.size())
            mColumns.get(x).addTile(tile);
    }

    public void addTile(TextureRegion tile) {
        mColumns.get(mColumns.size() - 1).addTile(tile);
    }

    public TextureRegion getTile(int x, int y) {
        if (x >= 0 && x < mColumns.size())
            return mColumns.get(x).getTile(y);
        return null;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public Properties getProperties() {
        return mProperties;
    }

    public ArrayList<TileColumn> getColumns() {
        return mColumns;
    }

    public TileColumn getColumn(int x) {
        if (x >= 0 && x < mColumns.size())
            return mColumns.get(x);
        return null;
    }
}

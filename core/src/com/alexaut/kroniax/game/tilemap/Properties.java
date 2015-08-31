package com.alexaut.kroniax.game.tilemap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Properties {
    Map<String, Integer> mProperties;

    public Properties() {
        mProperties = new HashMap<String, Integer>();
    }

    public void add(String key, int value) {
        mProperties.put(key, value);
    }

    public boolean hasProperty(String key) {
        return mProperties.get(key) != null;
    }

    public int get(String key) {
        return mProperties.get(key);
    }

    public Set<String> getKeySet() {
        return mProperties.keySet();
    }

}

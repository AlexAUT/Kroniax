package com.alexaut.kroniax.game.tilemap;

import java.util.HashMap;

import com.alexaut.kroniax.game.gameobjects.RectLevelObject;
import com.alexaut.kroniax.game.scripts.CheckPointScript;
import com.alexaut.kroniax.game.scripts.FinishScript;
import com.alexaut.kroniax.game.scripts.GravityChangeScript;
import com.alexaut.kroniax.game.scripts.Script;
import com.alexaut.kroniax.game.scripts.SpeedChangeScript;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class TileMapLoader {

    public TileMap load(FileHandle file) {
        TileMap map = new TileMap();

        String[] fileContent = file.readString().split("\\r?\\n");

        parseProperties(map, fileContent);
        parseTilesets(map, fileContent);
        parseTileLayers(map, fileContent);
        parseMapObjects(map, fileContent);

        return map;
    }

    private void parseProperties(TileMap map, String[] file) {
        for (int i = 0; i < file.length; i++) {
            if (file[i].equalsIgnoreCase("[width]"))
                map.getProperties().add("width", Integer.parseInt(file[++i]));
            else if (file[i].equalsIgnoreCase("[height]"))
                map.getProperties().add("height", Integer.parseInt(file[++i]));
            else if (file[i].equalsIgnoreCase("[tilewidth]"))
                map.getProperties().add("tilewidth", Integer.parseInt(file[++i]));
            else if (file[i].equalsIgnoreCase("[tileheight]"))
                map.getProperties().add("tileheight", Integer.parseInt(file[++i]));
            else if (file[i].equalsIgnoreCase("[mapproperties]")) {
                while (!file[++i].equalsIgnoreCase("[/mapproperties]")) {
                    String[] property = file[i].split(" ");
                    if (property.length == 1)
                        map.getProperties().add(property[0], 0);
                    else if (property.length > 1)
                        map.getProperties().add(property[0], Integer.parseInt(property[1]));
                }
            }
        }
    }

    private void parseTilesets(TileMap map, String[] file) {

        boolean foundStartTag = false;

        for (int i = 0; i < file.length; i++) {
            if (file[i].equalsIgnoreCase("[tilesets]"))
                foundStartTag = true;
            else if (file[i].equalsIgnoreCase("[/tilesets]"))
                break;
            else if (foundStartTag) {
                String[] tilesetInfo = file[i].split(" ");

                String filePath = "data/sprites/" + tilesetInfo[0];
                FileHandle fileHandle = Gdx.files.internal(filePath);
                if (!fileHandle.exists())
                    fileHandle = Gdx.files.external(filePath);

                int firstID = Integer.parseInt(tilesetInfo[1]);
                int margin = Integer.parseInt(tilesetInfo[2]);
                int spacing = Integer.parseInt(tilesetInfo[3]);

                map.getTilesets().add(
                        new Tileset(fileHandle, firstID, margin, spacing, map.getTileWidth(), map.getTileHeight()));
            }
        }
    }

    private void parseTileLayers(TileMap map, String[] file) {
        boolean insideLayer = false;
        for (int i = 0; i < file.length; i++) {
            if (file[i].equalsIgnoreCase("[layer]")) {
                insideLayer = true;
                map.getTileLayers().add(new TileLayer(map.getWidth()));
                // Get name
                map.getTileLayers().get(map.getTileLayers().size() - 1).setName(file[++i]);
                i++; // Tile dimensions not used here
            } else if (file[i].equalsIgnoreCase("[/layer]"))
                insideLayer = false;
            else if (insideLayer) {
                if (file[i].equalsIgnoreCase("[data]"))
                    i = parseLayerData(++i, file, map);
                else if (file[i].equalsIgnoreCase("[properties]"))
                    i = parseProperties(++i, file, map);
            }
        }
    }

    private int parseLayerData(int i, String[] file, TileMap map) {
        for (; i < file.length; i++) {
            if (file[i].equalsIgnoreCase("[/data]"))
                break;
            String[] colValues = file[i].split(" ");
            if (colValues.length >= 2) {
                TileLayer layer = map.getTileLayers().get(map.getTileLayers().size() - 1);
                // check if the lines describes a few empty lines
                if (colValues[0].equalsIgnoreCase("x")) {
                    int count = Integer.parseInt(colValues[1]);
                    for (int j = 0; j < count; j++)
                        layer.addColumn(0);
                } else {
                    // Normal row
                    int startValue = Integer.parseInt(colValues[0]);
                    layer.addColumn(startValue);

                    for (int j = 1; j < colValues.length; j++) {
                        // Check if the tile describes placeholder for empty
                        // tiles
                        if (colValues[j].equalsIgnoreCase("x")) {
                            int count = Integer.parseInt(colValues[++j]);
                            for (int k = 0; k < count; k++)
                                layer.addTile(null);
                        } else { // Normal tile
                            int id = Integer.parseInt(colValues[j]);
                            layer.addTile(map.getTileRegion(id));
                        }

                    }
                }
            }
        }
        return i;
    }

    private int parseProperties(int i, String[] file, TileMap map) {
        TileLayer activeLayer = map.getTileLayers().get(map.getTileLayers().size() - 1);

        for (; i < file.length; i++) {
            if (file[i].equalsIgnoreCase("[/properties]"))
                break;
            String[] property = file[i].split(" ");
            if (property.length == 1)
                activeLayer.getProperties().add(property[0], 0);
            else if (property.length > 1)
                activeLayer.getProperties().add(property[0], Integer.parseInt(property[1]));
        }
        return i;
    }

    private void parseMapObjects(TileMap map, String[] file) {
        boolean foundTag = false;
        for (int i = 0; i < file.length; i++) {
            if (file[i].equalsIgnoreCase("[objects]"))
                foundTag = true;
            else if (file[i].equalsIgnoreCase("[/objects]"))
                break;
            else if (foundTag) {
                // Check type
                if (file[i].equalsIgnoreCase("[rect]"))
                    i = parseRect(++i, file, map);

            }
        }
    }

    private int parseRect(int i, String[] file, TileMap map) {
        String[] properties = file[i].split(" ");
        if (properties.length < 5)
            return i; // Todo add error handling system
        String type = properties[0];
        // Convert to the libgdx coordinate system
        int w = Integer.parseInt(properties[3]);
        int h = Integer.parseInt(properties[4]);
        int x = Integer.parseInt(properties[1]);
        int y = (map.getHeight() * map.getTileHeight()) - Integer.parseInt(properties[2]);

        // Get properties
        HashMap<String, String> properties_map = new HashMap<String, String>();
        for (i = i + 1; i < file.length; i++) {
            if (file[i].equalsIgnoreCase("[/rect]"))
                break;
            if (file[i].equalsIgnoreCase("[properties]"))
                i = parseProperties(++i, file, properties_map);
        }
        // Create the script for the map object
        Script script = null;
        if (type.equalsIgnoreCase("finish")) {
            script = new FinishScript();
        } else if (type.equalsIgnoreCase("checkpoint")) {
            if (properties_map.size() > 1)
                script = new CheckPointScript(Float.parseFloat(properties_map.get("start_x")), Float.parseFloat(properties_map.get("start_y")),
                        Float.parseFloat(properties_map.get("start_angle")));
        } else if (type.equalsIgnoreCase("speed_change")) {
            if (properties_map.size() > 1)
                script = new SpeedChangeScript(Float.parseFloat(properties_map.get("time")), Float.parseFloat(properties_map.get("value")));
        } else if (type.equalsIgnoreCase("gravity_change")) {
            if (properties_map.size() > 1)
                script = new GravityChangeScript(Float.parseFloat(properties_map.get("time")), Float.parseFloat(properties_map.get("value")));
        }

        map.getScripts().add(script);
        map.getLevelObjects().add(new RectLevelObject(type, x, y, w, h));
        // They both have the same index (the script can also be null)
        // So they are linked by indices

        return i;
    }

    private int parseProperties(int i, String[] file, HashMap<String, String> properties) {
        for (; i < file.length; i++) {
            if (file[i].equalsIgnoreCase("[/properties]"))
                break;
            String[] values = file[i].split(" ", 2);
            if (values.length == 1)
                properties.put(values[0], "0");
            else if (values.length == 2)
                properties.put(values[0], values[1]);
        }
        return i;
    }
}

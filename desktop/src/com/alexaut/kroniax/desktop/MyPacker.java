package com.alexaut.kroniax.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class MyPacker {
    public static void main(String[] args) throws Exception {
        Settings settings = new Settings();
        settings.maxWidth = 1024;
        settings.maxHeight = 1024;
        TexturePacker.process(settings, "../images", "../android/assets/data/skins/", "menu");

        // TexturePacker.process("../../images/", "/textures/", "default");
    }
}
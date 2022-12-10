package me.bruno.packbuilder.enums.texture;

import me.bruno.packbuilder.enums.Files;

public enum Map implements Files {

    // textures/map

    TEXTURES_MAP_MAP_BACKGROUND("textures\\map", "map_background", "1.6"),
    TEXTURES_MAP_MAP_BACKGROUND_CHECKERBOARD("textures\\map", "map_background_checkerboard", "1.15"),
    TEXTURES_MAP_MAP_ICONS("textures\\map", "map_icons", "1.6");

    Map(String dir, String nameNew, String nameOld, String introducedVersion) {
        this.dir = dir;
        this.nameNew = nameNew;
        this.nameOld = nameOld;
        this.introducedVersion = introducedVersion;
    }

    Map(String dir, String nameNew, String introducedVersion) {
        this.dir = dir;
        this.nameNew = nameNew;
        this.nameOld = nameNew;
        this.introducedVersion = introducedVersion;
    }



    private final String dir;
    private final String nameNew;
    private final String nameOld;
    private final String introducedVersion;

    @Override
    public String getDirectory() {
        return dir;
    }

    @Override
    public String getNewerName() {
        return nameNew;
    }

    @Override
    public String getOlderNames() {
        return nameOld;
    }

    @Override
    public String getIntroducedVersion() {
        return introducedVersion;
    }
}

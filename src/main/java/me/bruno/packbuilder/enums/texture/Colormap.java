package me.bruno.packbuilder.enums.texture;

import me.bruno.packbuilder.enums.Files;

public enum Colormap implements Files {
    // textures/colormap

    TEXTURES_COLORMAP_FOLIAGE("textures\\colormap", "foliage", "1.6"),
    TEXTURES_COLORMAP_GRASS("textures\\block", "grass", "1.6");

    Colormap(String dir, String nameNew, String nameOld, String introducedVersion) {
        this.dir = dir;
        this.nameNew = nameNew;
        this.nameOld = nameOld;
        this.introducedVersion = introducedVersion;
    }

    Colormap(String dir, String nameNew, String introducedVersion) {
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

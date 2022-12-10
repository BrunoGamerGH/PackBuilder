package me.bruno.packbuilder.enums.texture;

import me.bruno.packbuilder.enums.Files;

public enum Environment implements Files {

    // textures/environment

    TEXTURES_ENVIRONMENT_CLOUDS("textures\\environment", "clouds", "1.6"),
    TEXTURES_ENVIRONMENT_END_SKY("textures\\environment", "end_sky", "1.6"),
    TEXTURES_ENVIRONMENT_MOON_PHASES("textures\\environment", "moon_phases", "1.6"),
    TEXTURES_ENVIRONMENT_RAIN("textures\\environment", "rain", "1.6"),
    TEXTURES_ENVIRONMENT_SNOW("textures\\environment", "snow", "1.6"),
    TEXTURES_ENVIRONMENT_SUN("textures\\environment", "snow", "1.6");

    Environment(String dir, String nameNew, String nameOld, String introducedVersion) {
        this.dir = dir;
        this.nameNew = nameNew;
        this.nameOld = nameOld;
        this.introducedVersion = introducedVersion;
    }

    Environment(String dir, String nameNew, String introducedVersion) {
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

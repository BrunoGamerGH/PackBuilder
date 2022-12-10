package me.bruno.packbuilder.enums.texture;

import me.bruno.packbuilder.enums.Files;

public enum Painting implements Files {

    //textures/painting

    TEXTURES_PAINTING_PAINTINGS_KRISTOFFER_ZETTERSTRAND("textures\\painting", "paintings_kristoffer_zetterstrand", "1.6"),
    TEXTURES_PAINTING_ALBAN("textures\\painting", "alban", "1.14"),
    TEXTURES_PAINTING_AZTEC("textures\\painting", "aztec", "1.14"),
    TEXTURES_PAINTING_AZTEC2("textures\\painting", "aztec2", "1.14"),
    TEXTURES_PAINTING_BACK("textures\\painting", "back", "1.14"),
    TEXTURES_PAINTING_BOMB("textures\\painting", "bomb", "1.14"),
    TEXTURES_PAINTING_BURNING_SKULL("textures\\painting", "burning_skull", "1.14"),
    TEXTURES_PAINTING_BUST("textures\\painting", "bust", "1.14"),
    TEXTURES_PAINTING_COURBET("textures\\painting", "courbet", "1.14"),
    TEXTURES_PAINTING_CREEBET("textures\\painting", "creebet", "1.14"),
    TEXTURES_PAINTING_DONKEY_KONG("textures\\painting", "donkey_kong", "1.14"),
    TEXTURES_PAINTING_EARTH("textures\\painting", "earth", "1.19"),
    TEXTURES_PAINTING_FIGHTERS("textures\\painting", "fighters", "1.14"),
    TEXTURES_PAINTING_FIRE("textures\\painting", "fire", "1.19"),
    TEXTURES_PAINTING_GRAHAM("textures\\painting", "graham", "1.14"),
    TEXTURES_PAINTING_KEBAB("textures\\painting", "kebab", "1.14"),
    TEXTURES_PAINTING_MATCH("textures\\painting", "match", "1.14"),
    TEXTURES_PAINTING_PIGSCENE("textures\\painting", "pigscene", "1.14"),
    TEXTURES_PAINTING_PLANT("textures\\painting", "plant", "1.14"),
    TEXTURES_PAINTING_POINTER("textures\\painting", "pointer", "1.14"),
    TEXTURES_PAINTING_POOL("textures\\painting", "pool", "1.14"),
    TEXTURES_PAINTING_SEA("textures\\painting", "sea", "1.14"),
    TEXTURES_PAINTING_SKELETON("textures\\painting", "skeleton", "1.14"),
    TEXTURES_PAINTING_SKULL_AND_ROSES("textures\\painting", "skull_and_roses", "1.14"),
    TEXTURES_PAINTING_STAGE("textures\\painting", "stage", "1.14"),
    TEXTURES_PAINTING_SUNSET("textures\\painting", "sunset", "1.14"),
    TEXTURES_PAINTING_VOID("textures\\painting", "void", "1.14"),
    TEXTURES_PAINTING_WANDERER("textures\\painting", "wanderer", "1.14"),
    TEXTURES_PAINTING_WASTELAND("textures\\painting", "wasteland", "1.14"),
    TEXTURES_PAINTING_WATER("textures\\painting", "water", "1.19"),
    TEXTURES_PAINTING_WIND("textures\\painting", "wind", "1.19"),
    TEXTURES_PAINTING_WITHER("textures\\painting", "wither", "1.14");

    Painting(String dir, String nameNew, String nameOld, String introducedVersion) {
        this.dir = dir;
        this.nameNew = nameNew;
        this.nameOld = nameOld;
        this.introducedVersion = introducedVersion;
    }

    Painting(String dir, String nameNew, String introducedVersion) {
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

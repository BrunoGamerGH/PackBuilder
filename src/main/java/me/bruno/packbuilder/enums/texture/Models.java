package me.bruno.packbuilder.enums.texture;

import me.bruno.packbuilder.enums.Files;

public enum Models implements Files {

    //textures/models

    TEXTURES_MODELS_ARMOR_DIR("textures\\models", "armor", "1.6"),
    TEXTURES_MODELS_ARMOR_CHAINMAIL_LAYER_1("textures\\models\\armor", "chainmail_layer_1", "1.6"),
    TEXTURES_MODELS_ARMOR_CHAINMAIL_LAYER_2("textures\\models\\armor", "chainmail_layer_2", "1.6"),
    TEXTURES_MODELS_ARMOR_DIAMOND_LAYER_1("textures\\models\\armor", "diamond_layer_1", "1.6"),
    TEXTURES_MODELS_ARMOR_DIAMOND_LAYER_2("textures\\models\\armor", "diamond_layer_2", "1.6"),
    TEXTURES_MODELS_ARMOR_GOLD_LAYER_1("textures\\models\\armor", "gold_layer_1", "1.6"),
    TEXTURES_MODELS_ARMOR_GOLD_LAYER_2("textures\\models\\armor", "gold_layer_2", "1.6"),
    TEXTURES_MODELS_ARMOR_IRON_LAYER_1("textures\\models\\armor", "iron_layer_1", "1.6"),
    TEXTURES_MODELS_ARMOR_IRON_LAYER_2("textures\\models\\armor", "iron_layer_2", "1.6"),
    TEXTURES_MODELS_ARMOR_LEATHER_LAYER_1("textures\\models\\armor", "chainmail_layer_1", "1.6"),
    TEXTURES_MODELS_ARMOR_LEATHER_LAYER_1_OVERLAY("textures\\models\\armor", "chainmail_layer_1_overlay", "1.6"),
    TEXTURES_MODELS_ARMOR_LEATHER_LAYER_2("textures\\models\\armor", "chainmail_layer_2", "1.6"),
    TEXTURES_MODELS_ARMOR_LEATHER_LAYER_2_OVERLAY("textures\\models\\armor", "chainmail_layer_2_overlay", "1.6"),
    TEXTURES_MODELS_ARMOR_NETHERITE_LAYER_1("textures\\models\\armor", "netherite_layer_1", "1.16"),
    TEXTURES_MODELS_ARMOR_NETHERITE_LAYER_2("textures\\models\\armor", "netherite_layer_2", "1.16"),
    TEXTURES_MODELS_ARMOR_TURTLE_LAYER_1("textures\\models\\armor", "turtle_layer_1", "1.13");

    Models(String dir, String nameNew, String nameOld, String introducedVersion) {
        this.dir = dir;
        this.nameNew = nameNew;
        this.nameOld = nameOld;
        this.introducedVersion = introducedVersion;
    }

    Models(String dir, String nameNew, String introducedVersion) {
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

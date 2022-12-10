package me.bruno.packbuilder.enums.texture;

import me.bruno.packbuilder.enums.Files;

public enum Misc implements Files {

    // textures/misc

    TEXTURES_MISC_ENCHANTED_ITEM_GLINT("textures\\misc", "enchanted_item_glint", "1.6"),
    TEXTURES_MISC_FORCEFIELD("textures\\misc", "forcefield", "1.8"),
    TEXTURES_MISC_NAUSEA("textures\\misc", "nausea", "1.16"),
    TEXTURES_MISC_POWDER_SNOW_OUTLINE("textures\\misc", "powder_snow_outline", "1.17"),
    TEXTURES_MISC_PUMPKINBLUR("textures\\misc", "pumpkinblur", "1.6"),
    TEXTURES_MISC_SHADOW("textures\\misc", "shadow", "1.6"),
    TEXTURES_MISC_SPYGLASS_SCOPE("textures\\misc", "spyglass_scope", "1.17"),
    TEXTURES_MISC_UNDERWATER("textures\\misc", "underwater", "1.6"),
    TEXTURES_MISC_UNKNOWN_PACK("textures\\misc", "unknown_pack", "1.6"),
    TEXTURES_MISC_UNKNOWN_SERVER("textures\\misc", "unknown_server", "1.8"),
    TEXTURES_MISC_VIGNETTE("textures\\misc", "vignette", "1.6"),
    TEXTURES_MISC_WHITE("textures\\misc", "white", "1.17");

    Misc(String dir, String nameNew, String nameOld, String introducedVersion) {
        this.dir = dir;
        this.nameNew = nameNew;
        this.nameOld = nameOld;
        this.introducedVersion = introducedVersion;
    }

    Misc(String dir, String nameNew, String introducedVersion) {
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

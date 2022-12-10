package me.bruno.packbuilder.enums.texture;

import me.bruno.packbuilder.enums.Files;

public enum MobEffect implements Files {

    // textures/mob_effect

    TEXTURES_MOB_EFFECT_ABSORPTION("textures\\mob_effect", "absorption", "1.14"),
    TEXTURES_MOB_EFFECT_BAD_OMEN("textures\\mob_effect", "bad_omen", "1.14"),
    TEXTURES_MOB_EFFECT_BLINDNESS("textures\\mob_effect", "blindness", "1.14"),
    TEXTURES_MOB_EFFECT_CONDUIT_POWER("textures\\mob_effect", "condiut_power", "1.14"),
    TEXTURES_MOB_EFFECT_DARKNESS("textures\\mob_effect", "darkness", "1.19"),
    TEXTURES_MOB_EFFECT_DOLPHINS_GRACE("textures\\mob_effect", "dolphins_grace", "1.14"),
    TEXTURES_MOB_EFFECT_FIRE_RESISTANCE("textures\\mob_effect", "fire_resistance", "1.14"),
    TEXTURES_MOB_EFFECT_GLOWING("textures\\mob_effect", "glowing", "1.14"),
    TEXTURES_MOB_EFFECT_HASTE("textures\\mob_effect", "haste", "1.14"),
    TEXTURES_MOB_EFFECT_HEALTH_BOOST("textures\\mob_effect", "health_boost", "1.14"),
    TEXTURES_MOB_EFFECT_HERO_OF_THE_VILLAGE("textures\\mob_effect", "hero_of_the_village", "1.14"),
    TEXTURES_MOB_EFFECT_HUNGER("textures\\mob_effect", "hunger", "1.14"),
    TEXTURES_MOB_EFFECT_INSTANT_DAMAGE("textures\\mob_effect", "instant_damage", "1.14"),
    TEXTURES_MOB_EFFECT_INSTANT_HEALTH("textures\\mob_effect", "instant_health", "1.14"),
    TEXTURES_MOB_EFFECT_INVISIBILITY("textures\\mob_effect", "invisibility", "1.14"),
    TEXTURES_MOB_EFFECT_JUMP_BOOST("textures\\mob_effect", "jump_boost", "1.14"),
    TEXTURES_MOB_EFFECT_LEVITATION("textures\\mob_effect", "levitation", "1.14"),
    TEXTURES_MOB_EFFECT_LUCK("textures\\mob_effect", "luck", "1.14"),
    TEXTURES_MOB_EFFECT_MINING_FATIGUE("textures\\mob_effect", "mining_fatigue", "1.14"),
    TEXTURES_MOB_EFFECT_NAUSEA("textures\\mob_effect", "nausea", "1.14"),
    TEXTURES_MOB_EFFECT_NIGHT_VISION("textures\\mob_effect", "night_vision", "1.14"),
    TEXTURES_MOB_EFFECT_POISON("textures\\mob_effect", "poison", "1.14"),
    TEXTURES_MOB_EFFECT_REGENERATION("textures\\mob_effect", "regeneration", "1.14"),
    TEXTURES_MOB_EFFECT_RESISTANCE("textures\\mob_effect", "resistance", "1.14"),
    TEXTURES_MOB_EFFECT_SATURATION("textures\\mob_effect", "saturation", "1.14"),
    TEXTURES_MOB_EFFECT_SLOW_FALLING("textures\\mob_effect", "slow_falling", "1.14"),
    TEXTURES_MOB_EFFECT_SLOWNESS("textures\\mob_effect", "slowness", "1.14"),
    TEXTURES_MOB_EFFECT_SPEED("textures\\mob_effect", "speed", "1.14"),
    TEXTURES_MOB_EFFECT_STRENGHT("textures\\mob_effect", "strenght", "1.14"),
    TEXTURES_MOB_EFFECT_UNLUCK("textures\\mob_effect", "unluck", "1.14"),
    TEXTURES_MOB_EFFECT_WATER_BREATHING("textures\\mob_effect", "water_breathing", "1.14"),
    TEXTURES_MOB_EFFECT_WEAKNESS("textures\\mob_effect", "weakness", "1.14"),
    TEXTURES_MOB_EFFECT_WITHER("textures\\mob_effect", "wither", "1.14");

    MobEffect(String dir, String nameNew, String nameOld, String introducedVersion) {
        this.dir = dir;
        this.nameNew = nameNew;
        this.nameOld = nameOld;
        this.introducedVersion = introducedVersion;
    }

    MobEffect(String dir, String nameNew, String introducedVersion) {
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

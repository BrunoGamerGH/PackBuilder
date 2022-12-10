package me.bruno.packbuilder;

public class Folders {

    private String name;
    private String dir;
    private String counterpart1_8;



    public Folders(String name, String dir, String counterpart1_8) {
        this.name = name;
        this.dir = dir;
        this.counterpart1_8 = counterpart1_8;
    }
    public Folders(String name, String dir) {
        this.name = name;
        this.dir = dir;
        this.counterpart1_8 = name;
    }


    public static String minecraftDir() {
        return Main.mainDir + "\\assets\\minecraft";
    }
    public static String assetsDir() {
        return Main.mainDir + "\\assets";
    }

    public String getDirectory() {
        return dir;
    }

    public String getCounterpart1_8() {
        return counterpart1_8;
    }

    public String getName() {
        return name;
    }
    public String finalDir() {
        return getDirectory() + "\\" + name;
    }
    public String finalDirWith1_8Name() {
        return getDirectory() + "\\" + counterpart1_8;
    }
    public String getBetterName() {
        return finalDir().replace(Main.mainDir + "\\assets\\minecraft\\", "").replace(Main.mainDir + "\\assets\\minecraft", "");
    }
    public String getBetter1_8Name() {
        return finalDirWith1_8Name().replace(Main.mainDir + "\\assets\\minecraft\\", "").replace(Main.mainDir + "\\assets\\minecraft", "");
    }


}

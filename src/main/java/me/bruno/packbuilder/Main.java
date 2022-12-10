package me.bruno.packbuilder;

import me.bruno.packbuilder.apache.FileUtils;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {


    private static final String[] versions = new String[]{"1.6", "1.7", "1.8", "1.9", "1.10", "1.11", "1.12", "1.13", "1.14", "1.15", "1.16", "1.17", "1.18", "1.19"};
    private static final String[] load = new String[]{"|", "/", "-", "\\"};
    private static final InputStreamReader inputStream = new InputStreamReader(System.in);
    private static final BufferedReader reader = new BufferedReader(inputStream);
    public static final String mainDir = System.getProperty("user.dir");
    private static final Folders[] defaultFolders = new Folders[]{new Folders("assets", mainDir), new Folders("minecraft",mainDir + "\\assets"),
    new Folders("blockstates", mainDir + "\\assets\\minecraft"),new Folders("font", mainDir + "\\assets\\minecraft"),
            new Folders("lang", mainDir + "\\assets\\minecraft"),
    new Folders("models",mainDir + "\\assets\\minecraft"), new Folders("block", mainDir + "\\assets\\minecraft\\models"),
            new Folders("item",mainDir + "\\assets\\minecraft\\models"),
    new Folders("texts", mainDir + "\\assets\\minecraft"), new Folders("textures", mainDir + "\\assets\\minecraft"),
            new Folders("block",mainDir + "\\assets\\minecraft\\textures", "blocks"),
    new Folders("colormap", mainDir + "\\assets\\minecraft\\textures"),
            new Folders("effect", mainDir + "\\assets\\minecraft\\textures"),
            new Folders("entity", mainDir + "\\assets\\minecraft\\textures"),
    new Folders("environment", mainDir + "\\assets\\minecraft\\textures"),
    new Folders("font", mainDir + "\\assets\\minecraft\\textures"),
    new Folders("gui", mainDir + "\\assets\\minecraft\\textures"),
    new Folders("item", mainDir + "\\assets\\minecraft\\textures", "items"),
    new Folders("map", mainDir + "\\assets\\minecraft\\textures"),
    new Folders("misc", mainDir + "\\assets\\minecraft\\textures"),
    new Folders("mob_effect", mainDir + "\\assets\\minecraft\\textures"),
    new Folders("models", mainDir + "\\assets\\minecraft\\textures"),
    new Folders("painting", mainDir + "\\assets\\minecraft\\textures"),
    new Folders("particle", mainDir + "\\assets\\minecraft\\textures")};
    private static File packName = new File(mainDir, "name.mcpack");
    private static File packDescription = new File(mainDir, "description.mcpack");
    private static boolean iscmd = false;

    private static boolean isZip = false;
    private static int remainingPercentage = 0;

    private static void deleteDir(File file) throws IOException {

        FileVisitor<Path> visitor = new SimpleFileVisitor<>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                if (exc != null) {
                    throw exc;
                }
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        };
        Files.walkFileTree(file.toPath(), visitor);

    }


    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName != null) {
                if (fileName.endsWith("/")) {
                    zipOut.putNextEntry(new ZipEntry(fileName));
                    ZipEntry entry = new ZipEntry(fileName);
                } else {
                    zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                }
            }


            File[] children = fileToZip.listFiles();

            for (File childFile : children) {
                if (fileName != null) {
                    zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
                } else {
                    zipFile(childFile, childFile.getName(), zipOut);
                }

            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }

    private static void createPack(String version) throws IOException, InterruptedException {
        BufferedReader nameReader = new BufferedReader(new FileReader(packName));
        BufferedReader descriptionReader = new BufferedReader(new FileReader(packDescription));
        File icon = new File(mainDir, "pack.png");
        String line = descriptionReader.readLine();
        int packFormat = switch (version) {
            case "1.6", "1.7", "1.8" -> 1;
            case "1.9", "1.10" -> 2;
            case "1.11", "1.12" -> 3;
            case "1.13", "1.14" -> 4;
            case "1.15" -> 5;
            case "1.16" -> 6;
            case "1.17" -> 7;
            case "1.18" -> 8;
            case "1.19" -> 9;
            default -> 10;

        };

        String mainFolderName = nameReader.readLine();
        File mainFolder = new File(mainDir, mainFolderName);
        if (mainFolder.exists()) {
            deleteDir(mainFolder);
        }
        mainFolder.mkdirs();

        File assets = new File(mainFolder.getAbsolutePath(), "assets");
        assets.mkdirs();
        File minecraft = new File(assets.getAbsolutePath(), "minecraft");
        minecraft.mkdirs();
        if (icon.exists()) {
            File iconTo = new File(assets.toPath() + "\\" + icon.getName());
            Files.copy(icon.toPath(), iconTo.toPath());
        }


        File packMcMeta = new File(mainFolder.getAbsolutePath(), "pack.mcmeta");
        BufferedWriter packWriter = new BufferedWriter(new FileWriter(packMcMeta));
        // i could not find a better way (no im not using the json dependency cuz im bored)
        packWriter.write("{");
        packWriter.newLine();
        packWriter.write("    \"pack\": {");
        packWriter.newLine();
        packWriter.write("        \"description\": \"" + line +"\",");
        packWriter.newLine();
        packWriter.write("        \"pack_format\": " + packFormat);
        packWriter.newLine();
        packWriter.write("    }");
        packWriter.newLine();
        packWriter.write("}");
        packWriter.flush();
        packWriter.close();

        int files = 0;
        int filesDone = 0;
                for (Folders folders: defaultFolders) {
                    File file = new File(folders.finalDir());
                    if (file.exists()) {
                        File[] flies = file.listFiles(pathname -> !pathname.isDirectory());
                        if (flies != null) {
                            files += flies.length;
                        }

                    }

                }
        File builderFolder = new File(mainDir + "\\assets");
        int versionNumber = Integer.parseInt(version.split("\\.")[1]);
        if (!builderFolder.exists()) {
            builderFolder.mkdirs();
        }
                for (Folders folders: defaultFolders) {





                    File folder;
                    if (!folders.finalDir().equalsIgnoreCase(mainDir + "\\assets\\minecraft")) {
                        if (versionNumber <= 12) {
                            folder = new File(minecraft.getAbsolutePath() + "\\" + folders.getBetter1_8Name());
                        } else {
                            folder = new File(minecraft.getAbsolutePath() + "\\" + folders.getBetterName());
                        }
                        if (folder.exists()) {
                            deleteDir(folder);
                        }


                        if (folders.finalDir().equalsIgnoreCase(Folders.minecraftDir() + "\\blockstates")) {
                            if (versionNumber >= 8) {
                                folder.mkdirs();
                            }
                        } else if (folders.finalDir().equalsIgnoreCase(Folders.minecraftDir() + "\\shaders")) {
                            if (versionNumber >= 7) {
                                folder.mkdirs();
                            }

                        } else if (folders.finalDir().equalsIgnoreCase(Folders.minecraftDir() + "\\textures\\mob_effect")) {
                            if (versionNumber >= 14) {
                                folder.mkdirs();
                            }
                        } else if (folders.finalDir().equalsIgnoreCase(Folders.minecraftDir() + "\\models")) {
                            if (versionNumber >= 8) {
                                folder.mkdirs();
                            }
                        } else if (folders.finalDir().equalsIgnoreCase(Folders.minecraftDir() + "\\particles")) {
                            if (versionNumber >= 14) {
                                folder.mkdirs();
                            }
                        } else {
                            folder.mkdirs();
                        }
                    }

                }

                            for (Path path : Files.walk(builderFolder.toPath()).collect(Collectors.toList())) {

                                File file = path.toFile();
                                if (!file.isDirectory()) {

                                    boolean found = false;

                                    Set<Class<?>> classes = ClassFinder.getClasses(FileUtils.toFile(Main.class.getProtectionDomain().getCodeSource().getLocation()), "me.bruno.packbuilder");
                                    List<me.bruno.packbuilder.enums.Files> filesList = new ArrayList<>();
                                    List<Class<?>> filesFound = classes.stream().filter(aClass -> aClass.isAssignableFrom(me.bruno.packbuilder.enums.Files.class)).collect(Collectors.toList());

                                    for (Class<?> clazz : filesFound) {
                                        if (clazz.getEnumConstants() != null) {
                                            me.bruno.packbuilder.enums.Files[] files1 = (me.bruno.packbuilder.enums.Files[]) clazz.getEnumConstants();
                                            Collections.addAll(filesList, files1);
                                        }

                                    }






                                    for (me.bruno.packbuilder.enums.Files mcFiles : filesList) {
                                        String fromMc = file.getPath().replace(builderFolder.getPath() + "\\minecraft\\", "");


                                        if (fromMc.startsWith(toOldName(mcFiles.getDirectory())) || fromMc.startsWith(toNewName(mcFiles.getDirectory()))) {
                                            String fileName = file.getName().split("\\.")[0];
                                            String rest = file.getName().replace(fileName, "");
                                            if (fileName.equalsIgnoreCase(mcFiles.getOlderNames()) || fileName.equalsIgnoreCase(mcFiles.getNewerName())) {
                                                found = true;
                                                String factoredName;
                                                if (fileName.equalsIgnoreCase(mcFiles.getOlderNames()) && versionNumber >= 13) {
                                                    factoredName = fileName.replace(mcFiles.getOlderNames(), mcFiles.getNewerName());
                                                } else if (fileName.equalsIgnoreCase(mcFiles.getNewerName()) && versionNumber < 13) {
                                                    factoredName = fileName.replace(mcFiles.getNewerName(), mcFiles.getOlderNames());
                                                } else {
                                                    factoredName = fileName;
                                                }

                                                String dir = versionNumber >= 13 ? mcFiles.getDirectory() : toOldName(mcFiles.getDirectory());
                                                File to = new File(minecraft + "\\" + dir + "\\" + factoredName + rest);

                                                int versionFound = Integer.parseInt(mcFiles.getIntroducedVersion().split("\\.")[1]);
                                                if (!to.exists()) {
                                                    if (versionFound <= versionNumber) {
                                                        File foddler = new File(to.toPath().toString().replace("\\" +to.getName(), ""));
                                                        if (foddler.isDirectory()) {
                                                            if (foddler.exists()) {
                                                                Files.copy(file.toPath(), to.toPath());
                                                            } else {
                                                                File newFile;
                                                                if (versionNumber >= 13) {
                                                                    newFile = new File(minecraft + "\\" + dir + "\\" + mcFiles.getOlderNames() + rest);
                                                                } else {
                                                                    newFile = new File(minecraft + "\\" + dir + "\\" + mcFiles.getNewerName() + rest);
                                                                }
                                                                Files.copy(file.toPath(), newFile.toPath());
                                                            }
                                                        }

                                                        if (!to.isDirectory()) {
                                                            filesDone++;
                                                        }
                                                    }

                                                }

                                                break;
                                            }

                                        }
                                    }

                                    if (!found) {

                                        String fromMc = file.getPath().replace(builderFolder.getPath() + "\\minecraft\\", "").replace(file.getName(), "");

                                        String updated;

                                        if (versionNumber <= 12) {
                                            updated = toOldName(fromMc);
                                        } else {
                                            updated = toNewName(fromMc);
                                        }



                                        File to = new File(minecraft.getPath() + "\\" + updated + "\\" + file.getName());
                                        Files.copy(file.toPath(), to.toPath());
                                        if (!to.isDirectory())
                                        filesDone++;
                                    }

                                }



                                if (filesDone == 0) {
                                    remainingPercentage = 0;
                                } else {
                                    remainingPercentage = (int) Math.round((double) filesDone/(double)files * 1000);
                                }

                            }

                if (isZip) {
                    File file = new File(mainDir + "\\" + mainFolderName + ".zip");
                    if (file.exists()) {
                        file.delete();
                    }
                    FileOutputStream fop = new FileOutputStream(mainFolderName +".zip");
                    ZipOutputStream stream = new ZipOutputStream(fop);
                    zipFile(mainFolder, null, stream);


                    stream.close();
                    fop.close();
                    deleteDir(mainFolder);
                }
        if (iscmd) {
            System.out.println("DONE!");
        } else {
            System.out.println(SystemColors.GREEN_BOLD_BRIGHT + "DONE!");
        }


        Thread.sleep(3000);
    }


    public static void main(String[] args) throws InterruptedException, IOException {

        String version = stateVersion(disclaimer(false));
        boolean confirm = confirm(version, true);
        if (confirm) {
            exportingType();
            if (!Main.iscmd)
                System.out.println(SystemColors.BLUE_BOLD_BRIGHT + "Creating pack..." + SystemColors.RESET);
            else
                System.out.println("Creating pack...");
            createPack(version);
        }
    }

    private static void createFolders() throws InterruptedException, IOException {
        for (Folders folders: defaultFolders) {
            File folder = new File(folders.finalDir());
            if (!folder.exists()) {
                if (folder.mkdirs()) {
                    if (Main.iscmd)
                        System.out.println("Folder " + folders.getBetterName() + " created.");
                    else
                        System.out.println(SystemColors.GREEN_BOLD_BRIGHT + "Folder " + folders.getBetterName() + " created." + SystemColors.RESET);
                    Thread.sleep(100);
                } else {
                    if (Main.iscmd)
                        System.out.println("Could not create folder " + folders.getBetterName());
                    else
                        System.out.println(SystemColors.RED_BOLD_BRIGHT + "Could not create folder " + folders.getBetterName() + SystemColors.RESET);
                }

            }
        }
        if (!packName.exists()) {
            if (packName.createNewFile()) {
                if (Main.iscmd)
                    System.out.println("File " + packName.getName() + " created.");
                else
                    System.out.println(SystemColors.GREEN_BOLD_BRIGHT + "File " + packName.getName() + " created." + SystemColors.RESET);
                Thread.sleep(100);
            } else {
                if (Main.iscmd)
                    System.out.println("Could not create file " + packName.getName());
                else
                    System.out.println(SystemColors.RED_BOLD_BRIGHT + "Could not create file " + packName.getName() + SystemColors.RESET);
            }

        }
        if (!packDescription.exists()) {
            if (packDescription.createNewFile()) {
                if (Main.iscmd)
                    System.out.println("File " + packDescription.getName() + " created.");
                else
                    System.out.println(SystemColors.GREEN_BOLD_BRIGHT + "File " + packDescription.getName() + " created." + SystemColors.RESET);
                Thread.sleep(100);
            } else {
                if (Main.iscmd)
                    System.out.println("Could not create file " + packDescription.getName());
                else
                    System.out.println(SystemColors.RED_BOLD_BRIGHT + "Could not create file " + packDescription.getName() + SystemColors.RESET);
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(packName));
            writer.write("AAAAAAA");
            writer.flush();
            writer.close();
            BufferedWriter writer1 = new BufferedWriter(new FileWriter(packDescription));
            writer1.write("This is a pack description!");
            writer1.flush();
            writer1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread.sleep(1000);
        System.out.println();
        canCloseFolder();
        System.out.println();
    }
    private static void canCloseFolder() throws InterruptedException {
        if (Main.iscmd)
            System.out.println("(You can close the terminal and modify the folders now, or wait a bit and continue the process.)");
        else
            System.out.println(SystemColors.CYAN_BOLD + "(You can close the terminal and modify the folders now, or wait a bit and continue the process.)" + SystemColors.RESET);
        Thread.sleep(5000);
    }
    private static boolean check() throws InterruptedException, IOException {
        ArrayList<String> folderList = new ArrayList<>();
        boolean theyExistNot = false;
        for (Folders folders : defaultFolders) {
            File file = new File(folders.finalDir());
            File file1_12 = new File(folders.finalDirWith1_8Name());
            if (folders.getName().equalsIgnoreCase("assets") || folders.getName().equalsIgnoreCase("minecraft")) {
                if (!file.exists()) {
                    theyExistNot = true;
                    if (Main.iscmd) {
                        System.out.println("MAIN FOLDER \"" + folders.getName() +  "\" COULD NOT BE FOUND, CREATING.");
                    } else {
                        System.out.println(SystemColors.RED_BOLD_BRIGHT + "MAIN FOLDER \"" + folders.getName() +  "\" COULD NOT BE FOUND, CREATING." + SystemColors.RESET);
                    }

                    Thread.sleep(1000);
                    if (file.mkdirs()) {
                        if (Main.iscmd) {
                            System.out.println("FOLDER CREATED.");
                        } else {
                            System.out.println(SystemColors.GREEN_BOLD_BRIGHT + "FOLDER CREATED." + SystemColors.RESET);
                        }
                    } else {
                        if (Main.iscmd) {
                            System.out.println("COULD NOT CREATE FOLDER, ENDING PROCESS.");
                        } else {
                            System.out.println(SystemColors.RED_BOLD_BRIGHT + "COULD NOT CREATE FOLDER, ENDING PROCESS." + SystemColors.RESET);
                            Thread.sleep(4000);
                            System.exit(7);
                        }
                    }
                    Thread.sleep(500);
                }
            } else {
                if (!file.exists() && !file1_12.exists()) {
                    folderList.add(folders.getName());
                }
            }

        }
        if (theyExistNot) {
            System.out.println();
            canCloseFolder();
            System.out.println();
        }

        StringBuilder finalthing = new StringBuilder();
        StringBuilder files = new StringBuilder();
        for (int i = 0; i < folderList.size(); i++) {
            if (i == folderList.size() - 2) {
                folderList.set(i, folderList.get(i) + " and ");
            } else if (i != folderList.size() - 1) {
                folderList.set(i, folderList.get(i) + ", ");
            }
            finalthing.append(folderList.get(i));
        }

        ArrayList<String> settings = new ArrayList<>();




        if (!packName.exists()) {
            settings.add(packName.getCanonicalFile().getName());
        }
        if (!packDescription.exists()) {
            settings.add(packDescription.getCanonicalFile().getName());
        }
        for (int i = 0; i < settings.size(); i++) {
            if (i == settings.size() - 2) {
                settings.set(i, settings.get(i) + " and ");
            } else if (!(i == settings.size() - 1)) {
                settings.set(i, settings.get(i) + ", ");
            }
            files.append(settings.get(i));
        }

        if (folderList.isEmpty() && settings.isEmpty()) {
            return true;
        }

        if (!folderList.isEmpty()) {
            if (Main.iscmd) {
                System.out.println("Folders " + finalthing + " dont exist, would you like to add them? (yes/no)");
            } else {
                System.out.println(SystemColors.YELLOW_BOLD_BRIGHT + "Folders " + finalthing + " dont exist, would you like to add them? (yes/no)" + SystemColors.RESET);
            }
        } else if (!settings.isEmpty()) {
            if (Main.iscmd) {
                System.out.println("Files " + files + " dont exist, would you like to add them? (yes/no)");
            } else {
                System.out.println(SystemColors.YELLOW_BOLD_BRIGHT + "Files " + files + " dont exist, would you like to add them? (yes/no)" + SystemColors.RESET);
            }
        } else {
            if (Main.iscmd) {
                System.out.println("Files " + files + " AND folders " + finalthing + " dont exist, would you like to add them? (yes/no)");
            } else {
                System.out.println(SystemColors.YELLOW_BOLD_BRIGHT + "Files " + files + " AND folders " + finalthing + " dont exist, would you like to add them? (yes/no)" + SystemColors.RESET);
            }
        }


        String answer = reader.readLine();
        if (answer.equalsIgnoreCase("yes")) {
            createFolders();
        } else if (!answer.equalsIgnoreCase("no")){
            check();
        }
        return false;
    }

    private static boolean disclaimer(boolean triedOnce) throws IOException {
        if (!triedOnce) {
            System.out.println("DISCLAIMER: ");
            System.out.println();
            System.out.println();
            System.out.println("Some files may need external configurations (texture files modifying placement or files not existing in later versions)");
        }
        System.out.println("Do you wish to proceed? (yes/no)");

        String answer = reader.readLine();
        if (answer.equalsIgnoreCase("yes")) {
            return starter(false);
        } else if (answer.equalsIgnoreCase("no")) {
            System.exit(69420);
            return false;
        } else {
            return disclaimer(true);
        }
    }

    private static boolean starter(boolean triedOnce) throws IOException {
        if (!triedOnce) {
            System.out.println();
            System.out.println();
            System.out.println("Please state if you are using normal Windows terminal (cmd) or using an external terminal.");
            System.out.println();
            System.out.println();
            System.out.println("Type \"YES\" if you are using Windows terminal, type \"NO\" if using external terminal (recommended).");
        } else {
            System.out.println();
            System.out.println("Please send \"YES\" or \"NO\"");
        }
        String answer = reader.readLine();
        if (answer.equalsIgnoreCase("yes")) {
            return true;
        } else if (answer.equalsIgnoreCase("no")) {
            return false;
        } else {
            return starter(true);
        }
    }

    private static String stateVersion(boolean iscmd) throws InterruptedException, IOException {
        Main.iscmd = iscmd;
        check();
        if (!iscmd)
        System.out.println(SystemColors.GREEN_BOLD_BRIGHT + "Please provide a minecraft version ->"  + SystemColors.RESET);
        else
            System.out.println("Please provide a minecraft version ->");

        String line = null;
        try {
            line = reader.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
        String finalLine = line;
        if (Arrays.stream(versions).anyMatch(s -> s.equalsIgnoreCase(finalLine))) {

            return line;
        } else {
            if (!iscmd)
            System.out.println(SystemColors.RED_BOLD_BRIGHT + "Minecraft version does not exist or is not supported. (" + finalLine + ")"  + SystemColors.RESET);
            else
                System.out.println("Minecraft version does not exist or is not supported. (" + finalLine + ")");
            Thread.sleep(2000);
            System.out.println();
            return stateVersion(Main.iscmd);
        }

    }
    private static void exportingType() throws IOException {
        if (Main.iscmd)
            System.out.println("Would you like it to be a zip file or a folder? (zip/folder)");
        else
            System.out.println(SystemColors.PURPLE_BOLD_BRIGHT + "Would you like it to be a zip file or a folder? (zip/folder)" + SystemColors.RESET);

        String answer = reader.readLine();
        if (answer.equalsIgnoreCase("zip")) {
            isZip = true;
        } else if (answer.equalsIgnoreCase("folder")) {
            isZip = false;
        } else {
            exportingType();
        }
    }
    private static boolean confirm(String result, boolean firstAttempt) throws InterruptedException, IOException {
        if (firstAttempt) {
            if (!Main.iscmd)
            System.out.format(SystemColors.PURPLE_BOLD_BRIGHT + "The selected version is: " + SystemColors.YELLOW_BOLD_BRIGHT + "%s. " + SystemColors.RESET, result);
            else
                System.out.format( "The selected version is: %s.", result);
            System.out.println();
        } else {
            if (!Main.iscmd)
            System.out.println(SystemColors.RED_BOLD_BRIGHT + "Please type yes or no." + SystemColors.RESET);
            else
                System.out.println("Please type yes or no.");
        }
        Thread.sleep(500);


        if (!Main.iscmd)
        System.out.println(SystemColors.GREEN_BOLD_BRIGHT + "Confirm? (yes/no) ->" + SystemColors.RESET);
        else
            System.out.println("Confirm? (yes/no) ->");
        String line = null;
        try {
            line = reader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (line.equalsIgnoreCase("yes")) {
            return true;
        } else if (line.equalsIgnoreCase("no")) {
            if (!Main.iscmd)
            System.out.println(SystemColors.RED_BOLD_BRIGHT + "Interaction cancelled." + SystemColors.RESET);
            else
                System.out.println("Interaction cancelled.");
            Thread.sleep(2000);
            System.out.println();
           return confirm(stateVersion(iscmd), true);
        } else {
            return confirm(result, false);
        }
    }

    public static String toOldName(String dir) {
        if (dir.contains("block")) {
            String found = dir.replace("block", "blocks");
            if (found.contains("blockss")) {
                return found.replace("blockss", "blocks");
            } else {
                return found;
            }
        } else if (dir.contains("item")) {
            String found = dir.replace("item", "items");
            if (found.contains("itemss")) {
                return found.replace("itemss", "items");
            } else {
                return found;
            }
        }
        return dir;
    }

    public static String toNewName(String dir) {
        if (dir.contains("blocks")) {
            return dir.replace("blocks", "block");
        } else if (dir.contains("items")) {
            return dir.replace("items", "item");
        }
        return dir;
    }




}

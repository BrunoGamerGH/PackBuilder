package me.bruno.packbuilder.apache.filefilter;


import java.io.File;

public class FileFileFilter extends AbstractFileFilter {
    public static final IOFileFilter FILE = new FileFileFilter();

    protected FileFileFilter() {
    }

    public boolean accept(File file) {
        return file.isFile();
    }
}
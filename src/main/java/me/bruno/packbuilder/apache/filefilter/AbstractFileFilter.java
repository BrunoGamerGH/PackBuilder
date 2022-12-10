package me.bruno.packbuilder.apache.filefilter;

import java.io.File;

public abstract class AbstractFileFilter implements IOFileFilter {
    public AbstractFileFilter() {
    }

    public boolean accept(File file) {
        return this.accept(file.getParentFile(), file.getName());
    }

    public boolean accept(File dir, String name) {
        return this.accept(new File(dir, name));
    }
}

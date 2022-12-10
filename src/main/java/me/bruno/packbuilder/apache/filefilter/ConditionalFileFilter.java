package me.bruno.packbuilder.apache.filefilter;

import java.util.List;

public interface ConditionalFileFilter {
    void addFileFilter(IOFileFilter var1);

    List getFileFilters();

    boolean removeFileFilter(IOFileFilter var1);

    void setFileFilters(List var1);
}

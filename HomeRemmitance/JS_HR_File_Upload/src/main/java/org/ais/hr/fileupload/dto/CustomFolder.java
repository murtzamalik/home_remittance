package org.ais.hr.fileupload.dto;

import java.util.ArrayList;
import java.util.List;

public class CustomFolder {

    private String name;

    List<CustomFile> files;

    List<CustomFolder> folders;

    public CustomFolder(String name) {
        files = new ArrayList<>();
        folders = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CustomFile> getFiles() {
        return files;
    }

    public void addFile(CustomFile file) {
        this.files.add(file);
    }

    public List<CustomFolder> getFolders() {
        return folders;
    }

    public void addFolder(CustomFolder folder) {
        this.folders.add(folder);
    }

    @Override
    public String toString() {
        return "CustomFolder [name=" + name + ", files=" + files + ", folders=" + folders + "]";
    }
}

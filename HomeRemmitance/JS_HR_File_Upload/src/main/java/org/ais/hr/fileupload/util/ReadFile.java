package org.ais.hr.fileupload.util;

import org.ais.hr.fileupload.dto.CustomFile;
import org.ais.hr.fileupload.dto.CustomFolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    public List<CustomFile> FileReader(String path) throws IOException {
        CustomFolder parentFolder = new CustomFolder(path);
        List<CustomFile> mylist = displayIt(parentFolder);
        return mylist;
    }

    public List<CustomFile> displayIt(CustomFolder parentFolder) throws IOException {
        List<CustomFile> customFiles = new ArrayList<>();
        File node = new File(parentFolder.getName());
        if (node.isDirectory()) {
            String[] subNote = node.list();
            for (String filename : subNote) {
                String path = node + "\\" + filename;
                if (new File(path).isDirectory()) {
                    CustomFolder folder = new CustomFolder(path);
                    parentFolder.addFolder(folder);
                    displayIt(folder);
                } else {
                    File file = new File(path);
                    CustomFile customFile = new CustomFile();
                    customFile.setFileName(filename);
                    customFile.setFilePath(path);
                    customFiles.add(customFile);
                }
            }
        }
        return customFiles;
    }

}

package org.ais.hr.fileupload.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 3/21/2022
 * Time: 1:14 PM
 * Project : login
 */
public class NACTAListFromDirectoryRequest {


    @NotEmpty
    @NotNull
    private String filePath;


    @NotEmpty
    @NotNull
    private String fileName;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

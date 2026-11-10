package org.ais.hr.user_management.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Validated
public class TblMenuRequest {

    @NotNull(message = "Please Enter Icon Name")
    @NotEmpty(message = "Please Enter Icon Name")
    private String iconName;
    @NotNull(message = "Please Enter Icon Path")
    @NotEmpty(message = "Please Enter Icon Path")
    private String iconPath;

    @NotNull(message = "Please Enter Menu Code")
    @NotEmpty(message = "Please Enter Menu Code")
    private String menuCode;
    @NotNull(message = "Please Enter Menu Description")
    @NotEmpty(message = "Please Enter Menu Description")
    private String menuDescription;
    @NotNull(message = "Please Enter Menu Path")
    @NotEmpty(message = "Please Enter Menu Path")
    private String menuPath;
    @NotNull(message = "Please Enter Menu Type")
    @NotEmpty(message = "Please Enter Menu Type")
    private String menuType;
    @NotNull(message = "Please Enter Module Id")
    @Min(value=0,message="Please Enter Module Id")
    private long moduleId;

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public long getModuleId() {
        return moduleId;
    }

    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }
}

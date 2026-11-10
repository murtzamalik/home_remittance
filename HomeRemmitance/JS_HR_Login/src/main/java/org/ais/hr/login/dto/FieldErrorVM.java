package org.ais.hr.login.dto;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 1/28/2022
 * Time: 11:23 AM
 * Project : jcash
 */
public class FieldErrorVM {

    private String toastError;
    private String field;
    private String message;

    public FieldErrorVM(String field, String message) {
        this.toastError = "Enter All Required Fields.";
        this.field = field;
        this.message = message;
    }


    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToastError() {
        return toastError;
    }

    public void setToastError(String toastError) {
        this.toastError = toastError;
    }
}

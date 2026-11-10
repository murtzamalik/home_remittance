package org.ais.hr.transactions.exception;
import java.time.Instant;


public class ErrorMessage {

    private String timeStamp;
    private Integer status;
    private String error;
    private String message;


    public ErrorMessage(String timeStamp, Integer status, String error, String message) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }



    public ErrorMessage() {
        setTimeStamp(Instant.now().toString());
    }

    public ErrorMessage(Integer status, String error, String message) {
        setTimeStamp(Instant.now().toString());
        setStatus(status);
        setError(error);
        setMessage(message);
    }



    public String getTimeStamp() {
        return timeStamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "timeStamp='" + timeStamp + '\'' +
                ", status=" + status +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}


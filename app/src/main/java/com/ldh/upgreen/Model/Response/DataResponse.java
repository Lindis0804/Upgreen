package com.ldh.upgreen.Model.Response;

public class DataResponse {
    private boolean success = false;
    private String message="";
    private Data data = new Data();
    public DataResponse(){};

    public DataResponse(boolean success, String message, Data data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}

package org.edb.main.network.get;

import org.edb.main.ExternalService;

import java.util.ArrayList;

public class getExternalServiceListResponse {
    private int status;
    private Boolean success;
    private String message;
    private ArrayList<ExternalService> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public ArrayList<ExternalService> getData() {

        return data;
    }

    public void setData(ArrayList<ExternalService> data) {

        this.data = data;


    }

}

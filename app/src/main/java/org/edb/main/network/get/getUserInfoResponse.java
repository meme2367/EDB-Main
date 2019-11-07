
package org.edb.main.network.get;

import org.edb.main.TempUser;

public class getUserInfoResponse{
    private int status;
    private Boolean success;
    private String message;

    private TempUser data;


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


    public TempUser getData() {
        return data;
    }

    public void setData(TempUser data){
        this.data = data;
    }

}
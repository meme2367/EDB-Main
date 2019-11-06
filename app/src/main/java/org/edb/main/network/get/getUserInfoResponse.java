
package org.edb.main.network.get;

import com.google.gson.JsonObject;
import org.edb.main.User;

public class getUserInfoResponse{
    private int status;
    private Boolean success;
    private String message;

    private User data;


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


    public User getData() {
        return data;
    }

    public void setData(User data){
        this.data = data;
    }

}
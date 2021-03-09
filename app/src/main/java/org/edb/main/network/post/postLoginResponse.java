package org.edb.main.network.post;

public class postLoginResponse {
    private int status;
    private Boolean success;
    private String message;
    private Token data;


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

    public Token getData() {
        return data;
    }

    public String getToken() {
        return data.token.token;
    }


    public void setData(Token data) {

        this.data.token.token = data.token.token;
        this.data.token.refreshtoken = data.token.refreshtoken;
    }


}

class Token{
    public Token2 token;
}

class Token2{
    public String token;
    public String refreshtoken;


}
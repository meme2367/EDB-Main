package org.edb.main;
//유저 모델 클래스

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {

    private int idx;
    private StringProperty id;
    private StringProperty passwd;
    private StringProperty email;
    private StringProperty token;

    private StringProperty grade;//ADMIN, SERVICE_PROVIDER, USER


    public User(String id,String passwd,String email,String grade){
        this.id = new SimpleStringProperty(id);
        this.passwd = new SimpleStringProperty(passwd);
        this.email = new SimpleStringProperty(email);
        this.grade = new SimpleStringProperty(grade);

    }

    public String getPasswd() {
        return passwd.get();
    }

    public void setPasswd(String passwd){
        this.passwd.set(passwd);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id){
        this.id.set(id);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email){
        this.email.set(email);
    }

/*
    public int getIdx() {
        return idx.get();
    }

    public void setIdx(int idx){
        this.idx.set(idx);
    }
*/
    public String getGrade() {
        return grade.get();
    }

    public void setGrade(String grade){
        this.grade.set(grade);
        if(grade.isEmpty()) {this.grade.set("USER");}
    }

    public String getToken() {
        return token.get();
    }

    public void setToken(String token){
        this.token.set(token);
    }


}

package org.edb.main;


public class ExternalService {
    int user_idx;
    int external_service_idx;
    String name;
    String url;

    public ExternalService(int user_idx, int external_service_idx, String name, String url) {
        this.user_idx = user_idx;
        this.external_service_idx = external_service_idx;
        this.name = name;
        this.url = url;
    }

    public String  getName() {

        return name;
    }

    public String  getUrl() {

        return url;
    }

    public int getExternal_service_idx(){
        return external_service_idx;
    }
}

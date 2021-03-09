package org.edb.main;

public class ExternalServiceDetail {
    int if_achieve;
    int external_service_detail_idx;
    String name;

    public ExternalServiceDetail(int if_achieve, int external_service_detail_idx, String name) {
        this.if_achieve = if_achieve;
        this.external_service_detail_idx = external_service_detail_idx;
        this.name = name;
    }

    public String  getName() {

        return name;
    }

    public int getIf_achieve() {

        return if_achieve;
    }

    public int getExternal_service_detail_idx(){
        return external_service_detail_idx;
    }
}

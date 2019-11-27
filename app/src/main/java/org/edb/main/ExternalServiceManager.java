package org.edb.main;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ExternalServiceManager {
    private static ExternalServiceManager externalServiceManager;

    private Map<Integer, ExternalService> externalServices;
    private Map<Integer, ExternalServiceDetail> externalServiceDetails;


    private ExternalServiceManager(){
        externalServices=Collections.synchronizedMap(new HashMap<Integer,ExternalService>());
        externalServiceDetails=Collections.synchronizedMap(new HashMap<Integer,ExternalServiceDetail>());
    }

    public synchronized static ExternalServiceManager getExternalServiceManager(){
        if(externalServiceManager==null){
            externalServiceManager= new ExternalServiceManager();
        }

        return externalServiceManager;
    }

    public Map<Integer,ExternalService> getExternalServices(){
        return Collections.unmodifiableMap(externalServices);
    }

    public Map<Integer,ExternalServiceDetail> getExternalServiceDetails(){
        return Collections.unmodifiableMap(externalServiceDetails);
    }

    public void loadExternalServices(Iterable<ExternalService> data){
        for (ExternalService externalService:data) {
            externalServices.put(externalService.getExternal_service_idx(),externalService);
        }
    }

    public void loadExternalServiceDetails(Iterable<ExternalServiceDetail> data){
        for (ExternalServiceDetail externalServiceDetail:data) {
            externalServiceDetails.put(externalServiceDetail.getExternal_service_detail_idx(),externalServiceDetail);
        }
    }
}

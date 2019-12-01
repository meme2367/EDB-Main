package org.edb.main;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class PluginConfigConverter {
    protected Map<String,String> pluginConfigs;
    protected Date startDate;
    protected Date endDate;

    protected PluginConfigConverter(){
        pluginConfigs = new HashMap<String,String>();
    }

    public void addSingleConfig(String configName, String configStr){
        pluginConfigs.put(configName,configStr);
    }

    public void setSchedule(Date startDate, Date endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }

    protected abstract void convert();

    public abstract void convertStrConfigToMap(String configuration) ;

    public Map<String,String> getPluginConfigMap(){
        return pluginConfigs;
    }
}

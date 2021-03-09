package org.edb.main;

import org.edb.main.model.TargetProgram;
import org.edb.main.model.TargetWebsite;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class PluginConfigConverter {
    protected Map<String,Map<String,String>> pluginConfigs;
    protected Map<String,TargetProgram> targetPrograms;
    protected Map<String,TargetWebsite> targetWebsites;
    protected Date startDate;
    protected Date endDate;

    protected PluginConfigConverter(){
        pluginConfigs = new HashMap<String,Map<String,String>>();
    }

    public void addSingleConfig(String configName, Map<String,String> configAttributes){
        pluginConfigs.put(configName,configAttributes);
    }

    public void setSchedule(Date startDate, Date endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }

    protected abstract void makeFormatForPost();

    public abstract void convertStrConfigs(String configuration) ;

    public Map<String,Map<String,String>> getPluginConfigMap(){
        return pluginConfigs;
    }

    public abstract void setTargetPrograms(Map<String, TargetProgram> targetPrograms);

    public abstract void setTargetWebsites(Map<String, TargetWebsite> targetWebsites);

    public abstract Map<String, TargetProgram> getTargetPrograms();

    public abstract Map<String, TargetWebsite> getTargetWebsites();
}

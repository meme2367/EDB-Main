package org.edb.main;

import org.edb.main.model.PluginModel;
import org.edb.main.model.TargetProgram;
import org.edb.main.model.TargetWebsite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public abstract class EDBPlugin {
    protected Map<String,PluginConfig> pluginConfigs;
    protected Map<String, TargetProgram> targetPrograms;
    protected Map<String, TargetWebsite> targetWebsites;

    protected Date startDate;
    protected Date endDate;
    protected int pulginIdx;
    protected boolean isRunning;
    protected String fxPath;
    protected String androidPath;

    public abstract String getPluginConfigUIPath();

    public abstract void checkLifeCycle();
    public abstract void renewTrackingTarget();
    public abstract void checkForLogics();

    public  void extractConfigs(PluginConfigConverter pluginConfigConverter){
        pluginConfigConverter.setSchedule(startDate, endDate);
        pluginConfigConverter.setTargetPrograms(targetPrograms);
        pluginConfigConverter.setTargetWebsites(targetWebsites);
        for (Map.Entry<String, PluginConfig> entry : pluginConfigs.entrySet()){
            entry.getValue().extractConfig(pluginConfigConverter);
        }
    }

    public void decodeConfigs(PluginModel data, PluginConfigConverter pluginConfigConverter){
        Date from = new Date();
        Date to = new Date();
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            from = fm.parse(data.getStart_time());
            to = fm.parse(data.getEnd_time());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.startDate = from;
        this.endDate = to;

        pluginConfigConverter.convertStrConfigs(data.getConfiguration());
        targetPrograms = pluginConfigConverter.getTargetPrograms();
        targetWebsites = pluginConfigConverter.getTargetWebsites();
        Map<String,Map<String,String>> strConfigsMap = pluginConfigConverter.getPluginConfigMap();

//        TODO EDBPlugin 에러수정
        for (Map.Entry<String, Map<String,String>> entry : strConfigsMap.entrySet()) {
            pluginConfigs.get(entry.getKey()).decode(entry.getValue());
        }
    }

    public void applySchedule(Date startDate, Date endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void addTargetProgram(TargetProgram targetProgram) {
        targetPrograms.put(targetProgram.getTargetName(),targetProgram);
    }

    public void removeTargetProgram(TargetProgram targetProgram) {
        targetPrograms.remove(targetProgram.getTargetName());
    }

    public void addTargetWebsite(TargetWebsite targetWebsite) {
        targetWebsites.put(targetWebsite.getTargetURL(),targetWebsite);
    }

    public void removeTargetWebsite(TargetWebsite targetWebsite) {
        targetWebsites.remove(targetWebsite.getTargetURL());
    }

    public void applySingleConfig(String configName, String config){
        pluginConfigs.get(configName).addSingleConfig(config);
    }
}

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

        pluginConfigConverter.convertStrConfigToMap(data.getConfiguration());
        Map<String,String> strConfigsMap = pluginConfigConverter.getPluginConfigMap();

        for (Map.Entry<String, String> entry : strConfigsMap.entrySet()) {
            pluginConfigs.get(entry.getKey()).decode(entry.getValue());
        }
    }

    public void applySchedule(Date startDate, Date endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void addTargetProgram(TargetProgram targetProgram) {
        pluginConfigs.get("TargetProgram").addSingleConfig(targetProgram.toString());
    }

    public void removeTargetProgram(TargetProgram targetProgram) {
        pluginConfigs.get("TargetProgram").removeSingleConfig(targetProgram.toString());
    }

    public void removeTargetWebsite(TargetWebsite targetWebsite) {
        pluginConfigs.get("TargetWebsite").removeSingleConfig(targetWebsite.toString());
    }

    public void addTargetWebsite(TargetWebsite targetWebsite) {
        pluginConfigs.get("TargetWebsite").addSingleConfig(targetWebsite.toString());
    }

    public void applySingleConfig(String configName, String config){
        pluginConfigs.get(configName).addSingleConfig(config);
    }
}

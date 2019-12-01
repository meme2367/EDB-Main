package org.edb.main;

import org.edb.main.model.PluginModel;

import java.util.HashMap;
import java.util.Map;

public class EDBPluginManager {

    private Map<Integer,EDBPlugin> plugins;

    public EDBPluginManager(){
        plugins = new HashMap<Integer, EDBPlugin>();
    }

    public String findPluginConfigUIPath(Integer pluginIdx){

        return plugins.get(pluginIdx).getPluginConfigUIPath();
    }

    public void collectConfigs(int pluginIdx, PluginConfigConverter pluginConfigConverter) {

        EDBPlugin collectTargetPlugin = plugins.get(pluginIdx);
        collectTargetPlugin.extractConfigs(pluginConfigConverter);
    }

    public void applyConfigsFromServer(int pluginIdx, PluginModel data, PluginConfigConverter pluginConfigConverter){
        plugins.get(pluginIdx).decodeConfigs(data,pluginConfigConverter);
    }
}

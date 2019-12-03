package org.edb.main;

import org.edb.main.Platform.OSNativeExecutor;
import org.edb.main.Platform.WindowsNativeExecutor;
import org.edb.main.model.PluginModel;
import org.edb.main.model.TargetProgram;
import org.edb.main.util.DateFormatter;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

    public void scan(){
        Date curTime = new Date();
        try {
            curTime = DateFormatter.getSimpleFormattedDateFromDate(curTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        OSNativeExecutor osNativeExecutor = new WindowsNativeExecutor();
        List<String> curPrograms = osNativeExecutor.getCurPrograms();
        List<String> curWebsites = osNativeExecutor.getCurWebsites();
        for (EDBPlugin singlePlugin :
                plugins.values()) {
            singlePlugin.checkLifeCycle(curTime);
            singlePlugin.checkForLogics(curPrograms,curWebsites,curTime);
        }
    }
}

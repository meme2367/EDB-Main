package org.edb.main;

import java.util.HashMap;
import java.util.Map;

public class EDBPluginManager {

    private Map<Integer,EDBPlugin> plugins;

    public String findPluginConfigUIPath(Integer pluginIdx){

        return plugins.get(pluginIdx).getPluginConfigUIPath();
    }
}

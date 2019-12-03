package org.edb.cycle;

import org.edb.main.EDBPlugin;
import org.edb.main.PluginLogic;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class CyclePlugin extends EDBPlugin {
    private static final int pluginIdx=2;

    public String getPluginConfigUIPath() {
        return null;
    }

    public void checkLifeCycle() {

    }

    public void renewTrackingTarget() {

    }

    public void checkForLogics(List<String> curPrograms, List<String> curWebsites, Date curTime) {
        if(isRunning) {
            for (Map.Entry<String, PluginLogic> singleLogic : pluginLogics.entrySet()) {
                singleLogic.getValue().checkForLogic(this,curPrograms, curWebsites, curTime);
            }
        }
    }

    public int getPluginIdx() {
        return pluginIdx;
    }
}

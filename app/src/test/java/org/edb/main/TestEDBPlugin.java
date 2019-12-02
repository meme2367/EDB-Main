package org.edb.main;

import org.edb.main.model.TargetProgram;
import org.edb.main.model.TargetWebsite;

import java.util.HashMap;

public class TestEDBPlugin extends EDBPlugin {

    private static final int pluginIdx=1;

    public TestEDBPlugin(){
        pluginConfigs = new HashMap<String,PluginConfig>();
        targetPrograms = new HashMap<String, TargetProgram>();
        targetWebsites = new HashMap<String, TargetWebsite>();
        TestPluginConfig testPluginConfig = new TestPluginConfig();
        pluginConfigs.put("TestPluginConfig", testPluginConfig);

    }


    @Override
    public String getPluginConfigUIPath() {
        return null;
    }

    @Override
    public void checkLifeCycle() {

    }

    @Override
    public void renewTrackingTarget() {

    }

    @Override
    public void checkForLogics() {

    }

    @Override
    public int getPluginIdx() {
        return pluginIdx;
    }
}

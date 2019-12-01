package org.edb.main;

import java.util.HashMap;

public class TestEDBPlugin extends EDBPlugin {


    public TestEDBPlugin(){
        pluginConfigs = new HashMap<String,PluginConfig>();
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
}

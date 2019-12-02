package org.edb.main;

import java.util.Map;

public abstract class PluginConfig {
    public abstract void addSingleConfig(String singleConfig);

    public abstract void decode(Map<String, String> decodeConfig);

    public abstract void removeSingleConfig(String singleConfig);

    public abstract void extractConfig(PluginConfigConverter pluginConfigConverter);

}

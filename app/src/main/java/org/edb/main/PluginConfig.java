package org.edb.main;

public abstract class PluginConfig {
    public abstract void addSingleConfig(String singleConfig);

    public abstract void decode(String decodeConfig);

    public abstract void removeSingleConfig(String singleConfig);

    public abstract void extractConfig(PluginConfigConverter pluginConfigConverter);

}

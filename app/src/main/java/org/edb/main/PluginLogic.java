package org.edb.main;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class PluginLogic {

    public abstract void addSingleConfig(String attributeName, String attributeValue);

    public abstract void decodeFromMap(Map<String, String> decodeConfig);

    public abstract void removeSingleConfig(String singleConfig);

    public abstract void extractConfig(PluginConfigConverter pluginConfigConverter);

    public abstract void checkForLogic(EDBPlugin plugin, List<String> curPrograms, List<String> curWebsites, Date curTime);
}

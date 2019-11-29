package org.edb.main;

import java.util.Date;

public abstract class EDBPlugin {
    private Iterable<PluginConfig> pluginConfigs;
    private Date startDate;
    private Date endDate;
    private int pulginIdx;
    private boolean isRunning;
    private String fxPath;
    private String androidPath;

    public abstract void addTrackingTarget();
    public abstract void removeTrackingTarget();
    public abstract void extractConfigs();
    public abstract void decodeConfigs();
    public abstract String getPluginConfigUIPath();
    public abstract void applySchedule();
    public abstract void checkLifeCycle();
    public abstract void renewTrackingTarget();
    public abstract void checkForLogics();
}

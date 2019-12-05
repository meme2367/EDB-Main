package org.edb.main.UI;

import org.edb.main.PluginLogic;

public interface SpecificConfigUIController {

    public abstract void setPluginLogic(PluginLogic logic);

    public abstract void onPluginStart();
    public abstract void onPluginFinished();
    public abstract void renewUI();
}

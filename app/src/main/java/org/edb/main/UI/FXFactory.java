package org.edb.main.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import org.edb.main.BootApp;
import org.edb.main.EDBPluginManager;
import org.edb.main.UIEventHandler;


public class FXFactory {
    private UIEventHandler uiEventHandler;
    private FXManipulator fxManipulator;
    private EDBPluginManager pluginManager;

    private static FXFactory fxFactory;


    public void setPluginManager(EDBPluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }


    public static synchronized FXFactory getInstance(){
        if(fxFactory==null){
            fxFactory=new FXFactory();
        }
        return fxFactory;
    }

    private FXFactory() { }

    public void init(UIEventHandler uiEventHandler, FXManipulator fxManipulator){
        this.uiEventHandler=uiEventHandler;
        this.fxManipulator=fxManipulator;
    }

    public Parent loadMainUI(String path)throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BootApp.class.getResource(path));
        Parent parent =loader.load();

        loader.<MainUIController>getController().setUiEventHandler(uiEventHandler);
        fxManipulator.setMainUIController(loader.getController());

        return parent;
    }

    public Parent loadLoginUI(String path)throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent parent = loader.load();

        loader.<LoginDialogController>getController().setUiEventHandler(uiEventHandler);
        fxManipulator.setLoginDialogController(loader.getController());

        return parent;

    }

    public Parent loadAvailableExternalServiceUI(String path)throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent parent = loader.load();

        loader.<AvailableExternalServiceListController>getController().setUiEventHandler(uiEventHandler);
        fxManipulator.setAvailableExternalServicelListController(loader.getController());

        return parent;
    }

    public Parent loadUserExternalServiceUI(String path) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent parent = loader.load();

        UserExternalServiceListController controller = loader.<UserExternalServiceListController>getController();
        controller.setUiEventHandler(uiEventHandler);
        fxManipulator.setUserExternalServicelListController(loader.getController());
        controller.loadUserExternalServiceList();

        return parent;

    }

    public Parent loadPluginConfigUI(String path)throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent parent = loader.load();

        PluginConfigUIController controller = loader.<PluginConfigUIController>getController();
        controller.setUIEventHandler(uiEventHandler);
        controller.setPluginManager(pluginManager);

        return parent;
    }
}

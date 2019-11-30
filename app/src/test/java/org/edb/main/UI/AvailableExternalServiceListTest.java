package org.edb.main.UI;


import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.edb.main.ExternalService;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.util.ArrayList;


public class AvailableExternalServiceListTest extends Application
{
    public Stage primaryStage;
    public AvailableExternalServiceListController controller;
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage=stage;
        loadAvailableExternalServiceListFXML();
        convertToRowsTest(createServiceList());
        controller.addRowsToTableView();
    }

    public void loadAvailableExternalServiceListFXML()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BootApp.class.getResource("/fxml/AvailableExternalServiceList.fxml"));
        Parent parent= null;

        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        controller=(AvailableExternalServiceListController)loader.getController();
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    private ArrayList<ExternalService> createServiceList(){
        ArrayList<ExternalService> data=new ArrayList<>();
        data.add(new ExternalService(1, 2,"inflearn","www.inflearn.com"));
        return data;
    }

    private void convertToRowsTest(ArrayList<ExternalService> data){
        controller.convertToRows(data);
        ObservableList<FXExternalService> availableExternalData=controller.getAvailableExternalData();
        int size=availableExternalData.size();

        for (FXExternalService single:availableExternalData) {
            System.out.println(single.toString());
        }
    }

}

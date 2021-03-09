package org.edb.main.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.edb.main.BootApp;
import org.edb.main.ExternalService;
import org.edb.main.ExternalServiceDetail;
import org.edb.main.UIEventHandler;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

public class UserExternalServiceListTest extends Application {
    public Stage primaryStage;
    public UserExternalServiceListController controller;
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage=stage;
        loadUserExternalServiceListFXML();
        controller.handleUserExternalServiceResponse(createUserExternalServiceList());
        controller.showExternalServiceDetailTableList(2, createUserExternalServiceDetail());
    }


    public void loadUserExternalServiceListFXML()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BootApp.class.getResource("/fxml/UserExternalServiceList.fxml"));
        Parent parent= null;

        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        controller=(UserExternalServiceListController) loader.getController();
        controller.setUiEventHandler(getUIEventHandler());

        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    private ArrayList<ExternalService> createUserExternalServiceList(){
        ArrayList<ExternalService> data=new ArrayList<>();
        data.add(new ExternalService(1, 2,"inflearn","www.inflearn.com"));
        return data;
    }

    private ArrayList<ExternalServiceDetail> createUserExternalServiceDetail(){
        ArrayList<ExternalServiceDetail> data=new ArrayList<>();
        data.add(new ExternalServiceDetail(1,2,"lecture1"));
        return data;
    }

    private UIEventHandler getUIEventHandler(){
        UIEventHandler uiEventHandler = mock(UIEventHandler.class);
        doAnswer(new Answer<Void>(){
            @Override
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {

                return null;
            }
        }).when(uiEventHandler).onUserExternalServicesRequested();
        return uiEventHandler;
    }

}

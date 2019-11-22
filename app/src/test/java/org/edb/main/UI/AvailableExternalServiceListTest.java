package org.edb.main.UI;


import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.edb.main.ExternalService;
import org.edb.main.tempExternalService;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;


public class AvailableExternalServiceListTest extends Application
{
    public Stage primaryStage;
    public ImprovedAvailableExternalServiceListController controller;
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage=stage;
        loadAvailableExternalServiceListFXML();
        convertToRowsTest(createServiceList());
        controller.addRowsToTableView();
    }

    //이 메소드는 특히 재사용될 가능성이 높기 때문에 다른 테스트 클래스에서도 공통적으로 사용될 수 있는 방법을 고민할 필요가 있음.
    //테스트중에 서버 연결코드를 적용하지 않는 방법. 구체적 컨트롤러 사이 중간계층 만드는 것밖에 없음.

    public void loadAvailableExternalServiceListFXML()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BootApp.class.getResource("/fxml/improvedAvailableExternalServiceList.fxml"));
        Parent parent= null;

        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        controller=(ImprovedAvailableExternalServiceListController)loader.getController();
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    private ArrayList<tempExternalService> createServiceList(){
        ArrayList<tempExternalService> data=new ArrayList<>();
        data.add(new tempExternalService(1, 2,"inflearn","www.inflearn.com"));
        return data;
    }

    private void convertToRowsTest(ArrayList<tempExternalService> data){
        controller.convertToRows(data);
        ObservableList<ExternalService> availableExternalData=controller.getAvailableExternalData();
        int size=availableExternalData.size();

        for (ExternalService single:availableExternalData) {
            System.out.println(single.toString());
        }
    }

}

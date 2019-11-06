package org.edb.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainUI extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        primaryStage.setTitle("Extensible Distraction Blocker");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainUI.class.getResource("../../../fxml/sceneMain.fxml"));
        rootLayout = (BorderPane) loader.load();

//        Parent root = FXMLLoader.load(MainUI.class.getResource("fxml/sceneMain.fxml"));
        Scene scene = new Scene(rootLayout);


        primaryStage.setScene(scene);
        primaryStage.show();


        //상위 레이아웃 초기화 함수


    }

    public void setLayout(Pane scene) {
        rootLayout.setCenter(scene);
    }


    /*
    public void initMainUI(){
       // try{
            //fxml파일에서 상위 레이아웃을 가져온다
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainUI.class.getResource("../fxml/sceneMain.fxml"));
    //        rootLayout = (SplitPane)loader.load();

            //상위 레이아웃 포함 scene 보여주기
          //  Scene scene = new Scene(rootLayout);
      //      primaryStage.setScene(scene);
        //    primaryStage.show();

     //   }catch(IOException e){
         //   e.printStackTrace();
      //  }
    }
    */



}
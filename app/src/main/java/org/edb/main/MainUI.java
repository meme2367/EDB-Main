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




    @Override
    public void start(Stage primaryStage) throws Exception {



        String token = Authorization.getToken();

        if(token == ""){
            //token이 없으면
            Parent root = FXMLLoader.load(getClass().getResource("../../../fxml/sceneMain.fxml"));

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();
        }else{

            //token이 있으면
            Parent root = FXMLLoader.load(getClass().getResource("../../../fxml/sceneMainAfterLogin.fxml"));

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();
        }


    }


}

package org.edb.main.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.edb.main.Authorization;

public class BootApp extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        String token = Authorization.getToken();

        if(token == ""){
            //token이 없으면
//            Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainUI.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/improvedMainUI.fxml"));

            Scene scene = new Scene(root);

            this.primaryStage=primaryStage;
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        }else{

//            //token이 있으면
//            Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainUIAfterLogin.fxml"));
//
//            Scene scene = new Scene(root);
//
//            primaryStage.setScene(scene);
//            primaryStage.show();
        }


    }

    public static Stage getPrimaryStage(){
        return primaryStage;
    }


}

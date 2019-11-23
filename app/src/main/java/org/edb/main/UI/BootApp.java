package org.edb.main.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.edb.main.Authorization;
import org.edb.main.ServerResponseHandler;
import org.edb.main.UIManipulator;
import org.edb.main.network.RestAPIRequester;

import java.io.IOException;

public class BootApp extends Application {

    public static Stage primaryStage;

    public static HBox rootLayout;

    public BootApp() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("EDB-Main");
        MainUIController mainUIController=initRootLayout();

        FXManipulator fxManipulator= new FXManipulator();
        fxManipulator.setMainUIController(mainUIController);
        ServerResponseHandler serverResponseHandler=new ServerResponseHandler();
        serverResponseHandler.setUiManipulator(fxManipulator);
        RestAPIRequester restAPIRequester = new RestAPIRequester();
        restAPIRequester.setMainHandler(serverResponseHandler);
    }

    public MainUIController initRootLayout() {
        try {
            String token = Authorization.getToken();

            FXMLLoader loader = new FXMLLoader();
//            Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainUI.fxml"));
            loader.setLocation(BootApp.class.getResource("/fxml/MainUI.fxml"));
            rootLayout = (HBox) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

            return loader.getController();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }




}
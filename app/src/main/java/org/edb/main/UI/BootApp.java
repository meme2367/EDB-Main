package org.edb.main.UI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.edb.main.Authorization;
import org.edb.main.ExternalService;
import org.edb.main.network.RestApiConnector;
import org.edb.main.network.get.getAvailableExternalServiceResponse;
import org.edb.main.network.get.getExternalServiceListResponse;
import org.edb.main.tempExternalService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;

public class BootApp extends Application {

    public static Stage primaryStage;

    public static BorderPane rootLayout;

    public BootApp() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("EDB-Main");
        initRootLayout();

    }

    public void initRootLayout() {
        try {
            String token = Authorization.getToken();

            FXMLLoader loader = new FXMLLoader();
//            Parent root = FXMLLoader.load(getClass().getResource("/fxml/improvedMainUI.fxml"));
            loader.setLocation(BootApp.class.getResource("/fxml/improvedMainUI.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }




}
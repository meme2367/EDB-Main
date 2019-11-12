package org.edb.main.UI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ImprovedMainUIController {
    //    @FXML
//    private HBox rootContainer;
    @FXML
    private AnchorPane userPane;
    @FXML
    private Button loginBtn;
    @FXML
    private Button registerBtn;
    @FXML
    private Label userIdLbl;
    @FXML
    private ComboBox pluginComboBox;

    public Stage primaryStage;

    public BorderPane rootLayout;

    @FXML
    public Label getExternalServiceListButton;

    @FXML
    public Label postExternalServiceListButton;


    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showLoginDialog(ActionEvent event) throws Exception {
        Stage dialog = new Stage(StageStyle.DECORATED);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(primaryStage);
        dialog.setTitle("Login");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/loginDialog.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);

        dialog.setScene(scene);
        dialog.setResizable(false);
        dialog.show();
    }

    public void showRegisterDialog(ActionEvent event) throws RuntimeException {

    }




    public void postExternalServiceListButton(){

        /*
        try {
            // 연락처 요약을 가져온다.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/availableExternalServiceList.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // 연락처 요약을 상위 레이아웃 가운데로 설정한다.
            rootLayout.setCenter(personOverview);


        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        try {
            Parent list = FXMLLoader.load(getClass().getResource("/fxml/availableExternalServiceList.fxml"));
            Scene scene = new Scene(list);
            Stage primaryStage = (Stage)postExternalServiceListButton.getScene().getWindow();//현재 윈도우 가져오기

            primaryStage.setScene(scene);

        }catch(IOException e){
            e.printStackTrace();
        }
    }



    public void getExternalServiceListButton(){

        /*
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/availableExternalServiceList.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // 상위 레이아웃 가운데로 설정한다.
            rootLayout.setCenter(personOverview);

        } catch (IOException e) {
            e.printStackTrace();
        }

         */


        try {
           //화면이 고정됨, 새로운 stage를 띄우는 것이 낫다.
            Parent list = FXMLLoader.load(getClass().getResource("/fxml/userExternalServiceList.fxml"));
            Scene scene = new Scene(list);
            Stage primaryStage = (Stage)getExternalServiceListButton.getScene().getWindow();//현재 윈도우 가져오기

            primaryStage.setScene(scene);

        }catch(IOException e){
            e.printStackTrace();
        }



    }
}


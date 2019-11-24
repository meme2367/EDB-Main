package org.edb.main.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.edb.main.UIEventHandler;

import java.io.IOException;

public class MainUIController {
    //    @FXML
    private HBox rootContainer;
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
    @FXML
    private HBox centerUI;
    @FXML
    public Label getExternalServiceListButton;

    private Stage loginDialog;
    private UIEventHandler uiEventHandler;


    @FXML
    public Label postExternalServiceListButton;

    public Stage primaryStage;


    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setUiEventHandler(UIEventHandler uiEventHandler) {
        this.uiEventHandler = uiEventHandler;
    }


    public void changeCenterUI(String filePath){
        try {
            clearCenterUI();
            Parent list = FXMLLoader.load(getClass().getResource(filePath));
            Scene tempScene=BootApp.getPrimaryStage().getScene();
            HBox hbox=(HBox)tempScene.lookup("#centerUI");
            hbox.getChildren().add(list);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void changeCenterUI(Parent parent){
        clearCenterUI();
        Scene tempScene=BootApp.getPrimaryStage().getScene();
        HBox hbox=(HBox)tempScene.lookup("#centerUI");
        hbox.getChildren().add(parent);

    }

    private void clearCenterUI(){
        centerUI.getChildren().clear();
        System.out.println("clearCenterUI\n");
    }



    public void showLoginDialog(ActionEvent event) throws Exception {
        loginDialog = new Stage(StageStyle.DECORATED);
        loginDialog.initModality(Modality.WINDOW_MODAL);
        loginDialog.initOwner(primaryStage);
        loginDialog.setTitle("Login");

        Parent parent = FXFactory.getInstance().loadLoginUI("/fxml/loginDialog.fxml");

        Scene scene = new Scene(parent);

        loginDialog.setScene(scene);
        loginDialog.setResizable(false);
        loginDialog.show();
    }

    public void showRegisterDialog(ActionEvent event) throws RuntimeException {

    }


    public void showAvailableExternalServiceList() {

        try {
            Parent parent = FXFactory.getInstance().loadAvailableExternalServiceUI("/fxml/AvailableExternalServiceList.fxml");
            changeCenterUI(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void showUserExternalServiceList(){
        try {
            Parent parent = FXFactory.getInstance().loadUserExternalServiceUI("/fxml/UserExternalServiceList.fxml");
            changeCenterUI(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeLoginDialog() {
        if(loginDialog!=null) {
            loginDialog.close();
        }
    }

    public void setUILoggedIn(String id) {
        loginBtn.setDisable(true);
        userIdLbl.setVisible(true);
        userIdLbl.setText(id);
    }
}


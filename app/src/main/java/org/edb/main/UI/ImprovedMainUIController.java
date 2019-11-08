package org.edb.main.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

import java.net.URL;
import java.util.ResourceBundle;

public class ImprovedMainUIController implements Initializable {
    @FXML
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

    private Stage primaryStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showLoginDialog(ActionEvent event) throws Exception{
        Stage dialog = new Stage(StageStyle.DECORATED);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(primaryStage);
        dialog.setTitle("Login");

        FXMLLoader loader= new FXMLLoader(getClass().getResource("/fxml/loginDialog.fxml"));
        Parent parent = loader.load();
        loader.<LoginDialogController>getController().setPrimaryStage(primaryStage);

        Scene scene = new Scene(parent);

        dialog.setScene(scene);
        dialog.setResizable(false);
        dialog.show();
    }

    public void showRegisterDialog(ActionEvent event) throws RuntimeException{

    }


}

package org.edb.main.UI;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.edb.main.Authorization;
import org.edb.main.ExternalService;
import org.edb.main.User;
import org.edb.main.network.RestApiConnector;
import org.edb.main.network.get.getExternalServiceListResponse;
import org.edb.main.tempExternalService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ImprovedUserExternalServiceListController implements Initializable {

    @FXML
    private TableView<ExternalService> userExternalServiceListView;
    @FXML
    private TableColumn<ExternalService, String> userExternalServiceTitle;
    @FXML
    private TableColumn<ExternalService, String> userExternalServiceUrl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //초기화

        loadUserExternelServiceList();


    }

    private ObservableList<ExternalService> userExternalData = FXCollections.observableArrayList();




    public ObservableList<ExternalService> getUserExternalData() {
        return userExternalData;

    }

//    private void fillTable(){
//        Platform.runLater(()->{
//            Scene tempScene = BootApp.getPrimaryStage().getScene();
//            //nullpointer exception
//            Button loginBtn = (Button) tempScene.lookup("#loginBtn");
//            Label userIdLbl = (Label)tempScene.lookup("#userIdLbl");
//            loginBtn.setDisable(true);
//            userIdLbl.setVisible(true);
//            userIdLbl.setText(tempId);
//        });
//    }

    public void loadUserExternelServiceList() {
        String token;
        try {
            token = User.getUser().getToken();
        }
        catch(RuntimeException runtimeException){
            token="dummy";
        }

        System.out.print("\n외부서비스목록usertokentest\n");
        System.out.print(token);
        System.out.print("\n");

        Call<getExternalServiceListResponse> getExternalServiceListResponseCall =
                RestApiConnector.getExternalServiceNetworkService().getExternalServiceListAPI(token);

        getExternalServiceListResponseCall.enqueue(new Callback<getExternalServiceListResponse>() {

            @Override
            public void onResponse(Call<getExternalServiceListResponse> call, Response<getExternalServiceListResponse> response) {
                Platform.runLater(()->{
                    System.out.println("in runLater\n");
                    if (response.isSuccessful()) {
                        int status = response.body().getStatus();
                        if (status == 200) {
                            showExternalServiceTableList(response.body().getData());

                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<getExternalServiceListResponse> call, Throwable throwable) {
                System.out.print("error\n");
                System.out.println(throwable);
            }




            private void showExternalServiceTableList(ArrayList<tempExternalService> data) {
                for (tempExternalService value : data) {
                    userExternalData.add(new ExternalService(value.getName(), value.getUrl()));
                }
                userExternalServiceTitle.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
                userExternalServiceUrl.setCellValueFactory(cellData -> cellData.getValue().urlProperty());
                userExternalServiceListView.setItems(userExternalData);

            }
        });


    }

}

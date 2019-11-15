package org.edb.main.UI;

import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import org.edb.main.*;
import org.edb.main.network.RestApiConnector;
import org.edb.main.network.get.getExternalServiceDetailListResponse;
import org.edb.main.network.get.getExternalServiceListResponse;
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

    private ObservableList<ExternalService> userExternalData = FXCollections.observableArrayList();

    @FXML
    private TableView<ExternalServiceDetail> externalServiceDetailView;
    @FXML
    private TableColumn<ExternalServiceDetail, String> externalServiceDetailTitle;
    @FXML
    private TableColumn<ExternalServiceDetail, String> externalServiceDetail_IF_ARCHIEVE;

    @FXML
    private TableColumn<ExternalServiceDetail, Boolean> externalServiceDetailChkBox;


    private ObservableList<ExternalServiceDetail> externalDetailData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //초기화
        loadUserExternelServiceList();

        userExternalServiceListView.getSelectionModel().selectedItemProperty()
                .addListener((observable,oldValue,newValue)->showDetails(newValue));


    }

    public TableView<ExternalService> getUserExternalServiceListView() {
        return userExternalServiceListView;
    }

    public TableColumn<ExternalService, String> getUserExternalServiceTitle() {
        return userExternalServiceTitle;
    }

    public TableColumn<ExternalService, String> getUserExternalServiceUrl() {
        return userExternalServiceUrl;
    }

    public ObservableList<ExternalService> getUserExternalData() {
        return userExternalData;

    }





    public void loadUserExternelServiceList() {
        String token;


        System.out.print("\nuser\n");
        System.out.print(User.getUser().getToken());
        System.out.print(User.getUser().getId());

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

            private ImprovedUserExternalServiceListController controller;

            private Callback<getExternalServiceListResponse> init(ImprovedUserExternalServiceListController controller){
                this.controller=controller;
                return this;
            }

            @Override
            public void onResponse(Call<getExternalServiceListResponse> call, Response<getExternalServiceListResponse> response) {
                try {
                    Platform.runLater(() -> {
                        System.out.println("in runLater\n");
                        if (response.isSuccessful()) {
                            int status = response.body().getStatus();
                            if (status == 200) {

                                showExternalServiceTableList(response.body().getData());

                            }
                        }
                    });
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<getExternalServiceListResponse> call, Throwable throwable) {
                System.out.print("error\n");
                System.out.println(throwable);
            }




            private void showExternalServiceTableList(ArrayList<tempExternalService> data) {

                ObservableList<ExternalService> userExternalData = controller.getUserExternalData();

                for (tempExternalService value : data) {
                    userExternalData.add(new ExternalService(value.getName(), value.getUrl(),value.getExternal_service_idx()));
                }


                if (controller == null) {
                    System.out.println("null controller\n");
                }
                if (controller.getUserExternalData() == null) {
                    System.out.println("null ExternalData\n");
                }

                controller.getUserExternalServiceTitle().setCellValueFactory(cellData -> cellData.getValue().nameProperty());
//                userExternalServiceTitle.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
                controller.getUserExternalServiceUrl().setCellValueFactory(cellData -> cellData.getValue().urlProperty());
//                userExternalServiceUrl.setCellValueFactory(cellData -> cellData.getValue().urlProperty());
                controller.getUserExternalServiceListView().setItems(userExternalData);
//                userExternalServiceListView.setItems(userExternalData);
            }



        }.init(this));


    }

    public void showDetails(ExternalService clickValue){
        System.out.print("\n특정 IDX 클릭\n");
        System.out.print(clickValue.getIdx());

        loadDetailList(clickValue.getIdx());
    }

    public void loadDetailList(int idx) {
        String token;
        System.out.print("\nuser\n");
        System.out.print(User.getUser().getToken());
        System.out.print(User.getUser().getId());

        try {
            token = User.getUser().getToken();
        }
        catch(RuntimeException runtimeException){
            token="dummy";
        }

        Call<getExternalServiceDetailListResponse> getExternalServiceDetailListResponseCall =
                RestApiConnector.getExternalServiceNetworkService().getExternalServiceDetailListAPI(idx,token);

        getExternalServiceDetailListResponseCall.enqueue(new Callback<getExternalServiceDetailListResponse>() {

            private ImprovedUserExternalServiceListController controller;

            private Callback<getExternalServiceDetailListResponse> init(ImprovedUserExternalServiceListController controller) {
                this.controller = controller;
                return this;
            }

            @Override
            public void onResponse(Call<getExternalServiceDetailListResponse> call, Response<getExternalServiceDetailListResponse> response) {
                try {
                    Platform.runLater(() -> {
                        System.out.println("in runLater\n");
                        if (response.isSuccessful()) {
                            int status = response.body().getStatus();
                            if (status == 200) {
                                System.out.print("detail result test\n");
                                System.out.print(response.body().getData());

                                showExternalServiceDetailTableList(response.body().getData());


                            }
                        }





                            });


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<getExternalServiceDetailListResponse> call, Throwable throwable) {
                System.out.print("error\n");
                System.out.println(throwable);
            }

        });
    }


    private void showExternalServiceDetailTableList(ArrayList<tempExternalServiceDetail> data) {

        externalServiceDetailView.setVisible(true);
        externalServiceDetailTitle.setVisible(true);
        externalServiceDetail_IF_ARCHIEVE.setVisible(true);


        externalDetailData.clear();

        for (tempExternalServiceDetail value : data) {
            externalDetailData.add(new ExternalServiceDetail(value.getExternal_service_detail_idx(), value.getName(), value.getIf_achieve()));
        }

        externalServiceDetailTitle.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        externalServiceDetail_IF_ARCHIEVE.setCellValueFactory(cellData -> cellData.getValue().ifachieveProperty());

        externalServiceDetailChkBox.setCellValueFactory(new PropertyValueFactory<ExternalServiceDetail, Boolean>("check"));

        externalServiceDetailChkBox.setCellFactory(column -> new TableCell<ExternalServiceDetail, Boolean>() {
            @Override
            protected void updateItem(Boolean check, boolean empty) {
                super.updateItem(check, empty);
                if (check == null || empty) {
                    setGraphic(null);
                } else {
                    CheckBox box = new CheckBox();
                    BooleanProperty checked = (BooleanProperty) column.getCellObservableValue(getIndex());
                    ExternalServiceDetail cn = (ExternalServiceDetail) column.getTableView().getItems().get(getIndex());
                    if (checked.get()) {
                        System.out.println(cn.idxProperty() + " is Checked!");
                    } else {
                        System.out.println(cn.idxProperty() + " is Unchecked!");
                    }
                    box.setSelected(checked.get());
                    box.selectedProperty().bindBidirectional(checked);
                    setGraphic(box);
                }
            }
        });


        //externalServiceDetailView.getColumns().add(externalServiceDetailChkBox);
        //externalServiceDetailView.setEditable(true);
        externalServiceDetailView.setItems(externalDetailData);

    }



}

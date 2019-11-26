package org.edb.main.UI;

import javafx.application.Platform;

import javafx.beans.property.BooleanProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import org.edb.main.*;
import org.edb.main.network.RestApiConnector;
import org.edb.main.network.post.postExternalServiceResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserExternalServiceListController implements Initializable {

    @FXML
    private TableView<FXExternalService> userExternalServiceListView;
    @FXML
    private TableColumn<FXExternalService, String> userExternalServiceTitle;
    @FXML
    private TableColumn<FXExternalService, String> userExternalServiceUrl;

    private ObservableList<FXExternalService> userExternalData = FXCollections.observableArrayList();

    @FXML
    private TableView<FXExternalServiceDetail> externalServiceDetailView;
    @FXML
    private TableColumn<FXExternalServiceDetail, String> externalServiceDetailTitle;
    @FXML
    private TableColumn<FXExternalServiceDetail, String> externalServiceDetail_IF_ARCHIEVE;

    @FXML
    private TableColumn<FXExternalServiceDetail, Boolean> externalServiceDetailChkBox;

    @FXML
    private Button postExternalDetailBtn;

    private ObservableList<FXExternalServiceDetail> externalDetailData = FXCollections.observableArrayList();

    public ArrayList<Integer[]> list = new ArrayList<Integer[]>();

    private UIEventHandler uiEventHandler;

    public void setUiEventHandler(UIEventHandler uiEventHandler) {
        this.uiEventHandler=uiEventHandler;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //초기화
        userExternalServiceTitle.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        userExternalServiceUrl.setCellValueFactory(cellData -> cellData.getValue().urlProperty());

        loadUserExternalServiceList();

        //외부서비스목록테이블 선택시 목표달성테이블 띄우기
        userExternalServiceListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showDetails(newValue));

        externalServiceDetailTitle.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        externalServiceDetail_IF_ARCHIEVE.setCellValueFactory(cellData -> cellData.getValue().ifachieveProperty());

        externalServiceDetailChkBox.setCellValueFactory(new PropertyValueFactory<FXExternalServiceDetail, Boolean>("check"));

    }



    public void loadUserExternalServiceList() {
        uiEventHandler.onUserExternalServiceListLoaded();
    }

    public void handleUserExternalServiceResponse(ArrayList<ExternalService> data) {

        convertUserExternalServiceToRows(data);
        addUserExternalServiceRowsToTableView();
    }

    public void convertUserExternalServiceToRows(ArrayList<ExternalService> data) {

        for (ExternalService value : data) {
            userExternalData.add(new FXExternalService(value.getName(), value.getUrl(),value.getExternal_service_idx()));
        }
    }

    public void addUserExternalServiceRowsToTableView(){
        userExternalServiceListView.setItems(userExternalData);
    }


    public void showDetails(FXExternalService clickValue) {

        System.out.print("\n특정 IDX 클릭\n");
        System.out.print(clickValue.getIdx());
        //선택한 외부서비스의 external_idx
        loadDetailList(clickValue.getIdx());
    }

    public void loadDetailList(int externalIdx) {
        uiEventHandler.onExternalServiceDetailRequested(externalIdx);
    }


    public void showExternalServiceDetailTableList(int externalIdx, ArrayList<ExternalServiceDetail> data) {

        externalServiceDetailView.setVisible(true);
        externalServiceDetailTitle.setVisible(true);
        externalServiceDetail_IF_ARCHIEVE.setVisible(true);
        postExternalDetailBtn.setVisible(true);

        externalDetailData.clear();

        convertExternalServiceDetailToRows(data);

        //tablecolumn에 담길 tablecell을 만드는 cellFactory의 구현
        externalServiceDetailChkBox.setCellFactory(column -> new TableCell<FXExternalServiceDetail, Boolean>() {
            @Override
            protected void updateItem(Boolean check, boolean empty) {
                super.updateItem(check, empty);
                //check : 새롭게 입력된 값?
                //empty : 연결된 데이터가 있는지 여부?
                if (check == null || empty) {
                    setGraphic(null);
                } else {
                    //체크박스생성
                    //체크박스 클릭시 list 2차원 배열에 externalIdx 와 externalDetailIdx 를 저장함
                    // (이후에 postExternalDetailBtn 버튼 클릭 시에 저장된 list를 이용해 목표 달성 갱신)
                    CheckBox box = new CheckBox();
                    BooleanProperty checked = (BooleanProperty) column.getCellObservableValue(getIndex());

                    FXExternalServiceDetail cn = (FXExternalServiceDetail) column.getTableView().getItems().get(getIndex());
                    if (checked.get()) {
//                        System.out.println(cn.idxProperty() + " is Checked!");

                        list.add(new Integer[]{externalIdx, cn.idxProperty().get()});
                        //externalIdx는 다른 테이블에 정의되어 있기 때문에 row를 받는 방법은 불가능
                        //list에도 접근할 방법을 명시해줄 필요가 있음.
                        //tableView가 가지고 있도록?


                    } else {
//                        System.out.println(cn.idxProperty() + " is Unchecked!");
                        // 선택해제된것이라면 remove의 구현도 필요?
                    }
                    box.setSelected(checked.get());
                    box.selectedProperty().bindBidirectional(checked);
                    setGraphic(box);
                }
            }
        });
        //externalIdx를 받아올수 있는 다른방법을 고려한뒤 initialize 메소드로 이동 필요해보임..


        //externalServiceDetailView.getColumns().add(externalServiceDetailChkBox);
        //externalServiceDetailView.setEditable(true);
        addExternalServiceDetailToTable();
    }

    private void convertExternalServiceDetailToRows(ArrayList<ExternalServiceDetail> data) {
        for (ExternalServiceDetail value : data) {
            externalDetailData.add(new FXExternalServiceDetail(value.getExternal_service_detail_idx(), value.getName(), value.getIf_achieve()));
        }
    }

    private void addExternalServiceDetailToTable() {
        externalServiceDetailView.setItems(externalDetailData);
    }

    public void postExternalDetailRequest() {

        String token;

        System.out.print("\npostTest\n");
        System.out.print(User.getUser().getToken());

        try {
            token = User.getUser().getToken();
        } catch (RuntimeException runtimeException) {
            token = "dummy";
        }
//체크박스 클릭시 list 2차원 배열에 externalIdx 와 externalDetailIdx 를 저장
//(이후에 postExternalDetailBtn 버튼 클릭 시에 저장된 list를 이용해 목표 달성 갱신)

        //    @PUT("external/{externalIdx}/{externalDetailIdx}")
        //요청값: externalIdx, externalDetailIdx, token
        //응답값: statuscode, message, success여부

        for(int i = 0;i< list.size();i++){

            Call<postExternalServiceResponse> postExternalServiceDetailResponseCall =
                    RestApiConnector.getExternalServiceNetworkService().postExternalServiceListAPI(list.get(i)[0], list.get(i)[1], token);

            postExternalServiceDetailResponseCall.enqueue(new Callback<postExternalServiceResponse>() {

                private UserExternalServiceListController controller;

                private Callback<postExternalServiceResponse> init(UserExternalServiceListController controller) {
                    this.controller = controller;
                    return this;
                }

                @Override
                public void onResponse(Call<postExternalServiceResponse> call, Response<postExternalServiceResponse> response) {
                    try {
                        Platform.runLater(() -> {
                            System.out.println("in runLater\n");
                            if (response.isSuccessful()) {
                                int status = response.body().getStatus();
                                if (status == 200) {
                                    System.out.print("detail result test\n");
                                    System.out.print(response.body().getMessage());
                                }
                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<postExternalServiceResponse> call, Throwable throwable) {
                    System.out.print("error\n");
                    System.out.println(throwable);
                }

            });


        }
    }

}
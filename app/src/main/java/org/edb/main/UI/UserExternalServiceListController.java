package org.edb.main.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;

import org.edb.main.*;

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

    private int currentClickedExternalIdx;

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

//        loadUserExternalServiceList();
//         fxml을 로드한 후, 수동으로 위 메소드를 호출하는것이 더 자연스럽고 테스트상 편의성을 가진다.

        //외부서비스목록테이블 선택시 목표달성테이블 띄우기
        userExternalServiceListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showDetails(newValue));

        externalServiceDetailTitle.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        externalServiceDetail_IF_ARCHIEVE.setCellValueFactory(cellData -> cellData.getValue().ifachieveProperty());

    }

    public void loadUserExternalServiceList() {
        uiEventHandler.onUserExternalServicesRequested();
    }

    public void handleUserExternalServiceResponse(Iterable<ExternalService> data) {

        convertUserExternalServiceToRows(data);
        addUserExternalServiceRowsToTableView();
    }

    public void convertUserExternalServiceToRows(Iterable<ExternalService> data) {

        for (ExternalService value : data) {
            userExternalData.add(new FXExternalService(value.getName(), value.getUrl(),value.getExternal_service_idx()));
        }
    }

    public void addUserExternalServiceRowsToTableView(){
        userExternalServiceListView.setItems(userExternalData);
    }


    public void showDetails(FXExternalService clickValue) {
        currentClickedExternalIdx=clickValue.getIdx();

        System.out.print("\n특정 IDX 클릭\n");
        System.out.print(currentClickedExternalIdx);
        //선택한 외부서비스의 external_idx
        loadDetailList(currentClickedExternalIdx);
    }

    public void loadDetailList(int externalIdx) {
        uiEventHandler.onExternalServiceDetailRequested(externalIdx);
    }


    public void showExternalServiceDetailTableList(int externalIdx, Iterable<ExternalServiceDetail> data) {

        externalServiceDetailView.setVisible(true);
        externalServiceDetailTitle.setVisible(true);
        externalServiceDetail_IF_ARCHIEVE.setVisible(true);
        postExternalDetailBtn.setVisible(true);

        externalDetailData.clear();

        convertExternalServiceDetailToRows(data);

        addExternalServiceDetailToTable();
    }

    private void convertExternalServiceDetailToRows(Iterable<ExternalServiceDetail> data) {
        for (ExternalServiceDetail value : data) {
            externalDetailData.add(new FXExternalServiceDetail(value.getExternal_service_detail_idx(), value.getName(), value.getIf_achieve()));
        }
    }

    private void addExternalServiceDetailToTable() {
        externalServiceDetailView.setItems(externalDetailData);
    }

    public void postExternalDetailRequest() {
        uiEventHandler.onExternalServiceDetailRefreshRequested(currentClickedExternalIdx);
    }

}
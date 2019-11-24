package org.edb.main.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.edb.main.ExternalService;
import org.edb.main.UIEventHandler;

import org.edb.main.tempExternalService;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AvailableExternalServiceListController implements Initializable {
    @FXML
    private TableView<ExternalService> availableExternalServiceListView;
    @FXML
    private TableColumn<ExternalService, String> availableExternalServiceTitle;
    @FXML
    private TableColumn<ExternalService, String> availableExternalServiceUrl;

    private ObservableList<ExternalService> availableExternalData = FXCollections.observableArrayList();

    private UIEventHandler uiEventHandler;

    public void setUiEventHandler(UIEventHandler uiEventHandler) {
        this.uiEventHandler = uiEventHandler;
    }

    public ObservableList<ExternalService> getAvailableExternalData() {
        return availableExternalData;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //초기화
        availableExternalServiceTitle.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        availableExternalServiceUrl.setCellValueFactory(cellData -> cellData.getValue().urlProperty());

        loadAvailableExternalServiceList();
    }


    public void loadAvailableExternalServiceList() {
        uiEventHandler.onAvailableExternalServiceListLoaded();
    }

    public void handleExternalServiceResponse(ArrayList<tempExternalService> data){
        convertToRows(data);
        addRowsToTableView();
    }

    public void convertToRows(ArrayList<tempExternalService> data) {

        for (tempExternalService value : data) {
            availableExternalData.add(new ExternalService(value.getName(), value.getUrl(),value.getExternal_service_idx()));
        }
    }

    public void addRowsToTableView(){
        availableExternalServiceListView.setItems(availableExternalData);
    }


}

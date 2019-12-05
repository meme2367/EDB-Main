package org.edb.main.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.edb.main.*;
import org.edb.main.util.DateFormatter;

import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import static org.edb.main.BootApp.primaryStage;

public class PluginConfigUIController implements Initializable {

    @FXML
    private VBox configArea;
    @FXML
    private AnchorPane applyArea;
    @FXML
    private TextField targetWebsiteField;
    @FXML
    private TextField startHourField;
    @FXML
    private TextField startMinField;
    @FXML
    private TextField endHourField;
    @FXML
    private TextField endMinField;
    @FXML
    private TableView<FXTargetProgram> targetProgramTableView;
    @FXML
    private TableView<FXTargetWebsite> targetWebsiteTableView;
    @FXML
    private TableColumn<FXTargetProgram, String> targetProgramNameColumn;
    @FXML
    private TableColumn<FXTargetProgram, String> targetProgramPathColumn;
    @FXML
    private TableColumn<FXTargetWebsite, String> targetWebsiteURLColumn;
    @FXML
    private Button scheduleBtn;
    @FXML
    private Button modifyScheduleBtn;
    @FXML
    private AnchorPane scheduleArea;
    @FXML
    private Label scheduledTimeLbl;

    private EDBPlugin plugin;
    private EDBPluginManager pluginManager;
    private UIEventHandler uiEventHandler;
    private ObservableList<FXTargetProgram> fxTargetProgramObservableList = FXCollections.observableArrayList();
    private ObservableList<FXTargetWebsite> fxTargetWebsiteObservableList = FXCollections.observableArrayList();

    public void setPluginManager(EDBPluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    public void setUIEventHandler(UIEventHandler uiEventHandler) {
        this.uiEventHandler=uiEventHandler;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        targetProgramNameColumn.setCellValueFactory(cellData->cellData.getValue().targetNameProperty());
        targetProgramPathColumn.setCellValueFactory(cellData->cellData.getValue().targetPathProperty());
        targetWebsiteURLColumn.setCellValueFactory(cellData->cellData.getValue().targetURLProperty());


        targetProgramTableView.setItems(fxTargetProgramObservableList);
        targetWebsiteTableView.setItems(fxTargetWebsiteObservableList);
    }

    //TODO pluginConfigUI 화면불러오기, 구체적인 pluginConfigUI가 정의되지 않았기에 아직 불가능.
    public void fillPluginConfigUIContents(){
//        시간, 잠금대상, 잠금웹사이트 config를 받아와서, 이 ui에 뿌리고
//        plugin이 가진 모든 logic들에 대해서.
//        EDBLogic에서 logicConfigUI불러와서 configArea에 달아주고
//        logic에서 일치하는 Config들을 받아와서 logicConfigUI에 뿌린다.
    }

    public void addTargetProgram(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files","*.*"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        FXTargetProgram addedTargetProgram = new FXTargetProgram(selectedFile.getName(),selectedFile.getAbsolutePath());

        fxTargetProgramObservableList.add(addedTargetProgram);

        plugin.addTargetProgram(addedTargetProgram.convertToTargetProgram());
    }

    public void deleteTargetProgram(){

        FXTargetProgram selectedItem = targetProgramTableView.getSelectionModel().getSelectedItem();
        targetProgramTableView.getItems().remove(selectedItem);
        plugin.removeTargetProgram(selectedItem.convertToTargetProgram());

    }

    public void addTargetWebsite(){
        FXTargetWebsite addedTargetWebsite = new FXTargetWebsite(targetWebsiteField.getText());
        fxTargetWebsiteObservableList.add(addedTargetWebsite);
        plugin.addTargetWebsite(addedTargetWebsite.convertToTargetWebsite());
    }

    public void deleteTargetWebsite(){

        FXTargetWebsite selectedItem = targetWebsiteTableView.getSelectionModel().getSelectedItem();
        targetWebsiteTableView.getItems().remove(selectedItem);
        plugin.removeTargetWebsite(selectedItem.convertToTargetWebsite());
    }

    public void applyToServer(){
        uiEventHandler.onPostUserPlugin(plugin.getPluginIdx());
    }

    public void schedulePlugin(){

//        시작시간 = 시스템 시간과 textField 긁어와서 Date타입 만들기
        Date startDate = dateFromInt(Integer.parseInt(startHourField.getText()), Integer.parseInt(startMinField.getText()));
//        종료시간 = 동일
        Date endDate = dateFromInt(Integer.parseInt(endHourField.getText()),Integer.parseInt(endMinField.getText()));

//        Date타입을 plugin에 등록한다.
        plugin.applySchedule(startDate, endDate);

//        텍스트필드를 라벨로 바꾼다
//        취소버튼 보이게 하기
        scheduleArea.setVisible(false);
        scheduleBtn.setVisible(false);
        modifyScheduleBtn.setVisible(true);
        scheduledTimeLbl.setVisible(true);

        String scheduledTime = startDate.toString()+ "부터 " + endDate.toString()+"까지";
        scheduledTimeLbl.setText(scheduledTime);
    }

    private Date dateFromInt(int hour, int minute){
        // 시스템 시간 구하기
        Date time = new Date();

        //calendar에 시스템시간 지정하기
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time.getTime());

        //시간 더하기
        cal.add(Calendar.HOUR, hour);
        cal.add(Calendar.MINUTE, minute);

        //Date타입으로 변환하기
        //반환하기
        Date formattedTime = cal.getTime();
        try {
            formattedTime = DateFormatter.getSimpleFormattedDateFromDate(cal.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedTime;
    }

    public void modifySchedule(){
        scheduleArea.setVisible(true);
        scheduleBtn.setVisible(true);
        modifyScheduleBtn.setVisible(false);
        scheduledTimeLbl.setVisible(false);

    }

    public void onLifeCycleChanged(){
        if(plugin.isRunning()==false){
            modifySchedule();
        }
    }

    public void setPlugin(EDBPlugin plugin) {
        this.plugin = plugin;
    }

    public int getPluginIdx(){
        return plugin.getPluginIdx();
    }

}

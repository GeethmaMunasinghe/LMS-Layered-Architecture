package com.pcl.lms.controller;

import com.pcl.lms.DB.DbConnection;
import com.pcl.lms.bo.BoFactory;
import com.pcl.lms.bo.custom.IntakeBo;
import com.pcl.lms.dto.request.RequestIntakeDto;
import com.pcl.lms.dto.response.ResponseIntakeDto;
import com.pcl.lms.model.Intake;
import com.pcl.lms.tm.IntakeTm;
import com.pcl.lms.util.BoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class IntakeManagementFormController {

    public AnchorPane context;
    public TextField txtId;
    public Button btnSave;
    public DatePicker dteStart;
    public TextField txtName;
    public ComboBox<String> cbxProgram;
    public TextField txtSearch;
    public TableView<IntakeTm> tblIntake;
    public TableColumn<IntakeTm,String> colID;
    public TableColumn<IntakeTm,String> colName;
    public TableColumn<IntakeTm,Date> colDate;
    public TableColumn<IntakeTm,String> colProgram;
    public TableColumn<IntakeTm,Button> colOption;
    private String searchText="";

    IntakeBo intakeBo= BoFactory.getInstance().getBo(BoType.INTAKE);

    public void initialize(){
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        setIntakeID();
        setProgramsData();
        loadTableData(searchText);

        tblIntake.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            if (newValue!=null){
                setDataToForm((IntakeTm)newValue);
            }
        });
    }

    private void setDataToForm(IntakeTm tm) {
        txtId.setText(tm.getId());
        txtName.setText(tm.getName());
        dteStart.setValue(LocalDate.parse(tm.getDate().toString()));
        cbxProgram.setValue(tm.getProgram());
        btnSave.setText("Update");
    }

    private void loadTableData(String searchText) {
        try {
            List<ResponseIntakeDto> responseIntakeDtos=intakeBo.fetchIntakeByName(searchText);
            ObservableList<IntakeTm> intakeTmList=FXCollections.observableArrayList();
            for (ResponseIntakeDto responseIntakeDto:responseIntakeDtos){
                Button btn=new Button("Delete");
                intakeTmList.add(new IntakeTm(
                        responseIntakeDto.getId(),
                        responseIntakeDto.getDate(),
                        responseIntakeDto.getName(),
                        responseIntakeDto.getProgram(),
                        btn
                ));
                btn.setOnAction((event->{
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are you sure?",ButtonType.YES,ButtonType.NO);
                    alert.showAndWait();
                    if (alert.getResult()==ButtonType.YES){
                        try {
                            intakeBo.deleteIntake(responseIntakeDto.getId());
                            loadTableData(searchText);
                            setIntakeID();
                            new Alert(Alert.AlertType.INFORMATION,"Success").show();
                        } catch (SQLException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }));
            }
            tblIntake.setItems(intakeTmList);
        }catch (SQLException|ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private boolean deleteIntake(Intake intake) throws SQLException, ClassNotFoundException {
        Connection connection=DbConnection.getInstance().getConnection();
        PreparedStatement ps=connection.prepareStatement("DELETE FROM intake WHERE id=?");
        ps.setString(1,intake.getId().trim());
        return ps.executeUpdate()>0;
    }

    private ObservableList<Intake> fetchIntakeData(String searchText) throws SQLException, ClassNotFoundException {
        ObservableList<Intake> intakeObList=FXCollections.observableArrayList();
        Connection connection=DbConnection.getInstance().getConnection();
        PreparedStatement ps=connection.prepareStatement(
                "SELECT i.id,i.name,i.date,p.id,p.name FROM intake i JOIN program p ON p.id=i.program_id WHERE i.name LIKE?");
        ps.setString(1,"%"+searchText+"%");
        ResultSet set=ps.executeQuery();
        while (set.next()){
            intakeObList.add(new Intake(
                  set.getString(1),
                  set.getDate(3),
                  set.getString(2),
                  set.getString(4)+"-"+set.getString(5)
            ));
        }
        return intakeObList;
    }

    private void setProgramsData() {
        try {
            ObservableList<String> programsObList= intakeBo.getProgramListForCombo();
            cbxProgram.setItems(programsObList);
        }catch (SQLException|ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    private void setIntakeID() {
        try {
            txtId.setText(intakeBo.getLastIntakeId());

        }catch (SQLException|ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    public void newIntakeOnAction(ActionEvent actionEvent) {
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DashboardForm");
    }

    public void saveOnAction(ActionEvent actionEvent) {
        String cmbValue=cbxProgram.getValue();
        try {
            if (btnSave.getText().equals("Save")){
                //save
                boolean isSaved=intakeBo.saveIntake(new RequestIntakeDto(
                        txtId.getText(),
                        txtName.getText(),
                        Date.from(dteStart.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        cmbValue
                ));
                if (isSaved){
                    new Alert(Alert.AlertType.INFORMATION,"Saved").show();
                    setIntakeID();
                    setProgramsData();
                    clearFields();
                    loadTableData(searchText);
                }

            }else {
                boolean isUpdated=intakeBo.updateIntake(new RequestIntakeDto(
                        txtId.getText(),
                        txtName.getText(),
                        Date.from(dteStart.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        cbxProgram.getValue()
                ));
                if (isUpdated){
                    new Alert(Alert.AlertType.INFORMATION,"Update").show();
                    clearFields();
                    loadTableData(searchText);
                    setIntakeID();
                    btnSave.setText("Save");
                }
            }
        }catch (SQLException|ClassNotFoundException e){
            e.printStackTrace();
        }

    }



    private void clearFields() {
        txtName.clear();
        dteStart.setValue(null);
    }

    private void setUI(String location) throws IOException {
        Stage stage=(Stage) context.getScene().getWindow();
        stage.setScene(new Scene((FXMLLoader.load(getClass().getResource("/com/pcl/lms/"+location+".fxml")))));
    }
}

package com.pcl.lms.controller;

import com.pcl.lms.DB.Database;
import com.pcl.lms.DB.DbConnection;
import com.pcl.lms.bo.BoFactory;
import com.pcl.lms.bo.custom.ProgramBo;
import com.pcl.lms.bo.custom.RegisterBo;
import com.pcl.lms.bo.custom.StudentBo;
import com.pcl.lms.model.Enroll;
import com.pcl.lms.model.Programme;
import com.pcl.lms.model.Student;
import com.pcl.lms.util.BoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RegistrationFormController {

    public AnchorPane context;
    public TextField txtId;
    public Button btnSave;
    public ComboBox<String> cmbProgram;
    public RadioButton rbtnPaid;
    public ToggleGroup rbtnPayment;
    public ComboBox<String> cmbStudent;
    public RadioButton rbtnUnpaid;
    public TextField txtSearch;
    String searchText="";
    RegisterBo registerBo= BoFactory.getInstance().getBo(BoType.REGISTRATION);

    public void initialize(){
        setStudentId();
        setStudentData(searchText);
        txtSearch.textProperty().addListener((observable,oldValue,newValue)->{
            if (newValue!=null){
                this.searchText=newValue;
                setStudentData(searchText);
                cmbStudent.show();
            }
        });
        cmbStudent.valueProperty().addListener((observable,oldValue,newValue)->{
            setStudentId();
        });
        setProgramData();
    }

    private void setStudentId() {
        if (cmbStudent.getValue()==null){
            txtId.setText("Select student");
        }else {
            String studentComboValue=cmbStudent.getValue();
            String[] splittedComboValue=studentComboValue.split("-");
            txtId.setText(splittedComboValue[0]+"-"+splittedComboValue[1]);
        }
    }

    private void setStudentData(String searchText) {
        try {
            ObservableList<String> studentObList=FXCollections.observableArrayList();
            List<String> studentForComboByName =registerBo.findStudentForComboByName(searchText);
            for (String student:studentForComboByName){
                studentObList.add(student);
            }
            cmbStudent.setItems(studentObList);

        }catch (SQLException|ClassNotFoundException e){
            e.printStackTrace();
        }

    }


    private void setProgramData() {
        try {
            ObservableList<String> programObList= FXCollections.observableArrayList();
            List<String> programForCombo=registerBo.findProgramForCombo();
            for (String program:programForCombo){
                programObList.add(program);
            }
            cmbProgram.setItems(programObList);
        }catch (SQLException|ClassNotFoundException e){
            e.printStackTrace();
        }

    }


    public void newRegistrationOnAction(ActionEvent actionEvent) {
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DashboardForm");
    }

    public void saveOnAction(ActionEvent actionEvent) {
        try {
            boolean isSaved=saveEnrollment(new Enroll(
                    cmbStudent.getValue(),
                    cmbProgram.getValue(),
                    rbtnPaid.isSelected()
            ));
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Success").show();
            }
        }catch (SQLException|ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    private boolean saveEnrollment(Enroll enroll) throws SQLException, ClassNotFoundException {
        Connection connection=DbConnection.getInstance().getConnection();
        PreparedStatement ps=connection.prepareStatement("INSERT INTO enroll VALUES (?,?,?)");
        ps.setString(1,spliteID(enroll.getProgram()));
        ps.setString(2,spliteID(enroll.getStudent()));
        ps.setBoolean(3,enroll.isPaid());

        return ps.executeUpdate()>0;
    }

    private String spliteID(String program) {
        String[] split=program.split("-");
        return split[0]+"-"+split[1];
    }

    private void setUI(String location) throws IOException {
        Stage stage=(Stage) context.getScene().getWindow();
        stage.setScene(new Scene((FXMLLoader.load(getClass().getResource("/com/pcl/lms/"+location+".fxml")))));
    }
}

package com.pcl.lms.controller;

import com.pcl.lms.DB.Database;
import com.pcl.lms.DB.DbConnection;
import com.pcl.lms.bo.BoFactory;
import com.pcl.lms.bo.custom.UserBo;
import com.pcl.lms.dto.request.RequestUserDto;
import com.pcl.lms.env.StaticResource;
import com.pcl.lms.model.User;
import com.pcl.lms.util.BoType;
import com.pcl.lms.util.security.PasswordManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpFormController {
    public AnchorPane context;
    public Label lblCompany;
    public Label lblVersion;
    public TextField txtFullName;
    public TextField txtAge;
    public TextField txtEmail;
    public PasswordField txtPassword;
    UserBo user= BoFactory.getInstance().getBo(BoType.USER);
    public void initialize(){
        setStaticData();
    }

    private void setStaticData() {
        lblCompany.setText(StaticResource.getCompany());
        lblVersion.setText(StaticResource.getVersion());
    }

    public void alreadyHaveAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUI("LoginForm");
    }

    public void signupOnAction(ActionEvent actionEvent) throws IOException {
        String email=txtEmail.getText();
        String fullname=txtFullName.getText();
        int age= Integer.parseInt(txtAge.getText());
        String password=txtPassword.getText();

        try {
            boolean isSaved=user.registerUser(new RequestUserDto(
                    email,
                    fullname,
                    age,
                    password
            ));
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Account Created").show();
                setUI("LoginForm");
            }else {
                new Alert(Alert.AlertType.ERROR,"Registration failed...").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void setUI(String location) throws IOException {
        Stage stage=(Stage) context.getScene().getWindow();
        stage.setScene(new Scene((FXMLLoader.load(getClass().getResource("/com/pcl/lms/"+location+".fxml")))));
    }
}

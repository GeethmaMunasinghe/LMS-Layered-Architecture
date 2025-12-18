package com.pcl.lms.bo.custom;

import com.pcl.lms.bo.SuperBo;
import com.pcl.lms.dto.request.RequestStudentDto;
import com.pcl.lms.dto.response.ResponesStudentDto;
import com.pcl.lms.tm.StudentTM;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface StudentBo extends SuperBo {
    public boolean saveStudent(RequestStudentDto requestStudentDto) throws SQLException, ClassNotFoundException;
    public ObservableList<StudentTM> getStudents(String searchText) throws SQLException, ClassNotFoundException;

}

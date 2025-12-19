package com.pcl.lms.bo.custom;

import com.pcl.lms.bo.SuperBo;
import com.pcl.lms.dto.request.RequestStudentDto;
import com.pcl.lms.dto.response.ResponesStudentDto;
import com.pcl.lms.tm.StudentTM;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public interface StudentBo extends SuperBo {
    public boolean saveStudent(RequestStudentDto requestStudentDto) throws SQLException, ClassNotFoundException;
    public List<ResponesStudentDto> getStudents(String searchText) throws SQLException, ClassNotFoundException;
    public boolean deleteStudent(String studentId) throws SQLException, ClassNotFoundException;
    public boolean updateStudent(RequestStudentDto requestStudentDto) throws SQLException, ClassNotFoundException;
}

package com.pcl.lms.bo.custom.impl;

import com.pcl.lms.bo.custom.StudentBo;
import com.pcl.lms.dao.DaoFactory;
import com.pcl.lms.dao.custom.impl.StudentDaoImpl;
import com.pcl.lms.dto.request.RequestStudentDto;
import com.pcl.lms.dto.response.ResponesStudentDto;
import com.pcl.lms.entity.Student;
import com.pcl.lms.env.Session;
import com.pcl.lms.tm.StudentTM;
import com.pcl.lms.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class StudentBoImpl implements StudentBo {
    StudentDaoImpl studentDao= DaoFactory.getInstance().getDao(DaoType.STUDENT);
    @Override
    public boolean saveStudent(RequestStudentDto requestStudentDto) throws SQLException, ClassNotFoundException {
         return studentDao.save(new Student(
                requestStudentDto.getId(),
                requestStudentDto.getName(),
                requestStudentDto.getAddress(),
                Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(requestStudentDto.getDob())),
                Session.getEmail()
        ));
    }

    @Override
    public List<ResponesStudentDto> getStudents(String searchText) throws SQLException, ClassNotFoundException {
        List<Student> students =studentDao.findByName(searchText);
        List<ResponesStudentDto> responesStudentDtoList= FXCollections.observableArrayList();
        for (Student student:students){

            responesStudentDtoList.add(new ResponesStudentDto(
                student.getId(),
                    student.getName(),
                    student.getAddress(),
                    student.getDob().toString(),
                    student.getUser_email()
            ));
        }
        return responesStudentDtoList;
    }
}

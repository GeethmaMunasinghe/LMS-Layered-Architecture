package com.pcl.lms.bo.custom.impl;

import com.pcl.lms.DB.DbConnection;
import com.pcl.lms.bo.custom.StudentBo;
import com.pcl.lms.dao.DaoFactory;
import com.pcl.lms.dao.custom.RegisterDao;
import com.pcl.lms.dao.custom.impl.EnrollDaoImpl;
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

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class StudentBoImpl implements StudentBo {
    StudentDaoImpl studentDao= DaoFactory.getInstance().getDao(DaoType.STUDENT);
    EnrollDaoImpl enrollDao=DaoFactory.getInstance().getDao(DaoType.ENROLL);
    RegisterDao registerDao=DaoFactory.getInstance().getDao(DaoType.REGISTER);
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

    @Override
    public boolean deleteStudent(String studentId) throws SQLException, ClassNotFoundException {
        boolean isRegistered=registerDao.isExists(studentId);
        if (isRegistered){
            //transaction
            Connection conn= DbConnection.getInstance().getConnection();
            conn.setAutoCommit(false);
            try {
                boolean isDeleted=registerDao.deleteByTransaction(studentId,conn);
                if (isDeleted){
                    boolean isDeleted2=studentDao.deleteByTransaction(studentId,conn);
                    if (isDeleted2){
                        conn.commit();
                        return true;
                    }
                }else {
                    conn.rollback();
                    return false;
                }

            }catch (Exception e){
                conn.rollback();
            }finally {
                conn.setAutoCommit(true);
            }


        }else {
            return studentDao.delete(studentId);
        }

        Connection connection= DbConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            Boolean isEnrolledDeleted=enrollDao.deleteByStudentId(studentId);
            boolean isStudentDeleted=studentDao.delete(studentId);
            if (isEnrolledDeleted && isStudentDeleted){
                connection.commit();
                return true;
            }else {
                connection.rollback();
                return false;
            }
        }catch (SQLException e){
            connection.rollback();
            throw e;
        }finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public boolean updateStudent(RequestStudentDto requestStudentDto) throws SQLException, ClassNotFoundException {
        return studentDao.update(new Student(
                requestStudentDto.getId(),
                requestStudentDto.getName(),
                requestStudentDto.getAddress(),
                Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(requestStudentDto.getDob())),
                "gmail.com"
        ));
    }


}

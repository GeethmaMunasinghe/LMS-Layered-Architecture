package com.pcl.lms.dao.custom.impl;

import com.pcl.lms.dao.CrudUtil;
import com.pcl.lms.dao.custom.StudentDao;
import com.pcl.lms.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public boolean save(Student student) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO student VALUES(?,?,?,?,?)",
                student.getId(),
                student.getName(),
                student.getAddress(),
                student.getDob(),
                student.getUser_email()
        );
    }

    @Override
    public boolean update(Student student) throws SQLException, ClassNotFoundException {
       return CrudUtil.execute("UPDATE student SET name=?,address=?,dob=? WHERE id=?",
               student.getName(),student.getAddress(),student.getDob(),student.getId());
    }

    @Override
    public boolean delete(String studentId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM student WHERE id=?",studentId);
    }

    @Override
    public String findById(String s) {
        return null;
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public List<Student> findByName(String searchText) throws SQLException, ClassNotFoundException {
        List<Student> students=new ArrayList<>(); //Dynamic array
        ResultSet set =CrudUtil.execute("SELECT * FROM student WHERE name LIKE?","%"+searchText+"%");
        while (set.next()){
            students.add(new Student(
                  set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getDate(4),
                    set.getString(5)
            ));
        }
        return students;
    }

    @Override
    public boolean deleteByTransaction(String id, Connection conn) throws SQLException {
        PreparedStatement ps =conn.prepareStatement("DELETE FROM enroll WHERE student_id=?");
        ps.setString(1,id);
        return ps.executeUpdate()>0;
    }

}

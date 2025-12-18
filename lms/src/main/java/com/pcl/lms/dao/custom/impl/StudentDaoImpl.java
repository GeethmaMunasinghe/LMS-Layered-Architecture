package com.pcl.lms.dao.custom.impl;

import com.pcl.lms.dao.CrudUtil;
import com.pcl.lms.dao.custom.StudentDao;
import com.pcl.lms.entity.Student;

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
    public boolean update(Student student) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
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
}

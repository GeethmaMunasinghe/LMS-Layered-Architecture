package com.pcl.lms.dao.custom.impl;

import com.pcl.lms.dao.CrudUtil;
import com.pcl.lms.dao.custom.TeacherDao;
import com.pcl.lms.entity.Teacher;

import java.sql.SQLException;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {
    @Override
    public boolean save(Teacher teacher) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO teacher VALUES(?,?,?,?)",
                teacher.getId(),
                teacher.getName(),
                teacher.getContact(),
                teacher.getAddress()
        );

    }

    @Override
    public boolean update(Teacher teacher) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String findById(String s) {
        return null;
    }

    @Override
    public List<Teacher> findAll() {
        return null;
    }
}

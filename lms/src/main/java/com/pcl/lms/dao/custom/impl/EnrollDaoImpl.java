package com.pcl.lms.dao.custom.impl;

import com.pcl.lms.dao.CrudUtil;
import com.pcl.lms.dao.custom.EnrollDao;
import com.pcl.lms.entity.Enroll;

import java.sql.SQLException;
import java.util.List;

public class EnrollDaoImpl implements EnrollDao {
    @Override
    public boolean save(Enroll enroll) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Enroll enroll) {
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
    public List<Enroll> findAll() {
        return null;
    }

    @Override
    public Boolean deleteByStudentId(String studentId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM enroll WHERE student_id=?",studentId);

    }
}

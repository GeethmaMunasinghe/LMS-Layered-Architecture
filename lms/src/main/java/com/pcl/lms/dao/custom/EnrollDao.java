package com.pcl.lms.dao.custom;

import com.pcl.lms.dao.CrudDao;
import com.pcl.lms.entity.Enroll;

import java.sql.SQLException;

public interface EnrollDao extends CrudDao<Enroll,String> {
    public Boolean deleteByStudentId(String studentId) throws SQLException, ClassNotFoundException;
}

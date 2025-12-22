package com.pcl.lms.dao.custom.impl;

import com.pcl.lms.dao.CrudUtil;
import com.pcl.lms.dao.custom.RegisterDao;
import com.pcl.lms.entity.Registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RegisterDaoImpl implements RegisterDao {
    @Override
    public boolean save(Registration registration) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO enroll VALUES(?,?,?)",
                registration.getProgramId(),
                registration.getStudentId(),
                registration.isPaid()
        );
    }

    @Override
    public boolean update(Registration registration) throws SQLException, ClassNotFoundException {
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
    public List<Registration> findAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean isExists(String id) throws SQLException, ClassNotFoundException {
        ResultSet set =CrudUtil.execute("SELECT * FROM enroll WHERE student_id=?",id);
        if (set.next()){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByTransaction(String id, Connection conn) throws SQLException {
        PreparedStatement ps=conn.prepareStatement("DELETE FROM enroll WHERE student_id=?");
        ps.setString(1,id);
        return ps.executeUpdate()>0;
    }
}

package com.pcl.lms.dao.custom;

import com.pcl.lms.dao.CrudDao;
import com.pcl.lms.entity.User;

public interface UserDao extends CrudDao<User, String> {
    public User findByEmail(String email);

}

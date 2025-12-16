package com.pcl.lms.bo.custom.impl;

import com.pcl.lms.bo.custom.UserBo;
import com.pcl.lms.dao.DaoFactory;
import com.pcl.lms.dao.custom.impl.UserDaoImpl;
import com.pcl.lms.dto.request.RequestUserDto;
import com.pcl.lms.entity.User;
import com.pcl.lms.util.DaoType;
import com.pcl.lms.util.security.PasswordManager;

import java.sql.SQLException;

public class UserBoImpl implements UserBo {
    UserDaoImpl user= DaoFactory.getInstance().getDao(DaoType.USER);

    @Override
    public boolean registerUser(RequestUserDto requestUserDto) throws SQLException, ClassNotFoundException {
        boolean isSaved=user.save(new User(
                requestUserDto.getEmail(),
                requestUserDto.getFullName(),
                requestUserDto.getAge(),
                new PasswordManager().encode(requestUserDto.getPassword())
        ));
        if (isSaved){
            return true;
        }
        return false;
    }
}

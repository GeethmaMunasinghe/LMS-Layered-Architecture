package com.pcl.lms.bo.custom.impl;

import com.pcl.lms.bo.custom.ProgramBo;
import com.pcl.lms.dao.DaoFactory;
import com.pcl.lms.dao.custom.ProgramDao;
import com.pcl.lms.dto.request.RequestProgramDto;
import com.pcl.lms.entity.Program;
import com.pcl.lms.util.DaoType;

import java.sql.SQLException;

public class ProgramBoImpl implements ProgramBo {
    ProgramDao programDao= DaoFactory.getInstance().getDao(DaoType.PROGRAM);
    @Override
    public boolean saveProgram(RequestProgramDto program) throws SQLException, ClassNotFoundException {
        return programDao.save(new Program(
               program.getId(),
               program.getName(),
               program.getCost(),
               splitId(program.getTeacher())
        ));
    }

    @Override
    public String splitId(String comboText) {
        String[] splittedArr=comboText.split("-");
        return splittedArr[0]+"-"+splittedArr[1];
    }
}

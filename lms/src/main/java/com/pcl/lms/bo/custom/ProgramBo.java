package com.pcl.lms.bo.custom;

import com.pcl.lms.bo.SuperBo;
import com.pcl.lms.dto.request.RequestProgramDto;

import java.sql.SQLException;

public interface ProgramBo extends SuperBo {
    public boolean saveProgram(RequestProgramDto program) throws SQLException, ClassNotFoundException;
    public String splitId(String comboText);
}

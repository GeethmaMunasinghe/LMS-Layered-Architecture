package com.pcl.lms.bo.custom;

import com.pcl.lms.bo.SuperBo;
import com.pcl.lms.dto.request.RequestProgramDto;
import com.pcl.lms.dto.response.ResponseProgramDto;

import java.sql.SQLException;
import java.util.List;

public interface ProgramBo extends SuperBo {
    public boolean saveProgram(RequestProgramDto program) throws SQLException, ClassNotFoundException;
    public String splitId(String comboText);
    public List<ResponseProgramDto> fetchProgramByName(String text) throws SQLException, ClassNotFoundException;
}

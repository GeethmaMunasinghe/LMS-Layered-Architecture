package com.pcl.lms.bo;

import com.pcl.lms.bo.custom.impl.ProgramBoImpl;
import com.pcl.lms.bo.custom.impl.StudentBoImpl;
import com.pcl.lms.bo.custom.impl.TeacherBoImpl;
import com.pcl.lms.bo.custom.impl.UserBoImpl;
import com.pcl.lms.dao.custom.impl.TeacherDaoImpl;
import com.pcl.lms.util.BoType;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory(){}

    public static BoFactory getInstance(){
        if (boFactory==null){
            boFactory=new BoFactory();
        }
        return boFactory;
    }

    public <T>T getBo(BoType boType){
        switch (boType){
            case USER:
                return(T) new UserBoImpl();
            case STUDENT:
                return (T) new StudentBoImpl();
            case TEACHER:
                return (T) new TeacherBoImpl();
            case PROGRAM:
                return (T) new ProgramBoImpl();
            default:
                return null;
        }
    }
}

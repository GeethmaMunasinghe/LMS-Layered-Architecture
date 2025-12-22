package com.pcl.lms.util.tools;

public class IdGenerator {
    public static String generatedId(String lastID){
        if (lastID!=null){
            String[] split=lastID.split("-");
            String lastChar=split[1];
            int lastDigit=Integer.parseInt(lastChar);
            lastDigit++;
            return split[0]+"-"+lastDigit;
        }else {
            return null;
        }
    }
}

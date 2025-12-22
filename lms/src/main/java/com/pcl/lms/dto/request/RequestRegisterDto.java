package com.pcl.lms.dto.request;

public class RequestRegisterDto {
    private String student;
    private String program;
    private boolean isPaid;

    public RequestRegisterDto() {
    }

    public RequestRegisterDto(String student, String program, boolean isPaid) {
        this.student = student;
        this.program = program;
        this.isPaid = isPaid;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}

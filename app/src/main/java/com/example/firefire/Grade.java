package com.example.firefire;

public class Grade {
    public String subject;
    public String grade;
    public String gname;
    public String quarter;

    public Grade(){}

    public Grade(String subject, String gname, String grade, String quarter) {
        this.subject = subject;
        this.gname = gname;
        this.grade= grade;
        this.quarter=quarter;
    }

    public String getSubject() {
        return subject;
    }

    public String getGrade() {
        return grade;
    }

    public String getGname() {
        return gname;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setSubject(String Subject) {
        subject = Subject;
    }

    public void setGrade(String Grade) {
        grade = Grade;
    }

    public void setGname(String Gname) {
        gname = Gname;
    }

    public void setQuarter(String Quarter) {
        quarter = Quarter;
    }

}

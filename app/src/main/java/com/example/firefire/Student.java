package com.example.firefire;

/**
 * new class students
 */
public class Student {

    public String name;
    public String adress;
    public String num;
    public String hnum;
    public String dnum;
    public String dname;
    public String mnum;
    public String mname;

    public Student() {}

    /**
     * student class builder
     * @param name
     * @param adress
     * @param num
     * @param hnum
     * @param dnum
     * @param dname
     * @param mnum
     * @param mname
     */
    public Student (String name, String adress, String num, String hnum, String dnum, String dname, String mnum,String mname) {
        this.name=name;
        this.adress=adress;
        this.num=num;
        this.dname=dname;
        this.dnum=dnum;
        this.mname=mname;
        this.mnum=mnum;
        this.hnum=hnum;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public String getNum() {
        return num;
    }

    public String getDnum() {
        return dnum;
    }

    public String getDname() {
        return dname;
    }

    public String getMnum() {
        return mnum;
    }

    public String getMname() {
        return mname;
    }

    public String getHnum() {
        return hnum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setNum(String num) { this.num = num; }

    public void setDnum(String dnum) {
        this.dnum = dnum;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public void setMnum(String mum) {
        this.mnum = mnum;
    }

    public void setMname(String mname) { this.mname = mname; }

    public void setHnum(String hnum) {
        this.hnum = hnum;
    }



}


package com.adventofcode.day4;

public class PassportBean {
    private String byr;
    private String iyr;
    private String eyr;
    private String hgt;
    private String hcl;
    private String ecl;
    private String pid;
    private String cid;
    private int fieldsPopulated;

    public String getByr() {
        return byr;
    }

    public void setByr(String byr) {
        this.byr = byr;
        updateFieldsPopulated(byr);
    }

    public String getIyr() {
        return iyr;
    }

    public void setIyr(String iyr) {
        this.iyr = iyr;
        updateFieldsPopulated(iyr);
    }

    public String getEyr() {
        return eyr;
    }

    public void setEyr(String eyr) {
        this.eyr = eyr;
        updateFieldsPopulated(eyr);
    }

    public String getHgt() {
        return hgt;
    }

    public void setHgt(String hgt) {
        this.hgt = hgt;
        updateFieldsPopulated(hgt);
    }

    public String getHcl() {
        return hcl;
    }

    public void setHcl(String hcl) {
        this.hcl = hcl;
        updateFieldsPopulated(hcl);
    }

    public String getEcl() {
        return ecl;
    }

    public void setEcl(String ecl) {
        this.ecl = ecl;
        updateFieldsPopulated(ecl);
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
        updateFieldsPopulated(pid);
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
        updateFieldsPopulated(cid);
    }

    private void updateFieldsPopulated(String value) {
        if (value != null) {
            fieldsPopulated++;
        }
    }

    public int getFieldsPopulated() {
        return fieldsPopulated;
    }

    public void setProperty(ValidField name, String value) {
        switch (name) {
            case byr:
                setByr(value);
                break;
            case iyr:
                setIyr(value);
                break;
            case eyr:
                setEyr(value);
                break;
            case hgt:
                setHgt(value);
                break;
            case hcl:
                setHcl(value);
                break;
            case ecl:
                setEcl(value);
                break;
            case pid:
                setPid(value);
                break;
            case cid:
                setCid(value);
                break;
        }
    }

    public boolean isValid() {
        return fieldsPopulated >= 7;
    }
}

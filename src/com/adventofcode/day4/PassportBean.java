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

    public String getByr() {
        return byr;
    }

    public void setByr(String byr) {
        this.byr = byr;
    }

    public String getIyr() {
        return iyr;
    }

    public void setIyr(String iyr) {
        this.iyr = iyr;
    }

    public String getEyr() {
        return eyr;
    }

    public void setEyr(String eyr) {
        this.eyr = eyr;
    }

    public String getHgt() {
        return hgt;
    }

    public void setHgt(String hgt) {
        this.hgt = hgt;
    }

    public String getHcl() {
        return hcl;
    }

    public void setHcl(String hcl) {
        this.hcl = hcl;
    }

    public String getEcl() {
        return ecl;
    }

    public void setEcl(String ecl) {
        this.ecl = ecl;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
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
        return byr != null && iyr != null && eyr != null && hgt != null
                && hcl != null && ecl != null && pid != null; // it is accepted that cid might not be set
    }

    public String toString() {
        return "byr=" + byr + ", iyr=" + iyr + ", eyr=" + eyr + ", hgt=" + hgt
                + ", hcl=" + hcl + ", ecl=" + ecl + ", pid=" + pid + ", cid=" + cid
                + "\t Is valid=" + isValid();
    }
}

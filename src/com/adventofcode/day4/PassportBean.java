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

    public boolean areRequiredFieldsPopulated() {
        return byr != null && iyr != null && eyr != null && hgt != null
                && hcl != null && ecl != null && pid != null; // it is accepted that cid might not be set
    }

    private boolean isByrValid() {
        return byr.length() == 4
                && Integer.valueOf(byr) >= 1920
                && Integer.valueOf(byr) <= 2002;
    }

    private boolean isIyrValid() {
        return iyr.length() == 4
                && Integer.valueOf(iyr) >= 2010
                && Integer.valueOf(iyr) <= 2020;
    }

    private boolean isEyrValid() {
        return eyr.length() == 4
                && Integer.valueOf(eyr) >= 2020
                && Integer.valueOf(eyr) <= 2030;
    }

    private boolean isHgtValid() {
        Integer height = null;
        if (hgt.endsWith("cm")) {
            height = Integer.valueOf(hgt.split("cm")[0]);
            return height >= 150 && height <= 193;
        } else if (hgt.endsWith("in")) {
            height = Integer.valueOf(hgt.split("in")[0]);
            return height >= 59 && height <= 76;
        }
        return false;
    }

    private boolean isHclValid() {
        return hcl.matches("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$");
    }

    private boolean isEclValid() {
        return ecl.equals("amb") || ecl.equals("blu") || ecl.equals("brn")
                || ecl.equals("gry") || ecl.equals("grn") || ecl.equals("hzl") || ecl.equals("oth");
    }

    private boolean isPidValid() {
        return pid.matches("\\d+") && pid.length() == 9;
    }

    public boolean isValid() {
        return areRequiredFieldsPopulated() && isByrValid() && isEclValid() && isEyrValid()
                && isHclValid() && isHgtValid() && isIyrValid() && isPidValid();
    }

    public String toString() {
        return "byr=" + byr + ", iyr=" + iyr + ", eyr=" + eyr + ", hgt=" + hgt
                + ", hcl=" + hcl + ", ecl=" + ecl + ", pid=" + pid + ", cid=" + cid
                + "\t Are required fields populated?=" + areRequiredFieldsPopulated()
                + "\t Is valid?=" + isValid();
    }
}

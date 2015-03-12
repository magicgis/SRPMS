package entity;

import java.util.Collection;

/**
 * DATE:2015/3/12
 * TIME:14:20
 * Created by guofan on 2015/3/12
 */
public class Staff {
    private String staId;
    private String staName;
    private String deptId;
    private String iDcard;
    private String rankId;
    private String edu;
    private String position;
    private String degree;
    private String staSNm;
    private Collection<Account> accountsByStaId;
    private Collection<Booksta> bookstasByStaId;
    private Collection<Papersta> paperstasByStaId;
    private Base baseByDeptId;
    private Base baseByRankId;

    public String getStaId() {
        return staId;
    }

    public void setStaId(String staId) {
        this.staId = staId;
    }

    public String getStaName() {
        return staName;
    }

    public void setStaName(String staName) {
        this.staName = staName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getiDcard() {
        return iDcard;
    }

    public void setiDcard(String iDcard) {
        this.iDcard = iDcard;
    }

    public String getRankId() {
        return rankId;
    }

    public void setRankId(String rankId) {
        this.rankId = rankId;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getStaSNm() {
        return staSNm;
    }

    public void setStaSNm(String staSNm) {
        this.staSNm = staSNm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Staff staff = (Staff) o;

        if (degree != null ? !degree.equals(staff.degree) : staff.degree != null) return false;
        if (deptId != null ? !deptId.equals(staff.deptId) : staff.deptId != null) return false;
        if (edu != null ? !edu.equals(staff.edu) : staff.edu != null) return false;
        if (iDcard != null ? !iDcard.equals(staff.iDcard) : staff.iDcard != null) return false;
        if (position != null ? !position.equals(staff.position) : staff.position != null) return false;
        if (rankId != null ? !rankId.equals(staff.rankId) : staff.rankId != null) return false;
        if (staId != null ? !staId.equals(staff.staId) : staff.staId != null) return false;
        if (staName != null ? !staName.equals(staff.staName) : staff.staName != null) return false;
        if (staSNm != null ? !staSNm.equals(staff.staSNm) : staff.staSNm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = staId != null ? staId.hashCode() : 0;
        result = 31 * result + (staName != null ? staName.hashCode() : 0);
        result = 31 * result + (deptId != null ? deptId.hashCode() : 0);
        result = 31 * result + (iDcard != null ? iDcard.hashCode() : 0);
        result = 31 * result + (rankId != null ? rankId.hashCode() : 0);
        result = 31 * result + (edu != null ? edu.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (degree != null ? degree.hashCode() : 0);
        result = 31 * result + (staSNm != null ? staSNm.hashCode() : 0);
        return result;
    }

    public Collection<Account> getAccountsByStaId() {
        return accountsByStaId;
    }

    public void setAccountsByStaId(Collection<Account> accountsByStaId) {
        this.accountsByStaId = accountsByStaId;
    }

    public Collection<Booksta> getBookstasByStaId() {
        return bookstasByStaId;
    }

    public void setBookstasByStaId(Collection<Booksta> bookstasByStaId) {
        this.bookstasByStaId = bookstasByStaId;
    }

    public Collection<Papersta> getPaperstasByStaId() {
        return paperstasByStaId;
    }

    public void setPaperstasByStaId(Collection<Papersta> paperstasByStaId) {
        this.paperstasByStaId = paperstasByStaId;
    }

    public Base getBaseByDeptId() {
        return baseByDeptId;
    }

    public void setBaseByDeptId(Base baseByDeptId) {
        this.baseByDeptId = baseByDeptId;
    }

    public Base getBaseByRankId() {
        return baseByRankId;
    }

    public void setBaseByRankId(Base baseByRankId) {
        this.baseByRankId = baseByRankId;
    }
}

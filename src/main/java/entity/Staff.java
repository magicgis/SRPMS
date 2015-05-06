package entity;

import javax.persistence.*;

/**
 * Created by guofan on 2015/5/6.
 */
@Entity
public class Staff {
    private String id;
    private String name;
    private String idCard;
    private String edu;
    private String position;
    private String degree;
    private String staSnm;
    private String memo;
    private BaseInfo baseInfoByDeptId;
    private BaseInfo baseInfoByRankId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "id_card")
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Basic
    @Column(name = "edu")
    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    @Basic
    @Column(name = "position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Basic
    @Column(name = "degree")
    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Basic
    @Column(name = "sta_snm")
    public String getStaSnm() {
        return staSnm;
    }

    public void setStaSnm(String staSnm) {
        this.staSnm = staSnm;
    }

    @Basic
    @Column(name = "memo")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Staff staff = (Staff) o;

        if (id != null ? !id.equals(staff.id) : staff.id != null) return false;
        if (name != null ? !name.equals(staff.name) : staff.name != null) return false;
        if (idCard != null ? !idCard.equals(staff.idCard) : staff.idCard != null) return false;
        if (edu != null ? !edu.equals(staff.edu) : staff.edu != null) return false;
        if (position != null ? !position.equals(staff.position) : staff.position != null) return false;
        if (degree != null ? !degree.equals(staff.degree) : staff.degree != null) return false;
        if (staSnm != null ? !staSnm.equals(staff.staSnm) : staff.staSnm != null) return false;
        if (memo != null ? !memo.equals(staff.memo) : staff.memo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (idCard != null ? idCard.hashCode() : 0);
        result = 31 * result + (edu != null ? edu.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (degree != null ? degree.hashCode() : 0);
        result = 31 * result + (staSnm != null ? staSnm.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "id")
    public BaseInfo getBaseInfoByDeptId() {
        return baseInfoByDeptId;
    }

    public void setBaseInfoByDeptId(BaseInfo baseInfoByDeptId) {
        this.baseInfoByDeptId = baseInfoByDeptId;
    }

    @ManyToOne
    @JoinColumn(name = "rank_id", referencedColumnName = "id")
    public BaseInfo getBaseInfoByRankId() {
        return baseInfoByRankId;
    }

    public void setBaseInfoByRankId(BaseInfo baseInfoByRankId) {
        this.baseInfoByRankId = baseInfoByRankId;
    }
}

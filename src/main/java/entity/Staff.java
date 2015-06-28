package entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

/**
 * Created by guofan on 2015/6/10.
 */
@Entity
@Table(name = "staff")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Staff {
    private String id;
    private String name;
    private String idCard;
    private String edu;
    private String position;
    private String degree;
    private String staSnm;
    private String memo;
    private BaseInfo dept;
    private BaseInfo rank;
    private BaseInfo col;
    private User user;

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
        if (dept != null ? !dept.equals(staff.dept) : staff.dept != null) return false;
        if (rank != null ? !rank.equals(staff.rank) : staff.rank != null) return false;
        if (col != null ? !col.equals(staff.col) : staff.col != null) return false;
        return !(user != null ? !user.equals(staff.user) : staff.user != null);

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
        result = 31 * result + (dept != null ? dept.hashCode() : 0);
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        result = 31 * result + (col != null ? col.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "dept", referencedColumnName = "id")
    public BaseInfo getDept() {
        return dept;
    }

    public void setDept(BaseInfo dept) {
        this.dept = dept;
    }

    @ManyToOne
    @JoinColumn(name = "rank", referencedColumnName = "id")
    public BaseInfo getRank() {
        return rank;
    }

    public void setRank(BaseInfo rank) {
        this.rank = rank;
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "col", referencedColumnName = "id")
    public BaseInfo getCol() {
        return col;
    }

    public void setCol(BaseInfo col) {
        this.col = col;
    }

    @OneToOne(mappedBy = "staff")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

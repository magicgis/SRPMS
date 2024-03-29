package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by guofan on 2015/6/10.
 */
@Entity
@Table(name = "mag")
public class Mag {
    private String id;
    private String name;
    private String snm;
    private String cn;
    private String issn;
    private String sub;
    private String type;
    private String status;
    private String memo;
    private Standard standard;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
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
    @Column(name = "snm")
    public String getSnm() {
        return snm;
    }

    public void setSnm(String snm) {
        this.snm = snm;
    }

    @Basic
    @Column(name = "cn")
    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    @Basic
    @Column(name = "issn")
    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    @Basic
    @Column(name = "sub")
    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

        Mag mag = (Mag) o;

        if (id != null ? !id.equals(mag.id) : mag.id != null) return false;
        if (name != null ? !name.equals(mag.name) : mag.name != null) return false;
        if (snm != null ? !snm.equals(mag.snm) : mag.snm != null) return false;
        if (cn != null ? !cn.equals(mag.cn) : mag.cn != null) return false;
        if (issn != null ? !issn.equals(mag.issn) : mag.issn != null) return false;
        if (sub != null ? !sub.equals(mag.sub) : mag.sub != null) return false;
        if (type != null ? !type.equals(mag.type) : mag.type != null) return false;
        if (status != null ? !status.equals(mag.status) : mag.status != null) return false;
        return !(memo != null ? !memo.equals(mag.memo) : mag.memo != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (snm != null ? snm.hashCode() : 0);
        result = 31 * result + (cn != null ? cn.hashCode() : 0);
        result = 31 * result + (issn != null ? issn.hashCode() : 0);
        result = 31 * result + (sub != null ? sub.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "standard", referencedColumnName = "id")
    public Standard getStandard() {
        return standard;
    }

    public void setStandard(Standard standard) {
        this.standard = standard;
    }
}

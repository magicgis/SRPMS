package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by guofan on 2015/5/6.
 */
@Entity
public class Confer {
    private String id;
    private String type;
    private String name;
    private Timestamp time;
    private String addr;
    private String memo;
    private Collection<Paper> papersById;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "addr")
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
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

        Confer confer = (Confer) o;

        if (id != null ? !id.equals(confer.id) : confer.id != null) return false;
        if (type != null ? !type.equals(confer.type) : confer.type != null) return false;
        if (name != null ? !name.equals(confer.name) : confer.name != null) return false;
        if (time != null ? !time.equals(confer.time) : confer.time != null) return false;
        if (addr != null ? !addr.equals(confer.addr) : confer.addr != null) return false;
        if (memo != null ? !memo.equals(confer.memo) : confer.memo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (addr != null ? addr.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "conferByConferId")
    public Collection<Paper> getPapersById() {
        return papersById;
    }

    public void setPapersById(Collection<Paper> papersById) {
        this.papersById = papersById;
    }
}

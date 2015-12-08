package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by guofan on 2015/6/10.
 */
@Entity
@Table(name = "confer")
public class Confer {
    private String id;
    private String name;
    private String time;
    private String addr;
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
    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Confer confer = (Confer) o;

        if (id != null ? !id.equals(confer.id) : confer.id != null) return false;
        if (name != null ? !name.equals(confer.name) : confer.name != null) return false;
        if (time != null ? !time.equals(confer.time) : confer.time != null) return false;
        if (addr != null ? !addr.equals(confer.addr) : confer.addr != null) return false;
        return !(standard != null ? !standard.equals(confer.standard) : confer.standard != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (addr != null ? addr.hashCode() : 0);
        result = 31 * result + (standard != null ? standard.hashCode() : 0);
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

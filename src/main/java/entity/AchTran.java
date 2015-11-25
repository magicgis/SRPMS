package entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Map;

import static util.Trans.argMap;

/**
 * Created by guofan on 2015/10/22.
 */
@Entity
@Table(name = "ach_tran")
public class AchTran implements VirtualEntity {
    private String id;
    private String name;
    private String date;
    private String actualMoney;
    private String money;
    private Integer score;
    private String process;
    private String arg;
    private String tranUnit;

    private BaseInfo dept;


    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 255)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "date", nullable = true, insertable = true, updatable = true, length = 255)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "actual_money", nullable = true, insertable = true, updatable = true, length = 255)
    public String getActualMoney() {
        return actualMoney;
    }

    public void setActualMoney(String actualMoney) {
        this.actualMoney = actualMoney;
    }

    @Basic
    @Column(name = "money", nullable = true, insertable = true, updatable = true, length = 255)
    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Basic
    @Column(name = "score", nullable = true, insertable = true, updatable = true)
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Basic
    @Column(name = "process", nullable = true, insertable = true, updatable = true, length = 255)
    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    @Basic
    @Column(name = "tran_unit", nullable = true, insertable = true, updatable = true, length = 255)
    public String getTranUnit() {
        return tranUnit;
    }

    public void setTranUnit(String tranUnit) {
        this.tranUnit = tranUnit;
    }

    @Basic
    @Column(name = "arg", nullable = true, insertable = true, updatable = true, length = 3000)
    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    @Transient
    public Map getArgMap() {
        return argMap(this.arg);
    }

    public void setArgMap(Map argMap) {
        try {
            Map<String, Object> map = null;
            try {
                map = new ObjectMapper().convertValue(this, Map.class);
            } catch (Exception x) {
                x.printStackTrace();
            }
            if (map != null) {
                argMap.put("View", map);
            }
            this.arg = new ObjectMapper().writeValueAsString(argMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AchTran achTran = (AchTran) o;

        if (id != null ? !id.equals(achTran.id) : achTran.id != null) return false;
        if (name != null ? !name.equals(achTran.name) : achTran.name != null) return false;
        if (date != null ? !date.equals(achTran.date) : achTran.date != null) return false;
        if (actualMoney != null ? !actualMoney.equals(achTran.actualMoney) : achTran.actualMoney != null) return false;
        if (money != null ? !money.equals(achTran.money) : achTran.money != null) return false;
        if (score != null ? !score.equals(achTran.score) : achTran.score != null) return false;
        if (process != null ? !process.equals(achTran.process) : achTran.process != null) return false;
        if (arg != null ? !arg.equals(achTran.arg) : achTran.arg != null) return false;
        if (tranUnit != null ? !tranUnit.equals(achTran.tranUnit) : achTran.tranUnit != null) return false;
        return !(dept != null ? !dept.equals(achTran.dept) : achTran.dept != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (actualMoney != null ? actualMoney.hashCode() : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (process != null ? process.hashCode() : 0);
        result = 31 * result + (arg != null ? arg.hashCode() : 0);
        result = 31 * result + (tranUnit != null ? tranUnit.hashCode() : 0);
        result = 31 * result + (dept != null ? dept.hashCode() : 0);
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

    @Override
    @Transient
    @Deprecated
    public Standard getStandard() {
        return null;
    }

    @Override
    public void setStandard(Standard standard) {

    }
}

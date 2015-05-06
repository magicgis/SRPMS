package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by guofan on 2015/5/6.
 */
@Entity
public class Basepaper {
    private String id;
    private String type;
    private String colType;
    private Integer conferScore;
    private Integer magScore;
    private Integer maxNum;
    private String memo;

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
    @Column(name = "col_type")
    public String getColType() {
        return colType;
    }

    public void setColType(String colType) {
        this.colType = colType;
    }

    @Basic
    @Column(name = "confer_score")
    public Integer getConferScore() {
        return conferScore;
    }

    public void setConferScore(Integer conferScore) {
        this.conferScore = conferScore;
    }

    @Basic
    @Column(name = "mag_score")
    public Integer getMagScore() {
        return magScore;
    }

    public void setMagScore(Integer magScore) {
        this.magScore = magScore;
    }

    @Basic
    @Column(name = "max_num")
    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
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

        Basepaper basepaper = (Basepaper) o;

        if (id != null ? !id.equals(basepaper.id) : basepaper.id != null) return false;
        if (type != null ? !type.equals(basepaper.type) : basepaper.type != null) return false;
        if (colType != null ? !colType.equals(basepaper.colType) : basepaper.colType != null) return false;
        if (conferScore != null ? !conferScore.equals(basepaper.conferScore) : basepaper.conferScore != null)
            return false;
        if (magScore != null ? !magScore.equals(basepaper.magScore) : basepaper.magScore != null) return false;
        if (maxNum != null ? !maxNum.equals(basepaper.maxNum) : basepaper.maxNum != null) return false;
        if (memo != null ? !memo.equals(basepaper.memo) : basepaper.memo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (colType != null ? colType.hashCode() : 0);
        result = 31 * result + (conferScore != null ? conferScore.hashCode() : 0);
        result = 31 * result + (magScore != null ? magScore.hashCode() : 0);
        result = 31 * result + (maxNum != null ? maxNum.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        return result;
    }
}

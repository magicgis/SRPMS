package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by guofan on 2015/5/6.
 */
@Entity
public class Basepat {
    private String id;
    private String type;
    private Integer score;
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
    @Column(name = "score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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

        Basepat basepat = (Basepat) o;

        if (id != null ? !id.equals(basepat.id) : basepat.id != null) return false;
        if (type != null ? !type.equals(basepat.type) : basepat.type != null) return false;
        if (score != null ? !score.equals(basepat.score) : basepat.score != null) return false;
        if (maxNum != null ? !maxNum.equals(basepat.maxNum) : basepat.maxNum != null) return false;
        if (memo != null ? !memo.equals(basepat.memo) : basepat.memo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (maxNum != null ? maxNum.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        return result;
    }
}

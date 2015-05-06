package entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by guofan on 2015/5/6.
 */
@Entity
@Table(name = "sta_ref", schema = "", catalog = "srpms")
public class StaRef {
    private String id;
    private BigDecimal score;
    private String role;
    private String tableName;
    private Achievement achievementByAchId;
    private Appar apparByApparId;
    private Food foodByFdId;
    private Med medByMedId;
    private Other otherByOtherId;
    private Paper paperByPaperId;
    private Patent patentByPatentId;
    private Staff staffByStaId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "score")
    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "table_name")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StaRef staRef = (StaRef) o;

        if (id != null ? !id.equals(staRef.id) : staRef.id != null) return false;
        if (score != null ? !score.equals(staRef.score) : staRef.score != null) return false;
        if (role != null ? !role.equals(staRef.role) : staRef.role != null) return false;
        if (tableName != null ? !tableName.equals(staRef.tableName) : staRef.tableName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "ach_id", referencedColumnName = "id")
    public Achievement getAchievementByAchId() {
        return achievementByAchId;
    }

    public void setAchievementByAchId(Achievement achievementByAchId) {
        this.achievementByAchId = achievementByAchId;
    }

    @ManyToOne
    @JoinColumn(name = "appar_id", referencedColumnName = "id")
    public Appar getApparByApparId() {
        return apparByApparId;
    }

    public void setApparByApparId(Appar apparByApparId) {
        this.apparByApparId = apparByApparId;
    }

    @ManyToOne
    @JoinColumn(name = "fd_id", referencedColumnName = "id")
    public Food getFoodByFdId() {
        return foodByFdId;
    }

    public void setFoodByFdId(Food foodByFdId) {
        this.foodByFdId = foodByFdId;
    }

    @ManyToOne
    @JoinColumn(name = "med_id", referencedColumnName = "id")
    public Med getMedByMedId() {
        return medByMedId;
    }

    public void setMedByMedId(Med medByMedId) {
        this.medByMedId = medByMedId;
    }

    @ManyToOne
    @JoinColumn(name = "other_id", referencedColumnName = "id")
    public Other getOtherByOtherId() {
        return otherByOtherId;
    }

    public void setOtherByOtherId(Other otherByOtherId) {
        this.otherByOtherId = otherByOtherId;
    }

    @ManyToOne
    @JoinColumn(name = "paper_id", referencedColumnName = "id")
    public Paper getPaperByPaperId() {
        return paperByPaperId;
    }

    public void setPaperByPaperId(Paper paperByPaperId) {
        this.paperByPaperId = paperByPaperId;
    }

    @ManyToOne
    @JoinColumn(name = "patent_id", referencedColumnName = "id")
    public Patent getPatentByPatentId() {
        return patentByPatentId;
    }

    public void setPatentByPatentId(Patent patentByPatentId) {
        this.patentByPatentId = patentByPatentId;
    }

    @ManyToOne
    @JoinColumn(name = "sta_id", referencedColumnName = "id")
    public Staff getStaffByStaId() {
        return staffByStaId;
    }

    public void setStaffByStaId(Staff staffByStaId) {
        this.staffByStaId = staffByStaId;
    }
}

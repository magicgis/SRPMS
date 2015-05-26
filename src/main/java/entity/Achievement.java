package entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by guofan on 2015/5/6.
 */
@Entity
@Table(name = "achievement", schema = "", catalog = "srpms")
public class Achievement {
    private String id;
    private String name;
    private Date appraiseDate;
    private String appraiseResult;
    private String appraiseWay;
    private String appraiseId;
    private String regNo;
    private Date regDate;
    private Date tranDate;
    private String tranMoney;
    private String tranAccou;
    private Integer appraiseScore;
    private Integer tranScore;
    private String awdProp;
    private String awdType;
    private String awdRank;
    private Integer awdScore;
    private String memo;
    private Collection<AchUnit> achUnitsById;
    private BaseInfo baseInfoByDeptId;
    private Collection<StaRef> staRefsById;

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
    @Column(name = "appraise_date")
    public Date getAppraiseDate() {
        return appraiseDate;
    }

    public void setAppraiseDate(Date appraiseDate) {
        this.appraiseDate = appraiseDate;
    }

    @Basic
    @Column(name = "appraise_result")
    public String getAppraiseResult() {
        return appraiseResult;
    }

    public void setAppraiseResult(String appraiseResult) {
        this.appraiseResult = appraiseResult;
    }

    @Basic
    @Column(name = "appraise_way")
    public String getAppraiseWay() {
        return appraiseWay;
    }

    public void setAppraiseWay(String appraiseWay) {
        this.appraiseWay = appraiseWay;
    }

    @Basic
    @Column(name = "appraise_id")
    public String getAppraiseId() {
        return appraiseId;
    }

    public void setAppraiseId(String appraiseId) {
        this.appraiseId = appraiseId;
    }

    @Basic
    @Column(name = "reg_no")
    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    @Basic
    @Column(name = "reg_date")
    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Basic
    @Column(name = "tran_date")
    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }

    @Basic
    @Column(name = "tran_money")
    public String getTranMoney() {
        return tranMoney;
    }

    public void setTranMoney(String tranMoney) {
        this.tranMoney = tranMoney;
    }

    @Basic
    @Column(name = "tran_accou")
    public String getTranAccou() {
        return tranAccou;
    }

    public void setTranAccou(String tranAccou) {
        this.tranAccou = tranAccou;
    }

    @Basic
    @Column(name = "appraise_score")
    public Integer getAppraiseScore() {
        return appraiseScore;
    }

    public void setAppraiseScore(Integer appraiseScore) {
        this.appraiseScore = appraiseScore;
    }

    @Basic
    @Column(name = "tran_score")
    public Integer getTranScore() {
        return tranScore;
    }

    public void setTranScore(Integer tranScore) {
        this.tranScore = tranScore;
    }

    @Basic
    @Column(name = "awd_prop")
    public String getAwdProp() {
        return awdProp;
    }

    public void setAwdProp(String awdProp) {
        this.awdProp = awdProp;
    }

    @Basic
    @Column(name = "awd_type")
    public String getAwdType() {
        return awdType;
    }

    public void setAwdType(String awdType) {
        this.awdType = awdType;
    }

    @Basic
    @Column(name = "awd_rank")
    public String getAwdRank() {
        return awdRank;
    }

    public void setAwdRank(String awdRank) {
        this.awdRank = awdRank;
    }

    @Basic
    @Column(name = "awd_score")
    public Integer getAwdScore() {
        return awdScore;
    }

    public void setAwdScore(Integer awdScore) {
        this.awdScore = awdScore;
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

        Achievement that = (Achievement) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (appraiseDate != null ? !appraiseDate.equals(that.appraiseDate) : that.appraiseDate != null) return false;
        if (appraiseResult != null ? !appraiseResult.equals(that.appraiseResult) : that.appraiseResult != null)
            return false;
        if (appraiseWay != null ? !appraiseWay.equals(that.appraiseWay) : that.appraiseWay != null) return false;
        if (appraiseId != null ? !appraiseId.equals(that.appraiseId) : that.appraiseId != null) return false;
        if (regNo != null ? !regNo.equals(that.regNo) : that.regNo != null) return false;
        if (regDate != null ? !regDate.equals(that.regDate) : that.regDate != null) return false;
        if (tranDate != null ? !tranDate.equals(that.tranDate) : that.tranDate != null) return false;
        if (tranMoney != null ? !tranMoney.equals(that.tranMoney) : that.tranMoney != null) return false;
        if (tranAccou != null ? !tranAccou.equals(that.tranAccou) : that.tranAccou != null) return false;
        if (appraiseScore != null ? !appraiseScore.equals(that.appraiseScore) : that.appraiseScore != null)
            return false;
        if (tranScore != null ? !tranScore.equals(that.tranScore) : that.tranScore != null) return false;
        if (awdProp != null ? !awdProp.equals(that.awdProp) : that.awdProp != null) return false;
        if (awdType != null ? !awdType.equals(that.awdType) : that.awdType != null) return false;
        if (awdRank != null ? !awdRank.equals(that.awdRank) : that.awdRank != null) return false;
        if (awdScore != null ? !awdScore.equals(that.awdScore) : that.awdScore != null) return false;
        if (memo != null ? !memo.equals(that.memo) : that.memo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (appraiseDate != null ? appraiseDate.hashCode() : 0);
        result = 31 * result + (appraiseResult != null ? appraiseResult.hashCode() : 0);
        result = 31 * result + (appraiseWay != null ? appraiseWay.hashCode() : 0);
        result = 31 * result + (appraiseId != null ? appraiseId.hashCode() : 0);
        result = 31 * result + (regNo != null ? regNo.hashCode() : 0);
        result = 31 * result + (regDate != null ? regDate.hashCode() : 0);
        result = 31 * result + (tranDate != null ? tranDate.hashCode() : 0);
        result = 31 * result + (tranMoney != null ? tranMoney.hashCode() : 0);
        result = 31 * result + (tranAccou != null ? tranAccou.hashCode() : 0);
        result = 31 * result + (appraiseScore != null ? appraiseScore.hashCode() : 0);
        result = 31 * result + (tranScore != null ? tranScore.hashCode() : 0);
        result = 31 * result + (awdProp != null ? awdProp.hashCode() : 0);
        result = 31 * result + (awdType != null ? awdType.hashCode() : 0);
        result = 31 * result + (awdRank != null ? awdRank.hashCode() : 0);
        result = 31 * result + (awdScore != null ? awdScore.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "achievementByAchId")
    public Collection<AchUnit> getAchUnitsById() {
        return achUnitsById;
    }

    public void setAchUnitsById(Collection<AchUnit> achUnitsById) {
        this.achUnitsById = achUnitsById;
    }

    @ManyToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "id")
    public BaseInfo getBaseInfoByDeptId() {
        return baseInfoByDeptId;
    }

    public void setBaseInfoByDeptId(BaseInfo baseInfoByDeptId) {
        this.baseInfoByDeptId = baseInfoByDeptId;
    }

    @OneToMany(mappedBy = "achievementByAchId")
    public Collection<StaRef> getStaRefsById() {
        return staRefsById;
    }

    public void setStaRefsById(Collection<StaRef> staRefsById) {
        this.staRefsById = staRefsById;
    }
}

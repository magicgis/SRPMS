package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by guofan on 2015/8/12.
 */
@Entity
@Table(name = "project_money")
public class ProjectMoney {
    private String id;
    private String time;
    private BigDecimal mny;
    private BigDecimal outMny;
    private Integer step;
    @JsonIgnore
    private Project project;


    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    @Column(name = "mny")
    public BigDecimal getMny() {
        return mny;
    }

    public void setMny(BigDecimal mny) {
        this.mny = mny;
    }

    @Basic
    @Column(name = "out_mny")
    public BigDecimal getOutMny() {
        return outMny;
    }

    public void setOutMny(BigDecimal outMny) {
        this.outMny = outMny;
    }

    @Basic
    @Column(name = "step")
    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectMoney that = (ProjectMoney) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (mny != null ? !mny.equals(that.mny) : that.mny != null) return false;
        if (outMny != null ? !outMny.equals(that.outMny) : that.outMny != null) return false;
        if (step != null ? !step.equals(that.step) : that.step != null) return false;
        return !(project != null ? !project.equals(that.project) : that.project != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (mny != null ? mny.hashCode() : 0);
        result = 31 * result + (outMny != null ? outMny.hashCode() : 0);
        result = 31 * result + (step != null ? step.hashCode() : 0);
        result = 31 * result + (project != null ? project.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "project", referencedColumnName = "id")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}

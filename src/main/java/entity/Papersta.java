package entity;

/**
 * DATE:2015/3/13
 * TIME:2:23
 * Created by guofan on 2015/3/13
 */
public class Papersta {
    private String idps;
    private String paperRole;
    private Integer grScore;
    private Paper paperByPaperId;
    private Staff staffByStaId;

    public String getIdps() {
        return idps;
    }

    public void setIdps(String idps) {
        this.idps = idps;
    }

    public String getPaperRole() {
        return paperRole;
    }

    public void setPaperRole(String paperRole) {
        this.paperRole = paperRole;
    }

    public Integer getGrScore() {
        return grScore;
    }

    public void setGrScore(Integer grScore) {
        this.grScore = grScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Papersta papersta = (Papersta) o;

        if (grScore != null ? !grScore.equals(papersta.grScore) : papersta.grScore != null) return false;
        if (idps != null ? !idps.equals(papersta.idps) : papersta.idps != null) return false;
        if (paperRole != null ? !paperRole.equals(papersta.paperRole) : papersta.paperRole != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idps != null ? idps.hashCode() : 0;
        result = 31 * result + (paperRole != null ? paperRole.hashCode() : 0);
        result = 31 * result + (grScore != null ? grScore.hashCode() : 0);
        return result;
    }

    public Paper getPaperByPaperId() {
        return paperByPaperId;
    }

    public void setPaperByPaperId(Paper paperByPaperId) {
        this.paperByPaperId = paperByPaperId;
    }

    public Staff getStaffByStaId() {
        return staffByStaId;
    }

    public void setStaffByStaId(Staff staffByStaId) {
        this.staffByStaId = staffByStaId;
    }
}

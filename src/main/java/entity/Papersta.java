package entity;

/**
 * DATE:2015/3/12
 * TIME:14:20
 * Created by guofan on 2015/3/12
 */
public class Papersta {
    private String paperId;
    private String staId;
    private String paperRole;
    private Integer grScore;
    private Paper paperByPaperId;
    private Staff staffByStaId;

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getStaId() {
        return staId;
    }

    public void setStaId(String staId) {
        this.staId = staId;
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
        if (paperId != null ? !paperId.equals(papersta.paperId) : papersta.paperId != null) return false;
        if (paperRole != null ? !paperRole.equals(papersta.paperRole) : papersta.paperRole != null) return false;
        if (staId != null ? !staId.equals(papersta.staId) : papersta.staId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = paperId != null ? paperId.hashCode() : 0;
        result = 31 * result + (staId != null ? staId.hashCode() : 0);
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

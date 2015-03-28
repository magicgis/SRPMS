package entity;

/**
 * DATE:2015/3/13
 * TIME:2:23
 * Created by guofan on 2015/3/13
 */
public class Paper {
    private String paperId;
    private String paperName;
    private Base baseByUnit;

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paper paper = (Paper) o;

        return !(paperId != null ? !paperId.equals(paper.paperId) : paper.paperId != null) && !(paperName != null ? !paperName.equals(paper.paperName) : paper.paperName != null);

    }

    @Override
    public int hashCode() {
        int result = paperId != null ? paperId.hashCode() : 0;
        result = 31 * result + (paperName != null ? paperName.hashCode() : 0);
        return result;
    }

    public Base getBaseByUnit() {
        return baseByUnit;
    }

    public void setBaseByUnit(Base baseByUnit) {
        this.baseByUnit = baseByUnit;
    }
}

package entity;

import java.util.Collection;

/**
 * DATE:2015/3/12
 * TIME:14:20
 * Created by guofan on 2015/3/12
 */
public class Paper {
    private String paperId;
    private String paperName;
    private String unit;
    private Collection<Conferpaper> conferpapersByPaperId;
    private Base baseByUnit;
    private Collection<Papermag> papermagsByPaperId;
    private Collection<Papersta> paperstasByPaperId;

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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paper paper = (Paper) o;

        if (paperId != null ? !paperId.equals(paper.paperId) : paper.paperId != null) return false;
        if (paperName != null ? !paperName.equals(paper.paperName) : paper.paperName != null) return false;
        if (unit != null ? !unit.equals(paper.unit) : paper.unit != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = paperId != null ? paperId.hashCode() : 0;
        result = 31 * result + (paperName != null ? paperName.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        return result;
    }

    public Collection<Conferpaper> getConferpapersByPaperId() {
        return conferpapersByPaperId;
    }

    public void setConferpapersByPaperId(Collection<Conferpaper> conferpapersByPaperId) {
        this.conferpapersByPaperId = conferpapersByPaperId;
    }

    public Base getBaseByUnit() {
        return baseByUnit;
    }

    public void setBaseByUnit(Base baseByUnit) {
        this.baseByUnit = baseByUnit;
    }

    public Collection<Papermag> getPapermagsByPaperId() {
        return papermagsByPaperId;
    }

    public void setPapermagsByPaperId(Collection<Papermag> papermagsByPaperId) {
        this.papermagsByPaperId = papermagsByPaperId;
    }

    public Collection<Papersta> getPaperstasByPaperId() {
        return paperstasByPaperId;
    }

    public void setPaperstasByPaperId(Collection<Papersta> paperstasByPaperId) {
        this.paperstasByPaperId = paperstasByPaperId;
    }
}

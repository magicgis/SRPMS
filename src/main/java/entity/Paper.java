package entity;

/**
 * 论文
 */
public class Paper {
    /* 论文ID */
	private String paperid;
    /* 论文名称 */
	private String papername;
    /* 发表日期 */
	private String pubdate;
    /* TODO Unknown */
	private Unit unit;
    private String paperId;
    private String paperName;

	public String getPaperid() {
		return paperid;
	}

	public void setPaperid(String paperid) {
		this.paperid = paperid;
	}

	public String getPapername() {
		return papername;
	}

	public void setPapername(String papername) {
		this.papername = papername;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

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

        if (paperId != null ? !paperId.equals(paper.paperId) : paper.paperId != null) return false;
        if (paperName != null ? !paperName.equals(paper.paperName) : paper.paperName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = paperId != null ? paperId.hashCode() : 0;
        result = 31 * result + (paperName != null ? paperName.hashCode() : 0);
        return result;
    }
}

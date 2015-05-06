package entity;

import javax.persistence.*;

/**
 * Created by guofan on 2015/5/6.
 */
@Entity
public class Data {
    private String id;
    private String path;
    private Achievement achievementByAchId;
    private Appar apparByApparId;
    private Book bookByBkId;
    private Food foodByFdId;
    private Other otherByOtherId;
    private Paper paperByPaperId;
    private Patent patentByPatentId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Data data = (Data) o;

        if (id != null ? !id.equals(data.id) : data.id != null) return false;
        if (path != null ? !path.equals(data.path) : data.path != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (path != null ? path.hashCode() : 0);
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
    @JoinColumn(name = "bk_id", referencedColumnName = "id")
    public Book getBookByBkId() {
        return bookByBkId;
    }

    public void setBookByBkId(Book bookByBkId) {
        this.bookByBkId = bookByBkId;
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
}

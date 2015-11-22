package entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Map;

import static util.Trans.argMap;

/**
 * Created by guofan on 2015/10/11.
 */
@Entity
@Table(name = "book")
public class Book implements VirtualEntity {
    private String id;
    private String name;
    private String pubDate;
    private String pubType;
    private String publisher;
    private String isbn;
    private String year;
    private String score;
    private Integer sumWord;
    private String bulDate;
    private String process;
    private String arg;
    private BaseInfo dept;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 255)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "pub_date", nullable = true, insertable = true, updatable = true, length = 255)
    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Basic
    @Column(name = "pub_type", nullable = true, insertable = true, updatable = true, length = 255)
    public String getPubType() {
        return pubType;
    }

    public void setPubType(String pubType) {
        this.pubType = pubType;
    }

    @Basic
    @Column(name = "publisher", nullable = true, insertable = true, updatable = true, length = 255)
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Basic
    @Column(name = "isbn", nullable = true, insertable = true, updatable = true, length = 25)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "year", nullable = true, insertable = true, updatable = true, length = 255)
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Basic
    @Column(name = "score", nullable = true, insertable = true, updatable = true, length = 255)
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Basic
    @Column(name = "sum_word", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getSumWord() {
        return sumWord;
    }

    public void setSumWord(Integer sumWord) {
        this.sumWord = sumWord;
    }

    @Basic
    @Column(name = "bul_date", nullable = true, insertable = true, updatable = true, length = 255)
    public String getBulDate() {
        return bulDate;
    }

    public void setBulDate(String bulDate) {
        this.bulDate = bulDate;
    }


    @Basic
    @Column(name = "process", nullable = true, insertable = true, updatable = true, length = 255)
    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    @Basic
    @Column(name = "arg", nullable = true, insertable = true, updatable = true, length = 2000)
    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    @Transient
    public Map getArgMap() {
        return argMap(this.arg);
    }

    public void setArgMap(Map argMap) {
        try {
            this.arg = new ObjectMapper().writeValueAsString(argMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != null ? !id.equals(book.id) : book.id != null) return false;
        if (name != null ? !name.equals(book.name) : book.name != null) return false;
        if (pubDate != null ? !pubDate.equals(book.pubDate) : book.pubDate != null) return false;
        if (pubType != null ? !pubType.equals(book.pubType) : book.pubType != null) return false;
        if (publisher != null ? !publisher.equals(book.publisher) : book.publisher != null) return false;
        if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) return false;
        if (year != null ? !year.equals(book.year) : book.year != null) return false;
        if (score != null ? !score.equals(book.score) : book.score != null) return false;
        if (sumWord != null ? !sumWord.equals(book.sumWord) : book.sumWord != null) return false;
        if (bulDate != null ? !bulDate.equals(book.bulDate) : book.bulDate != null) return false;
        if (process != null ? !process.equals(book.process) : book.process != null) return false;
        if (arg != null ? !arg.equals(book.arg) : book.arg != null) return false;
        return !(dept != null ? !dept.equals(book.dept) : book.dept != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (pubDate != null ? pubDate.hashCode() : 0);
        result = 31 * result + (pubType != null ? pubType.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (sumWord != null ? sumWord.hashCode() : 0);
        result = 31 * result + (bulDate != null ? bulDate.hashCode() : 0);
        result = 31 * result + (process != null ? process.hashCode() : 0);
        result = 31 * result + (arg != null ? arg.hashCode() : 0);
        result = 31 * result + (dept != null ? dept.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "dept", referencedColumnName = "id")
    public BaseInfo getDept() {
        return dept;
    }

    public void setDept(BaseInfo dept) {
        this.dept = dept;
    }


    @Override
    @Transient
    @Deprecated
    public Standard getStandard() {
        return null;
    }

    @Override
    public void setStandard(Standard standard) {

    }
}

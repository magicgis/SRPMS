package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by guofan on 2015/5/6.
 */
@Entity
@Table(name = "book", schema = "", catalog = "srpms")
public class Book {
    private String id;
    private String name;
    private Timestamp pubDate;
    private String publisher;
    private String isbn;
    private String scYr;
    private String type;
    private BigDecimal wdNum;
    private String awdType;
    private String awdRank;
    private String memo;
    private BaseInfo baseInfoByDeptId;
    private Collection<BookSta> bookStasById;

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
    @Column(name = "pub_date")
    public Timestamp getPubDate() {
        return pubDate;
    }

    public void setPubDate(Timestamp pubDate) {
        this.pubDate = pubDate;
    }

    @Basic
    @Column(name = "publisher")
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Basic
    @Column(name = "isbn")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "sc_yr")
    public String getScYr() {
        return scYr;
    }

    public void setScYr(String scYr) {
        this.scYr = scYr;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "wd_num")
    public BigDecimal getWdNum() {
        return wdNum;
    }

    public void setWdNum(BigDecimal wdNum) {
        this.wdNum = wdNum;
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

        Book book = (Book) o;

        if (id != null ? !id.equals(book.id) : book.id != null) return false;
        if (name != null ? !name.equals(book.name) : book.name != null) return false;
        if (pubDate != null ? !pubDate.equals(book.pubDate) : book.pubDate != null) return false;
        if (publisher != null ? !publisher.equals(book.publisher) : book.publisher != null) return false;
        if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) return false;
        if (scYr != null ? !scYr.equals(book.scYr) : book.scYr != null) return false;
        if (type != null ? !type.equals(book.type) : book.type != null) return false;
        if (wdNum != null ? !wdNum.equals(book.wdNum) : book.wdNum != null) return false;
        if (awdType != null ? !awdType.equals(book.awdType) : book.awdType != null) return false;
        if (awdRank != null ? !awdRank.equals(book.awdRank) : book.awdRank != null) return false;
        if (memo != null ? !memo.equals(book.memo) : book.memo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (pubDate != null ? pubDate.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (scYr != null ? scYr.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (wdNum != null ? wdNum.hashCode() : 0);
        result = 31 * result + (awdType != null ? awdType.hashCode() : 0);
        result = 31 * result + (awdRank != null ? awdRank.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "id")
    public BaseInfo getBaseInfoByDeptId() {
        return baseInfoByDeptId;
    }

    public void setBaseInfoByDeptId(BaseInfo baseInfoByDeptId) {
        this.baseInfoByDeptId = baseInfoByDeptId;
    }

    @OneToMany(mappedBy = "bookByBkId")
    public Collection<BookSta> getBookStasById() {
        return bookStasById;
    }

    public void setBookStasById(Collection<BookSta> bookStasById) {
        this.bookStasById = bookStasById;
    }


}

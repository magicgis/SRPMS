package entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * DATE:2015/3/11
 * TIME:23:51
 * Created by guofan on 2015/3/11
 */
public class Book {
    private String bkId;
    private String bkName;
    private Timestamp pubDate;
    private String publisher;
    private String isbn;
    private String scYr;
    private String bkType;
    private BigDecimal bkWdNum;

    public String getBkId() {
        return bkId;
    }

    public void setBkId(String bkId) {
        this.bkId = bkId;
    }

    public String getBkName() {
        return bkName;
    }

    public void setBkName(String bkName) {
        this.bkName = bkName;
    }

    public Timestamp getPubDate() {
        return pubDate;
    }

    public void setPubDate(Timestamp pubDate) {
        this.pubDate = pubDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getScYr() {
        return scYr;
    }

    public void setScYr(String scYr) {
        this.scYr = scYr;
    }

    public String getBkType() {
        return bkType;
    }

    public void setBkType(String bkType) {
        this.bkType = bkType;
    }

    public BigDecimal getBkWdNum() {
        return bkWdNum;
    }

    public void setBkWdNum(BigDecimal bkWdNum) {
        this.bkWdNum = bkWdNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (bkId != null ? !bkId.equals(book.bkId) : book.bkId != null) return false;
        if (bkName != null ? !bkName.equals(book.bkName) : book.bkName != null) return false;
        if (bkType != null ? !bkType.equals(book.bkType) : book.bkType != null) return false;
        if (bkWdNum != null ? !bkWdNum.equals(book.bkWdNum) : book.bkWdNum != null) return false;
        if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) return false;
        if (pubDate != null ? !pubDate.equals(book.pubDate) : book.pubDate != null) return false;
        if (publisher != null ? !publisher.equals(book.publisher) : book.publisher != null) return false;
        if (scYr != null ? !scYr.equals(book.scYr) : book.scYr != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bkId != null ? bkId.hashCode() : 0;
        result = 31 * result + (bkName != null ? bkName.hashCode() : 0);
        result = 31 * result + (pubDate != null ? pubDate.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (scYr != null ? scYr.hashCode() : 0);
        result = 31 * result + (bkType != null ? bkType.hashCode() : 0);
        result = 31 * result + (bkWdNum != null ? bkWdNum.hashCode() : 0);
        return result;
    }
}

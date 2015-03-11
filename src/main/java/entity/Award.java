package entity;

/**
 * DATE:2015/3/11
 * TIME:23:51
 * Created by guofan on 2015/3/11
 */
public class Award {
    private String awdId;
    private String awdType;
    private String awdRank;

    public String getAwdId() {
        return awdId;
    }

    public void setAwdId(String awdId) {
        this.awdId = awdId;
    }

    public String getAwdType() {
        return awdType;
    }

    public void setAwdType(String awdType) {
        this.awdType = awdType;
    }

    public String getAwdRank() {
        return awdRank;
    }

    public void setAwdRank(String awdRank) {
        this.awdRank = awdRank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Award award = (Award) o;

        if (awdId != null ? !awdId.equals(award.awdId) : award.awdId != null) return false;
        if (awdRank != null ? !awdRank.equals(award.awdRank) : award.awdRank != null) return false;
        if (awdType != null ? !awdType.equals(award.awdType) : award.awdType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = awdId != null ? awdId.hashCode() : 0;
        result = 31 * result + (awdType != null ? awdType.hashCode() : 0);
        result = 31 * result + (awdRank != null ? awdRank.hashCode() : 0);
        return result;
    }
}

package entity;

/**
 * DATE:2015/3/13
 * TIME:2:23
 * Created by guofan on 2015/3/13
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

        return !(awdId != null ? !awdId.equals(award.awdId) : award.awdId != null) && !(awdRank != null ? !awdRank.equals(award.awdRank) : award.awdRank != null) && !(awdType != null ? !awdType.equals(award.awdType) : award.awdType != null);

    }

    @Override
    public int hashCode() {
        int result = awdId != null ? awdId.hashCode() : 0;
        result = 31 * result + (awdType != null ? awdType.hashCode() : 0);
        result = 31 * result + (awdRank != null ? awdRank.hashCode() : 0);
        return result;
    }
}

package entity;

import java.sql.Timestamp;

/**
 * DATE:2015/3/13
 * TIME:2:23
 * Created by guofan on 2015/3/13
 */
public class Confer {
    private String conferId;
    private String conferType;
    private String conferNm;
    private Timestamp conferTime;
    private String conferAddr;

    public String getConferId() {
        return conferId;
    }

    public void setConferId(String conferId) {
        this.conferId = conferId;
    }

    public String getConferType() {
        return conferType;
    }

    public void setConferType(String conferType) {
        this.conferType = conferType;
    }

    public String getConferNm() {
        return conferNm;
    }

    public void setConferNm(String conferNm) {
        this.conferNm = conferNm;
    }

    public Timestamp getConferTime() {
        return conferTime;
    }

    public void setConferTime(Timestamp conferTime) {
        this.conferTime = conferTime;
    }

    public String getConferAddr() {
        return conferAddr;
    }

    public void setConferAddr(String conferAddr) {
        this.conferAddr = conferAddr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Confer confer = (Confer) o;

        return !(conferAddr != null ? !conferAddr.equals(confer.conferAddr) : confer.conferAddr != null) && !(conferId != null ? !conferId.equals(confer.conferId) : confer.conferId != null) && !(conferNm != null ? !conferNm.equals(confer.conferNm) : confer.conferNm != null) && !(conferTime != null ? !conferTime.equals(confer.conferTime) : confer.conferTime != null) && !(conferType != null ? !conferType.equals(confer.conferType) : confer.conferType != null);

    }

    @Override
    public int hashCode() {
        int result = conferId != null ? conferId.hashCode() : 0;
        result = 31 * result + (conferType != null ? conferType.hashCode() : 0);
        result = 31 * result + (conferNm != null ? conferNm.hashCode() : 0);
        result = 31 * result + (conferTime != null ? conferTime.hashCode() : 0);
        result = 31 * result + (conferAddr != null ? conferAddr.hashCode() : 0);
        return result;
    }
}

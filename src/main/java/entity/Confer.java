package entity;

import java.sql.Timestamp;
import java.util.Collection;

/**
 * DATE:2015/3/12
 * TIME:14:20
 * Created by guofan on 2015/3/12
 */
public class Confer {
    private String conferId;
    private String conferType;
    private String conferNm;
    private Timestamp conferTime;
    private String conferAddr;
    private Collection<Conferpaper> conferpapersByConferId;

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

        if (conferAddr != null ? !conferAddr.equals(confer.conferAddr) : confer.conferAddr != null) return false;
        if (conferId != null ? !conferId.equals(confer.conferId) : confer.conferId != null) return false;
        if (conferNm != null ? !conferNm.equals(confer.conferNm) : confer.conferNm != null) return false;
        if (conferTime != null ? !conferTime.equals(confer.conferTime) : confer.conferTime != null) return false;
        if (conferType != null ? !conferType.equals(confer.conferType) : confer.conferType != null) return false;

        return true;
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

    public Collection<Conferpaper> getConferpapersByConferId() {
        return conferpapersByConferId;
    }

    public void setConferpapersByConferId(Collection<Conferpaper> conferpapersByConferId) {
        this.conferpapersByConferId = conferpapersByConferId;
    }
}

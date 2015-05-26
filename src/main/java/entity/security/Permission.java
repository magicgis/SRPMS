package entity.security;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by guofan on 2015/5/9.
 */
@Entity
@Table(name = "permission", schema = "", catalog = "srpms")
public class Permission {
    private String id;
    private Role roleByRoleId;
    private Url urlByUrlId;
    private Boolean isPermit;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "is_permit")
    public Boolean getIsPermit() {
        return isPermit;
    }

    public void setIsPermit(Boolean isPermit) {
        this.isPermit = isPermit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Permission that = (Permission) o;

        if (isPermit != null ? !isPermit.equals(that.isPermit) : that.isPermit != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (isPermit != null ? isPermit.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role")
    public Role getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(Role roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }

    @ManyToOne
    @JoinColumn(name = "url_id", referencedColumnName = "id")
    public Url getUrlByUrlId() {
        return urlByUrlId;
    }

    public void setUrlByUrlId(Url urlByUrlId) {
        this.urlByUrlId = urlByUrlId;
    }
}

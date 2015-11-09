package entity.security;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by guofan on 2015/5/9.
 */
@Entity
@Table(name = "url")
public class Url {
    private String id;
    private String url;
    private String type;
    private String define;
    private Collection<Permission> permissions;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
    @Column(name = "define")
    public String getDefine() {
        return define;
    }

    public void setDefine(String define) {
        this.define = define;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Url url1 = (Url) o;

        if (id != null ? !id.equals(url1.id) : url1.id != null) return false;
        if (url != null ? !url.equals(url1.url) : url1.url != null) return false;
        if (type != null ? !type.equals(url1.type) : url1.type != null) return false;
        return !(define != null ? !define.equals(url1.define) : url1.define != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (define != null ? define.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "url")
    public Collection<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Collection<Permission> permissions) {
        this.permissions = permissions;
    }
}

package entity.security;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by guofan on 2015/5/9.
 */
@Entity
public class Role {
    private String role;
    private String define;
    private Collection<Permission> permissionsByRole;

    @Id
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

        Role role1 = (Role) o;

        if (role != null ? !role.equals(role1.role) : role1.role != null) return false;
        if (define != null ? !define.equals(role1.define) : role1.define != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = role != null ? role.hashCode() : 0;
        result = 31 * result + (define != null ? define.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "roleByRoleId")
    public Collection<Permission> getPermissionsByRole() {
        return permissionsByRole;
    }

    public void setPermissionsByRole(Collection<Permission> permissionsByRole) {
        this.permissionsByRole = permissionsByRole;
    }
}

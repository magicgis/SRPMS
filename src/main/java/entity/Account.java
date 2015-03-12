package entity;

/**
 * DATE:2015/3/12
 * TIME:14:20
 * Created by guofan on 2015/3/12
 */
public class Account {
    private String id;
    private String staId;
    private String email;
    private String pwd;
    private String privilege;
    private Staff staff;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStaId() {
        return staId;
    }

    public void setStaId(String staId) {
        this.staId = staId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (email != null ? !email.equals(account.email) : account.email != null) return false;
        if (id != null ? !id.equals(account.id) : account.id != null) return false;
        if (privilege != null ? !privilege.equals(account.privilege) : account.privilege != null) return false;
        if (pwd != null ? !pwd.equals(account.pwd) : account.pwd != null) return false;
        if (staId != null ? !staId.equals(account.staId) : account.staId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (staId != null ? staId.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        result = 31 * result + (privilege != null ? privilege.hashCode() : 0);
        return result;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staffByStaId) {
        this.staff = staffByStaId;
    }
}

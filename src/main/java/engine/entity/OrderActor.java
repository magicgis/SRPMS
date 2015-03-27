package engine.entity;

/**
 * DATE:2015/3/20
 * TIME:19:59
 * Created by guofan on 2015/3/20
 */
public class OrderActor {
    private String idoa;
    private String actor;
    private String order;
    private String type;
    private Integer role;


    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer major) {
        this.role = major;
    }

    public String getIdoa() {
        return idoa;
    }

    public void setIdoa(String idoa) {
        this.idoa = idoa;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderActor)) return false;

        OrderActor that = (OrderActor) o;

        if (actor != null ? !actor.equals(that.actor) : that.actor != null) return false;
        if (idoa != null ? !idoa.equals(that.idoa) : that.idoa != null) return false;
        if (order != null ? !order.equals(that.order) : that.order != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idoa != null ? idoa.hashCode() : 0;
        result = 31 * result + (actor != null ? actor.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}

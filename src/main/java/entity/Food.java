package entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by guofan on 2015/5/6.
 */
@Entity
public class Food {
    private String id;
    private String name;
    private String prodNo;
    private String memo;
    private Collection<Data> datasById;
    private Collection<FoodUnit> foodUnitsById;
    private Collection<StaRef> staRefsById;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "prod_no")
    public String getProdNo() {
        return prodNo;
    }

    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }

    @Basic
    @Column(name = "memo")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        if (id != null ? !id.equals(food.id) : food.id != null) return false;
        if (name != null ? !name.equals(food.name) : food.name != null) return false;
        if (prodNo != null ? !prodNo.equals(food.prodNo) : food.prodNo != null) return false;
        if (memo != null ? !memo.equals(food.memo) : food.memo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (prodNo != null ? prodNo.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "foodByFdId")
    public Collection<Data> getDatasById() {
        return datasById;
    }

    public void setDatasById(Collection<Data> datasById) {
        this.datasById = datasById;
    }

    @OneToMany(mappedBy = "foodByFdId")
    public Collection<FoodUnit> getFoodUnitsById() {
        return foodUnitsById;
    }

    public void setFoodUnitsById(Collection<FoodUnit> foodUnitsById) {
        this.foodUnitsById = foodUnitsById;
    }

    @OneToMany(mappedBy = "foodByFdId")
    public Collection<StaRef> getStaRefsById() {
        return staRefsById;
    }

    public void setStaRefsById(Collection<StaRef> staRefsById) {
        this.staRefsById = staRefsById;
    }
}

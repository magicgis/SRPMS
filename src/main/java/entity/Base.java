package entity;

/**
 * DATE:2015/3/13
 * TIME:2:23
 * Created by guofan on 2015/3/13
 */
public class Base {
    private String baseId;
    private String table;
    private String keycode;
    private String value;

    public String getBaseId() {
        return baseId;
    }

    public void setBaseId(String baseId) {
        this.baseId = baseId;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getKeycode() {
        return keycode;
    }

    public void setKeycode(String keycode) {
        this.keycode = keycode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Base base = (Base) o;

        return !(baseId != null ? !baseId.equals(base.baseId) : base.baseId != null) && !(keycode != null ? !keycode.equals(base.keycode) : base.keycode != null) && !(table != null ? !table.equals(base.table) : base.table != null) && !(value != null ? !value.equals(base.value) : base.value != null);

    }

    @Override
    public int hashCode() {
        int result = baseId != null ? baseId.hashCode() : 0;
        result = 31 * result + (table != null ? table.hashCode() : 0);
        result = 31 * result + (keycode != null ? keycode.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}

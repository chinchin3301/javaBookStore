package entity;

import java.util.Objects;

public class Local {
    private Long id;
    private String shortName;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Local local = (Local) o;
        return id.equals(local.id) && shortName.equals(local.shortName) && name.equals(local.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shortName, name);
    }

    @Override
    public String toString() {
        return "Local{" +
                "id=" + id +
                ", shortName='" + shortName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

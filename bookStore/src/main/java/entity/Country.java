package entity;

import java.util.Objects;

public class Country {
    private Long id;
    private String name;
    private Long localsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLocalsId() {
        return localsId;
    }

    public void setLocalsId(Long localsId) {
        this.localsId = localsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id.equals(country.id) && name.equals(country.name) && localsId.equals(country.localsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, localsId);
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", localsId=" + localsId +
                '}';
    }
}

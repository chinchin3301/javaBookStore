package entity;

import java.util.Objects;

public class Publisher {
    private Long id;
    private String name;
    private Long countriesId;

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

    public Long getCountriesId() {
        return countriesId;
    }

    public void setCountriesId(Long countriesId) {
        this.countriesId = countriesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return id.equals(publisher.id) && name.equals(publisher.name) && countriesId.equals(publisher.countriesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, countriesId);
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countriesId=" + countriesId +
                '}';
    }
}

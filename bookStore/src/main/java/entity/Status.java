package entity;

import java.util.Objects;

public class Status {
    private Long id;
    private String title;
    private Long localsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        Status status = (Status) o;
        return id.equals(status.id) && title.equals(status.title) && localsId.equals(status.localsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, localsId);
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", localsId=" + localsId +
                '}';
    }
}

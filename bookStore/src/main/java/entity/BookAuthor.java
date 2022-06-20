package entity;

import java.util.Objects;

public class BookAuthor {
    private Long id;
    private Long booksId;
    private Long authorsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBooksId() {
        return booksId;
    }

    public void setBooksId(Long booksId) {
        this.booksId = booksId;
    }

    public Long getAuthorsId() {
        return authorsId;
    }

    public void setAuthorsId(Long authorsId) {
        this.authorsId = authorsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookAuthor that = (BookAuthor) o;
        return id.equals(that.id) && booksId.equals(that.booksId) && authorsId.equals(that.authorsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, booksId, authorsId);
    }

    @Override
    public String toString() {
        return "BookAuthor{" +
                "id=" + id +
                ", booksId=" + booksId +
                ", authorsId=" + authorsId +
                '}';
    }
}

package entity;

import java.util.Objects;

public class Basket {
    private Long id;
    private Long count;
    private Long usersId;
    private Long booksId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getUsersId() {
        return usersId;
    }

    public void setUsersId(Long usersId) {
        this.usersId = usersId;
    }

    public Long getBooksId() {
        return booksId;
    }

    public void setBooksId(Long booksId) {
        this.booksId = booksId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return id.equals(basket.id) && count.equals(basket.count) && usersId.equals(basket.usersId) && booksId.equals(basket.booksId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, count, usersId, booksId);
    }

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", count=" + count +
                ", usersId=" + usersId +
                ", booksId=" + booksId +
                '}';
    }
}

package entity;

import java.util.Objects;

public class OrderItem {
    private Long id;
    private Long count;
    private Long cost;
    private Long ordersId;
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

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
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
        OrderItem orderItem = (OrderItem) o;
        return id.equals(orderItem.id) && count.equals(orderItem.count) && cost.equals(orderItem.cost) && ordersId.equals(orderItem.ordersId) && booksId.equals(orderItem.booksId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, count, cost, ordersId, booksId);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", count=" + count +
                ", cost=" + cost +
                ", ordersId=" + ordersId +
                ", booksId=" + booksId +
                '}';
    }
}

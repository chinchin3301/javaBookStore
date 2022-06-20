package entity;

import java.util.Date;
import java.util.Objects;

public class Order {
    private Long id;
    private Date createTime;
    private Date finishTime;
    private Long totalCost;
    private Long statusId;
    private Long usersId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getUsersId() {
        return usersId;
    }

    public void setUsersId(Long usersId) {
        this.usersId = usersId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id) && createTime.equals(order.createTime) && finishTime.equals(order.finishTime) && totalCost.equals(order.totalCost) && statusId.equals(order.statusId) && usersId.equals(order.usersId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, finishTime, totalCost, statusId, usersId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", finishTime=" + finishTime +
                ", totalCost=" + totalCost +
                ", statusId=" + statusId +
                ", usersId=" + usersId +
                '}';
    }
}

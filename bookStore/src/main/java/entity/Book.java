package entity;

import java.util.Objects;

public class Book {
    private Long id;
    private String title;
    private String description;
    private Long price;
    private Long count;
    private Long languagesId;
    private Long genreId;
    private Long publishersId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getLanguagesId() {
        return languagesId;
    }

    public void setLanguagesId(Long languagesId) {
        this.languagesId = languagesId;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public Long getPublishersId() {
        return publishersId;
    }

    public void setPublishersId(Long publishersId) {
        this.publishersId = publishersId;
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
        Book book = (Book) o;
        return id.equals(book.id) && title.equals(book.title) && description.equals(book.description) && price.equals(book.price) && count.equals(book.count) && languagesId.equals(book.languagesId) && genreId.equals(book.genreId) && publishersId.equals(book.publishersId) && localsId.equals(book.localsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, price, count, languagesId, genreId, publishersId, localsId);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", languagesId=" + languagesId +
                ", genreId=" + genreId +
                ", publishersId=" + publishersId +
                ", localsId=" + localsId +
                '}';
    }
}

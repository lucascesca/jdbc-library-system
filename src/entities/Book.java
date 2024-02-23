package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Book implements Serializable {
    private int id;
    private String title;
    private Date publishing_year;
    private String edition;
    private Publisher publisher;
    private List<Author> authors = new ArrayList<>();

    public Book() {}

    public Book(int id, String title, Date publishing_year, String edition, Publisher publisher) {
        this.id = id;
        this.title = title;
        this.publishing_year = publishing_year;
        this.edition = edition;
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublishing_year() {
        return publishing_year;
    }

    public void setPublishing_year(Date publishing_year) {
        this.publishing_year = publishing_year;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Author> getAuthors() { return authors; }

    public void addAuthor(List<Author> list) {
        authors.addAll(list);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return getId() == book.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publishing_year=" + publishing_year +
                ", edition='" + edition + '\'' +
                '}';
    }
}

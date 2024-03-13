package entities.pk;

import entities.Author;
import entities.Book;

import java.io.Serializable;
import java.util.Objects;

public class BookAuthorPK implements Serializable {
    private Book book;
    private Author author;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookAuthorPK that)) return false;
        return Objects.equals(getBook(), that.getBook()) && Objects.equals(getAuthor(), that.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBook(), getAuthor());
    }
}

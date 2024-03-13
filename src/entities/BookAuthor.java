package entities;

import entities.pk.BookAuthorPK;

import java.util.Objects;

public class BookAuthor {
    private BookAuthorPK pk = new BookAuthorPK();

    public BookAuthor(Book book, Author author) {
        pk.setBook(book);
        pk.setAuthor(author);
    }

    public BookAuthorPK getPk() {
        return pk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookAuthor that)) return false;
        return Objects.equals(getPk(), that.getPk());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPk());
    }
}

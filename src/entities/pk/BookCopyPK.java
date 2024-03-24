package entities.pk;

import entities.Book;

import java.io.Serializable;
import java.util.Objects;

public class BookCopyPK implements Serializable {
    private Integer copyId;
    private Integer bookId;

    public int getCopyId() {
        return copyId;
    }

    public void setCopyId(int copyId) {
        this.copyId = copyId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookCopyPK that)) return false;
        return getCopyId() == that.getCopyId() && getBookId() == that.getBookId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCopyId(), getBookId());
    }

    @Override
    public String toString() {
        return "BookCopyPK{" +
                "copyId=" + copyId +
                ", bookId=" + bookId +
                '}';
    }
}

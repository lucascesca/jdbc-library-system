package entities;

import java.io.Serializable;
import java.util.Objects;

public class BookCopy implements Serializable {
    private int copyId;
    private int bookId;

    public BookCopy() {}

    public BookCopy(Integer id, Integer bookId) {
        this.copyId = id;
        this.bookId = bookId;
    }

    public int getCopyId() {
        return copyId;
    }

    public void setCopyId(int copyId) {
        this.copyId = copyId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookCopy bookCopy)) return false;
        return getCopyId() == bookCopy.getCopyId() && getBookId() == bookCopy.getBookId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCopyId(), getBookId());
    }

    @Override
    public String toString() {
        return "BookCopy{" +
                "id=" + copyId +
                ", bookId=" + bookId +
                '}';
    }
}

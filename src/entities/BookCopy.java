package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookCopy extends Book implements Serializable {
    private int copyId;
    private int bookId;
    private Book book;

    private List<Loan> loans = new ArrayList<>();

    public BookCopy() {}

    public BookCopy(Integer id, Book book) {
        this.copyId = id;
        this.book = book;
        this.bookId = this.book.getId();
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

    public List<Loan> getLoans() {
        return loans;
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
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
                ", book=" + book +
                '}';
    }
}

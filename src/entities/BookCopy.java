package entities;

import entities.pk.BookCopyPK;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookCopy  implements Serializable {
    private BookCopyPK pk = new BookCopyPK();

    private List<Loan> loans = new ArrayList<>();

    public BookCopy() {}

    public BookCopy(int copyId, int bookId) {
        pk.setCopyId(copyId);
        pk.setBookId(bookId);
    }

    public BookCopyPK getPk() { return pk; }

    public void setPk(int copyId, int bookId) {
        pk.setCopyId(copyId);
        pk.setBookId(bookId);
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    @Override
    public String toString() {
        return "BookCopy{" +
                "pk=" + pk +
                '}';
    }
}

package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Loan implements Serializable {
    private int id;
    private Date loanDate;
    private Date previewReturnDate;
    private BookCopy copy;
    private Client client;

    public Loan() {}

    public Loan(int id, Date loanDate, Date previewReturnDate, BookCopy copy, Client client) {
        this.id = id;
        this.loanDate = loanDate;
        this.previewReturnDate = previewReturnDate;
        this.copy = copy;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getPreviewReturnDate() {
        return previewReturnDate;
    }

    public void setPreviewReturnDate(Date previewReturnDate) {
        this.previewReturnDate = previewReturnDate;
    }

    public BookCopy getCopy() {
        return copy;
    }

    public void setCopy(BookCopy copy) {
        this.copy = copy;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Loan loan)) return false;
        return getId() == loan.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", loanDate=" + loanDate +
                ", forseenReturn=" + previewReturnDate +
                ", copy=" + copy +
                ", client=" + client +
                '}';
    }
}

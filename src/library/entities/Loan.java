package library.entities;

import java.util.Date;

import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;
import library.interfaces.entities.ELoanState;

public class Loan implements ILoan {

    IBook _book;
    IMember _borrower;
    Date _borrowDate, _dueDate;
    ELoanState _state;
    int _loanId;

    public Loan(IBook book, IMember borrower, Date borrowDate, Date dueDate) {
	if (book == null || borrower == null || borrowDate == null
		|| dueDate == null)
	    throw new IllegalArgumentException(
		    "Loan parameters cannot be null.");
	if (dueDate.compareTo(borrowDate) < 0)
	    throw new IllegalArgumentException(
		    "Due date cannot be earlier than borrow date.");

	_book = book;
	_borrower = borrower;
	_borrowDate = borrowDate;
	_dueDate = dueDate;
	_state = ELoanState.PENDING;
    }

    @Override
    public void commit(int loanId) {
	if (loanId < 0)
	    throw new IllegalArgumentException(
		    "Loan ID cannot be less than zero.");
	if (_state != ELoanState.PENDING)
	    throw new RuntimeException("Loan 'state' not pending." + _state);

	_loanId = loanId;

	_book.borrow(this);
	_borrower.addLoan(this);

	_state = ELoanState.CURRENT;
    }

    @Override
    public void complete() {
	// TODO Auto-generated method stub

    }

    @Override
    public boolean isCurrent() {
	return _state == ELoanState.CURRENT;
    }

    @Override
    public boolean isOverDue() {
	return _state == ELoanState.OVERDUE;
    }

    @Override
    public boolean checkOverDue(Date currentDate) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public IMember getBorrower() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public IBook getBook() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public int getID() {
	return _loanId;
    }

    @Override
    public String toString() {
	return _book.toString() + " - " + _borrower.toString() + " ("
		+ _borrowDate.toString() + " to " + _dueDate.toString() + ")";
    }
}

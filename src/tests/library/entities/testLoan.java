package tests.library.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Calendar;
import java.util.Date;

import library.entities.Loan;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testLoan {

    IBook _book;
    IMember _borrower;
    Loan _loan;
    Date _borrowDate, _dueDate;
    Calendar _calendar;

    @Before
    public void setUp() throws Exception {
	_book = mock(IBook.class);
	_borrower = mock(IMember.class);

	_calendar = Calendar.getInstance();
	_borrowDate = _calendar.getTime();
	_calendar.add(Calendar.DATE, ILoan.LOAN_PERIOD);
	_dueDate = _calendar.getTime();

	_loan = new Loan(_book, _borrower, _borrowDate, _dueDate);

    }

    @After
    public void tearDown() throws Exception {
	_loan = null;
    }

    @Test
    public void constructor_AllParametersLegal_NoException() {
	ILoan loan = new Loan(_book, _borrower, _borrowDate, _dueDate);
	assertTrue(loan instanceof Loan);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_NullBook_IllegalArgumentException() {
	ILoan loan = new Loan(null, _borrower, _borrowDate, _dueDate);
	assertTrue(loan instanceof Loan);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_NullBorrower_IllegalArgumentException() {
	ILoan loan = new Loan(_book, null, _borrowDate, _dueDate);
	assertTrue(loan instanceof Loan);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_NullBorrowDate_IllegalArgumentException() {
	ILoan loan = new Loan(_book, _borrower, null, _dueDate);
	assertTrue(loan instanceof Loan);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_NullDueDate_IllegalArgumentException() {
	ILoan loan = new Loan(_book, _borrower, _borrowDate, null);
	assertTrue(loan instanceof Loan);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_DueDateLessThenBorrowDate_IllegalArgumentException() {
	ILoan loan = new Loan(_book, _borrower, _dueDate, _borrowDate);
	assertTrue(loan instanceof Loan);
    }

    @Test
    public void constructor_DueDateEqualToBorrowDate_NoException() {
	ILoan loan = new Loan(_book, _borrower, _dueDate, _dueDate);
	assertTrue(loan instanceof Loan);
    }

    @Test(expected = IllegalArgumentException.class)
    public void commit_LoanIdNegative_IllegalArgumentException() {
	_loan.commit(-1);
    }

    @Test
    public void commit_LoanIdZero_NoException() {
	_loan.commit(0);
    }

    @Test
    public void commit_LoanIdPositive_NoException() {
	_loan.commit(1);
	assertTrue(_loan.isCurrent());
    }

    @Test(expected = RuntimeException.class)
    public void commit_StateNotPending_RuntimeException() {
	_loan.commit(1);
	assertTrue(_loan.isCurrent());
	_loan.commit(1);
    }

    @Test
    public void commit_VerifyMethods_NoException() {
	int loanId = 1;
	_loan.commit(loanId);
	verify(_book).borrow(_loan);
	verify(_borrower).addLoan(_loan);
	assertTrue(_loan.isCurrent());
	assertTrue(_loan.getID() == loanId);
    }

    @Test
    public void isCurrent_StatePending_NoException() {
	assertFalse(_loan.isCurrent());
    }

}

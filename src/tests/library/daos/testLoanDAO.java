package tests.library.daos;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import library.daos.LoanMapDAO;
import library.interfaces.daos.ILoanHelper;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class testLoanDAO {

    LoanMapDAO _loanDao;
    ILoanHelper _helper;
    IBook _book;
    IMember _borrower;

    @Before
    public void setUp() throws Exception {
	_helper = mock(ILoanHelper.class);
	_loanDao = new LoanMapDAO(_helper);

	_book = mock(IBook.class);
	_borrower = mock(IMember.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void constructor_AllParametersLegal_NoException() {
	LoanMapDAO loanDao = new LoanMapDAO(mock(ILoanHelper.class));
	assertTrue(loanDao instanceof LoanMapDAO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_NullHelper_IllegalArgumentException() {
	LoanMapDAO loanDao = new LoanMapDAO(null);
	assertTrue(loanDao instanceof LoanMapDAO);
    }

    @Test
    public void createLoan_NormalFlow_HelperMakeLoanMethodCalled() {
	ILoan loan = mock(ILoan.class);
	when(
		_helper.makeLoan(eq(_book), eq(_borrower), any(Date.class),
			any(Date.class))).thenReturn(loan);

	ILoan loan2 = _loanDao.createLoan(_borrower, _book);
	verify(_helper).makeLoan(eq(_book), eq(_borrower), any(Date.class),
		any(Date.class));
	assertEquals(loan, loan2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createLoan_NullBorrower_IllegalArgumentException() {
	_loanDao.createLoan(null, _book);
	assertTrue(_loanDao instanceof LoanMapDAO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createLoan_NullBook_IllegalArgumentException() {
	_loanDao.createLoan(_borrower, null);
	assertTrue(_loanDao instanceof LoanMapDAO);
    }

    @Test
    public void commitLoan_NormalFlow_LoanStoredWithUniqueId() {
	ILoan loan = mock(ILoan.class);
	ArgumentCaptor<Integer> argument = ArgumentCaptor
		.forClass(Integer.class);
	_loanDao.commitLoan(loan);
	verify(loan).commit(argument.capture());
	assertEquals(_loanDao.getLoanByID(argument.getValue()), loan);
    }

    @Test
    public void getLoanById_NoneFound_ReturnsNull() {
	assertEquals(_loanDao.getLoanByID(0), null);
    }

    @Test
    public void getLoanByBook_NoneFound_ReturnsNull() {
	assertEquals(_loanDao.getLoanByBook(mock(IBook.class)), null);
    }
}

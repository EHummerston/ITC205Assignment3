package tests.library.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import library.entities.Book;
import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testBook {
	
	String _author;
	String _title;
	String _callNumber;
	int _bookId;
	
	Book _book;
	ILoan _loan;
	
	@Before
	public void setUp() throws Exception {
		_author = "FName LName";
		_title = "Title";
		_callNumber = "call#";
		_bookId = 1;
		
		_book = new Book (_author, _title, _callNumber, _bookId);
		_loan = mock(ILoan.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void constructor_AllParametersLegal_NoException () {
		IBook book = new Book (_author, _title, _callNumber, _bookId);
		assertTrue (book instanceof Book);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void constructor_NullAuthor_IllegalArgumentException () {
		IBook book = new Book (null, _title, _callNumber, _bookId);
		assertTrue (book instanceof Book);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void constructor_BlankAuthor_IllegalArgumentException () {
		IBook book = new Book ("", _title, _callNumber, _bookId);
		assertTrue (book instanceof Book);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void constructor_NullTitle_IllegalArgumentException () {
		IBook book = new Book (_author, null, _callNumber, _bookId);
		assertTrue (book instanceof Book);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void constructor_BlankTitle_IllegalArgumentException () {
		IBook book = new Book (_author, "", _callNumber, _bookId);
		assertTrue (book instanceof Book);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void constructor_NullCallNumber_IllegalArgumentException () {
		IBook book = new Book (_author, _title, null, _bookId);
		assertTrue (book instanceof Book);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void constructor_BlankCallNumber_IllegalArgumentException () {
		IBook book = new Book (_author, _title, "", _bookId);
		assertTrue (book instanceof Book);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void constructor_BookIdNegative_IllegalArgumentException () {
		IBook book = new Book (_author, _title, _callNumber, -1);
		assertTrue (book instanceof Book);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void constructor_BookIdZero_IllegalArgumentException () {
		IBook book = new Book (_author, _title, _callNumber, 0);
		assertTrue (book instanceof Book);
	}
	
	@Test
	public void borrow_StateAvailable_NoExceptions () {
		assertEquals(_book.getState(),EBookState.AVAILABLE);
		_book.borrow(_loan);
		assertEquals(_book.getLoan(),_loan);
		assertEquals(_book.getState(),EBookState.ON_LOAN);
	}
	
	@Test (expected=RuntimeException.class)
	public void borrow_StateOnLoan_RunTimeException () {
		_book.borrow(_loan);
		assertEquals(_book.getState(),EBookState.ON_LOAN);
		_book.borrow(_loan);
	}
	
	
}

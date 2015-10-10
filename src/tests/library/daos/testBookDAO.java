package tests.library.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import library.daos.BookMapDAO;
import library.interfaces.daos.IBookHelper;
import library.interfaces.entities.IBook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class testBookDAO {

	BookMapDAO _bookDao;
	IBookHelper _helper;
	IBook _book;

	String _author;
	String _title;
	String _callNumber;

	@Before
	public void setUp() throws Exception {
		_helper = mock(IBookHelper.class);
		_bookDao = new BookMapDAO(_helper);
		_book = mock(IBook.class);

		_author = "FName LName";
		_title = "Title";
		_callNumber = "call#";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void constructor_HelperNotNull_NoExceptions () {
		BookMapDAO bookDao = new BookMapDAO (mock(IBookHelper.class));
		assertTrue (bookDao instanceof BookMapDAO);
	}

	@Test (expected=IllegalArgumentException.class)
	public void constructor_NullHelper_IllegalArgumentException () {
		BookMapDAO bookDao = new BookMapDAO (null);
		assertTrue (bookDao instanceof BookMapDAO);
	}

	@Test
	public void addBook_NormalFlow_BookIdEqualsMapKey () {

		when(_helper.makeBook(eq(_author), eq(_title), eq(_callNumber), anyInt())).thenReturn(_book);
		ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
		
		_bookDao.addBook(_author, _title, _callNumber);
		verify(_helper).makeBook(any(String.class), any(String.class), any(String.class), argument.capture());
		
		assertEquals(_book, _bookDao.getBookByID(argument.getValue()));
	}
	
	@Test
	public void getBookByID_BookNotFound_ReturnNull () {
		verifyZeroInteractions(_helper);
		assertEquals(_bookDao.getBookByID(0),(IBook)null);
	}

}

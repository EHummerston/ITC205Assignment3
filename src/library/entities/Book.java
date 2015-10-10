package library.entities;

import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;

public class Book implements IBook {

	String _author;
	String _title;
	String _callNumber;
	int _id;
	ILoan _loan;
	EBookState _state;
	
	public Book (String author, String title, String callNumber, int bookId)
	{
		if( author == null || author == "" || 
				title == null || title == "" ||
				callNumber == null || callNumber == "" )
			throw new IllegalArgumentException ("Illegal parameters (author, title and/or call number) in book declaration.");
		if(	bookId <= 0 )
			throw new IllegalArgumentException ("Book ID must be above 0");
		
		_author = author;
		_title = title;
		_callNumber = callNumber;
		_id = bookId;
		
		_state = EBookState.AVAILABLE;
	}
	
	@Override
	public void borrow(ILoan loan) {
		if(_state != EBookState.AVAILABLE)
			throw new RuntimeException ("Book not available.");
		
		_loan = loan;
		_state = EBookState.ON_LOAN;

	}

	@Override
	public ILoan getLoan() {
		if(_state != EBookState.ON_LOAN)
			return null;
		else return _loan;
	}

	@Override
	public void returnBook(boolean damaged) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void repair() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public EBookState getState() {
		return _state;
	}

	@Override
	public String getAuthor() {
		return _author;
	}

	@Override
	public String getTitle() {
		return _title;
	}

	@Override
	public String getCallNumber() {
		return _callNumber;
	}

	@Override
	public int getID() {
		return _id;
	}
	
	@Override
	public String toString() {
		return "BID#" + _id + " " + _author + ", " + _title;
	}

}

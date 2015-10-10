package library.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.IBookHelper;
import library.interfaces.entities.IBook;

public class BookMapDAO implements IBookDAO {

	int _nextId;
	Map<Integer, IBook> _bookMap;
	IBookHelper _helper;
	
	public BookMapDAO (IBookHelper helper)
	{
		if(helper == null)
			throw new IllegalArgumentException ("Book Data Access Helper cannot be null.");
		
		_helper = helper;
		_bookMap = new HashMap<>();
		_nextId = 1;
	}
	
	@Override
	public IBook addBook(String author, String title, String callNo) {
		
		IBook newBook = _helper.makeBook(author, title, callNo, _nextId);
		_bookMap.put(_nextId, newBook);
		_nextId++;
		
		return newBook; 
	}

	@Override
	public IBook getBookByID(int id) {
		return _bookMap.get(id);
	}

	@Override
	public List<IBook> listBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IBook> findBooksByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IBook> findBooksByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IBook> findBooksByAuthorTitle(String author, String title) {
		// TODO Auto-generated method stub
		return null;
	}

}

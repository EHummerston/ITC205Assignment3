package library.daos;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library.interfaces.daos.ILoanDAO;
import library.interfaces.daos.ILoanHelper;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

public class LoanMapDAO implements ILoanDAO {

	ILoanHelper _helper;
	Calendar _calendar;
	HashMap<Integer, ILoan> _loanMap;
	int _nextId;
	
	public LoanMapDAO (ILoanHelper helper)
	{
		if(helper == null)
			throw new IllegalArgumentException("Loan Data Access Helper cannot be null.");
		
		_helper = helper;
		_loanMap = new HashMap<>();
	}
	
	@Override
	public ILoan createLoan(IMember borrower, IBook book) {
		if(borrower == null || book == null)
			throw new IllegalArgumentException("Loan parameters cannot be null.");
			
		_calendar = Calendar.getInstance();
		Date borrowDate = _calendar.getTime();
		_calendar.add(Calendar.DATE,ILoan.LOAN_PERIOD);
		Date dueDate = _calendar.getTime();
		return _helper.makeLoan(book, borrower, borrowDate, dueDate);
	}

	@Override
	public void commitLoan(ILoan loan) {
		loan.commit(_nextId);
		_loanMap.put(_nextId, loan);
		
		_nextId++;
	}

	@Override
	public ILoan getLoanByID(int id) {
		return _loanMap.get(id);
	}

	@Override
	public ILoan getLoanByBook(IBook book) {
		for (Map.Entry<Integer, ILoan> entry : _loanMap.entrySet())
		{
		    ILoan loan = entry.getValue();
		    if(loan.getBook().getID()==book.getID() &&
		    		(loan.isCurrent()||loan.isOverDue()) )
		    	return loan;
		}
		return null;
	}

	@Override
	public List<ILoan> listLoans() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ILoan> findLoansByBorrower(IMember borrower) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ILoan> findLoansByBookTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateOverDueStatus(Date currentDate) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ILoan> findOverDueLoans() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNextId()
	{
		return _nextId;
	}
	
}

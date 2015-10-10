package library;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import library.interfaces.EBorrowState;
import library.interfaces.IBorrowUI;
import library.interfaces.IBorrowUIListener;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.ILoanDAO;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;
import library.interfaces.hardware.ICardReader;
import library.interfaces.hardware.ICardReaderListener;
import library.interfaces.hardware.IDisplay;
import library.interfaces.hardware.IPrinter;
import library.interfaces.hardware.IScanner;
import library.interfaces.hardware.IScannerListener;

public class BorrowUC_CTL implements ICardReaderListener, 
									 IScannerListener, 
									 IBorrowUIListener {
	
	private ICardReader _reader;
	private IScanner _scanner; 
	private IPrinter _printer; 
	private IDisplay _display;
	//private String state;
	private int _scanCount = 0;
	private IBorrowUI _ui;
	private EBorrowState _state; 
	private IBookDAO _bookDAO;
	private IMemberDAO _memberDAO;
	private ILoanDAO _loanDAO;
	
	private List<IBook> _bookList;
	private List<ILoan> _loanList;
	private IMember _borrower;
	
	private JPanel previous;


	public BorrowUC_CTL(ICardReader reader, IScanner scanner, 
			IPrinter printer, IDisplay display,
			IBookDAO bookDAO, ILoanDAO loanDAO, IMemberDAO memberDAO ) {

		this._display = display;
		this._ui = new BorrowUC_UI(this);
		
		_reader = reader;
		_scanner = scanner;
		
		_state = EBorrowState.CREATED;
		//state = EBorrowState.SCANNING_BOOKS;
	}
	
	public void initialise() {
		previous = _display.getDisplay();
		_display.setDisplay((JPanel) _ui, "Borrow UI");
		_reader.addListener(this);
		_scanner.addListener(this);
		
		_reader.setEnabled(true);
		this.setState(EBorrowState.INITIALIZED);
	}
	
	public void close() {
		_display.setDisplay(previous, "Main Menu");
		System.out.println("close");
	}

	@Override
	public void cardSwiped(int memberID) {
		//TODO Not implemented yet.
		_reader.setEnabled(false);
		_scanner.setEnabled(true);
		this.setState(EBorrowState.SCANNING_BOOKS);
	}
	
	
	
	@Override
	public void bookScanned(int barcode) {
		if(_state!=EBorrowState.SCANNING_BOOKS)
			throw new RuntimeException("System not it state: Scanning Books.");
		
		
		
	}

	
	private void setState(EBorrowState state) {
		_state = state;
		_ui.setState(state);
	}

	@Override
	public void cancelled() {
		close();
	}
	
	@Override
	public void scansCompleted() {
		throw new RuntimeException("Not implemented yet");
	}

	@Override
	public void loansConfirmed() {
		throw new RuntimeException("Not implemented yet");
	}

	@Override
	public void loansRejected() {
		throw new RuntimeException("Not implemented yet");
	}

	private String buildLoanListDisplay(List<ILoan> loans) {
		StringBuilder bld = new StringBuilder();
		for (ILoan ln : loans) {
			if (bld.length() > 0) bld.append("\n\n");
			bld.append(ln.toString());
		}
		return bld.toString();		
	}

}

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

public class BorrowUC_CTL implements ICardReaderListener, IScannerListener,
	IBorrowUIListener {

    private ICardReader _reader;
    private IScanner _scanner;
    @SuppressWarnings("unused")
    private IPrinter _printer;
    private IDisplay _display;
    // private String state;
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

    public BorrowUC_CTL(ICardReader reader, IScanner scanner, IPrinter printer,
	    IDisplay display, IBookDAO bookDAO, ILoanDAO loanDAO,
	    IMemberDAO memberDAO) {

	this._display = display;
	this._ui = new BorrowUC_UI(this);

	_reader = reader;
	_scanner = scanner;
	_printer = printer;

	_bookDAO = bookDAO;
	_loanDAO = loanDAO;
	_memberDAO = memberDAO;

	_state = EBorrowState.CREATED;
    }

    public void initialise() {
	previous = _display.getDisplay();
	_display.setDisplay((JPanel) _ui, "Borrow UI");
	_reader.addListener(this);
	_scanner.addListener(this);

	_reader.setEnabled(true);
	this.setState(EBorrowState.INITIALIZED);

	cardSwiped(0);
    }

    public void close() {
	_display.setDisplay(previous, "Main Menu");
	System.out.println("close");
    }

    @Override
    public void cardSwiped(int memberID) {
	// TODO Not implemented yet

	_reader.setEnabled(false);
	_scanner.setEnabled(true);
	_bookList = new ArrayList<>();
	_loanList = new ArrayList<>();
	_borrower = _memberDAO.getMemberByID(2);
	this.setState(EBorrowState.SCANNING_BOOKS);
	_ui.displayMemberDetails(_borrower.getID(), _borrower.getLastName()
		+ ", " + _borrower.getFirstName(), _borrower.getContactPhone());
    }

    @Override
    public void bookScanned(int barcode) {
	if (_state != EBorrowState.SCANNING_BOOKS)
	    throw new RuntimeException("System not in state: Scanning Books.");

	IBook scannedBook = _bookDAO.getBookByID(barcode);
	System.out.println("Scanned for book (Book ID: " + barcode + ")");

	if (scannedBook == null) {
	    // book not found
	    System.out.println("Scanned book not found (Book ID: " + barcode
		    + ")");
	} else {
	    // book in system
	    System.out.println(scannedBook.toString() + " - "
		    + scannedBook.getState().toString());

	    if (scannedBook.getState() != EBookState.AVAILABLE) {
		// book not available
		System.out.println("Scanned book not available.");
	    } else if (_bookList.contains(scannedBook)) {
		// book already scanned
		System.out.println("Book already scanned");
	    } else {
		_loanList.add(_loanDAO.createLoan(_borrower, scannedBook));
		_bookList.add(scannedBook);
		_scanCount++;

		if (_scanCount < IMember.LOAN_LIMIT) {
		    _ui.displayScannedBookDetails(scannedBook.toString());
		    _ui.displayPendingLoan(buildLoanListDisplay(_loanList));
		} else {
		    scansCompleted();
		}

	    }

	}

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

	setState(EBorrowState.CONFIRMING_LOANS);
	_ui.displayConfirmingLoan(buildLoanListDisplay(_loanList));

    }

    @Override
    public void loansConfirmed() {
	// TODO Not implemented yet
	throw new RuntimeException("Not implemented yet");
    }

    @Override
    public void loansRejected() {
	// TODO Not implemented yet
	throw new RuntimeException("Not implemented yet");
    }

    private String buildLoanListDisplay(List<ILoan> loans) {
	StringBuilder bld = new StringBuilder();
	for (ILoan ln : loans) {
	    if (bld.length() > 0)
		bld.append("\n\n");
	    bld.append(ln.toString());
	}
	return bld.toString();
    }

}

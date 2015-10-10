package library.entities;

import java.util.List;

import library.interfaces.entities.EMemberState;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

public class Member implements IMember {

    float _fineAmout;
    String _firstName;
    String _lastName;
    String _contactPhone;
    String _emailAddress;
    int _id;
    ILoan _loanList;
    EMemberState _state;

    public Member(String firstName, String lastName, String contactPhone,
	    String emailAddress, int id) {
	if (firstName == null || firstName == "" || lastName == null
		|| lastName == "" || contactPhone == null || contactPhone == ""
		|| emailAddress == null || emailAddress == "")
	    throw new IllegalArgumentException(
		    "Illegal parameters (name, contact number and/or email address) in book declaration.");
	if (id <= 0)
	    throw new IllegalArgumentException("Member ID must be above 0");

	_firstName = firstName;
	_lastName = lastName;
	_contactPhone = contactPhone;
	_emailAddress = emailAddress;
	_id = id;

	_state = EMemberState.BORROWING_ALLOWED;
    }

    @Override
    public boolean hasOverDueLoans() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean hasReachedLoanLimit() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean hasFinesPayable() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean hasReachedFineLimit() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public float getFineAmount() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public void addFine(float fine) {
	// TODO Auto-generated method stub

    }

    @Override
    public void payFine(float payment) {
	// TODO Auto-generated method stub

    }

    @Override
    public void addLoan(ILoan loan) {
	// TODO Auto-generated method stub

    }

    @Override
    public List<ILoan> getLoans() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void removeLoan(ILoan loan) {
	// TODO Auto-generated method stub

    }

    @Override
    public EMemberState getState() {
	return _state;
    }

    @Override
    public String getFirstName() {
	return _firstName;
    }

    @Override
    public String getLastName() {
	return _lastName;
    }

    @Override
    public String getContactPhone() {
	return _contactPhone;
    }

    @Override
    public String getEmailAddress() {
	return _emailAddress;
    }

    @Override
    public int getID() {
	return _id;
    }

    @Override
    public String toString() {
	return "MID#" + _id + " " + _lastName + ", " + _firstName;
    }

}

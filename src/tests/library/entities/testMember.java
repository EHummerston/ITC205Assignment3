package tests.library.entities;

import static org.junit.Assert.*;
import library.entities.Member;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testMember {

    String _firstName;
    String _lastName;
    String _contactPhone;
    String _emailAddress;
    int _id;

    @Before
    public void setUp() throws Exception {
	_firstName = "FName";
	_lastName = "LName";
	_contactPhone = "Contact#";
	_emailAddress = "EmailAddress";
	_id = 1;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void constructor_AllParametersLegal_NoExceptions() {
	Member member = new Member(_firstName, _lastName, _contactPhone,
		_emailAddress, _id);
	assertTrue(member instanceof Member);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_NullFirstName_IllegalArgumentException() {
	Member member = new Member(null, _lastName, _contactPhone,
		_emailAddress, _id);
	assertTrue(member instanceof Member);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_BlankFirstName_IllegalArgumentException() {
	Member member = new Member("", _lastName, _contactPhone, _emailAddress,
		_id);
	assertTrue(member instanceof Member);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_NullLastName_IllegalArgumentException() {
	Member member = new Member(_firstName, null, _contactPhone,
		_emailAddress, _id);
	assertTrue(member instanceof Member);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_BlankLastName_IllegalArgumentException() {
	Member member = new Member(_firstName, "", _contactPhone,
		_emailAddress, _id);
	assertTrue(member instanceof Member);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_NullContactPhone_IllegalArgumentException() {
	Member member = new Member(_firstName, _lastName, null, _emailAddress,
		_id);
	assertTrue(member instanceof Member);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_BlankContactPhone_IllegalArgumentException() {
	Member member = new Member(_firstName, _lastName, "", _emailAddress,
		_id);
	assertTrue(member instanceof Member);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_NullEmailAddress_IllegalArgumentException() {
	Member member = new Member(_firstName, _lastName, _contactPhone, null,
		_id);
	assertTrue(member instanceof Member);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_BlankEmailAddress_IllegalArgumentException() {
	Member member = new Member(_firstName, _lastName, _contactPhone, "",
		_id);
	assertTrue(member instanceof Member);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_NegativeId_IllegalArgumentException() {
	Member member = new Member(_firstName, _lastName, _contactPhone,
		_emailAddress, -1);
	assertTrue(member instanceof Member);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_IdZero_IllegalArgumentException() {
	Member member = new Member(_firstName, _lastName, _contactPhone,
		_emailAddress, 0);
	assertTrue(member instanceof Member);
    }

}

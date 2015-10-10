package tests.library.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import library.daos.MemberMapDAO;
import library.interfaces.daos.IMemberHelper;
import library.interfaces.entities.IBook;
import library.interfaces.entities.IMember;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class testMemberDAO {

    MemberMapDAO _memberDao;
    IMemberHelper _helper;
    IMember _member;

    String _firstName;
    String _lastName;
    String _contactPhone;
    String _emailAddress;

    @Before
    public void setUp() throws Exception {
	_helper = mock(IMemberHelper.class);
	_memberDao = new MemberMapDAO(_helper);
	_member = mock(IMember.class);

	_firstName = "FName";
	_lastName = "LName";
	_contactPhone = "Contact#";
	_emailAddress = "EmailAddress";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void constructor_AllParametersLegal_NoExceptions() {
	MemberMapDAO memberDao = new MemberMapDAO(mock(IMemberHelper.class));
	assertTrue(memberDao instanceof MemberMapDAO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_NullHelper_IllegalArgumentException() {
	MemberMapDAO memberDao = new MemberMapDAO(null);
	assertTrue(memberDao instanceof MemberMapDAO);
    }

    @Test
    public void addMember_NormalFlow_MemberIdEqualsMapKey() {

	when(
		_helper.makeMember(eq(_firstName), eq(_lastName),
			eq(_contactPhone), eq(_emailAddress), anyInt()))
		.thenReturn(_member);
	ArgumentCaptor<Integer> argument = ArgumentCaptor
		.forClass(Integer.class);

	_memberDao.addMember(_firstName, _lastName, _contactPhone,
		_emailAddress);
	verify(_helper).makeMember(any(String.class), any(String.class),
		any(String.class), any(String.class), argument.capture());

	assertEquals(_member, _memberDao.getMemberByID(argument.getValue()));
    }

    @Test
    public void getMemberByID_MemberNotFound_ReturnNull() {
	verifyZeroInteractions(_helper);
	assertEquals(_memberDao.getMemberByID(0), (IBook) null);
    }
}

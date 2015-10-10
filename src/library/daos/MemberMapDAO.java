package library.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library.interfaces.daos.IMemberDAO;
import library.interfaces.daos.IMemberHelper;
import library.interfaces.entities.IBook;
import library.interfaces.entities.IMember;

public class MemberMapDAO implements IMemberDAO {

	Map<Integer, IMember> _memberMap;
	int _nextId;
	IMemberHelper _helper;
	
	public MemberMapDAO (IMemberHelper helper)
	{
		if(helper == null)
		throw new IllegalArgumentException ("Member Data Access Helper cannot be null.");
	
	_helper = helper;
	_memberMap = new HashMap<>();
	_nextId = 1;
	}
	
	@Override
	public IMember addMember(String firstName, String lastName,
			String ContactPhone, String emailAddress) {
		
		IMember newMember = _helper.makeMember(firstName, lastName, ContactPhone, emailAddress, _nextId);
		_memberMap.put(_nextId, newMember);
		_nextId++;
		
		return newMember;
	}

	@Override
	public IMember getMemberByID(int id) {
		return _memberMap.get(id);
	}

	@Override
	public List<IMember> listMembers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IMember> findMembersByLastName(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IMember> findMembersByEmailAddress(String emailAddress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IMember> findMembersByNames(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

}

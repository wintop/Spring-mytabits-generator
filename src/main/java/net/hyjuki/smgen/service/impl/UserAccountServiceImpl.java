package net.hyjuki.smgen.service.impl;

import net.hyjuki.smgen.base.dao.BaseDao;
import net.hyjuki.smgen.base.service.GenericService;
import net.hyjuki.smgen.dao.UserAccountDao;
import net.hyjuki.smgen.model.UserAccount;
import net.hyjuki.smgen.service.UserAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl extends GenericService<UserAccount> implements UserAccountService {
	@Autowired
	private UserAccountDao userAccountDao;

	@Override
	public BaseDao getBaseDao() {
		return userAccountDao;
	}

	@Override
	public UserAccount login(String username) {
		return userAccountDao.getByUserName(username);
	}
}
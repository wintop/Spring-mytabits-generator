package net.hyjuki.smgen.service;

import net.hyjuki.smgen.base.service.BaseService;
import net.hyjuki.smgen.model.UserAccount;

public interface UserAccountService extends BaseService<UserAccount> {
    UserAccount login(String username);
}
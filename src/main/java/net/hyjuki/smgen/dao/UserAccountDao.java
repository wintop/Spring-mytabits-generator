package net.hyjuki.smgen.dao;

import net.hyjuki.smgen.base.dao.BaseDao;
import net.hyjuki.smgen.model.UserAccount;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAccountDao extends BaseDao<UserAccount> {
    UserAccount getByUserName(String username);
}
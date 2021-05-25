package net.hyjuki.smgen.service.impl;

import net.hyjuki.smgen.base.dao.BaseDao;
import net.hyjuki.smgen.base.service.GenericService;
import net.hyjuki.smgen.base.utils.DbUtils;
import net.hyjuki.smgen.dao.DataBaseDao;
import net.hyjuki.smgen.db.DbInformation;
import net.hyjuki.smgen.model.DataBase;
import net.hyjuki.smgen.service.DataBaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataBaseServiceImpl extends GenericService<DataBase> implements DataBaseService {
	@Autowired
	private DataBaseDao dataBaseDao;

	@Override
	public BaseDao getBaseDao() {
		return dataBaseDao;
	}

	@Override
	public List<String> getDatabases(DataBase database) throws SQLException {
		DbInformation dbMySQLInfomation = DbUtils.getDbMySQLInfomation(database);

		return dbMySQLInfomation.getCatalogs();
	}
}
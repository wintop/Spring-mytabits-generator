package net.hyjuki.smgen.service.impl;

import net.hyjuki.smgen.base.dao.BaseDao;
import net.hyjuki.smgen.base.service.GenericService;
import net.hyjuki.smgen.dao.TableInfoDao;
import net.hyjuki.smgen.model.TableInfo;
import net.hyjuki.smgen.service.TableInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableInfoServiceImpl extends GenericService<TableInfo> implements TableInfoService {
	@Autowired
	private TableInfoDao tableInfoDao;

	@Override
	public BaseDao getBaseDao() {
		return tableInfoDao;
	}
}
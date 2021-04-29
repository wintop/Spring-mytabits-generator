package net.hyjuki.smgen.genconfig.service.impl;

import net.hyjuki.smgen.genconfig.base.dao.BaseDao;
import net.hyjuki.smgen.genconfig.base.service.GenericService;
import net.hyjuki.smgen.genconfig.dao.TableInfoDao;
import net.hyjuki.smgen.genconfig.model.TableInfo;
import net.hyjuki.smgen.genconfig.service.TableInfoService;

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
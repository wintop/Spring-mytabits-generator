package net.hyjuki.smgen.service.impl;

import net.hyjuki.smgen.base.dao.BaseDao;
import net.hyjuki.smgen.base.service.GenericService;
import net.hyjuki.smgen.dao.TableIndexDao;
import net.hyjuki.smgen.model.TableIndex;
import net.hyjuki.smgen.service.TableIndexService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableIndexServiceImpl extends GenericService<TableIndex> implements TableIndexService {
	@Autowired
	private TableIndexDao tableIndexDao;

	@Override
	public BaseDao getBaseDao() {
		return tableIndexDao;
	}
}
package net.hyjuki.smgen.genconfig.service.impl;

import net.hyjuki.smgen.genconfig.base.dao.BaseDao;
import net.hyjuki.smgen.genconfig.base.service.GenericService;
import net.hyjuki.smgen.genconfig.dao.TableIndexDao;
import net.hyjuki.smgen.genconfig.model.TableIndex;
import net.hyjuki.smgen.genconfig.service.TableIndexService;

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
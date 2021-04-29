package net.hyjuki.smgen.genconfig.service.impl;

import net.hyjuki.smgen.genconfig.base.dao.BaseDao;
import net.hyjuki.smgen.genconfig.base.service.GenericService;
import net.hyjuki.smgen.genconfig.dao.TableColumnDao;
import net.hyjuki.smgen.genconfig.model.TableColumn;
import net.hyjuki.smgen.genconfig.service.TableColumnService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableColumnServiceImpl extends GenericService<TableColumn> implements TableColumnService {
	@Autowired
	private TableColumnDao tableColumnDao;

	@Override
	public BaseDao getBaseDao() {
		return tableColumnDao;
	}
}
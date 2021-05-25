package net.hyjuki.smgen.service.impl;

import net.hyjuki.smgen.base.dao.BaseDao;
import net.hyjuki.smgen.base.service.GenericService;
import net.hyjuki.smgen.dao.TableColumnDao;
import net.hyjuki.smgen.model.TableColumn;
import net.hyjuki.smgen.service.TableColumnService;

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
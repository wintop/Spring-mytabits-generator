package net.hyjuki.smgen.genconfig.service.impl;

import net.hyjuki.smgen.genconfig.base.dao.BaseDao;
import net.hyjuki.smgen.genconfig.base.service.GenericService;
import net.hyjuki.smgen.genconfig.dao.TableFunctionDao;
import net.hyjuki.smgen.genconfig.model.TableFunction;
import net.hyjuki.smgen.genconfig.service.TableFunctionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableFunctionServiceImpl extends GenericService<TableFunction> implements TableFunctionService {
	@Autowired
	private TableFunctionDao tableFunctionDao;

	@Override
	public BaseDao getBaseDao() {
		return tableFunctionDao;
	}
}
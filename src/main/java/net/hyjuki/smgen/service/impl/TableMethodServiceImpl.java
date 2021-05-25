package net.hyjuki.smgen.service.impl;

import net.hyjuki.smgen.base.dao.BaseDao;
import net.hyjuki.smgen.base.service.GenericService;
import net.hyjuki.smgen.dao.TableMethodDao;
import net.hyjuki.smgen.model.TableMethod;
import net.hyjuki.smgen.service.TableMethodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableMethodServiceImpl extends GenericService<TableMethod> implements TableMethodService {
	@Autowired
	private TableMethodDao tableMethodDao;

	@Override
	public BaseDao getBaseDao() {
		return tableMethodDao;
	}
}
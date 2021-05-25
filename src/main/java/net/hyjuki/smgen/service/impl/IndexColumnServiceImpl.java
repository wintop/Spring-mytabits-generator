package net.hyjuki.smgen.service.impl;

import net.hyjuki.smgen.base.dao.BaseDao;
import net.hyjuki.smgen.base.service.GenericService;
import net.hyjuki.smgen.dao.IndexColumnDao;
import net.hyjuki.smgen.model.IndexColumn;
import net.hyjuki.smgen.service.IndexColumnService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexColumnServiceImpl extends GenericService<IndexColumn> implements IndexColumnService {
	@Autowired
	private IndexColumnDao indexColumnDao;

	@Override
	public BaseDao getBaseDao() {
		return indexColumnDao;
	}
}
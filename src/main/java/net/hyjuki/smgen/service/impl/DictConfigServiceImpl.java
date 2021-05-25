package net.hyjuki.smgen.service.impl;

import net.hyjuki.smgen.base.dao.BaseDao;
import net.hyjuki.smgen.base.service.GenericService;
import net.hyjuki.smgen.dao.DictConfigDao;
import net.hyjuki.smgen.model.DictConfig;
import net.hyjuki.smgen.service.DictConfigService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictConfigServiceImpl extends GenericService<DictConfig> implements DictConfigService {
	@Autowired
	private DictConfigDao dictConfigDao;

	@Override
	public BaseDao getBaseDao() {
		return dictConfigDao;
	}
}
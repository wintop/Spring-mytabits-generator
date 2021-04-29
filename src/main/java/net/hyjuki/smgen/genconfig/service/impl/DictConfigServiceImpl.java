package net.hyjuki.smgen.genconfig.service.impl;

import net.hyjuki.smgen.genconfig.base.dao.BaseDao;
import net.hyjuki.smgen.genconfig.base.service.GenericService;
import net.hyjuki.smgen.genconfig.model.DictConfig;
import net.hyjuki.smgen.genconfig.service.DictConfigService;
import net.hyjuki.smgen.smgen.genconfig.dao.DictConfigDao;

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
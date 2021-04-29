package net.hyjuki.smgen.genconfig.service.impl;

import net.hyjuki.smgen.genconfig.base.dao.BaseDao;
import net.hyjuki.smgen.genconfig.base.service.GenericService;
import net.hyjuki.smgen.genconfig.dao.DictInfoDao;
import net.hyjuki.smgen.genconfig.model.DictInfo;
import net.hyjuki.smgen.genconfig.service.DictInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictInfoServiceImpl extends GenericService<DictInfo> implements DictInfoService {
	@Autowired
	private DictInfoDao dictInfoDao;

	@Override
	public BaseDao getBaseDao() {
		return dictInfoDao;
	}
}
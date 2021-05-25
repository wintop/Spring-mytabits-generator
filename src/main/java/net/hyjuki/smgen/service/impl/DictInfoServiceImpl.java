package net.hyjuki.smgen.service.impl;

import net.hyjuki.smgen.base.dao.BaseDao;
import net.hyjuki.smgen.base.service.GenericService;
import net.hyjuki.smgen.dao.DictInfoDao;
import net.hyjuki.smgen.model.DictInfo;
import net.hyjuki.smgen.service.DictInfoService;

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
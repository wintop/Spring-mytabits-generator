package net.hyjuki.smgen.genconfig.base.service;

import java.io.Serializable;
import java.util.List;

import net.hyjuki.smgen.genconfig.base.dao.BaseDao;

public abstract class GenericService<T> implements BaseService<T> {
	public abstract BaseDao<T> getBaseDao();

	@Override
	public T get(Serializable id) {
		return getBaseDao().get(id);
	}

	@Override
	public List<T> find(T t) {
		return getBaseDao().find(t);
	}

	@Override
	public int save(T t) {
		return getBaseDao().save(t);
	}

	@Override
	public int update(T t) {
		return getBaseDao().update(t);
	}

	@Override
	public int remove(Serializable id) {
		return getBaseDao().remove(id);
	}
}
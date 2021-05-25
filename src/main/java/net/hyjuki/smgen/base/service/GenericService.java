package net.hyjuki.smgen.base.service;

import java.io.Serializable;
import java.util.List;

import net.hyjuki.smgen.base.dao.BaseDao;
import net.hyjuki.smgen.base.common.Pageable;

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

	@Override
	public List<T> pagination(T t, Pageable page) {
		return getBaseDao().pagination(t, page);
	}

	@Override
	public long total(T t) {
		return getBaseDao().total(t);
	}
}
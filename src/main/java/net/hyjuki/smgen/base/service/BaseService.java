package net.hyjuki.smgen.base.service;

import net.hyjuki.smgen.base.common.Pageable;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T> {
	T get(Serializable id);
	List<T> find(T t);
	int save(T t);
	int update(T t);
	int remove(Serializable id);
	List<T> pagination(T t, Pageable page);
	long total(T t);
}
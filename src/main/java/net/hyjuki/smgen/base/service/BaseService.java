package net.hyjuki.smgen.base.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T> {
	T get(Serializable id);
	List<T> find(T t);
	int save(T t);
	int update(T t);
	int remove(Serializable id);
	List<T> pagination(T t);
	long total(T t);
}
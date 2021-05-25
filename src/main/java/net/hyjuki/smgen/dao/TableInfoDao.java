package net.hyjuki.smgen.dao;

import net.hyjuki.smgen.base.dao.BaseDao;
import net.hyjuki.smgen.model.TableInfo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TableInfoDao extends BaseDao<TableInfo> {
}
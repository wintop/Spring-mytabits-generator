package net.hyjuki.smgen.genconfig.dao;

import net.hyjuki.smgen.genconfig.base.dao.BaseDao;
import net.hyjuki.smgen.genconfig.model.TableInfo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TableInfoDao extends BaseDao<TableInfo> {
}
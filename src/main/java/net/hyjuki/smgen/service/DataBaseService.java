package net.hyjuki.smgen.service;

import net.hyjuki.smgen.base.service.BaseService;
import net.hyjuki.smgen.db.DbInformation;
import net.hyjuki.smgen.model.DataBase;

import java.sql.SQLException;
import java.util.List;

public interface DataBaseService extends BaseService<DataBase> {
    List<String> getDatabases(DataBase database) throws SQLException;
}
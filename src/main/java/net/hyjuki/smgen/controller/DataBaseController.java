package net.hyjuki.smgen.controller;

import net.hyjuki.smgen.base.utils.DbUtils;
import net.hyjuki.smgen.base.common.HjkResponse;
import net.hyjuki.smgen.base.common.MessageData;
import net.hyjuki.smgen.base.utils.WebUtils;
import net.hyjuki.smgen.db.DbInformation;
import net.hyjuki.smgen.db.Table;
import net.hyjuki.smgen.model.DataBase;
import net.hyjuki.smgen.model.TableInfo;
import net.hyjuki.smgen.model.UserAccount;
import net.hyjuki.smgen.service.DataBaseService;

import net.hyjuki.smgen.service.TableInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping("data_base/")
public class DataBaseController {
	private static final Logger logger = LoggerFactory.getLogger(DataBaseController.class);
	@Autowired
	private DataBaseService dataBaseService;
	@Autowired
	private TableInfoService tableInfoService;

	@RequestMapping("get")
	public HjkResponse getDataBase(Integer id) {
		logger.info("====getDataBase(get), id: {}", id);

		if (id == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(dataBaseService.get(id));
	}

	@RequestMapping("list")
	public HjkResponse getDataBaseList(DataBase dataBase) {
		logger.info("====getDataBaseList(list), dataBase: {}", dataBase);

		if (dataBase == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(dataBaseService.find(dataBase));
	}

	@RequestMapping("add")
	public HjkResponse addDataBase(@RequestBody DataBase dataBase) {
		logger.info("====addDataBase(add), dataBase: {}", dataBase);

		if (dataBase == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}

		UserAccount userInfo = WebUtils.getUserInfo();
		dataBase.setUserId(userInfo.getId());

		return HjkResponse.success(dataBaseService.save(dataBase));
	}

	@RequestMapping("edit")
	public HjkResponse editDataBase(@RequestBody DataBase dataBase) {
		logger.info("====editDataBase(edit), dataBase: {}", dataBase);

		if (dataBase == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(dataBaseService.update(dataBase));
	}

	@RequestMapping("get_dbs")
	public HjkResponse getOtherDatabases(@RequestBody DataBase dataBase) throws SQLException {
		logger.info("====getOtherDatabases(get_dbs), dataBase: {}", dataBase);

		if (dataBase == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}

		return HjkResponse.success(dataBaseService.getDatabases(dataBase));
	}

	@RequestMapping("tables")
	public HjkResponse getTables(@RequestBody DataBase dataBase) throws SQLException {
		logger.info("====getTables(tables), database: {}", dataBase);
		DbInformation dbInformation = DbUtils.getDbMySQLInfomation(dataBase);
		System.out.println("dbInfomation:   " + dbInformation.getConnection());
		List<Table> tableList = dbInformation.getTableList();
		List<TableInfo> tableInfos = new ArrayList<>();
		for (Table table: tableList) {
			TableInfo tableInfo = new TableInfo();
			tableInfo.setName(table.getTableName());
			tableInfo.setDbName(table.getTableCat());
			tableInfo.setComment(table.getRemarks());
			tableInfo.setDbId(dataBase.getId());
			tableInfos.add(tableInfo);
		}
		return HjkResponse.success(tableInfos);
	}

	@RequestMapping("copy_table")
	public HjkResponse copyDbTables(@RequestBody List<TableInfo> tableInfos) throws SQLException {
		logger.info("====getTables(tables), tableInfos: {}", Arrays.toString(tableInfos.toArray()));
		UserAccount userInfo = WebUtils.getUserInfo();
		for (TableInfo table: tableInfos) {
			table.setUserId(userInfo.getId());
			tableInfoService.save(table);
		}
		return HjkResponse.success();
	}
}
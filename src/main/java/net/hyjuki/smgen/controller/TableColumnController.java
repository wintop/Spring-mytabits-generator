package net.hyjuki.smgen.controller;

import net.hyjuki.smgen.base.utils.DbUtils;
import net.hyjuki.smgen.base.common.HjkResponse;
import net.hyjuki.smgen.base.common.MessageData;
import net.hyjuki.smgen.db.DbInformation;
import net.hyjuki.smgen.model.DataBase;
import net.hyjuki.smgen.model.TableColumn;
import net.hyjuki.smgen.model.TableInfo;
import net.hyjuki.smgen.service.DataBaseService;
import net.hyjuki.smgen.service.TableColumnService;

import net.hyjuki.smgen.service.TableInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("table_column/")
public class TableColumnController {
	private static final Logger logger = LoggerFactory.getLogger(TableColumnController.class);
	@Autowired
	private TableColumnService tableColumnService;
	@Autowired
	private TableInfoService tableInfoService;
	@Autowired
	private DataBaseService dataBaseService;

	@RequestMapping("get")
	public HjkResponse getTableColumn(Integer id) {
		logger.info("====getTableColumn(get), id: {}", id);

		if (id == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(tableColumnService.get(id));
	}

	@RequestMapping("list")
	public HjkResponse getTableColumnList(Integer tableId) {
		logger.info("====getTableColumnList(list), tableId: {}", tableId);

		if (tableId == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		TableColumn column = new TableColumn();
		column.setTableId(tableId);

		return HjkResponse.success(tableColumnService.find(column));
	}

	@RequestMapping("add")
	public HjkResponse addTableColumn(@RequestBody TableColumn tableColumn) {
		logger.info("====addTableColumn(add), tableColumn: {}", tableColumn);

		if (tableColumn == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(tableColumnService.save(tableColumn));
	}

	@RequestMapping("edit")
	public HjkResponse editTableColumn(@RequestBody TableColumn tableColumn) {
		logger.info("====editTableColumn(edit), tableColumn: {}", tableColumn);

		if (tableColumn == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(tableColumnService.update(tableColumn));
	}

	/**
	 * 根据表获取表的名称，DbInfomation中获取表的字段信息
	 * @param tableId 表ID
	 * @return
	 */
	@RequestMapping("get_db_column")
	public HjkResponse getDbTableColumn(Integer tableId) {
		logger.info("====getDbTableColumn(get_db_column), tableId: {}", tableId);
		TableInfo tableInfo = tableInfoService.get(tableId);
		System.out.println(tableInfo);
		DataBase dataBase = dataBaseService.get(tableInfo.getDbId());
		DbInformation dbMySQLInfomation = DbUtils.getDbMySQLInfomation(dataBase);
		return HjkResponse.success(dbMySQLInfomation.getColumns(tableInfo.getName()));
	}

	@RequestMapping("copy_column")
	public HjkResponse copyColumnToTable(@RequestBody List<TableColumn> columnList) {
		logger.info("====copyColumnToTable(copy_column), columnList: {}", columnList);


		return HjkResponse.success();
	}
}
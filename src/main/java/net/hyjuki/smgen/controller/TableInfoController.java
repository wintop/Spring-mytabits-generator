package net.hyjuki.smgen.controller;

import net.hyjuki.smgen.base.common.HjkResponse;
import net.hyjuki.smgen.base.common.MessageData;
import net.hyjuki.smgen.base.common.PageResult;
import net.hyjuki.smgen.base.common.Pageable;
import net.hyjuki.smgen.model.TableInfo;
import net.hyjuki.smgen.service.TableInfoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("table_info/")
public class TableInfoController {
	private static final Logger logger = LoggerFactory.getLogger(TableInfoController.class);
	@Autowired
	private TableInfoService tableInfoService;

	@RequestMapping("get")
	public HjkResponse getTableInfo(Integer id) {
		logger.info("====getTableInfo(get), id: {}", id);

		if (id == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(tableInfoService.get(id));
	}

	@RequestMapping("list")
	public HjkResponse getTableInfoList() {
		logger.info("====getTableInfoList(list)");

		return HjkResponse.success(tableInfoService.find(null));
	}

	@RequestMapping("page")
	public HjkResponse getPagination(Integer pageNo, Integer pageSize) {
		logger.info("====getTableInfoList(list)");

		List<TableInfo> tableList = tableInfoService.pagination(new TableInfo(), Pageable.of(pageNo, pageSize));
		long total = tableInfoService.total(null);
		return HjkResponse.success(new PageResult(tableList, total));
	}

	@RequestMapping("add")
	public HjkResponse addTableInfo(TableInfo tableInfo) {
		logger.info("====addTableInfo(add), tableInfo: {}", tableInfo);

		if (tableInfo == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(tableInfoService.save(tableInfo));
	}

	@RequestMapping("edit")
	public HjkResponse editTableInfo(@RequestBody TableInfo tableInfo) {
		logger.info("====editTableInfo(edit), tableInfo: {}", tableInfo);

		if (tableInfo == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(tableInfoService.update(tableInfo));
	}
}
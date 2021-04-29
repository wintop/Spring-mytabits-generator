package net.hyjuki.smgen.genconfig.controller;

import net.hyjuki.smgen.genconfig.base.utils.HjkResponse;
import net.hyjuki.smgen.genconfig.base.utils.MessageData;
import net.hyjuki.smgen.genconfig.model.TableInfo;
import net.hyjuki.smgen.genconfig.service.TableInfoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("table/")
public class TableInfoController {
	private static final Logger logger = LoggerFactory.getLogger(TableInfoController.class);
	@Autowired
	private TableInfoService tableInfoService;

	@RequestMapping("get")
	public HjkResponse getTableInfo(Long id) {
		logger.info("====getTableInfo(get), id: {}", id);

		if (id == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(tableInfoService.get(id));
	}

	@RequestMapping("list")
	public HjkResponse getTableInfoList(@RequestBody TableInfo tableInfo) {
		logger.info("====getTableInfoList(list), tableInfo: {}", tableInfo);

		if (tableInfo == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(tableInfoService.find(tableInfo));
	}

	@RequestMapping("add")
	public HjkResponse addTableInfo(@RequestBody TableInfo tableInfo) {
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
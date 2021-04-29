package net.hyjuki.smgen.genconfig.controller;

import net.hyjuki.smgen.genconfig.base.utils.HjkResponse;
import net.hyjuki.smgen.genconfig.base.utils.MessageData;
import net.hyjuki.smgen.genconfig.model.TableIndex;
import net.hyjuki.smgen.genconfig.service.TableIndexService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tableIndex/")
public class TableIndexController {
	private static final Logger logger = LoggerFactory.getLogger(TableIndexController.class);
	@Autowired
	private TableIndexService tableIndexService;

	@RequestMapping("get")
	public HjkResponse getTableIndex(Long id) {
		logger.info("====getTableIndex(get), id: {}", id);

		if (id == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(tableIndexService.get(id));
	}

	@RequestMapping("list")
	public HjkResponse getTableIndexList(@RequestBody TableIndex tableIndex) {
		logger.info("====getTableIndexList(list), tableIndex: {}", tableIndex);

		if (tableIndex == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(tableIndexService.find(tableIndex));
	}

	@RequestMapping("add")
	public HjkResponse addTableIndex(@RequestBody TableIndex tableIndex) {
		logger.info("====addTableIndex(add), tableIndex: {}", tableIndex);

		if (tableIndex == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(tableIndexService.save(tableIndex));
	}

	@RequestMapping("edit")
	public HjkResponse editTableIndex(@RequestBody TableIndex tableIndex) {
		logger.info("====editTableIndex(edit), tableIndex: {}", tableIndex);

		if (tableIndex == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(tableIndexService.update(tableIndex));
	}
}
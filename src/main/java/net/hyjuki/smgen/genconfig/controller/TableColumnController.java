package net.hyjuki.smgen.genconfig.controller;

import net.hyjuki.smgen.genconfig.base.utils.HjkResponse;
import net.hyjuki.smgen.genconfig.base.utils.MessageData;
import net.hyjuki.smgen.genconfig.model.TableColumn;
import net.hyjuki.smgen.genconfig.service.TableColumnService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tableColumn/")
public class TableColumnController {
	private static final Logger logger = LoggerFactory.getLogger(TableColumnController.class);
	@Autowired
	private TableColumnService tableColumnService;

	@RequestMapping("get")
	public HjkResponse getTableColumn(Long id) {
		logger.info("====getTableColumn(get), id: {}", id);

		if (id == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(tableColumnService.get(id));
	}

	@RequestMapping("list")
	public HjkResponse getTableColumnList(@RequestBody TableColumn tableColumn) {
		logger.info("====getTableColumnList(list), tableColumn: {}", tableColumn);

		if (tableColumn == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(tableColumnService.find(tableColumn));
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
}
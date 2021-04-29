package net.hyjuki.smgen.genconfig.controller;

import net.hyjuki.smgen.genconfig.base.utils.HjkResponse;
import net.hyjuki.smgen.genconfig.base.utils.MessageData;
import net.hyjuki.smgen.genconfig.model.TableFunction;
import net.hyjuki.smgen.genconfig.service.TableFunctionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tableFunction/")
public class TableFunctionController {
	private static final Logger logger = LoggerFactory.getLogger(TableFunctionController.class);
	@Autowired
	private TableFunctionService tableFunctionService;

	@RequestMapping("get")
	public HjkResponse getTableFunction(Long id) {
		logger.info("====getTableFunction(get), id: {}", id);

		if (id == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(tableFunctionService.get(id));
	}

	@RequestMapping("list")
	public HjkResponse getTableFunctionList(@RequestBody TableFunction tableFunction) {
		logger.info("====getTableFunctionList(list), tableFunction: {}", tableFunction);

		if (tableFunction == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(tableFunctionService.find(tableFunction));
	}

	@RequestMapping("add")
	public HjkResponse addTableFunction(@RequestBody TableFunction tableFunction) {
		logger.info("====addTableFunction(add), tableFunction: {}", tableFunction);

		if (tableFunction == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(tableFunctionService.save(tableFunction));
	}

	@RequestMapping("edit")
	public HjkResponse editTableFunction(@RequestBody TableFunction tableFunction) {
		logger.info("====editTableFunction(edit), tableFunction: {}", tableFunction);

		if (tableFunction == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(tableFunctionService.update(tableFunction));
	}
}
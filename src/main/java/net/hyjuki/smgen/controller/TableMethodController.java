package net.hyjuki.smgen.controller;

import net.hyjuki.smgen.base.common.HjkResponse;
import net.hyjuki.smgen.base.common.MessageData;
import net.hyjuki.smgen.model.TableMethod;
import net.hyjuki.smgen.service.TableMethodService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tableMethod/")
public class TableMethodController {
	private static final Logger logger = LoggerFactory.getLogger(TableMethodController.class);
	@Autowired
	private TableMethodService tableMethodService;

	@RequestMapping("get")
	public HjkResponse getTableMethod(Integer id) {
		logger.info("====getTableMethod(get), id: {}", id);

		if (id == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(tableMethodService.get(id));
	}

	@RequestMapping("list")
	public HjkResponse getTableMethodList(@RequestBody TableMethod tableMethod) {
		logger.info("====getTableMethodList(list), tableMethod: {}", tableMethod);

		if (tableMethod == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(tableMethodService.find(tableMethod));
	}

	@RequestMapping("add")
	public HjkResponse addTableMethod(@RequestBody TableMethod tableMethod) {
		logger.info("====addTableMethod(add), tableMethod: {}", tableMethod);

		if (tableMethod == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(tableMethodService.save(tableMethod));
	}

	@RequestMapping("edit")
	public HjkResponse editTableMethod(@RequestBody TableMethod tableMethod) {
		logger.info("====editTableMethod(edit), tableMethod: {}", tableMethod);

		if (tableMethod == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(tableMethodService.update(tableMethod));
	}

}
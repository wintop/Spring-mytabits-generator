package net.hyjuki.smgen.genconfig.controller;

import net.hyjuki.smgen.genconfig.base.utils.HjkResponse;
import net.hyjuki.smgen.genconfig.base.utils.MessageData;
import net.hyjuki.smgen.genconfig.model.DictConfig;
import net.hyjuki.smgen.genconfig.service.DictConfigService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dictConfig/")
public class DictConfigController {
	private static final Logger logger = LoggerFactory.getLogger(DictConfigController.class);
	@Autowired
	private DictConfigService dictConfigService;

	@RequestMapping("get")
	public HjkResponse getDictConfig(Long id) {
		logger.info("====getDictConfig(get), id: {}", id);

		if (id == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(dictConfigService.get(id));
	}

	@RequestMapping("list")
	public HjkResponse getDictConfigList(@RequestBody DictConfig dictConfig) {
		logger.info("====getDictConfigList(list), dictConfig: {}", dictConfig);

		if (dictConfig == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(dictConfigService.find(dictConfig));
	}

	@RequestMapping("add")
	public HjkResponse addDictConfig(@RequestBody DictConfig dictConfig) {
		logger.info("====addDictConfig(add), dictConfig: {}", dictConfig);

		if (dictConfig == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(dictConfigService.save(dictConfig));
	}

	@RequestMapping("edit")
	public HjkResponse editDictConfig(@RequestBody DictConfig dictConfig) {
		logger.info("====editDictConfig(edit), dictConfig: {}", dictConfig);

		if (dictConfig == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(dictConfigService.update(dictConfig));
	}
}
package net.hyjuki.smgen.genconfig.controller;

import net.hyjuki.smgen.genconfig.base.utils.HjkResponse;
import net.hyjuki.smgen.genconfig.base.utils.MessageData;
import net.hyjuki.smgen.genconfig.model.DictInfo;
import net.hyjuki.smgen.genconfig.service.DictInfoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dictInfo/")
public class DictInfoController {
	private static final Logger logger = LoggerFactory.getLogger(DictInfoController.class);
	@Autowired
	private DictInfoService dictInfoService;

	@RequestMapping("get")
	public HjkResponse getDictInfo(Long id) {
		logger.info("====getDictInfo(get), id: {}", id);

		if (id == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(dictInfoService.get(id));
	}

	@RequestMapping("list")
	public HjkResponse getDictInfoList(@RequestBody DictInfo dictInfo) {
		logger.info("====getDictInfoList(list), dictInfo: {}", dictInfo);

		if (dictInfo == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(dictInfoService.find(dictInfo));
	}

	@RequestMapping("add")
	public HjkResponse addDictInfo(@RequestBody DictInfo dictInfo) {
		logger.info("====addDictInfo(add), dictInfo: {}", dictInfo);

		if (dictInfo == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(dictInfoService.save(dictInfo));
	}

	@RequestMapping("edit")
	public HjkResponse editDictInfo(@RequestBody DictInfo dictInfo) {
		logger.info("====editDictInfo(edit), dictInfo: {}", dictInfo);

		if (dictInfo == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(dictInfoService.update(dictInfo));
	}

}
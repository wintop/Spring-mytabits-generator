package net.hyjuki.smgen.controller;

import net.hyjuki.smgen.base.common.HjkResponse;
import net.hyjuki.smgen.base.common.MessageData;
import net.hyjuki.smgen.model.IndexColumn;
import net.hyjuki.smgen.service.IndexColumnService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("indexColumn/")
public class IndexColumnController {
	private static final Logger logger = LoggerFactory.getLogger(IndexColumnController.class);
	@Autowired
	private IndexColumnService indexColumnService;

	@RequestMapping("get")
	public HjkResponse getIndexColumn(Integer id) {
		logger.info("====getIndexColumn(get), id: {}", id);

		if (id == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(indexColumnService.get(id));
	}

	@RequestMapping("list")
	public HjkResponse getIndexColumnList(@RequestBody IndexColumn indexColumn) {
		logger.info("====getIndexColumnList(list), indexColumn: {}", indexColumn);

		if (indexColumn == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(indexColumnService.find(indexColumn));
	}

	@RequestMapping("add")
	public HjkResponse addIndexColumn(@RequestBody IndexColumn indexColumn) {
		logger.info("====addIndexColumn(add), indexColumn: {}", indexColumn);

		if (indexColumn == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(indexColumnService.save(indexColumn));
	}

	@RequestMapping("edit")
	public HjkResponse editIndexColumn(@RequestBody IndexColumn indexColumn) {
		logger.info("====editIndexColumn(edit), indexColumn: {}", indexColumn);

		if (indexColumn == null) {
			return HjkResponse.fail(MessageData.ERROR_PARAM_NULL);
		}
		return HjkResponse.success(indexColumnService.update(indexColumn));
	}
}
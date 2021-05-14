package net.hyjuki.smgen.gencode.xml.base;

import net.hyjuki.smgen.base.utils.GenUtils;

import java.util.List;

public class SelectSort {
    private List<String> columns;
    private String sortType;
    private String sortSql;

    private SelectSort(String sortType, String... columns) {
        this.sortType = sortType;
        this.sortSql = GenUtils.concatBySeparator(GenUtils.SPACE, MapperConstants.ORDER_BY,
                GenUtils.concatBySeparator(GenUtils.COMMA, columns), sortType);
    }

    private SelectSort(String sortType, List<String> columns) {
        this.sortType = sortType;
        this.sortSql = GenUtils.concatBySeparator(GenUtils.SPACE, MapperConstants.ORDER_BY,
                GenUtils.concatBySeparator(GenUtils.COMMA, columns), sortType);
    }

    private String getSortSql(String sortType, String... columns) {
        SelectSort sort = new SelectSort(sortType, columns);
        return this.sortSql;
    }
}

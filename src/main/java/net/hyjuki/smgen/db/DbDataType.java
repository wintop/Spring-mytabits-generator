package net.hyjuki.smgen.db;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DbDataType {
    private Map<Integer, Class> dataType;
    private DbDataType() {
        dataType = new HashMap<>();
        dataType.put(Types.ARRAY, Object.class);
        dataType.put(Types.BIGINT, Long.class);
        dataType.put(Types.BIT, Boolean.class);
        dataType.put(Types.BOOLEAN, Boolean.class);
        dataType.put(Types.CHAR, String.class);
        dataType.put(Types.CLOB, String.class);
        dataType.put(Types.DATALINK, Object.class);
        dataType.put(Types.DATE, Date.class);
        dataType.put(Types.DECIMAL, BigDecimal.class);
        dataType.put(Types.DISTINCT, Object.class);
        dataType.put(Types.DOUBLE, Double.class);
        dataType.put(Types.FLOAT, Double.class);
        dataType.put(Types.INTEGER, Integer.class);
        dataType.put(Types.JAVA_OBJECT, Object.class);
        dataType.put(Types.LONGNVARCHAR, String.class);
        dataType.put(Types.NCHAR, String.class);
        dataType.put(Types.NCLOB, String.class);
        dataType.put(Types.NVARCHAR, String.class);
        dataType.put(Types.NULL, Object.class);
        dataType.put(Types.NUMERIC, BigDecimal.class);
        dataType.put(Types.OTHER, Object.class);
        dataType.put(Types.REAL, Float.class);
        dataType.put(Types.REF, Object.class);
        dataType.put(Types.SMALLINT, Short.class);
        dataType.put(Types.STRUCT, Object.class);
        dataType.put(Types.TIME, Date.class);
        dataType.put(Types.TIMESTAMP, Date.class);
        dataType.put(Types.TINYINT, Byte.class);
        dataType.put(Types.VARCHAR, String.class);
        dataType.put(Types.BINARY, Byte[].class);
        dataType.put(Types.VARBINARY, Byte[].class);
        dataType.put(Types.LONGVARBINARY, Byte[].class);
    }

    public static DbDataType getInstance() {
        return new DbDataType();
    }

    public String getName(int type) {
        // java自带的这个Types对于mysql来说数据类型不全，这个是为了Text类型做的判断
        if (type == -1) {
            return String.class.getSimpleName();
        }
        return dataType.get(type).getSimpleName();
    }

    public String getFullName(int type) {
        return dataType.get(type).getName();
    }
}

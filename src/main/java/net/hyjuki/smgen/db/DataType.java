package net.hyjuki.smgen.db;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;

public enum DataType {
    ARRAY(Types.ARRAY, Object.class),
    BIGINT(Types.BIGINT, Long.class),
    BIT(Types.BIT, Boolean.class),
    BOOLEAN(Types.BOOLEAN, Boolean.class),
    CHAR(Types.CHAR, String.class),
    CLOB(Types.CLOB, String.class),
    DATALINK(Types.DATALINK, Object.class),
    DATE(Types.DATE, Date.class),
    DECIMAL(Types.DECIMAL, BigDecimal.class),
    DISTINCT(Types.DISTINCT, Object.class),
    DOUBLE(Types.DOUBLE, Double.class),
    FLOAT(Types.FLOAT, Double.class),
    INTEGER(Types.INTEGER, Integer.class),
    JAVA_OBJECT(Types.JAVA_OBJECT, Object.class),
    LONGNVARCHAR(Types.LONGNVARCHAR, String.class),
    NCHAR(Types.NCHAR, String.class),
    NCLOB(Types.NCLOB, String.class),
    NVARCHAR(Types.NVARCHAR, String.class),
    NULL(Types.NULL, Object.class),
    NUMERIC(Types.NUMERIC, BigDecimal.class),
    OTHER(Types.OTHER, Object.class),
    REAL(Types.REAL, Float.class),
    REF(Types.REF, Object.class),
    SMALLINT(Types.SMALLINT, Short.class),
    STRUCT(Types.STRUCT, Object.class),
    TIME(Types.TIME, Date.class),
    TIMESTAMP(Types.TIMESTAMP, Date.class),
    TINYINT(Types.TINYINT, Byte.class),
    VARCHAR(Types.VARCHAR, String.class);

    private int type;
    private Class name;

    DataType(int type, Class name) {
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return this.name.getSimpleName();
    }

    public String getFullName() {
        return this.name.getName();
    }

    public static String getName(int type) {
        for (DataType t: DataType.values()) {
            if (t.type == type) {
                return t.getName();
            }
        }

        return "";
    }

    public static void main(String[] args) {
        System.out.println(DataType.VARCHAR.getName());
        System.out.println(DataType.VARCHAR.getFullName());
        System.out.println(DataType.VARCHAR.name());
        System.out.println(DataType.getName(Types.DATE));
    }
}

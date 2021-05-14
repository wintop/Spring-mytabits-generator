package net.hyjuki.smgen.db;

import org.apache.ibatis.type.ByteObjectArrayTypeHandler;

import java.math.BigDecimal;
import java.util.Date;

public enum DataType {
    CHAR("CHAR", Character.class), // types: 1
    NUMERIC("NUMERIC", BigDecimal.class), // types: 2
    DECIMAL("DECIMAL", BigDecimal.class), // types: 3
    INT("INT", Integer.class), // types: 4
    MEDIUMINT("MEDIUMINT", Integer.class), // types: 4
    SMALLINT("SMALLINT", Short.class), // types: 5
    VARCHAR("VARCHAR", String.class), // types: 12
    FLOAT("FLOAT", Float.class), // types: 7
    DOUBLE("DOUBLE", Double.class), // types: 8
    YEAR("YEAR", Date.class), // types: 91
    DATE("DATE", Date.class), // types: 91
    TIME("TIME", Date.class), // types: 92
    DATETIME("DATETIME", Date.class), // types: 93
    TIMESTAMP("TIMESTAMP", Date.class), // types: 93
    BIT("BIT", Boolean.class), //  types:-7
    TINYINT("TINYINT", Byte.class), //  types:-6
    BIGINT("BIGINT", Long.class), //  types:-5
    BLOB("BLOB", String.class), //  types:-4 byte[]
    VARBINARY("VARBINARY", String.class), //  types:-3 byte[]
    BINARY("BINARY", String.class), //  types:-2 byte[]
    GEOMETRY("GEOMETRY", Object.class), //  types:-2
    ENUM("ENUM", String .class), // types: 1
    SET("SET", Object.class), // types: 1
    TEXT("TEXT", String.class), //  types:-1
    JSON("JSON", Object.class); //  types:-1

    private String type;
    private Class name;

    DataType(String type, Class name) {
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return this.name.getSimpleName();
    }

    public String getFullName() {
        return this.name.getName();
    }

    public static void main(String[] args) {
        System.out.println(Byte.class);
    }
}

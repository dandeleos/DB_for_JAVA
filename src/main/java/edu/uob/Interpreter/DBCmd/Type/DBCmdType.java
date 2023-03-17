package edu.uob.DBCmd.Type;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum DBCmdType {
    USE("use"),
    CREATE("create"),
    DROP("drop"),
    ALTER("alter"),
    INSERT("insert"),
    SELECT("select"),
    UPDATE("update"),
    DELETE("delete"),
    JOIN("join");

    private String cmdType;
    DBCmdType(String cmdType) {
        this.cmdType = cmdType;
    }

    public static DBCmdType getEnum(String cmdType) {
        DBCmdType result = null;
        for (DBCmdType t : values()) {
            if (t.toString().equalsIgnoreCase(cmdType)) {
                result = t;
                break;
            }
        }
//        if (result == null) {
//            throw new IllegalArgumentException();
//        }
        return result;
    }

//    public static Set<String> DBCmdSet = List.of(DBCmdType.values()).stream()
//            .map(e -> e.toString().toLowerCase())
//            .collect(Collectors.toSet());
}

package edu.uob.DBCmd.Type;

public enum DBKeyWord {
    FROM("FROM"),
    WHERE("WHERE"),
    SET("SET"),
    DATABASE("DATABASE"),
    TABLE("TABLE");


    private String keyWord;
    private DBKeyWord(String keyWord){
        this.keyWord = keyWord;
    }


    public static DBKeyWord getEnum(String keyWord) {
        DBKeyWord result = null;
        for (DBKeyWord t : values()) {
            if (t.toString().equalsIgnoreCase(keyWord)) {
                result = t;
                break;
            }
        }
//        if (result == null) {
//            throw new IllegalArgumentException();
//        }
        return result;
    }


}

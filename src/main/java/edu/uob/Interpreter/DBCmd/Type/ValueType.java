package edu.uob.DBCmd.Type;

public enum ValueType {
    STRING("'[^']*'"),
    BOOL("true|false"),
    FLOAT("[+-]?[0-9]*\\.[0-9]+"),
    INT("[+-]?[0-9]+");

    private String regex;
    private ValueType(String regex){
        this.regex = regex;
    }

}

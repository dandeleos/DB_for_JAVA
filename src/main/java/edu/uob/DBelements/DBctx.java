package edu.uob.Centre;

import edu.uob.DBCmd.Type.DBCmdType;

import java.util.ArrayList;
import java.util.List;

public class DBctx {
    private List<String> queryTokens;
    private String currentDB;
    private String result;
    private DBCmdType cmdType;
    public DBctx() {
        queryTokens = new ArrayList<>();
    }
    public void setQueryTokens(List<String> queryTokens) {
        this.queryTokens = queryTokens;
    }
    public List<String> getQueryTokens() {
        return queryTokens;
    }

    public
}

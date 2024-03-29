package edu.uob.DBCmd;

import core.DBContext;

public interface DBCommand {
    void parseInput(DBContext ctx) throws Exception;
    void executeCommand(DBContext ctx) throws Exception;
}

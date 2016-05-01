package edu.miamioh.cse.finalproject.shell;

public interface Command {

    String execute(String param, Router router);

    boolean fit(String commandString);
}

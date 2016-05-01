package edu.miamioh.cse.finalproject.shell;

import edu.miamioh.cse.finalproject.shell.Command;
import edu.miamioh.cse.finalproject.shell.Router;

public class ExitCommand implements Command {


    @Override
    public String execute(String param, Router router) {
        System.exit(0);
        return null;
    }

    @Override
    public boolean fit(String commandString) {
        return "c".equalsIgnoreCase(commandString);
    }
}

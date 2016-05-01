package edu.miamioh.cse.finalproject.shell;

import edu.miamioh.cse.finalproject.core.ZipCoding;
import edu.miamioh.cse.finalproject.shell.Command;
import edu.miamioh.cse.finalproject.shell.Router;

public class ZipCodingCommand implements Command {
    private ZipCoding zipCoding;

    public ZipCodingCommand(ZipCoding zipCoding) {
        this.zipCoding = zipCoding;
    }

    @Override
    public String execute(String param, Router router) {
        return null;
    }

    @Override
    public boolean fit(String commandString) {
        return true;
    }
}

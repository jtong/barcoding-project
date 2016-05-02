package edu.miamioh.cse.finalproject.shell;

import edu.miamioh.cse.finalproject.core.TranslateMessage;
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
        TranslateMessage translateMessage = zipCoding.toZipCode(param);
        if (translateMessage.getSuccess()) {
            return translateMessage.getMessage() + "\n" + router.start();
        }
        return translateMessage.getMessage();
    }

    @Override
    public boolean fit(String commandString) {
        return true;
    }
}

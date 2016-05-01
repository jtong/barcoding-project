package edu.miamioh.cse.finalproject.shell;

import edu.miamioh.cse.finalproject.core.BarCoding;
import edu.miamioh.cse.finalproject.core.TranslateMessage;
import edu.miamioh.cse.finalproject.shell.Command;
import edu.miamioh.cse.finalproject.shell.Router;

public class BarCodingCommand implements Command {

    private BarCoding barCoding;

    public BarCodingCommand(BarCoding barCoding) {
        this.barCoding = barCoding;
    }

    @Override
    public String execute(String param, Router router) {

        TranslateMessage translateMessage = barCoding.toBarcode(param);
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

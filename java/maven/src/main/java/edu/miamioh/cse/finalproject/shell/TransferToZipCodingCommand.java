package edu.miamioh.cse.finalproject.shell;

import edu.miamioh.cse.finalproject.core.ZipCoding;

import java.util.Arrays;

public class TransferToZipCodingCommand implements Command {
    private final Command zipCodingCommand;

    public TransferToZipCodingCommand(ZipCoding zipCoding) {
        this.zipCodingCommand = new ZipCodingCommand(zipCoding);
    }

    @Override
    public String execute(String param, Router router) {
        router.setAcceptableCommands(Arrays.asList(zipCodingCommand));
        return "Please input bar code:";
    }

    @Override
    public boolean fit(String commandString) {
        return "b".equalsIgnoreCase(commandString);
    }
}

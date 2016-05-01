package edu.miamioh.cse.finalproject.shell;

import edu.miamioh.cse.finalproject.core.BarCoding;

import java.util.Arrays;

public class TransferToBarCodingCommand implements Command {
    private Command barCodingCommand;

    public TransferToBarCodingCommand(BarCoding barCoding) {
        this.barCodingCommand = new BarCodingCommand(barCoding);
    }


    @Override
    public String execute(String param, Router router) {
        router.setAcceptableCommands(Arrays.asList(barCodingCommand));
        return "Please input zipcode. \n";
    }

    @Override
    public boolean fit(String commandString) {
        return "a".equalsIgnoreCase(commandString);
    }
}

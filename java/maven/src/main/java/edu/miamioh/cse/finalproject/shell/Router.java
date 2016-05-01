package edu.miamioh.cse.finalproject.shell;

import edu.miamioh.cse.finalproject.core.BarCoding;
import edu.miamioh.cse.finalproject.core.ZipCoding;

import java.util.Arrays;
import java.util.List;

public class Router {
    private Command exitCommand;
    private BarCoding barCoding;
    private List<Command> acceptableCommands;
    
    private List<Command> originCommands;

    public Router(Command exitCommand, BarCoding barCoding, ZipCoding zipCoding) {

        this.exitCommand = exitCommand;
        this.barCoding = barCoding;

        originCommands = Arrays.asList(new TransferToBarCodingCommand(barCoding), new TransferToZipCodingCommand(zipCoding),exitCommand);
        this.acceptableCommands = originCommands;
    }

    public String start() {
        this.acceptableCommands = originCommands;
        return "I can accept these commands:\n" +
                " a. input zipcode, translate to barcode. \n" +
                " b. input barcode, translate to zipcode. \n" +
                " c. quit. \n" +
                " What is your command? \n";
    }

    public String acceptCommand(String commandString) {
        for (int i = 0; i < acceptableCommands.size(); i++) {
            Command command = acceptableCommands.get(i);
            if(command.fit(commandString)){
                return command.execute(commandString, this);
            }
        }
                
        return start();
    }

    public List<Command> getAcceptableCommands() {
        return acceptableCommands;
    }

    void setAcceptableCommands(List<Command> acceptableCommands) {
        this.acceptableCommands = acceptableCommands;
    }
}

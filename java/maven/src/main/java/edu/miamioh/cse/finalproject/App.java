package edu.miamioh.cse.finalproject;

import edu.miamioh.cse.finalproject.core.BarCoding;
import edu.miamioh.cse.finalproject.core.ZipCoding;
import edu.miamioh.cse.finalproject.shell.ExitCommand;
import edu.miamioh.cse.finalproject.shell.Router;

import java.io.Console;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Router router =new Router(new ExitCommand(), new BarCoding(), new ZipCoding());
        router.start();
        Console con = System.console();
        if (con != null) {
            Scanner scanner = new Scanner(con.reader());
            while (true) {
                String commandString = scanner.nextLine();
                System.out.println(router.acceptCommand(commandString));
            }
        }
    }
}

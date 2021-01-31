package ual.views;

import java.util.*;
import ual.controllers.*;

public class CLI {
    public CLI(){
        Controller controller = new ControllerClass();
        Scanner scanner = new Scanner(System.in);
        while(true){
            String line = scanner.nextLine();
            if (line == "") return;

            String[] commands = line.split(" ");

            switch (commands[0]) {
                case "RJ":
                    String name = commands[1];
                    break;
                case "EJ":
                    break;
                case "LJ":
                    break;
                case "IJ":
                    break;
                case "IC":
                    break;
                case "D":
                    break;
                case "CN":
                    break;
                case "RN":
                    break;
                case "T":
                    break;
                case "V":
                    break;
                default:
                    System.out.println("Instrução invalida.");
            }
        }
    }
}

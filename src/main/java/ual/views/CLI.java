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
<<<<<<< Updated upstream
=======

                case "G":
                    String fileName = commands[1];
                    controller.saveFile(fileName);
                    System.out.println("Ficheiro gravado com sucesso.");
                    break;

                case "L":
                    fileName = commands[1];
                    try {
                        controller = Controller.loadFile(fileName);
                        System.out.println("Ficheiro lido com sucesso.");
                    } catch (Exception e) {
                        System.out.println("Ficheiro inexistente.");
                    }
                    break;

                case "debug":
                    player = commands[1];
                    //System.out.println("Y:" + (Integer.parseInt(tableLine) - 1) + " X:" + (((int)tableColumn.toCharArray()[0]) - 'A' ));
                    controller.printDebugTable(player);
                    break;

>>>>>>> Stashed changes
                default:
                    System.out.println("Instrução invalida.");
            }
        }
    }
}

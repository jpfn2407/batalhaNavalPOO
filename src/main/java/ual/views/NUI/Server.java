package ual.views.NUI;

import com.dosse.upnp.UPnP;
import ual.controllers.Controller;
import ual.controllers.ControllerClass;
import ual.models.players.Player;
import ual.models.tables.OngoingScore;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        Controller controller = new ControllerClass();
        while (true){

            try {
                Class.forName("com.dosse.upnp.UPnP");
                System.out.println("UPnP library loaded.");
            } catch (Exception e1) {
                System.err.println("Couldn't find library.");
                System.err.println(e1);
                System.exit(1);
            }

            System.out.println("Attempting UPnP port forwarding...");
            if (UPnP.isUPnPAvailable()) { //is UPnP available?
                if (UPnP.isMappedTCP(4242)) { //is the port already mapped?
                    System.out.println("UPnP port forwarding not enabled: port is already mapped");
                } else if (UPnP.openPortTCP(4242)) { //try to map port
                    System.out.println("UPnP port forwarding enabled");
                } else {
                    System.out.println("UPnP port forwarding failed");
                }
            } else {
                System.out.println("UPnP is not available");
            }


            try {
                ServerSocket serverSocket = new ServerSocket(4242);
                Socket socket = serverSocket.accept();
                InetAddress inetAddress = socket.getInetAddress();
                System.out.println(inetAddress.getHostAddress());
                Scanner scanner = new Scanner(socket.getInputStream());
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                String line = scanner.nextLine();
                String[] commands = line.split(" ");
                switch (commands[0]) {
                    case "RJ":
                        String name = commands[1];
                        if (controller.hasPlayerName(name)) {
                            printWriter.println("Jogador existente.");
                        } else {
                            controller.registerPlayer(name);
                            printWriter.println("Jogador registado com sucesso.");
                        }
                        break;
                    case "EJ":
                        name = commands[1];
                        if(!controller.hasPlayerName(name)){
                            printWriter.println("Jogador não existente.");
                        }
                        else if(controller.isPlayerInActiveGame(name)){
                            printWriter.println("Jogador participa no jogo em curso.");
                        }
                        else{
                            controller.removePlayer(name);
                            printWriter.println("Jogador removido com sucesso.");
                        }
                        break;
                    case "LJ":
                        List<Player> playerList = controller.getPlayerList();
                        if(playerList.isEmpty() || playerList == null){
                            printWriter.println("Não existem jogadores registados.");
                        }
                        else {
                            for (Player player : playerList) {
                                printWriter.println(player.getName() + " " + player.getNumberOfGames() + " " + player.getVictories());
                            }
                        }
                        break;
                    case "IJ":
                        String player1 = commands[1];
                        String player2 = commands[2];
                        if(controller.isGameActive()){
                            printWriter.println("Existe um jogo em curso.");
                        }
                        else if(!controller.hasPlayerName(player1) || !controller.hasPlayerName(player2) || player1.equals(player2)){
                            printWriter.println("Jogadores não registados ou repetidos.");
                        }
                        else{
                            controller.startGame(player1, player2);
                            printWriter.println("Jogo iniciado entre " + player1 + " e " + player2 + ".");
                        }
                        break;
                    case "IC":
                        if (!controller.isGameActive()){
                            printWriter.println("Não existe jogo em curso.");
                        }
                        else if(controller.isThereBoatsLeftToPlace()){
                            printWriter.println("Navios não colocados.");
                        }
                        else{
                            controller.startWar();
                            printWriter.println("Combate iniciado.");
                            controller.printHiddenTable();
                        }
                        break;
                    case "D":
                        player1 = commands[1];
                        player2 = null;
                        if(commands.length == 3) player2 = commands[2];
                        if (!controller.isGameActive()){
                            printWriter.println("Não existe jogo em curso.");
                        }
                        else if(!controller.isPlayerInActiveGame(player1) || (player2!= null && !controller.isPlayerInActiveGame(player2))){
                            printWriter.println("Jogador não participa no jogo em curso.");
                        }
                        else{
                            controller.giveUp(player1, player2);
                            printWriter.println("Desistência com sucesso. Jogo terminado.");
                        }
                        break;
                    case "CN":
                        String player = commands[1];
                        String type = commands[2];
                        String tableLine = commands[3];
                        String tableColumn = commands[4];
                        String tableOrientation = null;
                        if(commands.length == 6) tableOrientation = commands[5];
                        if(tableOrientation == null) tableOrientation = "N";
                        if (!controller.isGameActive()){
                            printWriter.println("Não existe jogo em curso.");
                        }
                        else if(controller.isWarActive()){
                            printWriter.println("Combate em curso.");
                        }
                        else if(!controller.isPlayerInActiveGame(player)){
                            printWriter.println("Jogador não participa no jogo em curso.");
                        }
                        else if(!controller.isBoatInsideTable(tableColumn, tableLine)){
                            printWriter.println("Posição irregular.");
                        }
                        else if(!controller.hasBoatTypeLeftToPlace(player, type)){
                            printWriter.println("Não tem mais navios dessa tipologia disponíveis.");
                        }
                        else if(!controller.isValidPosition(player, type, tableLine, tableColumn, tableOrientation)){
                            printWriter.println("Não é possível colocar navios.");
                        }
                        else{
                            controller.placeBoat(player, type, tableLine, tableColumn, tableOrientation);
                            printWriter.println("Navio colocado com sucesso.");
                        }
                        break;
                    case "RN":
                        player = commands[1];
                        tableLine = commands[2];
                        tableColumn = commands[3];
                        if (!controller.isGameActive()){
                            printWriter.println("Não existe jogo em curso.");
                        }
                        else if(controller.isWarActive()){
                            printWriter.println("Combate em curso.");
                        }
                        else if(!controller.isPlayerInActiveGame(player)){
                            printWriter.println("Jogador não participa no jogo em curso.");
                        }
                        else if(!controller.isBoatInThisPosition(player, tableLine, tableColumn) && controller.isBoatInsideTable(tableColumn, tableLine)){
                            printWriter.println("Não existe navio na posição.");
                        }
                        else{
                            controller.removeBoat(player, tableLine, tableColumn);
                            printWriter.println("Navio removido com sucesso.");
                        }
                        break;
                    case "T":
                        player = commands[1];
                        tableLine = commands[2];
                        tableColumn = commands[3];
                        if (!controller.isGameActive()){
                            printWriter.println("Não existe jogo em curso.");
                        }
                        else if(!controller.isWarActive()){
                            printWriter.println("Jogo em curso sem combate iniciado.");
                        }
                        else if(!controller.isPlayerInActiveGame(player)){
                            printWriter.println("Jogador não participa no jogo em curso.");
                        }
                        else if(!controller.isBoatInsideTable(tableColumn, tableLine) || controller.isShotAtAlreadyShotSpot(player, tableLine, tableColumn)){
                            printWriter.println("Posição irregular.");
                        }
                        else if(!controller.isPlayerTurn(player)){
                            printWriter.println("Não é a sua vez de jogar.");
                        }
                        else{
                            printWriter.println(controller.shoot(player, tableLine, tableColumn));
                            controller.hasBoatsLeftToPlay(player);
                            controller.printHiddenTable();
                        }
                        break;
                    case "V":
                        if (!controller.isGameActive()){
                            printWriter.println("Não existe jogo em curso.");
                        }
                        else if(!controller.isWarActive()){
                            printWriter.println("Jogo em curso sem combate iniciado.");
                        }
                        else{
                            List<OngoingScore> score = controller.getOngoingScoreList();
                            for (OngoingScore ongoingScore : score) {
                                printWriter.println(ongoingScore.getPlayerName() + " " + ongoingScore.getShots() + " " + ongoingScore.getShotsOnBoats() + " " + ongoingScore.getSunkedShips());
                            }
                        }
                        break;
                    default:
                        printWriter.println("Instrução invalida.");
                }
                printWriter.flush();
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

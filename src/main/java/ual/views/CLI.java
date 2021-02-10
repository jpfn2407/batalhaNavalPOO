package ual.views;

import java.sql.SQLOutput;
import java.util.*;
import ual.controllers.*;
import ual.models.players.Player;
import ual.models.tables.OngoingScore;

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
                    if(controller.hasPlayerName(name)){
                        System.out.println("Jogador existente.");
                    } else {
                        controller.registerPlayer(name);
                        System.out.println("Jogador registado com sucesso.");
                    }
                    break;
                case "EJ":
                    name = commands[1];
                    if(!controller.hasPlayerName(name)){
                        System.out.println("Jogador não existente.");
                    }
                    else if(controller.isPlayerInActiveGame(name)){
                        System.out.println("Jogador participa no jogo em curso.");
                    }
                    else{
                        controller.removePlayer(name);
                        System.out.println("Jogador removido com sucesso.");
                    }
                    break;
                case "LJ":
                    List<Player> playerList = controller.getPlayerList();
                    if(playerList.isEmpty() || playerList == null){
                        System.out.println("Não existem jogadores registados.");
                    }
                    else {
                        for (Player player : playerList) {
                            System.out.println(player.getName() + " " + player.getNumberOfGames() + " " + player.getVictories());
                        }
                    }
                    break;
                case "IJ":
                    String player1 = commands[1];
                    String player2 = commands[2];
                    if(controller.isGameActive()){
                        System.out.println("Existe um jogo em curso.");
                    }
                    else if(!controller.hasPlayerName(player1) || !controller.hasPlayerName(player2) || player1.equals(player2)){
                        System.out.println("Jogadores não registados ou repetidos.");
                    }
                    else{
                        controller.startGame(player1, player2);
                        System.out.println("Jogo iniciado entre " + player1 + " e " + player2 + ".");
                    }
                    break;
                case "IC":
                    if (!controller.isGameActive()){
                        System.out.println("Não existe jogo em curso.");
                    }
                    else if(controller.isThereBoatsLeftToPlace()){
                        System.out.println("Navios não colocados.");
                    }
                    else{
                        controller.startWar();
                        System.out.println("Combate iniciado.");
                    }
                    break;
                case "D":
                    player1 = commands[1];
                    player2 = null;
                    if(commands.length == 3) player2 = commands[2];
                    if (!controller.isGameActive()){
                        System.out.println("Não existe jogo em curso.");
                    }
                    else if(!controller.isPlayerInActiveGame(player1) || (player2!= null && !controller.isPlayerInActiveGame(player2))){
                        System.out.println("Jogador não participa no jogo em curso.");
                    }
                    else{
                        controller.giveUp(player1, player2);
                        System.out.println("Desistência com sucesso. Jogo terminado.");
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
                        System.out.println("Não existe jogo em curso.");
                    }
                    else if(controller.isWarActive()){
                        System.out.println("Combate em curso.");
                    }
                    else if(!controller.isPlayerInActiveGame(player)){
                        System.out.println("Jogador não participa no jogo em curso.");
                    }
                    else if(!controller.isBoatInsideTable(tableColumn, tableLine)){
                        System.out.println("Posição irregular.");
                    }
                    else if(!controller.hasBoatTypeLeftToPlace(player, type)){
                        System.out.println("Não tem mais navios dessa tipologia disponíveis.");
                    }
                    else if(!controller.isValidPosition(player, type, tableLine, tableColumn, tableOrientation)){
                        System.out.println("Não é possível colocar navios.");
                    }
                    else{
                        controller.placeBoat(player, type, tableLine, tableColumn, tableOrientation);
                        System.out.println("Navio colocado com sucesso.");
                    }
                    break;
                case "RN":
                    player = commands[1];
                    tableLine = commands[2];
                    tableColumn = commands[3];
                    if (!controller.isGameActive()){
                        System.out.println("Não existe jogo em curso.");
                    }
                    else if(controller.isWarActive()){
                        System.out.println("Combate em curso.");
                    }
                    else if(!controller.isPlayerInActiveGame(player)){
                        System.out.println("Jogador não participa no jogo em curso.");
                    }
                    else if(!controller.isBoatInThisPosition(player, tableLine, tableColumn) && controller.isBoatInsideTable(tableColumn, tableLine)){
                        System.out.println("Não existe navio na posição.");
                    }
                    else{
                        controller.removeBoat(player, tableLine, tableColumn);
                        System.out.println("Navio removido com sucesso.");
                    }
                    break;
                case "T":
                    player = commands[1];
                    tableLine = commands[2];
                    tableColumn = commands[3];
                    if (!controller.isGameActive()){
                        System.out.println("Não existe jogo em curso.");
                    }
                    else if(!controller.isWarActive()){
                        System.out.println("Jogo em curso sem combate iniciado.");
                    }
                    else if(!controller.isPlayerInActiveGame(player)){
                        System.out.println("Jogador não participa no jogo em curso.");
                    }
                    else if(!controller.isBoatInsideTable(tableColumn, tableLine) || controller.isShotAtAlreadyShotSpot(player, tableLine, tableColumn)){
                    System.out.println("Posição irregular.");
                    }
                    else if(!controller.isPlayerTurn(player)){
                        System.out.println("Não é a sua vez de jogar.");
                    }
                    else{
                        System.out.println(controller.shoot(player, tableLine, tableColumn));
                        controller.hasBoatsLeftToPlay(player);
                    }
                    break;
                case "V":
                    if (!controller.isGameActive()){
                        System.out.println("Não existe jogo em curso.");
                    }
                    else if(!controller.isWarActive()){
                        System.out.println("Jogo em curso sem combate iniciado.");
                    }
                    else{
                        List<OngoingScore> score = controller.getOngoingScoreList();
                        for (OngoingScore ongoingScore : score) {
                            System.out.println(ongoingScore.getPlayerName() + " " + ongoingScore.getShots() + " " + ongoingScore.getShotsOnBoats() + " " + ongoingScore.getSunkedShips());
                        }
                    }
                    break;
                case "G":
                    break;
                case "L":
                    break;

                case "debug":
                    player = commands[1];
                    //System.out.println("Y:" + (Integer.parseInt(tableLine) - 1) + " X:" + (((int)tableColumn.toCharArray()[0]) - 'A' ));
                    controller.printDebugTable(player);
                    break;

                default:
                    System.out.println("Instrução invalida.");
            }
        }
    }
}

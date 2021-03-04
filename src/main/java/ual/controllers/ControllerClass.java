package ual.controllers;

<<<<<<< Updated upstream
public class ControllerClass implements Controller{
=======
import ual.models.GameLogic;
import ual.models.PlayersList;
import ual.models.players.Player;
import ual.models.tables.OngoingScore;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class ControllerClass implements Controller{

    private GameLogic gameLogic;
    private PlayersList playersList;

    public ControllerClass(){
        this.gameLogic = new GameLogic();
        this.playersList = new PlayersList();
    }

    public boolean hasPlayerName(String name) {
        return this.playersList.hasPlayer(name);
    }

    public void registerPlayer(String name) {
        this.playersList.registerPlayer(name);
    }

    public boolean isPlayerInActiveGame(String name) {
        return this.gameLogic.playerIsInGame(name);
    }

    public void removePlayer(String name) {
        this.playersList.deletePlayer(name);
    }

    public List<Player> getPlayerList() {
        return this.playersList.getListOfPlayers();
    }

    public boolean isGameActive() {
        return this.gameLogic.isGameActive();
    }

    public void startGame(String player1, String player2) {
        this.gameLogic.startGame(this.playersList.getPlayer(player1), this.playersList.getPlayer(player2));
    }

    public boolean isThereBoatsLeftToPlace() {
        return this.gameLogic.isThereBoatsLeftToPlaceTotal();
    }

    public boolean isWarActive() {
        return this.gameLogic.isWarActive();
    }

    public void startWar() {
        this.gameLogic.startWar();
    }

    public void giveUp(String player1, String player2) {
        if(player2 == null){
            this.gameLogic.oneGiveUp(player1);
        } else {
            this.gameLogic.bothGiveUp();
        }
    }

    public boolean isBoatInsideTable(String tableColumn, String tableLine) {
        return this.gameLogic.isBoatInsideTable(tableColumn, tableLine);
    }

    public boolean hasBoatTypeLeftToPlace(String player, String type) {
        return this.gameLogic.hasBoatTypeLeftToPlace(player, type);
    }

    public boolean isValidPosition(String player, String type, String tableLine, String tableColumn, String tableOrientation) {
        return this.gameLogic.isValidPosition(player, type, tableLine, tableColumn, tableOrientation);
    }

    public void placeBoat(String player, String type, String tableLine, String tableColumn, String tableOrientation) {
        this.gameLogic.placeBoat(player, type, tableColumn, tableLine, tableOrientation);
    }

    public boolean isBoatInThisPosition(String player, String tableLine, String tableColumn) {
        return this.gameLogic.isBoatInThisPosition(player, tableLine, tableColumn);
    }

    public void removeBoat(String player, String tableLine, String tableColumn) {
        this.gameLogic.removeBoat(player, tableLine, tableColumn);
    }

    public String shoot(String player, String tableLine, String tableColumn) {
        return this.gameLogic.shoot(player, tableLine, tableColumn);
    }

    public boolean isPlayerTurn(String player) {
        return this.gameLogic.isPlayerTurn(player);
    }

    public boolean isShotAtAlreadyShotSpot(String player, String tableLine, String tableColumn) {
        return this.gameLogic.isShotAtAlreadyShotSpot(player, tableLine, tableColumn);
    }

    public void hasBoatsLeftToPlay(String player) {
        this.gameLogic.hasBoatsLeftToPlay(player);
    }

    public List<OngoingScore> getOngoingScoreList() {
        return this.gameLogic.getOngoingScoreList();
    }

    public void printHiddenTable() {
        this.gameLogic.printHiddenTable();
    }

    public void saveFile(String fileName) {
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    /////////////////////////////////
    public void printDebugTable(String player){
        this.gameLogic.printDebugTable(player);
    }

    public void debugSet(String player, String tableColumn, String tableLine){
        this.gameLogic.debugSet(player, tableColumn, tableLine);
    }
>>>>>>> Stashed changes
}

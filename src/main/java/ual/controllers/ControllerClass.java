package ual.controllers;

import ual.models.GameLogic;
import ual.models.PlayersList;
import ual.models.players.Player;
import ual.models.tables.OngoingScore;

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
        return false;
    }

    public void startWar() {

    }

    public void giveUp(String player1, String player2) {

    }

    public boolean isBoatInsideTable(String tableLine, String tableColumn) {
        return false;
    }

    public boolean hasBoatTypeLeftToPlace(String player, String type) {
        return false;
    }

    public boolean isValidPosition(String player, String type, String tableLine, String tableColumn, String tableOrientation) {
        return false;
    }

    public void placeBoat(String player, String type, String tableLine, String tableColumn, String tableOrientation) {

    }

    public boolean isBoatInThisPosition(String tableLine, String tableColumn) {
        return false;
    }

    public void removeBoat(String player, String tableLine, String tableColumn) {

    }

    public boolean isWarActive() {
        return false;
    }

    public boolean shoot(String player, String tableLine, String tableColumn) {
        return false;
    }

    public List<OngoingScore> getOngoingScoreList() {
        return null;
    }
}

package ual.models;

import ual.models.players.Player;
import ual.models.tables.OngoingScore;
import ual.models.tables.TableClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameLogic {

    private Player player1;
    private Player player2;
    private Map<String, TableClass> tables;
    private boolean gameIsOn;
    private boolean warIsOn;
    private Player turn;

    public GameLogic(){
        this.gameIsOn = false;
        this.warIsOn = false;
        this.player1 = null;
        this.player2 = null;
        this.tables = new HashMap<>();
    }

    public boolean playerIsInGame(String name) {
        if (gameIsOn) {
            return (this.player1.getName().equals(name) || this.player2.getName().equals(name));
        }
        return false;
    }

    public boolean isGameActive() {
        return this.gameIsOn;
    }

    public boolean isWarActive() {
        return this.warIsOn;
    }

    public void startGame(Player player1, Player player2){
        this.gameIsOn = true;
        this.tables = new HashMap<>();
        this.player1 = player1;
        this.player2 = player2;
        this.tables.put(player1.getName(), new TableClass());
        this.tables.put(player2.getName(), new TableClass());
        this.warIsOn = false;
    }

    public boolean isThereBoatsLeftToPlaceTotal() {
        return (this.tables.get(player1.getName()).hasAnyBoatsLeftToPlace() ||
                this.tables.get(player2.getName()).hasAnyBoatsLeftToPlace());
    }

    public void startWar() {
        this.warIsOn = true;
        this.turn = this.player1;
    }

    public void oneGiveUp(String player) {
        if(this.player1.getName().equals(player)){
            this.player1.addGamePlayed();
            this.player2.addGamePlayed();
            this.player2.addVictory();
        } else {
            this.player1.addGamePlayed();
            this.player2.addGamePlayed();
            this.player1.addVictory();
        }
        this.endGame();
    }

    public void bothGiveUp() {
        this.player1.addGamePlayed();
        this.player2.addGamePlayed();
        this.endGame();
    }

    public void endGame(){
        this.player1 = null;
        this.player2 = null;
        this.tables = new HashMap<>();
        this.gameIsOn = false;
        this.warIsOn = false;
    }

    public boolean isBoatInsideTable(String tableColumn, String tableLine) {
        TableClass tempTable = new TableClass();
        return tempTable.isBoatInside(tableColumn, tableLine);
    }

    public boolean hasBoatTypeLeftToPlace(String player, String type) {
        return this.tables.get(player).hasBoatsLeftToPlace(type);
    }

    public boolean isValidPosition(String player, String type, String tableLine, String tableColumn, String tableOrientation) {
        return this.tables.get(player).isValidBoatPosition(type, tableColumn, tableLine, tableOrientation);
    }

    public void placeBoat(String player, String type, String tableColumn, String tableLine, String tableOrientation) {
        this.tables.get(player).placeBoat(type, tableColumn, tableLine, tableOrientation);
    }

    public boolean isBoatInThisPosition(String player, String tableLine, String tableColumn) {
        return this.tables.get(player).isBoatInThisPosition(tableLine, tableColumn);
    }

    public void removeBoat(String player, String tableLine, String tableColumn) {
        this.tables.get(player).removeBoat(tableLine, tableColumn);
    }

    public String shoot(String player, String tableLine, String tableColumn) {
        if(this.player1.getName().equals(player)){
            this.turn = this.player2;
            return this.tables.get(player2.getName()).getShotAt(tableLine, tableColumn);
        } else {
            this.turn = this.player1;
            return this.tables.get(player1.getName()).getShotAt(tableLine, tableColumn);
        }
    }

    public boolean isShotAtAlreadyShotSpot(String player, String tableLine, String tableColumn) {
        if(this.player1.getName().equals(player)){
            return this.tables.get(player2.getName()).isShotAtAlreadyShotSpot(tableLine, tableColumn);
        } else {
            return this.tables.get(player1.getName()).isShotAtAlreadyShotSpot(tableLine, tableColumn);
        }
    }

    public void hasBoatsLeftToPlay(String player) {
        if(this.player1.getName().equals(player)){
            if(!this.tables.get(player2.getName()).hasBoatsLeftToPlay()){
                this.endWarGame(this.player1);
            }
        } else {
            if(!this.tables.get(player1.getName()).hasBoatsLeftToPlay()){
                this.endWarGame(this.player2);
            }
        }
    }

    public boolean isPlayerTurn(String player) {
        return this.turn.getName().equals(player);
    }

    private void endWarGame(Player winner){
        this.player1.addGamePlayed();
        this.player2.addGamePlayed();
        if(winner == this.player1){
            this.player1.addVictory();
        }
        else{
            this.player2.addVictory();
        }
        this.endGame();
    }

    public List<OngoingScore> getOngoingScoreList() {
        List<OngoingScore> scoreboard = new ArrayList<>();
        scoreboard.add(this.tables.get(player2.getName()).getOngoingScore(player1));
        scoreboard.add(this.tables.get(player1.getName()).getOngoingScore(player2));
        return scoreboard;
    }

    //////////////////////////////

    public void printDebugTable(String player) {
        this.tables.get(player).printDebugTable();
    }

    public void debugSet(String player, String tableColumn, String tableLine){
        this.tables.get(player).debugSet(tableColumn, tableLine);
    }
}

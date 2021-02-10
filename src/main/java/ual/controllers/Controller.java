package ual.controllers;

import ual.models.players.Player;
import ual.models.tables.OngoingScore;

import java.util.List;

public interface Controller {

    boolean hasPlayerName(String name);

    void registerPlayer(String name);
    
    boolean isPlayerInActiveGame(String name);

    void removePlayer(String name);

    List<Player> getPlayerList();

    boolean isGameActive();

    void startGame(String player1, String player2);

    boolean isThereBoatsLeftToPlace();

    void startWar();

    void giveUp(String player1, String player2);

    boolean isBoatInsideTable(String tableLine, String tableColumn);

    boolean hasBoatTypeLeftToPlace(String player, String type);

    boolean isValidPosition(String player, String type, String tableLine, String tableColumn, String tableOrientation);

    void placeBoat(String player, String type, String tableLine, String tableColumn, String tableOrientation);

    boolean isBoatInThisPosition(String player, String tableLine, String tableColumn);

    void removeBoat(String player, String tableLine, String tableColumn);

    boolean isWarActive();

    String shoot(String player, String tableLine, String tableColumn);

    List<OngoingScore> getOngoingScoreList();

    boolean isPlayerTurn(String player);

    boolean isShotAtAlreadyShotSpot(String player, String tableLine, String tableColumn);

    void hasBoatsLeftToPlay(String player);

    void printHiddenTable();

    //////////////////////////////////

    void printDebugTable(String player);

    void debugSet(String player, String tableColumn, String tableLine);
}

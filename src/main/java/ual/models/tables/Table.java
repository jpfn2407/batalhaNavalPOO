package ual.models.tables;

import ual.models.players.Player;

public interface Table {
    boolean hasBoatsLeftToPlace(String type);

    boolean hasAnyBoatsLeftToPlace();

    boolean isValidBoatPosition(String type, String tableColumn, String tableLine, String tableOrientation);

    void placeBoat(String type, String tableColumn, String tableLine, String tableOrientation);

    boolean isBoatInThisPosition(String tableLine, String tableColumn);

    void removeBoat(String tableLine, String tableColumn);

    String getShotAt(String tableLine, String tableColumn);

    boolean isShotAtAlreadyShotSpot(String tableLine, String tableColumn);

    boolean hasBoatsLeftToPlay();

    OngoingScore getOngoingScore(Player player);

    void printTable();

    void printHiddenTable();
}

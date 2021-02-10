package ual.models.tables;

import ual.models.players.Player;

public class OngoingScore {
    private Player player;
    private Integer shots;
    private Integer shotsOnBoats;
    private Integer sunkedShipsNumber;

    public OngoingScore(){
        this.shots = 0;
        this.shotsOnBoats = 0;
        this.sunkedShipsNumber = 0;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public String getPlayerName() {
        return this.player.getName();
    }

    public Integer getShots() {
        return this.shots;
    }

    public Integer getShotsOnBoats() {
        return this.shotsOnBoats;
    }

    public Integer getSunkedShips() {
        return this.sunkedShipsNumber;
    }

    public void addShot(){
        this.shots++;
    }

    public void addShotOnBoat(){
        this.shotsOnBoats++;
    }

    public void addSunkedShip(){
        this.sunkedShipsNumber++;
    }
}

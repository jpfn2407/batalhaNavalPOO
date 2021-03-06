package ual.models.players;

import java.net.InetAddress;

public class Player {

    private String name;
    private Integer gamesPlayed;
    private Integer gamesVictories;
    private String clientAddress;

    public Player(String name){
        this.name = name;
        this.gamesPlayed = 0;
        this.gamesVictories = 0;
    }

    public Player(String name, String clientAddress){
        this.name = name;
        this.gamesPlayed = 0;
        this.gamesVictories = 0;
        this.clientAddress = clientAddress;
    }

    public String getName() {
        return this.name;
    }

    public Integer getNumberOfGames() {
        return this.gamesPlayed;
    }

    public Integer getVictories() {
        return this.gamesVictories;
    }

    public void addGamePlayed(){
        this.gamesPlayed += 1;
    }

    public void addVictory(){
        this.gamesVictories += 1;
    }

    public String getClientAddress() {
        return this.clientAddress;
    }
}

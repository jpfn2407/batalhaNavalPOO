package ual.models.players;

public class Player {

    private String name;
    private Integer gamesPlayed;
    private Integer gamesVictories;

    public Player(String name){
        this.name = name;
        this.gamesPlayed = 0;
        this.gamesVictories = 0;
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
}

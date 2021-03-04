package ual.models.players;

public class Player {
<<<<<<< Updated upstream
=======

    private String name;
    private Integer gamesPlayed;
    private Integer gamesVictories;
    private String ipAddress;

    public Player(String name){
        this.name = name;
        this.gamesPlayed = 0;
        this.gamesVictories = 0;
    }

    public Player(String name, String ipAddress){
        this.name = name;
        this.gamesPlayed = 0;
        this.gamesVictories = 0;
        this.ipAddress = ipAddress;
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

    public String getIpAddress() {
        return this.ipAddress;
    }
>>>>>>> Stashed changes
}

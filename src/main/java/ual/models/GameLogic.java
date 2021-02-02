package ual.models;

import ual.models.players.Player;
import ual.models.tables.TableClass;

public class GameLogic {

    private Player player1;
    private TableClass player1Table;
    private Player player2;
    private TableClass player2Table;
    private boolean gameIsOn;

    public GameLogic(){
        this.gameIsOn = false;
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

    public void startGame(Player player1, Player player2){
        this.gameIsOn = true;
        this.player1 = player1;
        this.player2 = player2;
        /*todo
           acabar isto.
           criar os tabuleiros.*/
    }
}

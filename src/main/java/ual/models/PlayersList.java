package ual.models;

import ual.models.players.Player;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayersList {

    private Map<String, Player> playerMap;

    public PlayersList(){
        this.playerMap = new HashMap<>();
    }

    public boolean hasPlayer(String name) {
        return this.playerMap.containsKey(name);
    }

    public void registerPlayer(String name) {
        this.playerMap.put(name, new Player(name));
    }

    public void registerPlayer(String name, String clientAddress) {
        this.playerMap.put(name, new Player(name, clientAddress));
    }

    public void deletePlayer(String name) {
        this.playerMap.remove(name);
    }

    public List<Player> getListOfPlayers() {
        return new ArrayList<>(this.playerMap.values());
    }

    public Player getPlayer(String name){
        return this.playerMap.get(name);
    }


    public boolean isIpLinkedWithName(String name, String clientAddress) {
        return this.playerMap.get(name).getClientAddress().equals(clientAddress);
    }
}

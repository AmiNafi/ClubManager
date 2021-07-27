package util;

import sample.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class Transfer implements Serializable {
    private String from, to;
    private Player player;
    private ArrayList<Player> currentClubPlayers, marketPlayers;
    public Transfer () {
        from = "";
        to = "";
        currentClubPlayers = new ArrayList<>();
        marketPlayers = new ArrayList<>();
        player = new Player();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setCurrentClubPlayers(ArrayList<Player> currentClubPlayers) {
        this.currentClubPlayers = currentClubPlayers;
    }

    public void setMarketPlayers(ArrayList<Player> marketPlayers) {
        this.marketPlayers = marketPlayers;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public ArrayList<Player> getMarketPlayers() {
        return marketPlayers;
    }

    public ArrayList<Player> getCurrentClubPlayers() {
        return currentClubPlayers;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}

package util;

import sample.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class LoginConfirm implements Serializable {
    private String clubName, password;
    private ArrayList<Player> currentClubPlayers, marketPlayers;
    private boolean status, type, logout;
    public LoginConfirm () {
        status = false;
        type = false;
        clubName = "";
        password = "";
        currentClubPlayers = new ArrayList<>();
        marketPlayers = new ArrayList<>();
        type = false;
        logout = false;
    }


    public void setType(boolean type) {
        this.type = type;
    }
    public void setLogout(boolean logout) {
        this.logout = logout;
    }
    public boolean getType() {
        return this.type;
    }
    public boolean getLogout() {
        return this.logout;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMarketPlayers(ArrayList<Player> marketPlayers) {
        this.marketPlayers = marketPlayers;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public void setCurrentClubPlayers(ArrayList<Player> currentClubPlayers) {
        this.currentClubPlayers = currentClubPlayers;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<Player> getCurrentClubPlayers() {
        return currentClubPlayers;
    }

    public ArrayList<Player> getMarketPlayers() {
        return marketPlayers;
    }

    public String getClubName() {
        return clubName;
    }
    public boolean getStatus () {
        return status;
    }


}

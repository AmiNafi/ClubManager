package sample;

import java.util.*;

public class PlayerList {
    private List <Player> players = new ArrayList();
    private HashMap<String, Integer> countByClub = new HashMap<>();
    private HashMap<String, Integer> countByCountry = new HashMap<>();

    public List<Player> getPlayers() {
        return players;
    }
    public HashMap<String, Integer> getCountByClub() {
        return countByClub;
    }
    public HashMap<String, Integer> getCountByCountry() {
        return countByCountry;
    }
    public void add (Player p) {
        players.add(p);
        int count1 = countByCountry.getOrDefault(p.getCountry(), 0);
        countByCountry.put(p.getCountry(), count1 + 1);
        int count2 = countByClub.getOrDefault(p.getClub(), 0);
        countByClub.put(p.getClub(), count2 + 1);
    }
    public void insertAll(List<Player> players) {
        for (Player p : players) {
            add(p);
        }
    }
}

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

    public Player searchByName(String name) {
        for (Player p : players) {
            if (name.equalsIgnoreCase(p.getName())) return p;
        }
        return null;
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
    public void showPlayerWithSalaryRange (double lo, double hi) {
        boolean found = false;
        for (Player p : players) {
            if (p.getWeeklySalary() >= lo && p.getWeeklySalary() <= hi) {
                found = true;
                p.show();
            }
        }
        if (!found) System.out.println("No such player with this weekly salary range");
    }
    public void showPlayerWithCountryAndClub(String country, String club) {
        boolean found = false;
        for (Player p : players) {
            if (p.getCountry().equalsIgnoreCase(country)) {
                if (p.getClub().equalsIgnoreCase(p.getClub()) || club.equalsIgnoreCase("ANY")) {
                    found = true;
                    p.show();
                }
            }
        }
        if (!found) System.out.println("No such player with this country and club");
    }
    public void showPlayerWithPosition(String position) {
        boolean found = false;
        for (Player p : players) {
            if (p.getPosition().equalsIgnoreCase(position)) {
                p.show();
                found = true;
            }
        }
        if (!found) System.out.println("No such player with this position");
    }
    public void showByMaxSalaryOfClub (String club) {
        double maxSalary = 0.0;
        boolean found = false;
        for (Player p : players) {
            if (p.getClub().equalsIgnoreCase(club)) {
                if (p.getWeeklySalary() > maxSalary) maxSalary = p.getWeeklySalary();
            }
        }
        for (Player p : players) {
            if (p.getClub().equalsIgnoreCase(club)) {
                if (p.getWeeklySalary() == maxSalary) {
                    p.show();
                    found = true;
                }
            }
        }
        if (!found) System.out.println("No such club with this name");
    }
    public void showByMaxAgeOfClub(String club) {
        int maxAge = 0;
        boolean found = false;
        for (Player p : players) {
            if (p.getClub().equalsIgnoreCase(club)) {
                if (p.getAge() > maxAge) maxAge = p.getAge();
            }
        }
        for (Player p : players) {
            if (p.getClub().equalsIgnoreCase(club)) {
                if (p.getAge() == maxAge) {
                    p.show();
                    found = true;
                }
            }
        }
        if (!found) System.out.println("No such club with this name");
    }
    public void showByMaxHeightOfClub(String club) {
        double maxHeight = 0;
        boolean found = false;
        for (Player p : players) {
            if (p.getClub().equalsIgnoreCase(club)) {
                if (p.getHeight() > maxHeight) maxHeight = p.getHeight();
            }
        }
        for (Player p : players) {
            if (p.getClub().equalsIgnoreCase(club)) {
                if (p.getHeight() == maxHeight) {
                    p.show();
                    found = true;
                }
            }
        }
        if (!found) System.out.println("No such club with this name");
    }
    public void showTotalYearlySalaryOfClub(String club) {
        boolean found = false;
        double total = 0.0;
        for (Player p : players) {
            if (p.getClub().equalsIgnoreCase(club)) {
                total += p.getWeeklySalary() * (365.0 / 7.0);
                found = true;
            }
        }
        if (!found) System.out.println("No such club with this name");
        else {
            System.out.println("Total Yearly Salary of Club " + club + " = " + total);
        }
    }
    public void showClubsByPosition (String position) {
        Map<String, Double> salary = new HashMap<>();
        for (Player p : players) {
            if (p.getPosition().equalsIgnoreCase(position)) {
                Double count = salary.getOrDefault(p.getClub(), 0.0);
                salary.put(p.getClub(), count + p.getWeeklySalary());
            }
        }
        List<Map.Entry<String, Double> > list = new LinkedList<Map.Entry<String, Double> >(salary.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double> >() {
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        for (HashMap.Entry<String, Double> entry : temp.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + " " + value);
        }
    }
}

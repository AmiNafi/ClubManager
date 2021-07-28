package server;

import sample.Player;
import util.LoginConfirm;
import util.NetworkUtil;
import util.Transfer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ReadThreadServer implements Runnable {
    private final Thread thr;
    private final NetworkUtil networkUtil;
    private String userName;
    private Server server;
    public ReadThreadServer(Server server, NetworkUtil networkUtil) {
        this.networkUtil = networkUtil;
        this.server = server;
        //HashMap<String, NetworkUtil> clientMap, ArrayList< Player > marketPlayers, HashMap< String, ArrayList<Player> > clubPlayers, HashMap<String, String> map,
        this.thr = new Thread(this);
        thr.start();
    }
    void sellAction(String from, Player toSell) {
        ArrayList <Player> arrayList = server.clubPlayers.getOrDefault(from, new ArrayList<>());
        int got = -1;
        for (int i = 0; i < arrayList.size(); i++) {
            Player player = arrayList.get(i);
            if (player.getName().equalsIgnoreCase(toSell.getName())) {
                got = i;
            }
        }
        if (got != -1) {
            arrayList.remove(got);
            toSell.setMarket(1);
            server.marketPlayers.add(toSell);
            server.clubPlayers.put(toSell.getName(), arrayList);
        }
    }

    void buyAction (String to, Player toBuy) {
        ArrayList < Player > arrayList2 = server.clubPlayers.getOrDefault(to, new ArrayList<>());
        toBuy.setClub(to);
        toBuy.setMarket(0);
        int got = -1;
        for (int i = 0; i < server.marketPlayers.size(); i++) {
            if (server.marketPlayers.get(i).getName().equalsIgnoreCase(toBuy.getName())) got = i;
        }
        if (got != -1) {
            arrayList2.add(toBuy);
            server.marketPlayers.remove(got);
            //server.clubPlayers.put(from, arrayList1);
            server.clubPlayers.put(to, arrayList2);
        }
    }
    void updateAll() {
        for (Map.Entry entry : server.clientMap.entrySet()) {
            Transfer t = new Transfer();
            NetworkUtil current = (NetworkUtil) entry.getValue();
            //System.out.println(server.clubPlayers.getOrDefault(entry.getKey(), new ArrayList<>()).size());
            ArrayList< Player > players = (server.clubPlayers.getOrDefault(entry.getKey(), new ArrayList<>()));
            for (Player p : players) t.getCurrentClubPlayers().add(p);
            for (Player p : server.marketPlayers) t.getMarketPlayers().add(p);
            try {
                current.write(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void run() {
        try {
            while (true) {
                Object o = networkUtil.read();
                if (o != null) {
                    if (o instanceof LoginConfirm) {
                        //LoginConfirm loginMessage = (LoginConfirm) o;
                        LoginConfirm loginMessage = (LoginConfirm) o;
                        if (loginMessage.getLogout()) {
                            server.clientMap.remove(loginMessage.getClubName());
                        }
                        if (loginMessage.getType() && server.userMap.get(loginMessage.getClubName()) == null) {
                            server.userMap.put(loginMessage.getClubName(), loginMessage.getPassword());
                        }
                        String password = server.userMap.get(loginMessage.getClubName());
                        if (loginMessage.getPassword().equals(password) && server.clientMap.getOrDefault(loginMessage.getClubName(), null) == null) {
                            loginMessage.setStatus(true);
                            ArrayList <Player> arrayList = server.clubPlayers.getOrDefault(loginMessage.getClubName(), new ArrayList<>());
                            for (Player p : arrayList) loginMessage.getCurrentClubPlayers().add(p);
                            for (Player p : server.marketPlayers) loginMessage.getMarketPlayers().add(p);
                            loginMessage.setMarketPlayers(server.marketPlayers);
                            server.clientMap.put(loginMessage.getClubName(), this.networkUtil);
                            userName = loginMessage.getClubName();
                        }
                        networkUtil.write(loginMessage);
                    }
                    else if (o instanceof Transfer) {
                        Transfer t = (Transfer) o;
                        if (t.getTo().equalsIgnoreCase("any")) {
                            sellAction(t.getFrom(), t.getPlayer());
                        }
                        else {
                            buyAction(t.getTo(), t.getPlayer());
                        }
                        updateAll();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (server.clientMap.getOrDefault(userName, null) != null) {
                    server.clientMap.remove(userName);
                }
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}




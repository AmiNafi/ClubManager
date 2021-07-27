package server;

import sample.FileLoad;
import sample.Player;
import sample.PlayerList;
import util.NetworkUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Server {

    private ServerSocket serverSocket;
    public HashMap<String, String> userMap;
    public HashMap<String, NetworkUtil> clientMap;
    public ArrayList<Player> marketPlayers;
    public ArrayList<Player> allPlayers;
    public HashMap< String, ArrayList<Player> > clubPlayers;
    PlayerList playerList;
    private FileLoad fileLoad;
    private Server server;
    Server() {
        server = this;
        fileLoad = new FileLoad();
        playerList = new PlayerList();
        marketPlayers = new ArrayList<>();
        clubPlayers = new HashMap<>();
        allPlayers = new ArrayList<>();
        userMap = new HashMap<>();
        clientMap = new HashMap<>();
        playerList.insertAll(fileLoad.readPlayers());
        allPlayers = (ArrayList<Player>) playerList.getPlayers();
        for (Player p : allPlayers) {
            if (p.getMarket() == 1) {
                marketPlayers.add(p);
            }
            else {
                ArrayList<Player> arrayList = clubPlayers.getOrDefault(p.getClub(), new ArrayList<>());
                arrayList.add(p);
                clubPlayers.put(p.getClub(), arrayList);
            }
        }

        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        new ReadThreadServer(server, networkUtil);
    }

    public HashMap<String, NetworkUtil> getClientMap() {
        return clientMap;
    }

    public static void main(String[] args) {
        new Server();
    }
}

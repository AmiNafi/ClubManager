package sample;

import java.io.*;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

public class FileLoad {
    private static final String INPUT_FILE_NAME = "players.txt";
    public List<Player> read () {
        List<Player> players = new ArrayList();
        try {
            //BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME))
            String line;
            File file = new File("src/database/players.txt");
            BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));

            while (true) {
                line = br.readLine();
                if (line == null) break;
                String[] tokens = line.split(",");
                Player p = new Player();
                p.setName(tokens[0]);
                p.setCountry(tokens[1]);
                p.setAge(Integer.parseInt(tokens[2]));
                p.setHeight(Double.parseDouble(tokens[3]));
                p.setClub(tokens[4]);
                p.setPosition(tokens[5]);
                p.setNumber(Integer.parseInt(tokens[6]));
                p.setWeeklySalary(Double.parseDouble(tokens[7]));
                players.add(p);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("An Error Occurred. Can't Read from file");
        }
        return players;
    }
    void write (List <Player> players) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(INPUT_FILE_NAME));
            for (Player p : players) {
                String line = p.getName();
                line += "," + p.getCountry();
                line += "," + p.getAge();
                line += "," + p.getHeight();
                line += "," + p.getClub();
                line += "," + p.getPosition();
                line += "," + p.getNumber();
                line += "," + p.getWeeklySalary();
                bw.write(line);
                bw.write("\n");
            }
            bw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            ///System.out.println("An Error Occurred. Can't write to file");
        }
    }
/*
    public static void main(String[] args) {
        FileLoad fl = new FileLoad();
        List <Player> players = new ArrayList<>();
        players = fl.read();
        fl.write(players);
    }
 */
}


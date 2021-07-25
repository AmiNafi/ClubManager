package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HomeController implements Initializable {

    @FXML
    private ImageView pageLogo;

    @FXML
    private Label pageTitle;

    @FXML
    private Button toggleButton;

    @FXML
    private TextField nameSearch;

    @FXML
    private TextField countrySearch;

    @FXML
    private TextField clubSearch;

    @FXML
    private TextField positionSearch;

    @FXML
    private TextField minSalary;

    @FXML
    private TextField maxSalary;

    @FXML
    private TextField minHeight;

    @FXML
    private TextField maxHeight;

    @FXML
    private TextField minAge;

    @FXML
    private TextField maxAge;

    @FXML
    private Label showCount;

    @FXML
    private HBox cardLayout;

    @FXML
    private GridPane playerContainer;

    @FXML
    void cancelFilterButton(ActionEvent event) {
        initFilter();
        showQuery();

    }
    boolean compareSearch (String given, String toMatch) {
        return (toMatch.replaceAll("\\s+","").equalsIgnoreCase(given.replaceAll("\\s+","")) || given.equalsIgnoreCase(""));
    }
    double parseFilter (String s) throws Exception {
        String temp = s.replaceAll(",", "").replaceAll("\\s+","");
        double ret;
        ret = Double.parseDouble(temp);
        return ret;
    }
    @FXML
    void cancelSearchButton(ActionEvent event) {
        initSearch();
        showQuery();
    }
    @FXML
    void filterButton(ActionEvent event) {
        String temp;
        try {
            fMaxHeight = parseFilter(maxHeight.getText());
        } catch (Exception e) {
            //e.printStackTrace();
            fMaxHeight = inf;
            maxHeight.setText("");
        }
        try {
            fMinHeight = parseFilter(minHeight.getText());
        } catch (Exception e) {
            //e.printStackTrace();
            fMinHeight = 0.0;
            minHeight.setText("");
        }
        try {
            fMaxSalary = parseFilter(maxSalary.getText());
        } catch (Exception e) {
            //e.printStackTrace();
            fMaxSalary = inf;
            maxSalary.setText("");
        }
        try {
            fMinSalary = parseFilter(minSalary.getText());
        } catch (Exception e) {
            //e.printStackTrace();
            fMinSalary = 0.0;
            minSalary.setText("");
        }
        try {
            fMaxAge = parseFilter(maxAge.getText());
        } catch (Exception e) {
            //e.printStackTrace();
            fMaxAge = inf;
            maxAge.setText("");
        }
        try {
            fMinAge = parseFilter(minAge.getText());
        } catch (Exception e) {
            //e.printStackTrace();
            fMinAge = 0.0;
            minAge.setText("");
        }
        showQuery();
    }

    @FXML
    void searchButton(ActionEvent event) {
        sName = nameSearch.getText();
        sCountry = countrySearch.getText();
        sClub = clubSearch.getText();
        sPosition = positionSearch.getText();
        showQuery();
    }
    @FXML
    void togglePage(ActionEvent event) {

    }
    private final double inf = 1000000007.0;
    private Main main;
    private ArrayList<Player> marketPlayers;
    private ArrayList<Player> currentPlayers;
    private FileLoad fileLoad;
    private PlayerList playerList;
    private int state;
    private double fMinSalary, fMaxSalary, fMinHeight, fMaxHeight, fMinAge, fMaxAge;
    String sName, sClub, sCountry, sPosition;

    public Main getMain() {
        return main;
    }
    void initFilter () {
        fMinSalary = 0.0; fMaxSalary = inf; fMinHeight = 0.0; fMaxHeight = inf; fMinAge = 0.0; fMaxAge = inf;
    }
    void initSearch () {
        sName = ""; sClub = ""; sCountry = ""; sPosition = "";
        nameSearch.setText("");
        clubSearch.setText("");
        countrySearch.setText("");
        positionSearch.setText("");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initFilter();
        initSearch();
        //this.start();
        fileLoad = new FileLoad();
        playerList = new PlayerList();
        playerList.insertAll(fileLoad.read());
        marketPlayers = new ArrayList<>();
        marketPlayers = (ArrayList<Player>) playerList.getPlayers();
        currentPlayers = new ArrayList<>();
        currentPlayers = (ArrayList<Player>) playerList.getPlayers();
        showData();
    }
    boolean processPlayer (Player p) {
        boolean ok = true;
        ok = ok && compareSearch(sName, p.getName());
        ok = ok && compareSearch(sCountry, p.getCountry());
        ok = ok && compareSearch(sClub, p.getClub());
        ok = ok && compareSearch(sPosition, p.getPosition());
        ok = ok && (p.getWeeklySalary() <= fMaxSalary && p.getWeeklySalary() >= fMinSalary);
        ok = ok && (p.getHeight() <= fMaxHeight && p.getHeight() >= fMinHeight);
        ok = ok && (p.getAge() <= fMaxAge && p.getAge() >= fMinAge);
        return  ok;
    }
    void showQuery () {
        try {
            cardLayout.getChildren().clear();
            int cnt = 0;
            for (Player player : currentPlayers) {
                if (processPlayer(player)) {
                    cnt++;
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("card.fxml"));
                    HBox cardBox = fxmlLoader.load();
                    CardController cardController = fxmlLoader.getController();
                    cardController.setData(player);
                    cardLayout.getChildren().add(cardBox);
                }
            }
            if (cnt > 1) showCount.setText(cnt + " Players Found");
            else if (cnt == 1) showCount.setText("1 Player Found");
            else showCount.setText("No Player Found");
        } catch (Exception e) {
            System.out.println("Query Show Problem");
            System.out.println(e);
        }
    }
    void showData () {

        int col = 0, row = 1;
        try {
            showQuery();
            for (Player player : currentPlayers) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("player.fxml"));
                VBox playerBox = fxmlLoader.load();
                PlayerController playerController = fxmlLoader.getController();
                playerController.setData(player);
                if (col == 6) {
                    col = 0;
                    ++row;
                }
                playerContainer.add(playerBox, col++, row);
                GridPane.setMargin(playerBox, new Insets(5));
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

}

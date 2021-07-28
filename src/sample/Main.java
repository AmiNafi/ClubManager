package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import util.NetworkUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Main extends Application {
    private Stage stage;
    private NetworkUtil networkUtil;
    private String clientName;
    private HomeController homeController;
    private Image clientLogo;
    public Stage getStage() {
        return stage;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClientLogo(Image clientLogo) {
        this.clientLogo = clientLogo;
    }

    public Image getClientLogo() {
        return clientLogo;
    }

    public String getClientName() {
        return clientName;
    }

    public HomeController getHomeController() {
        return homeController;
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        connectToServer();
        showLoginPage();
    }

    public void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        networkUtil = new NetworkUtil(serverAddress, serverPort);
        new ReadThread(this);
    }
    public void showLoginPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LoginController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
    public void showRegistrationPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("registration.fxml"));
        Parent root = loader.load();

        // Loading the controller
        RegistrationController controller = loader.getController();
        controller.setMain(this);
        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
    public void showHomePage(String clubName, ArrayList <Player> marketPlayers, ArrayList <Player> clubPlayers) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("home.fxml"));
        Parent root = loader.load();
        HomeController controller = loader.getController();
        homeController = controller;
        controller.setMain(this);
        controller.setData(clubName, clubPlayers, marketPlayers, 0);
        stage.setResizable(false);
        //stage.initStyle(StageStyle.UNDECORATED);
        try {
            Image image =  new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/ic_main.jpg")));
            stage.getIcons().add(image);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        stage.setTitle("Club Manager");
        stage.setScene(new Scene(root, 1050, 750));
        stage.setOnCloseRequest(event -> {
            System.exit(0);
        });
        stage.show();

    }
    public void showAlertIncorrectPassword() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }
    public void showAlertIncorrectInformation() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Information");
        alert.setHeaderText("Incorrect Information");
        alert.setContentText("Provided Information is not correct.");
        alert.showAndWait();
    }
    public void showAllertAlreadyLoggedIn() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("User Already Logged In");
        alert.setHeaderText("User Already Logged IN");
        alert.setContentText("Please Log Out First");
        alert.showAndWait();
    }
    public static void main(String[] args) {
        // This will launch the JavaFX application
        launch(args);
    }
}

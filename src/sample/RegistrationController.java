package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import util.LoginConfirm;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {

    @FXML
    private TextField clubName;

    @FXML
    private PasswordField password;

    @FXML
    void cancel(ActionEvent event) {
        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void register(ActionEvent event) {
        String clubName = this.clubName.getText();
        String password = this.password.getText();
        loginMessage.setClubName(clubName);
        loginMessage.setPassword(password);
        loginMessage.setType(true);
        try {
            main.getNetworkUtil().write(loginMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }
    private LoginConfirm loginMessage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginMessage = new LoginConfirm();
    }
}

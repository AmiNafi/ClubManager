package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.LoginConfirm;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;

    @FXML
    void loginAction(ActionEvent event) {
        String clubName = userText.getText();
        String password = passwordText.getText();
        LoginConfirm loginMessage = new LoginConfirm();
        loginMessage.setClubName(clubName);
        loginMessage.setPassword(password);
        try {
            main.getNetworkUtil().write(loginMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void registerAction(ActionEvent event) {
        try {
            main.showRegistrationPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }
}

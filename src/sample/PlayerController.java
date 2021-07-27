package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.Transfer;

import java.io.IOException;
import java.util.Optional;

public class PlayerController {

    @FXML
    private ImageView profilePic;

    @FXML
    private Label name;

    @FXML
    private Label club;

    @FXML
    private Button transferButton;

    @FXML
    void transferAction(ActionEvent event) {
       // System.out.println("Transfer of " + player.getName());
        Transfer transfer = new Transfer();
        if (player.getMarket() == 1) {
            transfer.setPlayer(this.player);
            transfer.setFrom(player.getClub());
            transfer.setTo(main.getClientName());
        }
        else {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("playerprice.fxml"));
                loader.setLocation(getClass().getResource("playerprice.fxml"));
                DialogPane dialogPane = loader.load();
                PlayerPriceController controller = loader.getController();
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setDialogPane(dialogPane);
                Optional<ButtonType> clickedButton = dialog.showAndWait();
                if (clickedButton.get() == ButtonType.OK) {
                    System.out.println(controller);
                    String s = controller.getPriceText().getText();
                    s = s.replaceAll(",", "").replaceAll("\\s+","");
                    try {
                        double price = Double.parseDouble(s);
                        player.setPrice(price);
                        transfer.setPlayer(this.player);
                        transfer.setFrom(player.getClub());
                        transfer.setTo("any");
                    } catch (Exception e) {
                        main.showAlertIncorrectInformation();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            //System.out.println(" Type " + transfer.getTo());
            //System.out.println(main.getNetworkUtil() + " and ");
            main.getNetworkUtil().write(transfer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Player player;
    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setData (Player player) {
        this.player = player;
        try {
            Image image = new Image(getClass().getResourceAsStream(player.getProfilePicSrc()));
            profilePic.setImage(image);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Image Exception");
        }
        name.setText(player.getName());
        club.setText(player.getClub());
        if (this.player.getMarket() == 1) {
            transferButton.setText("Buy");
        }
        else transferButton.setText("Sell");
    }
}

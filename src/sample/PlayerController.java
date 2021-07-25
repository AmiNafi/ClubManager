package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayerController {
    @FXML
    private ImageView profilePic;

    @FXML
    private Label name;

    @FXML
    private Label club;
    public void setData (Player player) {
        try {
            Image image = new Image(getClass().getResourceAsStream(player.getProfilePicSrc()));
            profilePic.setImage(image);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Image Exception");
        }
        name.setText(player.getName());
        club.setText(player.getClub());
    }
}

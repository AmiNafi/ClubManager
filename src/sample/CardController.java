package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class CardController {
    @FXML
    private HBox box;

    @FXML
    private ImageView profilePic;


    @FXML
    private HBox priceBox;

    @FXML
    private Label name;

    @FXML
    private Label price;

    @FXML
    private Label country;

    @FXML
    private Label club;

    @FXML
    private Label position;

    @FXML
    private Label salary;

    @FXML
    private Label age;

    @FXML
    private Label height;

    @FXML
    private Label number;

    private Main main;

    public Main getMain() {
        return main;
    }

    public void setData (Player player){
        try {
            Image image = new Image(getClass().getResourceAsStream(player.getProfilePicSrc()));
            profilePic.setImage(image);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Card image exception");
        }
        name.setText(player.getName());
        price.setText(Integer.toString(player.getPrice()));
        country.setText("Country: " + player.getCountry());
        club.setText("Club: " + player.getClub());
        position.setText("Position: " + player.getPosition());
        salary.setText("Weekly Salary: " + player.getWeeklySalary());
        age.setText("Age " + player.getAge());
        height.setText("Height " + player.getHeight());
        number.setText("Player Number " + player.getNumber());
    }
}

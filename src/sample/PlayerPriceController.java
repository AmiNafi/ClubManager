package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PlayerPriceController {
    @FXML
    private TextField priceText;

    public TextField getPriceText() {
        return priceText;
    }
}

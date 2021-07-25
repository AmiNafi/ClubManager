package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Objects;

public class Main extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        showHomePage();
    }


    public void showHomePage() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("home.fxml"));
        Parent root = loader.load();
        HomeController controller = loader.getController();
        //controller.join();
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
    public static void main(String[] args) {
        // This will launch the JavaFX application
        launch(args);
    }
}

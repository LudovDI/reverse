package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ReversiGraphic.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 600, 600));
        stage.setTitle("Reversi");
        stage.show();
    }
}

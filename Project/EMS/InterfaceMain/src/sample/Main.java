package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.dto.GlobalsDTO;
import sample.util.EmailClass;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Main extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Loading main menu window");

        window = primaryStage;

        // Start off with main menu
        Parent root = FXMLLoader.load(getClass().getResource("resources/view/main_menu.fxml"));
        window.setTitle("PULSE EVENTS");

        // Load icon using classpath resource
        Image icon = new Image(getClass().getResource("resources/images/icon.png").toExternalForm());
        window.getIcons().add(icon);

        window.setScene(new Scene(root, 900, 600));

        Font.loadFont(getClass().getResourceAsStream("resources/Fonts/Alifiyah.otf"), 10);
        Font.loadFont(getClass().getResourceAsStream("resources/Fonts/Honeymoon Avenue Script Demo.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("resources/Fonts/ArchivoNarrow-Regular.ttf"), 10);
        Font.loadFont(getClass().getResourceAsStream("resources/Fonts/JuliusSansOne-Regular.ttf"), 10);

        window.show();
    }


    public void setScene(Scene scene) {
        window.setScene(scene);
    }

    // configuring database details
    public static void setMySQL() {
        GlobalsDTO.setDb_name("ems");
        GlobalsDTO.setDb_username("root");
        GlobalsDTO.setDb_pass("shehan@12");
    }

    // configuring email details
    public static void setEmailDetails() {
        EmailClass.init(System.getProperties(), "shehananujayaownme@gmail.com", "own@shehanNewtik");
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        setMySQL();
        setEmailDetails();
        launch(args);
    }
}

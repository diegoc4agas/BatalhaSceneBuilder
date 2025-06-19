package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;  // Alterei de AnchorPane para VBox
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carrega o FXML da tela inicial
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/1-TelaDeInicio.fxml"));

        // Alterei para VBox, pois o FXML usa VBox como root
        VBox root = loader.load();

        // Define o título global da janela
        primaryStage.setTitle("Jogo de RPG - Devs do Caos");

        // Cria a cena e adiciona o conteúdo
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        // Exibe a janela
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

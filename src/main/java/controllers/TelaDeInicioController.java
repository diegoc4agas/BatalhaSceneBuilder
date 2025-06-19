package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaDeInicioController {

    @FXML
    public Button botaoJogar;

    @FXML
    public void initialize() {
        botaoJogar.setOnAction(event -> {
            try {
                // Carrega a próxima tela (Seleção de Personagem)
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/3-TelaDeJogador.fxml"));
                Scene scene = new Scene(loader.load());

                // Muda a cena na janela principal
                Stage stage = (Stage) botaoJogar.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

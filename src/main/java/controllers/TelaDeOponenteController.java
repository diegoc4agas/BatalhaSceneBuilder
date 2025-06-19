package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaDeOponenteController {

    @FXML
    private Label infoBox; // Caixa de informações personalizada

    @FXML
    private ImageView imagemHydra;

    @FXML
    private ImageView imagemMedusa;

    @FXML
    private ImageView imagemOrc;

    private String personagemSelecionado;
    private String oponenteSelecionado;

    public void definirPersonagemSelecionado(String personagem) {
        this.personagemSelecionado = personagem;
    }

    @FXML
    private Button botaoVoltar;

    @FXML
    public void initialize() {
        configurarCaixaDeInformacoes();

        // Configurar botão voltar
        botaoVoltar.setOnAction(event -> voltarParaTelaDeJogador());
    }

    private void voltarParaTelaDeJogador() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/3-TelaDeJogador.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) botaoVoltar.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela de jogador: " + e.getMessage());
        }
    }

    private void configurarCaixaDeInformacoes() {
        // Tooltip para a Hydra
        imagemHydra.setOnMouseEntered(event -> {
            /*infoBox.setText("🐉 Hydra\nHabilidade: Recuperação (+10 vida por turno)\nEfeito: Perde o turno ao se curar.");
            posicionarCaixa(100, 120); // Define posição fixa para a imagem
            infoBox.setVisible(true);*/
        });
        imagemHydra.setOnMouseExited(event -> infoBox.setVisible(false));
        imagemHydra.setOnMouseClicked(event -> selecionarOponente("Hydra"));

        // Tooltip para a Medusa
        imagemMedusa.setOnMouseEntered(event -> {
            /*infoBox.setText("👁️‍🗨️ Medusa\nHabilidade: Paralisia\nEfeito: 25% de chance de impedir ataques do inimigo.");
            posicionarCaixa(300, 120); // Define posição fixa para a imagem
            infoBox.setVisible(true);*/
        });
        imagemMedusa.setOnMouseExited(event -> infoBox.setVisible(false));
        imagemMedusa.setOnMouseClicked(event -> selecionarOponente("Medusa"));

        // Tooltip para o Orc
        imagemOrc.setOnMouseEntered(event -> {
            /*infoBox.setText("⚔️ Orc\nHabilidade: Força Bruta\nEfeito: Ataques devastadores, mas perde 10 de vida por turno.");
            posicionarCaixa(500, 120); // Define posição fixa para a imagem
            infoBox.setVisible(true);*/
        });
        imagemOrc.setOnMouseExited(event -> infoBox.setVisible(false));
        imagemOrc.setOnMouseClicked(event -> selecionarOponente("Orc"));
    }

    private void posicionarCaixa(double x, double y) {
        infoBox.setLayoutX(x);
        infoBox.setLayoutY(y);
        infoBox.setMouseTransparent(true); // Garante que a caixa não bloqueie cliques
    }

    private void selecionarOponente(String oponente) {
        oponenteSelecionado = oponente;
        irParaTelaPreBatalha();
    }

    private void irParaTelaPreBatalha() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/5-TelaPreBatalha.fxml"));
            Scene scene = new Scene(loader.load());

            // Passa as informações para o controlador da tela de pré-batalha
            TelaPreBatalhaController controladorPreBatalha = loader.getController();
            controladorPreBatalha.definirConfronto(personagemSelecionado, oponenteSelecionado);

            Stage stage = (Stage) imagemHydra.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

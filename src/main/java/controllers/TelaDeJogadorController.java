package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.io.IOException;

public class TelaDeJogadorController {

    @FXML
    private Label infoBox; // Caixa de informações para os infoboxes

    @FXML
    private ImageView guerreiroImagem;

    @FXML
    private ImageView magoImagem;

    @FXML
    private ImageView arqueiroImagem;

    private String personagemSelecionado;

    @FXML
    private Button botaoVoltar;

    @FXML
    public void initialize() {
        configurarCaixaDeInformacoes();

        // Configurar botão voltar
        botaoVoltar.setOnAction(event -> voltarParaMenuPrincipal());
    }

    private void voltarParaMenuPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/1-TelaDeInicio.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) botaoVoltar.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela de menu: " + e.getMessage());
        }
    }

    private void configurarCaixaDeInformacoes() {
        // Configurar comportamento para o Guerreiro
        /*guerreiroImagem.setOnMouseEntered(event -> {
            infoBox.setText("⚔️ Guerreiro\nHabilidade Especial: Nenhuma\nEfeito: Ataques constantes sem penalidade.");
            posicionarCaixa(320, 120); // Posição ajustada para a imagem do Guerreiro
            infoBox.setVisible(true);
        });
        guerreiroImagem.setOnMouseExited(event -> infoBox.setVisible(false));

        // Configurar comportamento para o Arqueiro
        arqueiroImagem.setOnMouseEntered(event -> {
            infoBox.setText("🏹 Arqueiro\nHabilidade Especial: Chance de Crítico\nEfeito: 14% de chance de causar dano x2.");
            posicionarCaixa(120, 120); // Posição ajustada para a imagem do Arqueiro
            infoBox.setVisible(true);
        });
        arqueiroImagem.setOnMouseExited(event -> infoBox.setVisible(false));

        // Configurar comportamento para o Mago
        magoImagem.setOnMouseEntered(event -> {
            infoBox.setText("🔥 Mago\nHabilidade Especial: Cura (+30 de vida)\nEfeito: Perde o turno ao curar.");
            posicionarCaixa(550, 120); // Posição ajustada para a imagem do Mago
            infoBox.setVisible(true);
        });
        magoImagem.setOnMouseExited(event -> infoBox.setVisible(false));*/
        configurarSelecaoDePersonagens();
    }

    private void configurarSelecaoDePersonagens() {
        // Seleção do Arqueiro
        arqueiroImagem.setOnMouseClicked(event -> selecionarPersonagem("Arqueiro"));

        // Seleção do Guerreiro
        guerreiroImagem.setOnMouseClicked(event -> selecionarPersonagem("Guerreiro"));

        // Seleção do Mago
        magoImagem.setOnMouseClicked(event -> selecionarPersonagem("Mago"));
    }

    private void selecionarPersonagem(String personagem) {
        personagemSelecionado = personagem;
        irParaTelaDeOponente();
    }

    private void posicionarCaixa(double x, double y) {
        infoBox.setLayoutX(x);
        infoBox.setLayoutY(y);
        infoBox.setMouseTransparent(true); // Garante que não bloqueia cliques
    }

    private void irParaTelaDeOponente() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/4-TelaDeOponente.fxml"));
            Scene scene = new Scene(loader.load());

            TelaDeOponenteController controladorOponente = loader.getController();
            controladorOponente.definirPersonagemSelecionado(personagemSelecionado);

            Stage stage = (Stage) arqueiroImagem.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela de oponente: " + e.getMessage());
        }
    }

}

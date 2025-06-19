package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaPreBatalhaController {

    @FXML
    private Button iniciarBatalha;

    @FXML
    private Button botaoVoltar;

    @FXML
    private Label nomePersonagem;

    @FXML
    private Label nomeOponente;

    @FXML
    private ImageView imagemPersonagem;

    @FXML
    private ImageView imagemOponente;

    @FXML
    private Label infoBox; // Caixa de informações personalizada

    private String personagem;
    private String oponente;

    private String caminhoImagemPersonagem;
    private String caminhoImagemOponente;

    public void definirConfronto(String personagem, String oponente) {
        this.personagem = personagem;
        this.oponente = oponente;

        // Atualiza os labels com os nomes
        nomePersonagem.setText(personagem);
        nomeOponente.setText(oponente);

        // Define os caminhos das imagens
        caminhoImagemPersonagem = "/img/Personagens/" + personagem + ".png";
        caminhoImagemOponente = "/img/Oponentes/" + oponente + ".png";

        try {
            // Carrega as imagens
            Image imagemDoPersonagem = carregarImagem(caminhoImagemPersonagem);
            Image imagemDoOponente = carregarImagem(caminhoImagemOponente);

            if (imagemDoPersonagem != null && imagemDoOponente != null) {
                imagemPersonagem.setImage(imagemDoPersonagem);
                imagemOponente.setImage(imagemDoOponente);

            }

            // Configura os tooltips dos personagens
            configurarTooltipPersonagens();

        } catch (Exception e) {
            System.err.println("Erro ao carregar as imagens:");
            System.err.println("Personagem: " + caminhoImagemPersonagem);
            System.err.println("Oponente: " + caminhoImagemOponente);
            e.printStackTrace();
        }
    }

    private Image carregarImagem(String caminho) {
        try {
            if (getClass().getResourceAsStream(caminho) == null) {
                System.err.println("Imagem não encontrada no caminho: " + caminho);
                return null;
            }
            return new Image(getClass().getResourceAsStream(caminho));
        } catch (Exception e) {
            System.err.println("Erro ao carregar a imagem: " + caminho);
            e.printStackTrace();
            return null;
        }
    }

    private void configurarTooltipPersonagens() {
        // Tooltip para o personagem do jogador
        imagemPersonagem.setOnMouseEntered(event -> {
            /*infoBox.setText("🔥 " + personagem + "\nHabilidade: Detalhes únicos do personagem.");
            posicionarCaixa(80, 120); // Posição fixa próxima ao jogador
            infoBox.setVisible(true);*/
        });
        imagemPersonagem.setOnMouseExited(event -> infoBox.setVisible(false));

        // Tooltip para o oponente
        imagemOponente.setOnMouseEntered(event -> {
            /*infoBox.setText("⚔️ " + oponente + "\nHabilidade: Informações específicas do oponente.");
            posicionarCaixa(500, 120); // Posição fixa próxima ao oponente
            infoBox.setVisible(true);*/
        });
        imagemOponente.setOnMouseExited(event -> infoBox.setVisible(false));
    }

    private void posicionarCaixa(double x, double y) {
        // Define a posição fixa da caixa de informações
        infoBox.setLayoutX(x);
        infoBox.setLayoutY(y);
    }

    @FXML
    public void initialize() {
        // Configura os eventos dos botões
        iniciarBatalha.setOnAction(event -> carregarTelaDeBatalha());
        botaoVoltar.setOnAction(event -> voltarParaTelaDePersonagem());
    }

    private void carregarTelaDeBatalha() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/6-TelaDeBatalha.fxml"));
            Scene scene = new Scene(loader.load());

            // Obtém o controlador da nova tela
            TelaDeBatalhaController controller = loader.getController();

            // Passa os dados para a Tela de Batalha
            controller.setNomePersonagem(personagem);
            controller.setNomeOponente(oponente);
            controller.setImagemPersonagem(caminhoImagemPersonagem);
            controller.setImagemOponente(caminhoImagemOponente);

            // Define a nova cena
            Stage stage = (Stage) iniciarBatalha.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela de batalha: " + e.getMessage());
        }
    }

    private void voltarParaTelaDePersonagem() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/3-TelaDeJogador.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) botaoVoltar.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao voltar para a tela de personagem: " + e.getMessage());
        }
    }
}

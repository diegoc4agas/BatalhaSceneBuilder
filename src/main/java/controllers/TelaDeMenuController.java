package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaDeMenuController {

    @FXML
    private Text textoInformacoes; // Área de texto da tela à direita

    @FXML
    private Button botaoSair; // Botão de sair

    @FXML
    private Button botaoBatalhar; // Botão para iniciar batalha

    // Método que atualiza o conteúdo da "tela pop-up" ao clicar em um botão
    @FXML
    private void mudarConteudoTela(javafx.event.ActionEvent event) {
        Button botaoClicado = (Button) event.getSource();
        switch (botaoClicado.getId()) {
            case "botaoBatalhar":
                textoInformacoes.setText("Prepare-se para a batalha!\n\nEscolha seus personagens e enfrente poderosos inimigos.");
                break;
            case "botaoConfiguracoes":
                textoInformacoes.setText("Configurações do jogo:\n\n- Ajustar volume\n- Alterar gráficos\n- Redefinir controles.");
                break;
            case "botaoComoJogar":
                textoInformacoes.setText("Como jogar:\n\n1. Escolha seus dados para atacar.\n2. Reduza a vida do inimigo a 0.\n3. Proteja sua vida e vença!");
                break;
            case "botaoRanking":
                textoInformacoes.setText("Ranking dos Jogadores:\n\n1º - Jogador X\n2º - Jogador Y\n3º - Jogador Z.");
                break;
            case "botaoSair":
                fecharJogo();
                break;
        }
    }

    // Método para carregar a tela de seleção de personagens
    @FXML
    private void carregarTelaDeJogador() {
        try {
            // Carrega o arquivo FXML da tela de seleção de personagens
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/3-TelaDeJogador.fxml"));
            Scene scene = new Scene(loader.load());

            // Obtém o Stage atual e define a nova cena
            Stage stage = (Stage) botaoBatalhar.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // Impressão de erro detalhada
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela de seleção de personagens: " + e.getMessage());
        }
    }

    // Método para fechar o jogo
    @FXML
    private void fecharJogo() {
        Stage stage = (Stage) botaoSair.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() {
        // Ação direta para o botão de batalha (caso não esteja configurado no FXML)
        botaoBatalhar.setOnAction(event -> carregarTelaDeJogador());
    }
}

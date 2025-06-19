package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.util.Optional;
import java.util.Random;

public class TelaDeBatalhaController {

    @FXML
    private Label nomePersonagem;

    @FXML
    private Label nomeOponente;

    @FXML
    private ProgressBar barraVidaJogador;

    @FXML
    private ProgressBar barraVidaOponente;

    @FXML
    private ImageView imagemPersonagem;

    @FXML
    private ImageView imagemOponente;

    @FXML
    private Label labelVidaJogador;

    @FXML
    private Label labelVidaOponente;

    @FXML
    private ImageView D6;

    @FXML
    private ImageView D10;

    @FXML
    private ImageView D20;

    @FXML
    private Label resultadoDado;

    @FXML
    private Button botaoPause;

    @FXML
    private Label infoBox;

    private final Random random = new Random();

    private double vidaJogador = 100;
    private double vidaOponente = 100;
    private int ataqueJogador = 5;
    private int ataqueOponente = 10;

    private boolean tooltipVisivel = false; // Variável para controlar o estado do tooltip

    // Métodos para receber dados da Tela de Pré-Batalha
    public void setNomePersonagem(String nome) {
        nomePersonagem.setText(nome);
    }

    public void setNomeOponente(String nome) {
        nomeOponente.setText(nome);
    }

    public void setImagemPersonagem(String caminhoImagem) {
        try {
            Image imagem = new Image(getClass().getResourceAsStream(caminhoImagem));
            imagemPersonagem.setImage(imagem);
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem do personagem: " + caminhoImagem);
            e.printStackTrace();
        }
    }

    public void setImagemOponente(String caminhoImagem) {
        try {
            Image imagem = new Image(getClass().getResourceAsStream(caminhoImagem));
            imagemOponente.setImage(imagem);
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem do oponente: " + caminhoImagem);
            e.printStackTrace();
        }
    }

    /*private void carregarTelaDeMenu() {
        try {
            // Carrega o FXML da tela de menu
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/2-TelaDeMenu.fxml"));
            Scene scene = new Scene(loader.load());

            // Obtém o Stage atual
            Stage stage = (Stage) barraVidaJogador.getScene().getWindow();

            // Define a nova cena no Stage
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela de menu.");
        }
    }*/

    private void exibirDialogPause() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Pause");
        alert.setHeaderText(null);
        alert.setContentText("Jogo pausado. Escolha uma opção:");

        // Define os botões do diálogo
        ButtonType botaoContinuar = new ButtonType("Continuar");
        ButtonType botaoSair = new ButtonType("Sair do Jogo");

        alert.getButtonTypes().setAll(botaoContinuar, botaoSair);

        // Vincula ao Stage principal
        Stage stagePrincipal = (Stage) barraVidaJogador.getScene().getWindow();
        alert.initOwner(stagePrincipal);

        // Exibe o diálogo e captura a escolha do jogador
        Optional<ButtonType> resultado = alert.showAndWait();
        if (resultado.isPresent()) {
            if (resultado.get() == botaoContinuar) {
                // O jogador escolheu continuar; apenas fecha o diálogo
            } /*else if (resultado.get() == botaoMenu) {
                carregarTelaDeMenu(); // Alterado para carregar a tela de menu
            }*/ else if (resultado.get() == botaoSair) {
                fecharJogo(); // Fecha o jogo
            }
        }
    }

    @FXML
    public void initialize() {
        // Configurações iniciais
        botaoPause.setOnAction(event -> exibirDialogPause());

        // Ajusta o `infoBox` para ignorar eventos de mouse
        infoBox.setMouseTransparent(true);

        // Configura os eventos de clique nos dados
        D6.setOnMouseClicked(event -> realizarCombate(6));
        D10.setOnMouseClicked(event -> realizarCombate(10));
        D20.setOnMouseClicked(event -> realizarCombate(20));

        // Configura tooltips
        //configurarTooltipsPersonagens();
        //configurarTooltipsDados();
    }

    /*private void configurarTooltipsPersonagens() {
        imagemPersonagem.setOnMouseEntered(event -> {
            exibirInfoBox("🔥 " + nomePersonagem.getText() + "\nHabilidade: Ataques precisos com vantagem em D20.", 80, 120);
        });
        imagemPersonagem.setOnMouseExited(event -> ocultarInfoBox());

        imagemOponente.setOnMouseEntered(event -> {
            exibirInfoBox("⚔️ " + nomeOponente.getText() + "\nHabilidade: Força Bruta que amplifica D6 e D10.", 500, 120);
        });
        imagemOponente.setOnMouseExited(event -> ocultarInfoBox());
    }

    private void configurarTooltipsDados() {
        D6.setOnMouseEntered(event -> {
            exibirInfoBox("🎲 Dado D6\nDano entre 1 e 6.\n100% de chance de acertar.", 268, 440);
        });
        D6.setOnMouseExited(event -> ocultarInfoBox());

        D10.setOnMouseEntered(event -> {
            exibirInfoBox("🎲 Dado D10\nDano entre 1 e 10.\nAlta precisão com 100% de chance de acerto.", 371, 440);
        });
        D10.setOnMouseExited(event -> ocultarInfoBox());

        D20.setOnMouseEntered(event -> {
            exibirInfoBox("🎲 Dado D20\nDano entre 1 e 20.\nRequer alta precisão para acertos.", 477, 440);
        });
        D20.setOnMouseExited(event -> ocultarInfoBox());
    }

    private void exibirInfoBox(String texto, double x, double y) {
        infoBox.setText(texto);
        posicionarCaixa(x, y);
        infoBox.setVisible(true);
        tooltipVisivel = true;
        infoBox.setMouseTransparent(true);

    }

    private void ocultarInfoBox() {
        infoBox.setVisible(false);
        tooltipVisivel = false;
    }*/

    private void posicionarCaixa(double x, double y) {
        infoBox.setLayoutX(x);
        infoBox.setLayoutY(y);
    }

    /*private void realizarAtaque(int tipoDado) {
        int danoJogador = ataqueJogador + random.nextInt(tipoDado) + 1;

        if (tipoDado == 20 && random.nextInt(100) < 20) {
            resultadoDado.setText("ERRO! " + nomePersonagem.getText() + " falhou o ataque!");
            return;
        }

        vidaOponente -= danoJogador;
        resultadoDado.setText(nomePersonagem.getText() + " atingiu " + nomeOponente.getText() + " com " + danoJogador + " de dano!");
        atualizarVidaOponente(Math.max(vidaOponente, 0));

        if (vidaOponente <= 0) {
            exibirDialogResultado(true);
            return;
        }

        turnoDoInimigo();
    }

    private void turnoDoInimigo() {
        int danoOponente = ataqueOponente + random.nextInt(6) + 1;
        vidaJogador -= danoOponente;

        resultadoDado.setText(nomeOponente.getText() + " atingiu " + nomePersonagem.getText() + " com " + danoOponente + " de dano!");
        atualizarVidaJogador(Math.max(vidaJogador, 0));

        if (vidaJogador <= 0) {
            exibirDialogResultado(false);
        }
    }*/

    private void realizarCombate(int tipoDado) {
        StringBuilder log = new StringBuilder();

        // Ataque do jogador
        int danoJogador = ataqueJogador + random.nextInt(tipoDado) + 1;

        if (tipoDado == 20 && random.nextInt(100) < 20) {
            resultadoDado.setText("ERRO! " + nomePersonagem.getText() + " falhou o ataque!");
            return;
        }

        vidaOponente -= danoJogador;
        log.append(nomePersonagem.getText() + " atingiu " + nomeOponente.getText() +
                " com " + danoJogador + " de dano!\n");

        atualizarVidaOponente(Math.max(vidaOponente, 0));

        if (vidaOponente <= 0) {
            resultadoDado.setText(log.toString());
            exibirDialogResultado(true);
            return;
        }

        // Contra-ataque do oponente
        int danoOponente = ataqueOponente + random.nextInt(6) + 1;
        vidaJogador -= danoOponente;

        log.append(nomeOponente.getText() + " contra-atacou " + nomePersonagem.getText() +
                " com " + danoOponente + " de dano!\n");

        resultadoDado.setText(log.toString());
        atualizarVidaJogador(Math.max(vidaJogador, 0));

        if (vidaJogador <= 0) {
            exibirDialogResultado(false);
        }
    }

    private void exibirDialogResultado(boolean venceu) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Resultado da Batalha");
        alert.setHeaderText(null);
        alert.setContentText(venceu ? "VOCÊ VENCEU, PARABÉNS!" : "VOCÊ PERDEU, TENTE NOVAMENTE!");

        // Botões de ação
        ButtonType botaoNovaPartida = new ButtonType("Nova Partida");
        ButtonType botaoRevanche = new ButtonType("Revanche");
        ButtonType botaoSair = new ButtonType("Sair do Jogo");
        alert.getButtonTypes().setAll(botaoNovaPartida, botaoRevanche, botaoSair);

        // Vincula ao Stage principal
        Stage stagePrincipal = (Stage) barraVidaJogador.getScene().getWindow();
        alert.initOwner(stagePrincipal);

        // Exibe o diálogo e captura resultado
        Optional<ButtonType> resultado = alert.showAndWait();
        if (resultado.isPresent()) {
            if (resultado.get() == botaoNovaPartida) {
                carregarTelaDePersonagem();
            } else if (resultado.get() == botaoRevanche) {
                reiniciarBatalha();
            } /*else if (resultado.get() == botaoMenu) {
                carregarTelaDeMenu(); // Novo método para carregar tela de menu
            }*/ else if (resultado.get() == botaoSair) {
                fecharJogo();
            }
        }
    }


    private void carregarTelaDePersonagem() {
        try {
            // Carrega o FXML da tela de jogador
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/3-TelaDeJogador.fxml"));
            Scene scene = new Scene(loader.load());

            // Obtém o Stage atual
            Stage stage = (Stage) barraVidaJogador.getScene().getWindow();

            // Define a nova cena no Stage
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela de personagens.");
        }
    }


    private void reiniciarBatalha() {
        vidaJogador = 100;
        vidaOponente = 100;
        atualizarVidaJogador(vidaJogador);
        atualizarVidaOponente(vidaOponente);
        resultadoDado.setText("A batalha recomeçou! Boa sorte!");
    }

    private void fecharJogo() {
        Stage stage = (Stage) barraVidaJogador.getScene().getWindow();
        stage.close();
    }

    public void atualizarVidaJogador(double vida) {
        barraVidaJogador.setProgress(vida / 100);
        labelVidaJogador.setText("Vida: " + (int) vida + "/100");
    }

    public void atualizarVidaOponente(double vida) {
        barraVidaOponente.setProgress(vida / 100);
        labelVidaOponente.setText("Vida: " + (int) vida + "/100");
    }
}
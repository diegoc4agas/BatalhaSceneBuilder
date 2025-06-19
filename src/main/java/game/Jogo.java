package game;

import models.Personagem;
import controllers.TelaDeBatalhaController;

public class Jogo {

    private Personagem personagem;
    private Personagem oponente;
    private TelaDeBatalhaController telaController;

    public Jogo(TelaDeBatalhaController telaController) {
        this.telaController = telaController;
        // Inicia os personagens
        personagem = new Personagem("Guerreiro", 100, "../img/Personagens/Guerreiro.png");
        oponente = new Personagem("Medusa", 100, "../img/Oponentes/Medusa.png");

        // Configura as informações iniciais
        telaController.setNomePersonagem(personagem.getNome());
        telaController.setNomeOponente(oponente.getNome());
        telaController.setImagemPersonagem(personagem.getImagemUrl());
        telaController.setImagemOponente(oponente.getImagemUrl());
        telaController.atualizarVidaJogador(personagem.getVida());
        telaController.atualizarVidaOponente(oponente.getVida());
    }

    // Método para realizar um ataque
    public void realizarAtaque() {
        personagem.atacar(oponente);
        telaController.atualizarVidaOponente(oponente.getVida());
        verificarFimDeJogo();
    }

    // Método para verificar o fim do jogo
    private void verificarFimDeJogo() {
        if (oponente.getVida() <= 0) {
            // Oponente derrotado
            telaController.setNomeOponente("Derrotado");
        }
    }

    // Método para realizar a defesa
    public void realizarDefesa() {
        // Implementar lógica de defesa se necessário
    }
}

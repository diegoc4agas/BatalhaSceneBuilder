package models;

public class Personagem {
    private String nome;
    private double vida;
    private String imagemUrl;

    public Personagem(String nome, double vida, String imagemUrl) {
        this.nome = nome;
        this.vida = vida;
        this.imagemUrl = imagemUrl;
    }

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    // Método para atacar o oponente
    public void atacar(Personagem oponente) {
        double dano = 10; // Dano padrão, pode ser personalizado
        oponente.setVida(oponente.getVida() - dano);
    }
}

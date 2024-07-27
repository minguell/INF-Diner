package INF_Diner.java;

import com.badlogic.gdx.graphics.Texture;

public class Ingrediente {
    private String nome;
    private boolean precisaCozinhar;
    private int tipoIngrediente;
    private Texture textura;


    public Ingrediente(String nome, boolean precisaCozinhar, int tipoIngrediente, Texture textura) {
        this.nome = nome;
        this.precisaCozinhar = precisaCozinhar;
        this.tipoIngrediente = tipoIngrediente;
        this.textura = textura;
    }

    public String getNome() {
        return nome;
    }

    public boolean isPrecisaCozinhar() {
        return precisaCozinhar;
    }

    public int getTipoIngrediente() {
        return tipoIngrediente;
    }

    public Texture getTextura() {
        return textura;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPrecisaCozinhar(boolean precisaCozinhar) {
        this.precisaCozinhar = precisaCozinhar;
    }

    public void setTipoIngrediente(int tipoIngrediente) {
        this.tipoIngrediente = tipoIngrediente;
    }
    
    public void setTextura(Texture textura) {
        this.textura = textura;
    }
    
}

package INF_Diner.java;

import com.badlogic.gdx.graphics.Texture;

public class Ingrediente {
    private String nome;
    private boolean precisaCozinhar;
    private int tipoIngrediente; //Identificador do ingrediente
    private Texture textura;

    //Construtor de ingrediente generico
    public Ingrediente(String nome, boolean precisaCozinhar, int tipoIngrediente, Texture textura) {
        this.nome = nome;
        this.precisaCozinhar = precisaCozinhar;
        this.tipoIngrediente = tipoIngrediente;
        this.textura = textura;
    }

    //Gera copia de ingrediente
    public Ingrediente(Ingrediente ingrediente){
        this.nome = ingrediente.getNome();
        this.precisaCozinhar = ingrediente.isPrecisaCozinhar();
        this.tipoIngrediente = ingrediente.getTipoIngrediente();
        this.textura = ingrediente.getTextura();
    }

    //Getter e Setter de Nome
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    //Getter e Setter de PrecisaCozinhar
    public boolean isPrecisaCozinhar() {
        return precisaCozinhar;
    }
    public void setPrecisaCozinhar(boolean precisaCozinhar) {
        this.precisaCozinhar = precisaCozinhar;
    }

    //Getter e Setter de TipoIngrediente
    public int getTipoIngrediente() {
        return tipoIngrediente;
    }
    public void setTipoIngrediente(int tipoIngrediente) {
        this.tipoIngrediente = tipoIngrediente;
    }

    //Getter e Setter de Textura
    public Texture getTextura() {
        return textura;
    }
    public void setTextura(Texture textura) {
        this.textura = textura;
    }

    //Marca o ingrediente como cozido
    public void cozinha(){
        this.precisaCozinhar = false;
        this.nome += " (Cozido)";
    }

    //Rotina de encerramento
    public void dispose(){
        this.textura.dispose();
    }

}

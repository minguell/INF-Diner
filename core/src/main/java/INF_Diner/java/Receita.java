package INF_Diner.java;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;

//Classe que representa as receitas, que sao pedidas pelos clientes e produzidas pela panela
public class Receita {
    private final String nome;
    private int tipoPrato; //Identificador do prato
    private final Texture textura;
    ArrayList<Ingrediente> ingredientes; //Componentes da receita

    //Produz prato especial Mistura - que deve ser colocado na panela e preparado
    public Receita(ArrayList<Ingrediente> ingredientes) {
        this.tipoPrato = 0;
        this.nome = "Mistura";
        this.textura = new Texture("Mistura.png");
        this.ingredientes = ingredientes;
    }

    //Gera copia de receita
    public Receita(Receita receita){
        this.tipoPrato = receita.tipoPrato;
        this.nome = receita.nome;
        this.textura = receita.textura;
        this.ingredientes = receita.ingredientes;
    }

    //Produz uma receita de dado tipo de prato
    public Receita(int tipoPrato) {
        this.tipoPrato = tipoPrato;
        this.nome = nomePorPrato(tipoPrato);
        this.ingredientes = ingredientesPorPrato(tipoPrato);
        this.textura = new Texture(this.nome + ".png");
    }

    //Getter e Setter de TipoPrato
    public int getTipoPrato() {
        return tipoPrato;
    }
    public void setTipoPrato(int tipoPrato) {
        this.tipoPrato = tipoPrato;
    }

    //Getter de Nome
    public String getNome() {
        return nome;
    }

    //Getter de Textura
    public Texture getTextura() {
        return textura;
    }

    //Getter e Setter de Ingredientes
    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }
    public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    //Matching do tipo de prato com seus ingredientes
    public ArrayList<Ingrediente> ingredientesPorPrato(int prato) {
        switch(prato){
            default:
                return new ArrayList<>();
        }
    }

    //Matching do tipo de prato com sua textura
    public String nomePorPrato(int prato) {
        switch(prato){
            case 0: return "Mistura";
            case 1: return "Gororoba";
            default: return "Invalido";
        }
    }

    //Rotina de destrucao
    public void dispose() {
        this.textura.dispose();
    }
}

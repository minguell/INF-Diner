package INF_Diner.java;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;

//Classe que representa as receitas, que sao pedidas pelos clientes e produzidas pela panela
//tipoIngrediente e o identificador de cada receita e ingredientes sao os requisitos pra produzi-la
public class Receita {
    private int tipoPrato;
    private final Texture skin;
    ArrayList<Ingrediente> ingredientes;

    public Receita(int tipoPrato) {
        this.tipoPrato = tipoPrato;
        this.ingredientes = ingredientesPorPrato(tipoPrato);
        this.skin = skinPorPrato(tipoPrato);
    }

    //Getter e Setter de TipoPrato
    public int getTipoPrato() {
        return tipoPrato;
    }
    public void setTipoPrato(int tipoPrato) {
        this.tipoPrato = tipoPrato;
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
                return new ArrayList<Ingrediente>();
        }
    }

    //Matching do tipo de prato com sua skin
    public Texture skinPorPrato(int prato) {
        switch(prato){
            default:
                return new Texture("placeholder.png");
        }
    }

    //Rotina de destrucao
    public void dispose() {
        this.skin.dispose();
    }
}

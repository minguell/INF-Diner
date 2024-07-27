package INF_Diner.java;

import java.util.ArrayList;

public class Panela {

    private ArrayList<Ingrediente> ingredientesDentro = new ArrayList<Ingrediente>();

    public Panela() {
    }

    public void addIngrediente(Ingrediente ingrediente) {
        ingredientesDentro.add(ingrediente);
    }

    public void removeIngrediente(Ingrediente ingrediente) {
        ingredientesDentro.remove(ingrediente);
    }

    public ArrayList<Ingrediente> getIngredientesDentro() {
        return ingredientesDentro;
    }

    

    
}

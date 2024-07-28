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
        ArrayList<Ingrediente> ingredientes = new ArrayList<>();
        switch(prato){
            case 2: //Pizza Demoniaca
                ingredientes.add(new Ingrediente("Pao", false, 5, new Texture("Pao.png")));
                ingredientes.add(new Ingrediente("Cinzas", false, 0, new Texture("Cinzas.png")));
                ingredientes.add(new Ingrediente("Ovo (Cozido)", false, 7, new Texture("Ovo.png")));
                ingredientes.add(new Ingrediente("Cinzas", false, 0, new Texture("Cinzas.png")));
                break;
            case 3: //Pizza
                ingredientes.add(new Ingrediente("Pao", false, 5, new Texture("Pao.png")));
                ingredientes.add(new Ingrediente("Tomate", false, 8, new Texture("Tomate.png")));
                ingredientes.add(new Ingrediente("Queijo", false, 6, new Texture("Queijo.png")));
                break;
            case 4: //A La Minuta
                ingredientes.add(new Ingrediente("Arroz (Cozido)", false, 2, new Texture("Arroz.png")));
                ingredientes.add(new Ingrediente("Feijao (Cozido)", false, 3, new Texture("Feijao.png")));
                ingredientes.add(new Ingrediente("Ovo (Cozido)", false, 7, new Texture("Ovo.png")));
                ingredientes.add(new Ingrediente("Alface", false, 9, new Texture("Alface.png")));
                ingredientes.add(new Ingrediente("Bife (Cozido)", false, 1, new Texture("Bife.png")));
                break;
            case 5: //Pao Com Ovo
                ingredientes.add(new Ingrediente("Pao", false, 5, new Texture("Pao.png")));
                ingredientes.add(new Ingrediente("Ovo (Cozido)", false, 7, new Texture("Ovo.png")));
                break;
            case 6: //Macarrao Com Queijo
                ingredientes.add(new Ingrediente("Macarrao (Cozido)", false, 4, new Texture("Macarrao.png")));
                ingredientes.add(new Ingrediente("Queijo", false, 6, new Texture("Queijo.png")));
                break;
            case 7: //Macarrao Com Peixe
                ingredientes.add(new Ingrediente("Macarrao (Cozido)", false, 4, new Texture("Macarrao.png")));
                ingredientes.add(new Ingrediente("Peixe (Cozido)", false, 10, new Texture("Peixe.png")));
                ingredientes.add(new Ingrediente("Ovo (Cozido)", false, 7, new Texture("Ovo.png")));
                break;
            case 8: //Macarrao Bolonhesa
                ingredientes.add(new Ingrediente("Macarrao (Cozido)", false, 4, new Texture("Macarrao.png")));
                ingredientes.add(new Ingrediente("Bife (Cozido)", false, 1, new Texture("Bife.png")));
                ingredientes.add(new Ingrediente("Tomate", false, 8, new Texture("Tomate.png")));
                break;
            case 9: //Hamburguer
                ingredientes.add(new Ingrediente("Pao", false, 5, new Texture("Pao.png")));
                ingredientes.add(new Ingrediente("Bife (Cozido)", false, 1, new Texture("Bife.png")));
                ingredientes.add(new Ingrediente("Queijo", false, 6, new Texture("Queijo.png")));
                ingredientes.add(new Ingrediente("Alface", false, 9, new Texture("Alface.png")));
                ingredientes.add(new Ingrediente("Pao", false, 5, new Texture("Pao.png")));
                break;
            case 10: //Marmita Simples
                ingredientes.add(new Ingrediente("Arroz (Cozido)", false, 2, new Texture("Arroz.png")));
                ingredientes.add(new Ingrediente("Feijao (Cozido)", false, 3, new Texture("Feijao.png")));
                break;
            case 11: //Salada
                ingredientes.add(new Ingrediente("Alface", false, 9, new Texture("Alface.png")));
                ingredientes.add(new Ingrediente("Tomate", false, 8, new Texture("Tomate.png")));
                ingredientes.add(new Ingrediente("Alface", false, 9, new Texture("Alface.png")));
                break;
            case 12: //Batida De Proteina
                ingredientes.add(new Ingrediente("Bife (Cozido)", false, 1, new Texture("Bife.png")));
                ingredientes.add(new Ingrediente("Peixe (Cozido)", false, 10, new Texture("Peixe.png")));
                ingredientes.add(new Ingrediente("Ovo (Cozido)", false, 7, new Texture("Ovo.png")));
                break;
            case 13: //Sanduiche De Peixe
                ingredientes.add(new Ingrediente("Pao", false, 5, new Texture("Pao.png")));
                ingredientes.add(new Ingrediente("Peixe (Cozido)", false, 10, new Texture("Peixe.png")));
                ingredientes.add(new Ingrediente("Pao", false, 5, new Texture("Pao.png")));
                break;
        }
        return ingredientes;
    }

    //Matching do tipo de prato com sua textura
    public String nomePorPrato(int prato) {
        switch(prato){
            case 0: return "Mistura";
            case 1: return "Gororoba";
            case 2: return "Pizza Demoniaca";
            case 3: return "Pizza";
            case 4: return "A La Minuta";
            case 5: return "Pao Com Ovo";
            case 6: return "Macarrao Com Queijo";
            case 7: return "Macarrao Com Peixe";
            case 8: return "Macarrao Bolonhesa";
            case 9: return "Hamburguer";
            case 10: return "Marmita Simples";
            case 11: return "Salada";
            case 12: return "Batida De Proteina";
            case 13: return "Sanduiche De Peixe";
            default: return "Invalido";
        }
    }

    public static Receita cozinhar(ArrayList<Receita> receitas, ArrayList<Ingrediente> ingredientesJogador){
        for(Receita receita : receitas){
            if(receita.getIngredientes().size() == ingredientesJogador.size()){
                boolean match = true;
                for(int x = 0; x < ingredientesJogador.size(); x++){
                    if(!receita.getIngredientes().get(x).getNome().equals(ingredientesJogador.get(x).getNome())){
                        match = false;
                        break;
                    }
                }
                if(match){
                    return new Receita(receita);
                }
            }
        }

        return new Receita(1);
    }
    //Rotina de destrucao
    public void dispose() {
        this.textura.dispose();
    }
}

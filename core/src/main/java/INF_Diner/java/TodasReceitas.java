package INF_Diner.java;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

//Refere a uma de cada receita, para fins de comparacao e exibicao
public class TodasReceitas {
    private final SpriteBatch batch = new SpriteBatch();
    private static final int TOTAL_RECEITAS = 14;
    private static final int TOTAL_RECEITAS_VALIDAS = TOTAL_RECEITAS - 3;
    private final Texture listaReceitas = new Texture("Receitas.png");
    public static final ArrayList<Receita> receitas = new ArrayList<>(); //Todas as receitas, usadas para comparacao
    private boolean mostrarReceitas = false;
    private final int TELA_X = 1920;
    private final int TEXTO_Y0 = 1040;
    private final int TEXTO_X = TELA_X/4 + 50;
    private final int TEXTO_Y = 1000;
    private final int TAMANHO_IMAGEM = 25;

    //Construtor
    TodasReceitas(){
        //Uma copia de cada receita tirando a mistura e a gororoba
        for(int x = 2; x < TOTAL_RECEITAS; x++){
            TodasReceitas.receitas.add(new Receita(x));
        }
    }

    //Getter TOTAL_RECEITAS
    public static int getTOTAL_RECEITAS() {
        return TOTAL_RECEITAS;
    }
    public static int getTOTAL_RECEITAS_VALIDAS() {
        return TOTAL_RECEITAS_VALIDAS;
    }

    //Getter ListaReceitas
    public Texture getListaReceitas() {
        return listaReceitas;
    }

    //Getter e Setter MostrarReceitas
    public boolean isMostrarReceitas() {
        return mostrarReceitas;
    }
    public void setMostrarReceitas(boolean mostrarReceitas) {
        this.mostrarReceitas = mostrarReceitas;
    }



    //Desenha a lista de receitas na tela
    public void desenhaReceitas(){
        int x = 0;
        this.batch.begin();
        if(this.mostrarReceitas){
            this.batch.draw(listaReceitas, TELA_X/4f, 0);
        }
        BitmapFont font = new BitmapFont();
        font.getData().setScale(2);
        font.draw(this.batch, "Ingredientes Devem Seguir Ordem", TEXTO_X, TEXTO_Y0);
        for(Receita receita : TodasReceitas.receitas){
            font.draw(this.batch, receita.getNome() + ":", TEXTO_X, TEXTO_Y - font.getLineHeight() * x);
            this.batch.draw(receita.getTextura(), TEXTO_X * 2f, TEXTO_Y - font.getLineHeight() * x - TAMANHO_IMAGEM, TAMANHO_IMAGEM, TAMANHO_IMAGEM);
            x++;
            if(x == 1){
                font.draw(this.batch, "Ingredientes: Segredo", TEXTO_X, TEXTO_Y - font.getLineHeight() * x);
            }
            else{
                font.draw(this.batch, TodasReceitas.listaIngredientes(receita), TEXTO_X, TEXTO_Y - font.getLineHeight() * x);
            }
            x++;
        }
        this.batch.end();
    }

    public static String listaIngredientes(Receita receita){
        StringBuilder lista = new StringBuilder("Ingredientes: ");
        for(Ingrediente ingrediente : receita.getIngredientes()) {
            lista.append(ingrediente.getNome()).append(", ");
        }
        lista.setLength(lista.length() - 2);
        return lista.toString();
    }

    public static Receita cozinhar(ArrayList<Ingrediente> ingredientesJogador){
        for(Receita receita : TodasReceitas.receitas){
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
}

package INF_Diner.java;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

//Classe que refere a uma de cada receita, para fins de comparacao e exibicao da tela com receitas
public class TodasReceitas {
    //Atributos
    private boolean mostrarReceitas = false;
    private final SpriteBatch batch = new SpriteBatch();
    private final Texture listaReceitas = new Texture("Receitas.png");
    public static ArrayList<Receita> receitas = new ArrayList<>(); //Todas as receitas, usadas para comparacao
    private final int TEXTO_X = INF_Diner.TELA_X/4 + 50;
    private final int TEXTO_Y = 1000;
    private final int TITULO_Y = 1040;
    private final int TAMANHO_IMAGEM = 25;
    private static final int TOTAL_RECEITAS = 14;
    private static final int TOTAL_RECEITAS_VALIDAS = TOTAL_RECEITAS - 3;

    //Construtor
    TodasReceitas(){
        //Uma copia de cada receita tirando a mistura e a gororoba
        TodasReceitas.receitas = new ArrayList<>();
        for(int x = 2; x < TOTAL_RECEITAS; x++){
            TodasReceitas.receitas.add(new Receita(x));
        }
    }

    //Getters e Setters
    public static int getTOTAL_RECEITAS() {
        return TOTAL_RECEITAS;
    }
    public static int getTOTAL_RECEITAS_VALIDAS() {
        return TOTAL_RECEITAS_VALIDAS;
    }
    public Texture getListaReceitas() {
        return listaReceitas;
    }
    public boolean isMostrarReceitas() {
        return mostrarReceitas;
    }
    public void setMostrarReceitas(boolean mostrarReceitas) {
        this.mostrarReceitas = mostrarReceitas;
    }
    public static ArrayList<Receita> getReceitas() {
        return receitas;
    }
    public static void setReceitas(ArrayList<Receita> receitas) {
        TodasReceitas.receitas = receitas;
    }
    public int getTEXTO_X() {
        return TEXTO_X;
    }
    public int getTEXTO_Y() {
        return TEXTO_Y;
    }
    public int getTITULO_Y() {
        return TITULO_Y;
    }
    public int getTAMANHO_IMAGEM() {
        return TAMANHO_IMAGEM;
    }

    //Outros Metodos

    //Desenha a lista de receitas na tela
    public void desenhaReceitas(){
        int x = 0;
        this.batch.begin();
        BitmapFont font = new BitmapFont();
        font.getData().setScale(2);
        this.batch.draw(listaReceitas, INF_Diner.TELA_X/4f, 0); //Desenha o fundo
        font.draw(this.batch, "Todos os ingredientes devem ser colocados em ordem", TEXTO_X, TITULO_Y);
        for(Receita receita : TodasReceitas.receitas){
            //Desenha o nome e imagem de cada receita, seguidas pelo seus ingredientes na linha seguinte, para cada uma das receitas validas
            font.draw(this.batch, receita.getNome() + ":", TEXTO_X, TEXTO_Y - font.getLineHeight() * x);
            this.batch.draw(receita.getTextura(), TEXTO_X * 2f, TEXTO_Y - font.getLineHeight() * x - TAMANHO_IMAGEM, TAMANHO_IMAGEM, TAMANHO_IMAGEM);
            x++;
            if(x == 1){ //Receita secreta
                font.draw(this.batch, "Ingredientes: Segredo", TEXTO_X, TEXTO_Y - font.getLineHeight() * x);
            }
            else{
                font.draw(this.batch, TodasReceitas.listarIngredientes(receita), TEXTO_X, TEXTO_Y - font.getLineHeight() * x);
            }
            x++;
        }
        this.batch.end();
    }

    //Converte uma lista de ingredientes de uma receita a uma string de nomes separada com ", "
    public static String listarIngredientes(Receita receita){
        StringBuilder lista = new StringBuilder("Ingredientes: ");
        for(Ingrediente ingrediente : receita.getIngredientes()) {
            lista.append(ingrediente.getNome()).append(", ");
        }
        lista.setLength(lista.length() - 2);
        return lista.toString();
    }

    //Faz um matching de uma mistura com os ingredientes de uma receita
    //Retorna uma receita completa em caso de sucesso, e uma gororoba caso contrario
    public static Receita cozinhar(ArrayList<Ingrediente> ingredientesJogador){
        for(Receita receita : TodasReceitas.receitas){ //Procura linearmente possiveis candidatos
            if(receita.getIngredientes().size() == ingredientesJogador.size()){//Filtra candidatos por tamanho
                boolean match = true;
                for(int x = 0; x < ingredientesJogador.size(); x++){ //Compara cada ingrediente por nome
                    if(!receita.getIngredientes().get(x).getNome().equals(ingredientesJogador.get(x).getNome())){
                        match = false;
                        break;
                    }
                }
                if(match){ //Caso todos nomes concordem retorna respectiva receita
                    return new Receita(receita);
                }
            }
        }
        return new Receita(1); //Retorna gororoba
    }
}

package INF_Diner.java;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input;

public class Jogador {
    private int dinheiro;
    private final Texture skin;
    private Receita receitaCarregada;
    private Ingrediente ingredienteCarregado;
    private int posX;
    private int posY;
    private final int VELOCIDADE = 2;
    private final int MIN_X = 0;
    private final int MIN_Y = 0;
    private final int MAX_X = 1920;
    private final int MAX_Y = 1080;

    public Jogador() {
        this.dinheiro = 0;
        this.skin = new Texture("Jogador.png");
        this.receitaCarregada = null;
        this.ingredienteCarregado = null;
        this.posX = 0;
        this.posY = 0;
    }

    //Getter e Setter de Dinheiro
    public int getDinheiro() {
        return dinheiro;
    }
    public void setDinheiro(int dinheiro) {
        this.dinheiro = dinheiro;
    }

    //Getter e Setter de ReceitaCarregada
    public Receita getReceitaCarregada() {
        return receitaCarregada;
    }
    public void setReceitaCarregada(Receita receitaCarregada) {
        this.receitaCarregada = receitaCarregada;
    }

    //Getter e Setter de IngredienteCarregado
    public Ingrediente getIngredienteCarregado() {
        return ingredienteCarregado;
    }
    public void setIngredienteCarregado(Ingrediente ingredienteCarregado) {
        this.ingredienteCarregado = ingredienteCarregado;
    }

    //Getter e Setter de PosX
    public int getPosX() {
        return posX;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }

    //Getter e Setter de PosY
    public int getPosY() {
        return posY;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }

    //Rotina de encerramento
    public void dispose(){
        skin.dispose();
    }

    //Incrementa dinheiro de jogador (que serve como pontuacao)
    public void recebeDinheiro(int valor){
        this.dinheiro += valor;
    }

    //Descarta tudo que o jogador carrega
    public void descartaCarregado(){
        this.ingredienteCarregado = null;
        this.receitaCarregada = null;
    }

    //Vai mover o jogador no sentido de dada input
    public void movimentar(){
        if(this.posY > MIN_Y && (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.UP))){
            this.posY -= VELOCIDADE;
        }
        if(this.posY < MAX_Y && (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyJustPressed(Input.Keys.DOWN))){
            this.posY += VELOCIDADE;
        }
        if(this.posX > MIN_X && (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyJustPressed(Input.Keys.LEFT))){
            this.posX -= VELOCIDADE;
        }
        if(this.posX < MAX_X && (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))){
            this.posX += VELOCIDADE;
        }
    }

    //Pega a receita pronta caso o jogador nao esteja carregando outra receita
    public void pegarReceita(Receita receita){
        if(this.receitaCarregada == null){
            this.receitaCarregada = receita;
        }
    }

    //Pega o ingrediente caso o jogador nao esteja carregando outro ingrediente
    public void pegarIngrediente(Ingrediente ingrediente){
        if(this.ingredienteCarregado == null){
            this.ingredienteCarregado = ingrediente;
        }
    }

}

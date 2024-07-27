package INF_Diner.java;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Jogador {
    private int dinheiro;
    private final Sprite skin;
    private Receita receitaCarregada;
    private Ingrediente ingredienteCarregado;
    private boolean viradoEsquerda; //1 esquerda -1 direita
    private int posX;
    private int posY;
    private final int VELOCIDADE = 10;
    private final int MIN_X = 240;
    private final int MIN_Y = 0;
    private final int MAX_X = 1440;
    private final int MAX_Y = 720;
    private final int X_INICIAL = 870;
    private final int Y_INICIAL = 720;

    public Jogador() {
        this.dinheiro = 0;
        this.skin = new Sprite(new Texture("Jogador.png"));
        this.receitaCarregada = null;
        this.ingredienteCarregado = null;
        this.posX = X_INICIAL;
        this.posY = Y_INICIAL;
        this.viradoEsquerda = true;
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
        if(this.posY > MIN_Y && (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN))){
            this.posY -= VELOCIDADE;
        }
        if(this.posY < MAX_Y && (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP))){
            this.posY += VELOCIDADE;
        }
        if(this.posX > MIN_X && (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT))){
            this.posX -= VELOCIDADE;
            if(!this.viradoEsquerda){
                this.skin.flip(true, false);
                this.viradoEsquerda = true;
            }
        }
        if(this.posX < MAX_X && (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT))){
            this.posX += VELOCIDADE;
            if(this.viradoEsquerda){
                this.skin.flip(true, false);
                this.viradoEsquerda = false;
            }
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

    public void render(SpriteBatch batch){
        batch.draw(this.skin, this.posX, this.posY);
    }

}

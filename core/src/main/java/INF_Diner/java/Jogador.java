package INF_Diner.java;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

//Classe que controla a estrutura do jogador e alguns de seus comandos
public class Jogador {
    private int dinheiro; //Serve como pontuacao do jogo
    private final Sprite skin;
    private Ingrediente ingredienteCarregado; //E vazio se alguma receita estiver sendo carregada
    private Receita receitaCarregada; //E vazio se alguma ingrediente estiver sendo carregado
    private boolean sentidoX; //true esquerda false direita
    private boolean sentidoY; //true baixo false cima
    //Posicao do jogador
    private int posX;
    private int posY;
    private int X_INICIAL = 870;
    private int Y_INICIAL = 720;
    private int MIN_X = 240;
    private int MIN_Y = 700;
    private int MAX_X = 1440;
    private int MAX_Y = 1440;
    private final int VELOCIDADE = 10;
    public enum Carregado {NADA, INGREDIENTE, RECEITA} //Opcoes do que o jogador pode estar carregando
    private Carregado carregando; //Indica o que o jogador esta carregando

    //Construtor
    public Jogador() {
        this.dinheiro = 0;
        this.carregando = Carregado.NADA;
        this.skin = new Sprite(new Texture("Jogador.png"));
        this.receitaCarregada = null;
        this.ingredienteCarregado = null;
        this.posX = X_INICIAL;
        this.posY = Y_INICIAL;
        this.sentidoX = true;
        this.sentidoY = true;
    }

    //Getter e Setter de Dinheiro
    public int getDinheiro() {
        return dinheiro;
    }
    public void setDinheiro(int dinheiro) {
        this.dinheiro = dinheiro;
    }

    //Getter e Setter de IngredienteCarregado
    public Ingrediente getIngredienteCarregado() {
        return ingredienteCarregado;
    }
    public void setIngredienteCarregado(Ingrediente ingredienteCarregado) {
        //Visa garantir que so uma coisa pode ser carregada ao mesmo tempo
        if(ingredienteCarregado == null){
            this.ingredienteCarregado = null;
            this.carregando = Carregado.NADA;
        }
        else if(this.carregando == Carregado.NADA){
            this.ingredienteCarregado = new Ingrediente(ingredienteCarregado);
            this.carregando = Carregado.INGREDIENTE;
        }
    }

    //Getter e Setter de ReceitaCarregada
    public Receita getReceitaCarregada() {
        return receitaCarregada;
    }
    public void setReceitaCarregada(Receita receitaCarregada) {
        //Visa garantir que so uma coisa pode ser carregada ao mesmo tempo
        if(receitaCarregada == null){
            this.receitaCarregada = null;
            this.carregando = Carregado.NADA;
        }
        else if(this.carregando == Carregado.NADA){
            this.receitaCarregada = new Receita(receitaCarregada);
            this.carregando = Carregado.RECEITA;
        }
    }

    //Getter de Carregando
    public Carregado getCaregando() {
        return carregando;
    }

    //Getters e Setters das Posicoes
    public int getPosX() {
        return posX;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }
    public int getPosY() {
        return posY;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }
    public int getX_INICIAL() {
        return X_INICIAL;
    }
    public int getY_INICIAL() {
        return Y_INICIAL;
    }

    //Getter da Velocidade
    public int getVELOCIDADE() {
        return VELOCIDADE;
    }

    //Getters e Setters dos sentidos
    public boolean isSentidoX() {
        return sentidoX;
    }
    public void setSentidoX(boolean sentidoX) {
        this.sentidoX = sentidoX;
    }
    public boolean isSentidoY() {
        return sentidoY;
    }
    public void setSentidoY(boolean sentidoY) {
        this.sentidoY = sentidoY;
    }

    //Getters dos minimos e maximos
    public int getMIN_X() {
        return MIN_X;
    }
    public int getMIN_Y() {
        return MIN_Y;
    }
    public int getMAX_X() {
        return MAX_X;
    }
    public int getMAX_Y() {
        return MAX_Y;
    }

    //Incrementa dinheiro de jogador (que serve como pontuacao)
    public void recebeDinheiro(int valor){
        this.dinheiro += valor;
    }

    //Descarta tudo que o jogador carrega
    public void descartaCarregado(){
        this.ingredienteCarregado = null;
        this.receitaCarregada = null;
        this.carregando = Carregado.NADA;
    }

    //Vai mover o jogador no sentido de dada input
    public void movimentar(){
        if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            if(!this.sentidoY){
                this.sentidoY = true;
            }
            if(this.posY > MIN_Y){
                this.posY -= VELOCIDADE;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)){
            if(this.sentidoY){
                this.sentidoY = false;
            }
            if(this.posY < MAX_Y){
                this.posY += VELOCIDADE;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            if(!this.sentidoX){
                this.skin.flip(true, false);
                this.sentidoX = true;
            }
            if(this.posX > MIN_X){
                this.posX -= VELOCIDADE;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            if(this.sentidoX){
                this.skin.flip(true, false);
                this.sentidoX = false;
            }
            if(this.posX < MAX_X){
                this.posX += VELOCIDADE;
            }
        }
    }

    //Desenha o jogador na tela
    public void render(SpriteBatch batch){
        batch.draw(this.skin, this.posX, this.posY);
    }

}

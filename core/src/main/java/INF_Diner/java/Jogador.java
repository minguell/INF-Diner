package INF_Diner.java;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;

//Classe que controla a estrutura do jogador e alguns de seus comandos
public class Jogador {
    //Atributos
    private Sprite skin;
    private Ingrediente ingredienteCarregado; //E vazio se alguma receita estiver sendo carregada
    private Receita receitaCarregada; //E vazio se alguma ingrediente estiver sendo carregado
    public enum Carregado {NADA, INGREDIENTE, RECEITA} //Opcoes do que o jogador pode estar carregando
    private Carregado carregando; //Indica o que o jogador esta carregando
    private int dinheiro; //Serve como pontuacao do jogo
    //Sentido, posicao e velocidade
    private boolean sentidoX; //true esquerda false direita
    private boolean sentidoY; //true baixo false cima
    private int posX;
    private int posY;
    private int X_INICIAL = 870;
    private int Y_INICIAL = 720;
    private int MIN_X = 240;
    private int MIN_Y = -10;
    private int MAX_X = 1440;
    private int MAX_Y = 720;
    private final int VELOCIDADE = 10;

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

    //Getters e Setters
    public int getDinheiro() {
        return dinheiro;
    }
    public void setDinheiro(int dinheiro) {
        this.dinheiro = dinheiro;
    }
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
    public Sprite getSkin() {
        return skin;
    }
    public void setSkin(Sprite skin) {
        this.skin = skin;
        if(!sentidoX){
            this.skin.flip(true, false);
        }
    }
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
    public Carregado getCaregando() {
        return carregando;
    }
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
    public void setX_INICIAL(int x_INICIAL) {
        X_INICIAL = x_INICIAL;
    }
    public void setY_INICIAL(int y_INICIAL) {
        Y_INICIAL = y_INICIAL;
    }
    public int getVELOCIDADE() {
        return VELOCIDADE;
    }
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
    public int getMIN_X() {
        return MIN_X;
    }
    public void setMIN_X(int MIN_X) {
        this.MIN_X = MIN_X;
    }
    public int getMIN_Y() {
        return MIN_Y;
    }
    public void setMIN_Y(int MIN_Y) {
        this.MIN_Y = MIN_Y;
    }
    public int getMAX_X() {
        return MAX_X;
    }
    public void setMAX_X(int MAX_X) {
        this.MAX_X = MAX_X;
    }
    public int getMAX_Y() {
        return MAX_Y;
    }
    public void setMAX_Y(int MAX_Y) {
        this.MAX_Y = MAX_Y;
    }

    //Outros Metodos

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

    //Verifica se o jogador saiu da cozinha e foi pro restaurante
    public boolean saiuCozinha(){
        if(this.posY == MIN_Y){
            this.posY = 900;
            this.MAX_Y = 910;
            this.MIN_Y = 720;
            return true;
        }
        return false;
    }

    //Verifica se o jogador saiu do restaurante e foi pra cozinha
    public boolean saiuRestaurante(){
        if(this.posY == MAX_Y){
            this.posY = 0;
            this.MAX_Y = 720;
            this.MIN_Y = -10;
            return true;
        }
        return false;
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


    //Desenha o jogador na tela
    public void render(SpriteBatch batch){
        batch.draw(this.skin, this.posX, this.posY);
    }

}

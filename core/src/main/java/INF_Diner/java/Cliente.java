package INF_Diner.java;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

//Classe abstrata que fornece um template pra diferentes tipos de clientes, que chegam no restaurante, fazem pedidos, os recebem e pagam por eles
public abstract class Cliente {
    //Atributos
    private float tempoEspera;
    private float tempoChegada;
    private Receita pedido;
    private Texture skin;
    private int posX;
    private int posY;
    private final Texture balaoDeFala;
    private final int TAM_X = 240;
    private final int TAM_Y = 180;
    private final int velocidade = 2;
    private final int MAX_Y = 360;
    private final int MIN_Y = -270;
    private boolean parado = false;
    private boolean pagou = false;

    //Construtor
    Cliente(int tileEntrada){
        this.tempoChegada = Cozinha.getTempo();
        this.pedido = randomizaPedido();
        this.skin = geraSkin();
        this.posX = TAM_X * tileEntrada;
        this.posY = MIN_Y;
        this.balaoDeFala = new Texture("BalaoDeFala.png");
    }

    //Construtor para testes
    Cliente(int tileEntrada, boolean teste){
        this.tempoChegada = Cozinha.getTempo();
        this.pedido = new Receita(4, null);
        this.skin = null;
        this.posX = TAM_X * tileEntrada;
        this.posY = MIN_Y;
        this.balaoDeFala = null;
    }

    //Getters e Setters
    public void setTempoEspera(float tempoEspera) {
        this.tempoEspera = tempoEspera;
    }
    public float getTempoEspera() {
        return tempoEspera;
    }
    public Texture getBalaoDeFala() {
        return balaoDeFala;
    }
    public int getTAM_X() {
        return TAM_X;
    }
    public int getTAM_Y() {
        return TAM_Y;
    }
    public int getMAX_Y() {
        return MAX_Y;
    }
    public int getMIN_Y() {
        return MIN_Y;
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
    public int getVelocidade() {
        return velocidade;
    }
    public boolean isParado() {
        return parado;
    }
    public void setParado(boolean parado) {
        this.parado = parado;
    }
    public Receita getPedido() {
        return pedido;
    }
    public void setPedido(Receita pedido) {
        this.pedido = pedido;
    }
    public boolean isPagou() {
        return pagou;
    }
    public void setPagou(boolean pagou) {
        this.pagou = pagou;
    }
    public float getTempoChegada() {
        return tempoChegada;
    }
    public void setTempoChegada(float tempoChegada) {
        this.tempoChegada = tempoChegada;
    }
    public Texture getSkin() {
        return skin;
    }
    public void setSkin(Texture skin) {
        this.skin = skin;
    }

    //Metodos Abstratos

    //O pedido pode ser atualizado com outro seguindo algum criterio
    abstract void atualizaPedido();
    //Cliente pode ter diferentes criterios de pagamento mas sempre devera pagar de alguma forma antes de sair
    abstract int pagaPedido();
    //Indica se o cliente esta satisfeito dado algum criterio
    abstract boolean satisfeito();
    //Skin pode ser determinada aleatoriamente ou seguindo algum criterio
    abstract Texture geraSkin();

    //Outros Metodos

    //Retorna uma receita aleatoria que pode ser usada como pedido do cliente
    public Receita randomizaPedido() {
        Random rand = new Random();
        return new Receita(rand.nextInt(TodasReceitas.getTOTAL_RECEITAS_VALIDAS()) + 3);
    }

    //Exibe um balao de fala com o pedido do cliente acima de sua textura
    public void fazPedido(SpriteBatch batch){
        batch.draw(balaoDeFala, posX, posY + TAM_Y, TAM_X, TAM_Y);
        batch.draw(pedido.getTextura(), posX + (float)TAM_X / 4, posY + TAM_Y + (float)TAM_Y / 4, (float) TAM_X / 2, (float) TAM_Y / 2);
    }

    //Verifica se o jogador ja excedeu o tempo maximo de espera do cliente
    public boolean tempoEsgotado(){
        return Cozinha.getTempo() - this.tempoChegada > this.tempoEspera;
    }

    //Atualiza o cliente com base na sua posicao e tempo, fazendo ele se mover pra cima, pra baixo ou pedir e ficar parado
    //Retorna um booleano que indica se o cliente ainda e valido (ainda esta na tela)
    public boolean atualiza(SpriteBatch batch){
        if(tempoEsgotado() || this.pagou){
            return vaiEmbora();
        }
        else if(posY < MAX_Y){
            this.posY += this.velocidade;
        }
        else if(posY == MAX_Y){
            fazPedido(batch);
            this.parado = true;
        }
        return true;
    }

    //Cliente se retira do restaurante indo pra tras
    //Retorna um booleano que indica se ele e valido: nao e se ele saiu e com isto sera encerrado
    public boolean vaiEmbora(){
        this.parado = false;
        this.posY -= this.velocidade;
        if(this.posY == MIN_Y){
            this.dispose();
            return false;
        }
        return true;
    }

    //Rotina de encerramento
    public void dispose() {
        this.pedido.dispose();
        this.skin.dispose();
    }
}

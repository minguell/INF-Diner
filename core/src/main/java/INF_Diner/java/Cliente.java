package INF_Diner.java;

import com.badlogic.gdx.graphics.Texture;
import java.util.Random;

public abstract class Cliente {
    private double tempoEspera;
    private double tempoChegada;
    private Receita pedido;
    private Texture skin;
    public static int contagem;
    private int posX;
    private int posY;
    private final int TOTAL_RECEITAS_VALIDAS = 1;

    Cliente(double tempoDeJogo){
        this.tempoChegada = tempoDeJogo;
        this.pedido = randomizaPedido();
        this.skin = geraSkin();
        Cliente.contagem++;
        this.posX = 0;
        this.posY = 0;
    }
    //Getter e Setter de TempoEspera
    public void setTempoEspera(int tempoEspera) {
        this.tempoEspera = tempoEspera;
    }
    public double getTempoEspera() {
        return tempoEspera;
    }

    //Getter e Setter de Pedido
    public Receita getPedido() {
        return pedido;
    }
    public void setPedido(Receita pedido) {
        this.pedido = pedido;
    }

    //Getter e Setter de TempoChegada
    public double getTempoChegada() {
        return tempoChegada;
    }
    public void setTempoChegada(double tempoChegada) {
        this.tempoChegada = tempoChegada;
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

    //Getter e Setter de Skin
    public Texture getSkin() {
        return skin;
    }
    public void setSkin(Texture skin) {
        this.skin = skin;
    }

    //Retorna uma receita aleatoria que pode ser usada como pedido do cliente
    public Receita randomizaPedido() {
        Random rand = new Random();
        return new Receita(rand.nextInt(TOTAL_RECEITAS_VALIDAS));
    }

    //Cliente deve pagar o pedido baseado na demora do jogador e no tipo de cliente
    abstract int pagaPedido(double tempoDeJogo);
    //Clientes devem fazer pedidos
    abstract void fazPedido();
    //Skin pode ser determinada aleatoriamente ou de alguma forma especifica, cada um subclasse decide
    abstract Texture geraSkin();
    //Verifica se o jogador ja excedeu o tempo maximo de espera do cliente
    public void tempoEsgotado(double tempoDeJogo){
        if(tempoDeJogo > this.tempoChegada + this.tempoEspera){
            vaiEmbora();
        }
    }

    //Cliente se retira do restaurante
    public void vaiEmbora(){
    }

    //Rotina de destrucao
    public void dispose() {
        this.pedido.dispose();
        this.skin.dispose();
    }
}

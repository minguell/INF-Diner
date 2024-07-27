package INF_Diner.java;

import com.badlogic.gdx.graphics.Texture;
import java.util.Random;

public abstract class Cliente {
    protected double tempoEspera; //MUDAR, VALOR ALEATORIO
    protected double tempoChegada;
    protected Receita pedido;
    protected Texture skin;
    public static int contagem;
    protected int posX;
    protected int posY;
    private final int TOTAL_RECEITAS = 1;

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

    //Retorna uma receita aleatoria que pode ser usada como pedido do cliente
    public Receita randomizaPedido() {
        Random rand = new Random();
        return new Receita(rand.nextInt(TOTAL_RECEITAS));
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

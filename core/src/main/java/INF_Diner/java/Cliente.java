package INF_Diner.java;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public abstract class Cliente {
    private float tempoEspera;
    private float tempoChegada;
    private Receita pedido;
    private Texture skin;
    private final Texture balaoDeFala = new Texture("BalaoDeFala.png");
    private int posX;
    private int posY;
    private final int TAM_X = 240;
    private final int TAM_Y = 180;
    private final int velocidade = 2;
    private final int MAX_Y = 360;
    private final int MIN_Y = -270;
    private boolean parado = false;
    private boolean pagou = false;

    Cliente(int entrada){
        this.tempoChegada = Cozinha.getTempo();
        this.pedido = randomizaPedido();
        this.skin = geraSkin();
        this.posX = TAM_X * entrada;
        this.posY = MIN_Y;
    }
    //Getter e Setter de TempoEspera
    public void setTempoEspera(float tempoEspera) {
        this.tempoEspera = tempoEspera;
    }
    public float getTempoEspera() {
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
    public float getTempoChegada() {
        return tempoChegada;
    }
    public void setTempoChegada(float tempoChegada) {
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

    //Getter e Setter de Parado
    public boolean isParado() {
        return parado;
    }
    public void setParado(boolean parado) {
        this.parado = parado;
    }

    //Getter e Setter de Skin
    public Texture getSkin() {
        return skin;
    }
    public void setSkin(Texture skin) {
        this.skin = skin;
    }

    //Getter e Setter de pagou
    public boolean isPagou() {
        return pagou;
    }
    public void setPagou(boolean pagou) {
        this.pagou = pagou;
    }

    //Retorna uma receita aleatoria que pode ser usada como pedido do cliente
    public Receita randomizaPedido() {
        Random rand = new Random();
        return new Receita(rand.nextInt(TodasReceitas.getTOTAL_RECEITAS_VALIDAS()) + 3);
    }

    //Cliente deve pagar o pedido baseado na demora do jogador e no tipo de cliente
    abstract int pagaPedido();
    //Indica se o cliente ja pediu tudo que pretende
    abstract boolean satisfeito();
    //Atualiza o pedido caso esteja disponivel para subclasse
    abstract void atualizaPedido();
    //Exibe um balao de fala com o pedido do cliente
    public void fazPedido(SpriteBatch batch){
        batch.draw(balaoDeFala, posX, posY + TAM_Y, TAM_X, TAM_Y);
        batch.draw(pedido.getTextura(), posX + (float)TAM_X / 4, posY + TAM_Y + (float)TAM_Y / 4, (float) TAM_X / 2, (float) TAM_Y / 2);
    }

    //Skin pode ser determinada aleatoriamente ou de alguma forma especifica, cada um subclasse decide
    abstract Texture geraSkin();
    //Verifica se o jogador ja excedeu o tempo maximo de espera do cliente
    public boolean tempoEsgotado(){
        return Cozinha.getTempo() - this.tempoChegada > this.tempoEspera;
    }

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

    //Cliente se retira do restaurante
    public boolean vaiEmbora(){
        this.parado = false;
        this.posY -= this.velocidade;
        if(this.posY == MIN_Y){
            this.dispose();
            return false;
        }
        return true;
    }

    //Rotina de destrucao
    public void dispose() {
        this.pedido.dispose();
        this.skin.dispose();
    }
}

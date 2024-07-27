package INF_Diner.java;

import com.badlogic.gdx.graphics.Texture;
import java.util.Random;

public class ProfessorRico extends Cliente{
    private int gorjeta;
    private Receita segundoPedido;
    private final int TOTAL_PROFESSORES_RICOS = 1;
    private final int MAX_GORJETA = 6;
    private final int TEMPO_ESPERA = 50;

    ProfessorRico(double tempoDeJogo){
        super(tempoDeJogo);
        Random rand = new Random();
        this.gorjeta = rand.nextInt(MAX_GORJETA);
        segundoPedido = randomizaPedido();
        this.tempoEspera = TEMPO_ESPERA;
    }

    //Getter e Setter de Gorjeta
    public int getGorjeta() {
        return gorjeta;
    }
    public void setGorjeta(int gorjeta) {
        this.gorjeta = gorjeta;
    }

    //Getter e setter de SegundoPedido
    public Receita getSegundoPedido() {
        return segundoPedido;
    }
    public void setSegundoPedido(Receita segundoPedido) {
        this.segundoPedido = segundoPedido;
    }


    //AINDA FALTA IMPLEMENTAR A CONSIDERACAO DO TEMPO DE JOGO
    @Override
    public int pagaPedido(double tempoDeJogo){
        return (this.pedido.ingredientes.size() * 2) + (this.segundoPedido.ingredientes.size() * 2) + this.gorjeta;
    }

    //IMPLEMENTAR
    @Override
    public void fazPedido(){

    }
    //Gera aleatoriamente uma skin para o professor rico dentre as disponiveis
    public Texture geraSkin(){
        Random rand = new Random();
        return new Texture("professor_rico" + rand.nextInt(TOTAL_PROFESSORES_RICOS));
    }

    //Rotina de destrucao
    @Override
    public void dispose() {
        super.dispose();
        this.segundoPedido.dispose();
    }
}

package INF_Diner.java;

import com.badlogic.gdx.graphics.Texture;
import java.util.Random;

public class Professor extends Cliente{
    private final int TOTAL_PROFESSORES = 1; //Numero de skins de professor disponiveis

    Professor(double tempoDeJogo){
        this.tempoChegada = tempoDeJogo;
        this.pedido = randomizaPedido();
        this.skin = geraSkin();
        Cliente.contagem++;
        this.posX = 0;
        this.posY = 0;
    }

    //AINDA FALTA IMPLEMENTAR A CONSIDERACAO DO TEMPO DE JOGO
    @Override
    public int pagaPedido(double tempoDeJogo){
        return (this.pedido.ingredientes.size() * 2);
    }

    //IMPLEMENTAR
    @Override
    public void fazPedido(){
    }

    //Gera aleatoriamente uma skin para o professor dentre as disponiveis
    @Override
    public Texture geraSkin(){
        Random rand = new Random();
        return new Texture("professor" + rand.nextInt(TOTAL_PROFESSORES));
    }
}

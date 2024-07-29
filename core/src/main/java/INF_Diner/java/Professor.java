package INF_Diner.java;

import com.badlogic.gdx.graphics.Texture;
import java.util.Random;

public class Professor extends Cliente{
    private final int TOTAL_PROFESSORES = 6; //Numero de skins de professor disponiveis
    private final float TEMPO_ESPERA = 35f;

    Professor(int entrada) {
        super(entrada);
        this.setTempoEspera(TEMPO_ESPERA);
    }

    //O professor pagao dobro da quantidade de ingredientes como valor
    @Override
    public int pagaPedido(){
        super.setPagou(true);
        return (this.getPedido().ingredientes.size() * 2);
    }

    //Indica que o jogador ja esta contente apos o primeiro pedido
    @Override
    public boolean satisfeito(){
        return true;
    }

    //Nao existe para professor
    @Override
    public void atualizaPedido(){
    }

    //Gera aleatoriamente uma skin para o professor dentre as disponiveis
    @Override
    public Texture geraSkin(){
        Random rand = new Random();
        return new Texture("Professor" + rand.nextInt(TOTAL_PROFESSORES) + ".png");
    }
}

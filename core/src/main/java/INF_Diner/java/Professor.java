package INF_Diner.java;

import com.badlogic.gdx.graphics.Texture;
import java.util.Random;

public class Professor extends Cliente{
    //Atributos
    private final int TOTAL_PROFESSORES = 6; //Numero de skins de professor disponiveis
    private final float TEMPO_ESPERA = 35f;

    //Construtor
    Professor(int tileEntrada) {
        super(tileEntrada);
        this.setTempoEspera(TEMPO_ESPERA);
    }

    //Getters
    public int getTOTAL_PROFESSORES() {
        return TOTAL_PROFESSORES;
    }
    public float getTEMPO_ESPERA() {
        return TEMPO_ESPERA;
    }

    //Outros Metodos

    //O professor paga o dobro da quantidade de ingredientes como valor
    @Override
    public int pagaPedido(){
        super.setPagou(true);
        return (this.getPedido().ingredientes.size() * 2);
    }

    //Nao existe para professor, ja que so tem um pedido fixo
    @Override
    public void atualizaPedido(){
    }

    //Indica que o jogador ja esta contente apos o primeiro pedido
    @Override
    public boolean satisfeito(){
        return true;
    }

    //Gera aleatoriamente uma skin para o professor dentre as disponiveis
    @Override
    public Texture geraSkin(){
        Random rand = new Random();
        return new Texture("Professor" + rand.nextInt(TOTAL_PROFESSORES) + ".png");
    }
}

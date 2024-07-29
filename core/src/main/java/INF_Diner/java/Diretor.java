package INF_Diner.java;

import com.badlogic.gdx.graphics.Texture;
import java.util.Random;

public class Diretor extends Cliente{
    private int pagamento;
    private int banquete;
    private final int TOTAL_DIRETORES = 2;
    private final int MAX_GORJETA = 20;
    private final float TEMPO_ESPERA = 70f;

    Diretor(int entrada){
        super(entrada);
        Random rand = new Random();
        this.pagamento = rand.nextInt(MAX_GORJETA);
        this.banquete = 2;
        this.setTempoEspera(TEMPO_ESPERA);
    }

    //Getter e Setter de Gorjeta
    public int getPagamento() {
        return pagamento;
    }
    public void setPagamento(int pagamento) {
        this.pagamento = pagamento;
    }

    //Getter e setter de Banquete
    public int getBanquete() {
        return banquete;
    }
    public void setBanquete(int banquete) {
        this.banquete = banquete;
    }

    //O pagamento equivale ao dobro do numero de ingredientes dos 3 pedidos mais uma gorjeta
    @Override
    public int pagaPedido(){
        super.setPagou(true);
        return pagamento + this.getPedido().ingredientes.size() * 2;
    }

    //Indica que o jogador ja esta contente apos o primeiro pedido
    @Override
    public boolean satisfeito(){
        return banquete == 0;
    }

    //Nao existe para professor
    @Override
    public void atualizaPedido(){
        this.banquete--;
        this.pagamento += this.getPedido().ingredientes.size();
        this.setPedido(randomizaPedido());
    }

    //Gera aleatoriamente uma skin para o professor rico dentre as disponiveis
    @Override
    public Texture geraSkin(){
        Random rand = new Random();
        return new Texture("Diretor" + rand.nextInt(TOTAL_DIRETORES) + ".png");
    }

    //Rotina de destrucao
    @Override
    public void dispose() {
        super.dispose();
    }
}

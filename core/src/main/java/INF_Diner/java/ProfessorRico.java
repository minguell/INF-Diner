package INF_Diner.java;

import com.badlogic.gdx.graphics.Texture;
import java.util.Random;

public class ProfessorRico extends Cliente{
    private int pagamento;
    private boolean deveRepetir;
    private final int TOTAL_PROFESSORES_RICOS = 4;
    private final int MAX_GORJETA = 6;
    private final float TEMPO_ESPERA = 55f;

    ProfessorRico(int entrada){
        super(entrada);
        Random rand = new Random();
        this.pagamento = rand.nextInt(MAX_GORJETA);
        this.setTempoEspera(TEMPO_ESPERA);
        this.deveRepetir = true;
    }

    //Getter e Setter de Pagamento
    public int getPagamento() {
        return pagamento;
    }
    public void setPagamento(int pagamento) {
        this.pagamento = pagamento;
    }

    //Getter e Setter de Repeticao
    public boolean isDeveRepetir() {
        return deveRepetir;
    }
    public void setDeveRepetir(boolean deveRepetir) {
        this.deveRepetir = deveRepetir;
    }

    //O pagamento vale o dobro da quantidade de ingredientes de cada mais um valor de gorjeta
    @Override
    public int pagaPedido(){
        super.setPagou(true);
        return pagamento + this.getPedido().ingredientes.size();
    }

    //Indica que o jogador ja esta contente apos o primeiro pedido
    @Override
    public boolean satisfeito(){
        return !this.deveRepetir;
    }

    //Nao existe para professor
    @Override
    public void atualizaPedido(){
        this.deveRepetir = false;
        this.pagamento += this.getPedido().ingredientes.size();
        this.setPedido(randomizaPedido());
    }

    //Gera aleatoriamente uma skin para o professor rico dentre as disponiveis
    public Texture geraSkin(){
        Random rand = new Random();
        return new Texture("ProfessorRico" + rand.nextInt(TOTAL_PROFESSORES_RICOS)  + ".png");
    }

    //Rotina de destrucao
    @Override
    public void dispose() {
        super.dispose();
    }
}

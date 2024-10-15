package INF_Diner.java;

import com.badlogic.gdx.graphics.Texture;
import java.util.Random;

public class ProfessorRico extends Cliente{
    //Atributos
    private int pagamento;
    private boolean deveRepetir;
    private final int TOTAL_PROFESSORES_RICOS = 4; //Numero de skins de professor rico
    private final int MAX_GORJETA = 6;
    private final float TEMPO_ESPERA = 55f;

    //Construtor
    ProfessorRico(int tileEntrada){
        super(tileEntrada);
        Random rand = new Random();
        this.pagamento = rand.nextInt(MAX_GORJETA);
        this.setTempoEspera(TEMPO_ESPERA);
        this.deveRepetir = true;
    }

    //Construtor pra teste
    ProfessorRico(int tileEntrada, boolean teste){
        super(tileEntrada, teste);
        Random rand = new Random();
        this.pagamento = rand.nextInt(MAX_GORJETA);
        this.setTempoEspera(TEMPO_ESPERA);
        this.deveRepetir = true;
    }

    //Getters e Setters
    public int getPagamento() {
        return pagamento;
    }
    public void setPagamento(int pagamento) {
        this.pagamento = pagamento;
    }
    public boolean isDeveRepetir() {
        return deveRepetir;
    }
    public void setDeveRepetir(boolean deveRepetir) {
        this.deveRepetir = deveRepetir;
    }
    public int getTOTAL_PROFESSORES_RICOS() {
        return TOTAL_PROFESSORES_RICOS;
    }
    public int getMAX_GORJETA() {
        return MAX_GORJETA;
    }
    public float getTEMPO_ESPERA() {
        return TEMPO_ESPERA;
    }

    //Outros Metodos

    //O pagamento vale o dobro da quantidade de ingredientes de cada mais um valor de gorjeta
    @Override
    public int pagaPedido(){
        super.setPagou(true);
        return pagamento + this.getPedido().ingredientes.size();
    }

    //O professor rico uso uma flag pra indicar que noa e mais necessario repetir
    @Override
    public void atualizaPedido(){
        this.deveRepetir = false;
        this.pagamento += this.getPedido().ingredientes.size() * 2;
        this.setPedido(randomizaPedido());
    }

    //O professor rico e satisfeito quando nao precisar repetir(nao tiver outro pedido)
    @Override
    public boolean satisfeito(){
        return !this.deveRepetir;
    }

    //Gera aleatoriamente uma skin para o professor rico dentre as disponiveis
    public Texture geraSkin(){
        Random rand = new Random();
        return new Texture("ProfessorRico" + rand.nextInt(TOTAL_PROFESSORES_RICOS)  + ".png");
    }
}

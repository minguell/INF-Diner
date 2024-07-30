package INF_Diner.java;

import com.badlogic.gdx.graphics.Texture;
import java.util.Random;

//Tipo especial de cliente caracterizado por ser mais raro, pagar gorjetas generosas, pedir 3 pratos e esperar por mais tempo
public class Diretor extends Cliente{
    //Atributos
    private int pagamento;
    private int banquete;
    private final int TOTAL_DIRETORES = 2; //Numero de skins de Diretor disponiveis
    private final int MAX_GORJETA = 20;
    private final float TEMPO_ESPERA = 70f;

    //Construtor
    Diretor(int tileEntrada){
        super(tileEntrada);
        Random rand = new Random();
        this.pagamento = rand.nextInt(MAX_GORJETA);
        this.setTempoEspera(TEMPO_ESPERA);
        this.banquete = 2;
    }

    //Construtor para testes
    Diretor(int tileEntrada, boolean teste){
        super(tileEntrada, teste);
        Random rand = new Random();
        this.pagamento = rand.nextInt(MAX_GORJETA);
        this.setTempoEspera(TEMPO_ESPERA);
        this.banquete = 2;
    }

    //Getters e Setters
    public int getPagamento() {
        return pagamento;
    }
    public void setPagamento(int pagamento) {
        this.pagamento = pagamento;
    }
    public int getTOTAL_DIRETORES() {
        return TOTAL_DIRETORES;
    }
    public int getMAX_GORJETA() {
        return MAX_GORJETA;
    }
    public float getTEMPO_ESPERA() {
        return TEMPO_ESPERA;
    }
    public int getBanquete() {
        return banquete;
    }
    public void setBanquete(int banquete) {
        this.banquete = banquete;
    }

    //Outros Metodos

    //O pagamento equivale ao dobro do numero de ingredientes dos 3 pedidos mais uma gorjeta
    @Override
    public int pagaPedido(){
        super.setPagou(true);
        return pagamento + this.getPedido().ingredientes.size() * 2;
    }

    //Atualiza o pedido do Diretor com o proximo do seu total (3)
    @Override
    public void atualizaPedido(){
        this.banquete--;
        this.pagamento += this.getPedido().ingredientes.size() * 2;
        this.setPedido(randomizaPedido());
    }

    //Indica que o Diretor esta satisfeito se o contador de pedidos for 0
    @Override
    public boolean satisfeito(){
        return banquete == 0;
    }

    //Gera aleatoriamente uma skin para o diretor dentre as disponiveis
    @Override
    public Texture geraSkin(){
        Random rand = new Random();
        return new Texture("Diretor" + rand.nextInt(TOTAL_DIRETORES) + ".png");
    }
}

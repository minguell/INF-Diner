package INF_Diner.java;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Random;

public class Diretor extends Cliente{
    private int gorjeta;
    private ArrayList<Receita> banquete;
    private final int TOTAL_DIRETORES = 1;
    private final int MAX_GORJETA = 20;
    private final int TEMPO_ESPERA = 65;

    Diretor(double tempoDeJogo){
        super(tempoDeJogo);
        Random rand = new Random();
        this.gorjeta = rand.nextInt(MAX_GORJETA);
        this.banquete = new ArrayList<>();
        this.banquete.add(randomizaPedido());
        this.banquete.add(randomizaPedido());
    }

    //Getter e Setter de Gorjeta
    public int getGorjeta() {
        return gorjeta;
    }
    public void setGorjeta(int gorjeta) {
        this.gorjeta = gorjeta;
    }

    //Getter e setter de Banquete
    public ArrayList<Receita> getBanquete() {
        return banquete;
    }
    public void setBanquete(ArrayList<Receita> banquete) {
        this.banquete = banquete;
    }


    //AINDA FALTA IMPLEMENTAR A CONSIDERACAO DO TEMPO DE JOGO
    @Override
    public int pagaPedido(double tempoDeJogo){
        return (this.pedido.ingredientes.size() * 2) + (this.banquete.get(0).ingredientes.size() * 2) + (this.banquete.get(0).ingredientes.size() * 2) + this.gorjeta;
    }

    //IMPLEMENTAR
    @Override
    public void fazPedido(){
    }

    //Gera aleatoriamente uma skin para o professor rico dentre as disponiveis
    @Override
    public Texture geraSkin(){
        Random rand = new Random();
        return new Texture("diretor" + rand.nextInt(TOTAL_DIRETORES));
    }

    //Rotina de destrucao
    @Override
    public void dispose() {
        super.dispose();
        this.banquete.get(0).dispose();
        this.banquete.get(1).dispose();
    }
}

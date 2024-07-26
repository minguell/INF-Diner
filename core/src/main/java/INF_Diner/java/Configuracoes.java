package INF_Diner.java;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

//Classe que permite o jogador escolher a dificuldade do jogo, o volume e o modo da tela
//Configuracoes herda de TelaOpcoes, uma classe abstrata que possui algumas funcionalidades de telas de opcoes genericas
public class Configuracoes extends TelaOpcoes {
    private boolean mostrarConfiguracoes;
    private int dificuldadeAtual;
    private final Texture[] dificuldades = new Texture[3];
    //Construtor
    public Configuracoes(AudioTelas audioTelas){
        this.fundoTela = new Texture("Configuracoes.png");
        this.audioTelas = audioTelas;
        this.dificuldadeAtual = 0;
        this.dificuldades[0] = new Texture("Facil.png");
        this.dificuldades[1] = new Texture("Medio.png");
        this.dificuldades[2] = new Texture("Brutal.png");
        this.mostrarConfiguracoes = false;
    }

    //Getter e Setter de MostrarConfiguracoes
    public boolean getMostrarConfiguracoes() {
        return mostrarConfiguracoes;
    }
    public void setMostrarConfiguracoes(boolean mostrarConfiguracoes) {
        this.mostrarConfiguracoes = mostrarConfiguracoes;
    }

    //Constante representando o numero de opcoes de botoes do menu
    @Override
    protected int getN_OPCOES(){
        return 4;
    }

    //Loop de exibicao/operacional da tela de opcoes
    @Override
    public void render() {
        this.audioTelas.tocarMusica();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        this.batch.begin();
        this.batch.draw(this.fundoTela, 0, 0);
        this.batch.draw(this.dificuldades[dificuldadeAtual], 840, 800);
        this.batch.end();
        this.tratadorDeEntradas();

    }

    //Processamento de inputs do jogador
    @Override
    public void tratadorDeEntradas()
    {
        super.tratadorDeEntradas(); //Referente as teclas TAB (muda modo da tela) e ENTER(selecionar)
        this.mudaOpcaoVertical(); //Referente as teclas W e S e as setas CIMA e BAIXO(mudar opcao)
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            this.audioTelas.efeitoSelecao();
            this.mostrarConfiguracoes = false;
        }
    }

    //Execucao da escolha do jogador ao apertar ENTER baseado na opcao
    @Override
    public void opcaoSelecionada(){
    }
}

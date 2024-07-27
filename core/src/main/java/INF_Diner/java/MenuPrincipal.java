package INF_Diner.java;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

//Classe que carrega e opera o menu em que o usuario se depara imediatamente quando abrir o jogo. Dela sao iniciados a pagina de configuracoes e a gameplay em si.
//MenuPrincipal herda de TelaOpcoes, uma classe abstrata que possui algumas funcionalidades de telas de opcoes genericas
public class MenuPrincipal extends TelaOpcoes {
    private final Texture botaoPlay;
    private final Texture botaoSettings;
    private final Texture botaoExit;
    private final Configuracoes config;
    private final Cozinha cozinha;
    private final int ALTURA_SETA = 300;
    private final int X_BOTOES = 1000; //X referente a posicao do mais a esquerda dos botoes
    private final int Y_BOTOES = 25;
    private final int DISTANCIA_BOTOES = 325;

    //Construtor
    MenuPrincipal() {
        this.fundoTela = new Texture("MenuPrincipal.png");
        this.botaoPlay = new Texture("BotaoPlay.png");
        this.botaoSettings = new Texture("BotaoSettings.png");
        this.botaoExit = new Texture("BotaoExit.png");
        this.audioTelas = new AudioTelas();
        this.config = new Configuracoes(audioTelas);
        this.cozinha = new Cozinha();
    }

    //Constante representando o numero de opcoes de botoes do menu
    @Override
    protected int getN_OPCOES(){
        return 3;
    }

    //Vai renderizar, dependendo de algumas condicoes, um entre o gameplay do jogo, a pagina de configuracoes e o menu principal
    public void gameLoop() {
        if(this.config.getMostrarConfiguracoes()){
            this.config.render();
        }
        else if (this.cozinha.getMostrarCozinha()){
            this.cozinha.render();
        }
        else {
            this.render();
        }
    }

    //Loop de exibicao/operacional do menu principal
    @Override
    public void render()
    {
        this.audioTelas.tocarMusica();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        this.batch.begin();
        this.batch.draw(fundoTela, 0, 0);
        this.batch.draw(this.botaoPlay, X_BOTOES, Y_BOTOES);
        this.batch.draw(this.botaoSettings, X_BOTOES + DISTANCIA_BOTOES, Y_BOTOES);
        this.batch.draw(this.botaoExit, X_BOTOES + 2 * DISTANCIA_BOTOES, Y_BOTOES);
        this.batch.draw(this.setaOpcaoAtual, X_BOTOES + DISTANCIA_BOTOES * this.opcao, ALTURA_SETA); //Desenha a seta acima da opcao selecionada
        this.batch.end();
        this.tratadorDeEntradas();
    }

    //Rotina de encerramento
    @Override
    public void dispose() {
        super.dispose();
        this.botaoPlay.dispose();
        this.botaoSettings.dispose();
        this.botaoExit.dispose();
        this.config.dispose();
        this.audioTelas.dispose();
    }

    //Processamento de inputs do jogador
    @Override
    public void tratadorDeEntradas()
    {
        super.tratadorDeEntradas(); //Referente as teclas TAB (muda modo da tela) e ENTER(selecionar)
        this.mudaOpcao(); //Referente as teclas A e D e as setas ESQUERDA e DIREITA(mudar opcao)
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            this.fechaJogo();
        }
    }

    //Execucao da escolha do jogador ao apertar ENTER baseado na opcao
    @Override
    public void opcaoSelecionada(){
        switch(this.opcao){
            case 0:
                this.iniciaJogo();
                break;

            case 1:
                this.abreOpcoes();
                break;

            case 2:
                this.fechaJogo();
                break;
        }
    }

    //Prepara o gameplay
    public void iniciaJogo(){
        this.cozinha.setMostrarCozinha(true);
    }

    //Fecha o programa
    public void fechaJogo(){
        Gdx.app.exit();
    }

    //Muda de pagina do menu principal pro menu de configuracoes
    public void abreOpcoes(){
        this.config.setMostrarConfiguracoes(true);
    }
}

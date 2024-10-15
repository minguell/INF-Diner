package INF_Diner.java;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

//Classe que carrega e opera o menu em que o usuario se depara imediatamente quando abrir o jogo. Dela sao iniciados a pagina de configuracoes e a gameplay em si.
//MenuPrincipal herda de TelaOpcoes, uma classe abstrata que possui algumas funcionalidades de telas de opcoes genericas
public class MenuPrincipal extends TelaOpcoes {
    //Atributos
    private final Texture botaoPlay;
    private final Texture botaoSettings;
    private final Texture botaoExit;
    private final Configuracoes config;
    private Cozinha cozinha;
    private boolean mostrarControles;
    private final Texture controles = new Texture("Controles.png");
    private int pontuacaoMaxima;
    private final int ALTURA_SETA = 300;
    private final int X_BOTOES = 1000; //X referente a posicao do mais a esquerda dos botoes
    private final int Y_BOTOES = 25;
    private final int DISTANCIA_BOTOES = 325;
    private final int X_TEXTO = 0;
    private final int Y_TEXTO = 75;

    //Construtor

    MenuPrincipal() {
        this.fundoTela = new Texture("MenuPrincipal.png");
        this.botaoPlay = new Texture("BotaoPlay.png");
        this.botaoSettings = new Texture("BotaoSettings.png");
        this.botaoExit = new Texture("BotaoExit.png");
        this.audioTelas = new AudioTelas();
        this.cozinha = new Cozinha();
        this.config = new Configuracoes(audioTelas, this.cozinha.getAudioJogo());
        this.mostrarControles = true;
        this.pontuacaoMaxima = 0;
    }

    //Getters e Setters

    public Texture getBotaoPlay() {
        return botaoPlay;
    }
    public Texture getBotaoSettings() {
        return botaoSettings;
    }
    public Texture getBotaoExit() {
        return botaoExit;
    }
    public Configuracoes getConfig() {
        return config;
    }
    public Cozinha getCozinha() {
        return cozinha;
    }
    public void setCozinha(Cozinha cozinha) {
        this.cozinha = cozinha;
    }
    public boolean isMostrarControles() {
        return mostrarControles;
    }
    public void setMostrarControles(boolean mostrarControles) {
        this.mostrarControles = mostrarControles;
    }
    public Texture getControles() {
        return controles;
    }
    public int getPontuacaoMaxima() {
        return pontuacaoMaxima;
    }
    public void setPontuacaoMaxima(int pontuacaoMaxima) {
        this.pontuacaoMaxima = pontuacaoMaxima;
    }
    public int getALTURA_SETA() {
        return ALTURA_SETA;
    }
    public int getX_BOTOES() {
        return X_BOTOES;
    }
    public int getY_BOTOES() {
        return Y_BOTOES;
    }
    public int getDISTANCIA_BOTOES() {
        return DISTANCIA_BOTOES;
    }
    public int getX_TEXTO() {
        return X_TEXTO;
    }
    public int getY_TEXTO() {
        return Y_TEXTO;
    }
    //Constante representando o numero de opcoes de botoes do menu
    @Override
    protected int getN_OPCOES(){
        return 3;
    }

    //Outros Metodos

    //Mostra, dependendo de condicoes, o gameplay do jogo, a pagina de configuracoes, o menu principal ou os controles
    public void gameLoop() {
        //Caso Controles
        if(this.mostrarControles){
            this.audioTelas.tocarMusica();
            ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
            this.batch.begin();
            this.batch.draw(controles, 0, 0);
            if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) || Gdx.input.isKeyJustPressed(Input.Keys.C) || Gdx.input.isKeyJustPressed(Input.Keys.TAB) ||
                Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isKeyJustPressed(Input.Keys.R) ||
                Gdx.input.isKeyJustPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.A) || Gdx.input.isKeyJustPressed(Input.Keys.S) || Gdx.input.isKeyJustPressed(Input.Keys.D) ||
                Gdx.input.isKeyJustPressed(Input.Keys.LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.DOWN) || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
                this.mostrarControles = false;
            }
            this.batch.end();
        }
        //Caso Configuracoes
        else if(this.config.isMostrarConfiguracoes()){
            this.config.render();
        }
        //Caso Jogo
        else if (this.cozinha.isMostrarCozinha()){
            this.pontuacaoMaxima = Math.max(this.cozinha.render(), pontuacaoMaxima);
        }
        //Caso Menu Principal
        else {
            this.render();
        }
    }

    //Loop de exibicao/operacional do menu principal
    @Override
    public void render()
    {   BitmapFont font = new BitmapFont();
        font.getData().setScale(5);
        this.audioTelas.tocarMusica();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        this.batch.begin();
        this.batch.draw(fundoTela, 0, 0);
        this.batch.draw(this.botaoPlay, X_BOTOES, Y_BOTOES);
        this.batch.draw(this.botaoSettings, X_BOTOES + DISTANCIA_BOTOES, Y_BOTOES);
        this.batch.draw(this.botaoExit, X_BOTOES + 2 * DISTANCIA_BOTOES, Y_BOTOES);
        this.batch.draw(this.setaOpcaoAtual, X_BOTOES + DISTANCIA_BOTOES * this.opcao, ALTURA_SETA); //Desenha a seta acima da opcao selecionada
        font.draw(this.batch, "Pontuacao Maxima: " + this.pontuacaoMaxima, X_TEXTO, Y_TEXTO);
        this.batch.end();
        this.tratadorDeEntradas();
    }

    //Processamento de inputs do jogador
    @Override
    public void tratadorDeEntradas()
    {
        super.tratadorDeEntradas(); //Referente as teclas TAB (muda modo da tela) e ENTER(selecionar)
        this.mudaOpcao(); //Referente as teclas A e D e as setas ESQUERDA e DIREITA(mudar opcao)
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){ //Fecha jogo
            this.fechaJogo();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.C)){ //Mostra menu de opcoes
            this.mostrarControles = true;
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
        this.cozinha = new Cozinha();
        this.cozinha.setMostrarCozinha(true);
        this.audioTelas.pararMusica();
    }

    //Fecha o programa
    public void fechaJogo(){
        Gdx.app.exit();
    }

    //Muda de pagina do menu principal pro menu de configuracoes
    public void abreOpcoes(){
        this.config.setMostrarConfiguracoes(true);
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
        this.cozinha.dispose();
    }
}

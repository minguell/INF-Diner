package INF_Diner.java;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

//Classe que permite o jogador escolher a dificuldade do jogo, o volume e o modo da tela
//Configuracoes herda de TelaOpcoes, uma classe abstrata que possui algumas funcionalidades de telas de opcoes genericas
public class Configuracoes extends TelaOpcoes {
    //Atributos
    private boolean mostrarConfiguracoes; //Inicia exibicao desta tela
    private final AudioJogo audioJogo;
    public static int dificuldadeAtual = 2; //Configuracao de dificuldade do jogo 0 facil 1 medio 2 brutal
    private final Texture[] dificuldades = new Texture[3];
    private final Texture aumentarVolume = new Texture("AumentarVolume.png");
    private final Texture diminuirVolume = new Texture("DiminuirVolume.png");
    private final Texture modoDaTela = new Texture("ModoDaTela.png");
    private final Texture voltar = new Texture("Voltar.png");
    private final int ALTURA_SETA = 610;
    private final int X_BOTOES = 50; //X referente a posicao do mais a esquerda dos botoes
    private final int Y_BOTOES = 390;
    private final int DISTANCIA_BOTOES = 400;

    //Construtor
    public Configuracoes(AudioTelas audioTelas, AudioJogo audioJogo){
        this.fundoTela = new Texture("Configuracoes.png");
        this.audioTelas = audioTelas;
        this.audioJogo = audioJogo;
        this.dificuldades[0] = new Texture("Facil.png");
        this.dificuldades[1] = new Texture("Medio.png");
        this.dificuldades[2] = new Texture("Brutal.png");
        this.mostrarConfiguracoes = false;
        Configuracoes.dificuldadeAtual = 2;
    }

    //Getters e Setters
    public boolean isMostrarConfiguracoes() {
        return mostrarConfiguracoes;
    }
    public void setMostrarConfiguracoes(boolean mostrarConfiguracoes) {
        this.mostrarConfiguracoes = mostrarConfiguracoes;
    }
    public int getDificuldadeAtual() {
        return dificuldadeAtual;
    }
    public void setDificuldadeAtual(int dificuldadeAtual) {
        Configuracoes.dificuldadeAtual = dificuldadeAtual;
    }
    public int getDISTANCIA_BOTOES() {
        return DISTANCIA_BOTOES;
    }
    public int getY_BOTOES() {
        return Y_BOTOES;
    }
    public int getX_BOTOES() {
        return X_BOTOES;
    }
    public int getALTURA_SETA() {
        return ALTURA_SETA;
    }
    public Texture getVoltar() {
        return voltar;
    }
    public Texture getModoDaTela() {
        return modoDaTela;
    }
    public Texture getDiminuirVolume() {
        return diminuirVolume;
    }
    public Texture getAumentarVolume() {
        return aumentarVolume;
    }
    public Texture[] getDificuldades() {
        return dificuldades;
    }
    public AudioJogo getAudioJogo() {
        return audioJogo;
    }
    //Constante representando o numero de opcoes de botoes do menu
    @Override
    protected int getN_OPCOES(){
        return 5;
    }

    //Outros Metodos

    //Loop de exibicao/operacional da tela de opcoes
    @Override
    public void render() {
        this.audioTelas.tocarMusica();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        this.batch.begin();
        this.batch.draw(this.fundoTela, 0, 0);
        this.batch.draw(this.dificuldades[Configuracoes.dificuldadeAtual], X_BOTOES, Y_BOTOES);
        this.batch.draw(this.aumentarVolume, X_BOTOES + DISTANCIA_BOTOES, Y_BOTOES);
        this.batch.draw(this.diminuirVolume, X_BOTOES + 2*DISTANCIA_BOTOES, Y_BOTOES);
        this.batch.draw(this.modoDaTela, X_BOTOES + 3*DISTANCIA_BOTOES, Y_BOTOES);
        this.batch.draw(this.voltar, X_BOTOES + 4*DISTANCIA_BOTOES, Y_BOTOES);
        this.batch.draw(this.setaOpcaoAtual, X_BOTOES + DISTANCIA_BOTOES * this.opcao, ALTURA_SETA); //Desenha a seta acima da opcao selecionada
        this.batch.end();
        this.tratadorDeEntradas();
    }

    //Processamento de inputs do jogador
    @Override
    public void tratadorDeEntradas()
    {
        super.tratadorDeEntradas(); //Referente as teclas TAB (muda modo da tela) e ENTER(selecionar)
        this.mudaOpcao(); //Referente as teclas A e D e as setas ESQUERDA e DIREITA(mudar opcao)
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            this.audioTelas.efeitoSelecao();
            this.mostrarConfiguracoes = false; //Volta pro menu principal
        }
    }

    //Execucao da escolha do jogador ao apertar ENTER baseado na opcao
    @Override
    public void opcaoSelecionada(){
        switch(this.opcao){
            case 0: //Mudar dificuldade
                if(Configuracoes.dificuldadeAtual == 2){
                    Configuracoes.dificuldadeAtual = 0;
                }
                else{
                    Configuracoes.dificuldadeAtual++;
                }
                break;
            case 1: //Aumentar volume
                if(audioTelas.getVolume() < 1.0f){
                    audioTelas.setVolume(0.1f + audioTelas.getVolume());
                    audioJogo.setVolume(0.1f + audioTelas.getVolume());
                }
                else{
                    audioTelas.efeitoErro();
                }
                break;
            case 2: //Diminuir volume
                if(audioTelas.getVolume() > 0.2f){
                    audioTelas.setVolume(audioTelas.getVolume() - 0.1f);
                    audioJogo.setVolume(audioTelas.getVolume() - 0.1f);
                }
                else if(audioTelas.getVolume() < 0.2f){
                    audioTelas.setVolume(0f);
                    audioJogo.setVolume(0f);
                }
                break;
            case 3: //Alterar modo tela
                alternarModoTela();
                break;
            case 4: //Voltar
                setMostrarConfiguracoes(false);
                break;
        }
    }

    //Rotina de encerramento
    @Override
    public void dispose(){
        super.dispose();
        this.dificuldades[0].dispose();
        this.dificuldades[1].dispose();
        this.dificuldades[2].dispose();
        this.aumentarVolume.dispose();
        this.diminuirVolume.dispose();
        this.modoDaTela.dispose();
        this.voltar.dispose();
    }
}

package INF_Diner.java;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//Define algumas funcionalidades essenciais de telas de opcoes genericas
public abstract class TelaOpcoes {
    //Atributos
    protected SpriteBatch batch = new SpriteBatch();
    protected Texture fundoTela;
    protected final Texture setaOpcaoAtual = new Texture("SetaOpcaoAtual.png"); //Indica visualmente a opcao selecionada
    protected AudioTelas audioTelas; //Responsavel por toda parte sonora das telas
    protected int opcao = 0; //Opcao inicial da tela, por padrao 0 e a primeira

    //Getters e Setters
    public Texture getFundoTela() {
        return fundoTela;
    }
    public void setFundoTela(Texture fundoTela) {
        this.fundoTela = fundoTela;
    }
    public Texture getSetaOpcaoAtual() {
        return setaOpcaoAtual;
    }
    public AudioTelas getAudioTelas() {
        return audioTelas;
    }
    public void setAudioTelas(AudioTelas audioTelas) {
        this.audioTelas = audioTelas;
    }
    public int getOpcao() {
        return opcao;
    }
    public void setOpcao(int opcao) {
        this.opcao = opcao;
    }

    //Metodos Abstratos

    //Constante que indica o numero de botoes de opcao em dada tela
    abstract int getN_OPCOES();
    //Deve existir para mostrar visualmente a tela de opcoes especifica
    abstract void render();
    //Deve existir pra permitir interatividade com a tela ao apertar ENTER
    abstract void opcaoSelecionada();

    //Outros Metodos

    //Altera a opcao selecionada quando o jogador apertar as teclas A D ou as setas ESQUEDA DIREITA
    public void mudaOpcao(){
        if((Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) || Gdx.input.isKeyJustPressed(Input.Keys.D))){
            if(this.opcao < getN_OPCOES() - 1){
                this.opcao++;
                this.audioTelas.efeitoMudarBotao();
            }
            else{
                this.audioTelas.efeitoErro();
            }

        }
        else if((Gdx.input.isKeyJustPressed(Input.Keys.LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.A))){
            if(this.opcao > 0){
                this.opcao--;
                this.audioTelas.efeitoMudarBotao();
            }
            else{
                this.audioTelas.efeitoErro();
            }
        }
        else if((Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.W)) || Gdx.input.isKeyJustPressed(Input.Keys.DOWN) || Gdx.input.isKeyJustPressed(Input.Keys.S)){
            this.audioTelas.efeitoErro(); //Faz barulho caso uma tecla invalida seja apertada
        }
    }

    //Operacoes genericas de tratamento de entrada que devem funcionar em qualquer tela de opcoes
    public void tratadorDeEntradas()
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            this.audioTelas.efeitoSelecao();
            this.opcaoSelecionada();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)){
            alternarModoTela();
        }
    }

    //Muda a tela de tela cheia a modo janela e vice-versa
    public void alternarModoTela(){
        Graphics.DisplayMode currentMode = Gdx.graphics.getDisplayMode();
        if (Gdx.graphics.isFullscreen())
            Gdx.graphics.setWindowedMode(currentMode.width, currentMode.height);
        else
            Gdx.graphics.setFullscreenMode(currentMode);
    }

    //Rotina de encerramento
    public void dispose(){
        this.fundoTela.dispose();
        this.batch.dispose();
        this.setaOpcaoAtual.dispose();
    }
}

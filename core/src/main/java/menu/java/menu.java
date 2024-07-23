package menu.java;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class menu extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture menuPrincipal;
    private Texture botaoPlay;
    private Texture botaoSettings;
    private Texture botaoExit;
    private Texture setaOpcaoAtual;
    private Music musica;
    private Sound mudarBotao;
    private Sound selecao;
    private Sound erro;
    private int opcao;
    private final int DISTANCIA_BOTOES = 325;
    private final int X_BOTOES = 1000;
    private final int Y_BOTOES = 25;
    private final int ALTURA_SETA = 300;
    private final int N_OPCOES = 2;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.menuPrincipal = new Texture("Logo.png");
        this.botaoPlay = new Texture("BotaoPlay.png");
        this.botaoSettings = new Texture("BotaoSettings.png");
        this.botaoExit = new Texture("BotaoExit.png");
        this.setaOpcaoAtual = new Texture("setaOpcaoAtual.png");
        this.musica = Gdx.audio.newMusic(Gdx.files.internal("MusicaMenu.mp3"));
        this.musica.setLooping(true);
        this.mudarBotao = Gdx.audio.newSound(Gdx.files.internal("MudarBotao.mp3"));
        this.selecao = Gdx.audio.newSound(Gdx.files.internal("SelecaoMenu.mp3"));
        this.erro = Gdx.audio.newSound(Gdx.files.internal("ErroMenu.mp3"));
        this.opcao = 0;
    }

    @Override
    public void render() {
        this.tratadorDeEntradas();
        this.musica.play();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(menuPrincipal, 0, 0);
        batch.draw(this.botaoPlay, X_BOTOES, Y_BOTOES);
        batch.draw(this.botaoSettings, X_BOTOES + DISTANCIA_BOTOES, Y_BOTOES);
        batch.draw(this.botaoExit, X_BOTOES + 2 * DISTANCIA_BOTOES, Y_BOTOES);
        batch.draw(this.setaOpcaoAtual, X_BOTOES + DISTANCIA_BOTOES * this.opcao, ALTURA_SETA); //Desenha a seta acima da opcao selecionada
        batch.end();
    }

    @Override
    public void dispose() {
        this.batch.dispose();
        this.menuPrincipal.dispose();
        this.botaoPlay.dispose();
        this.botaoSettings.dispose();
        this.botaoExit.dispose();
        this.setaOpcaoAtual.dispose();
        this.musica.dispose();
        this.mudarBotao.dispose();
        this.selecao.dispose();
        this.erro.dispose();
    }

    //Permite mudar o botao selecionado no menu principal com as setas esquerda/direita ou A e D
    public void mudaOpcao(){
        if((Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) || Gdx.input.isKeyJustPressed(Input.Keys.D))){
            if(this.opcao < N_OPCOES){
                this.opcao++;
                this.mudarBotao.play();
            }
            else{
                this.erro.play();
            }

        }
        else if((Gdx.input.isKeyJustPressed(Input.Keys.LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.A))){
            if(this.opcao > 0){
                this.opcao--;
                this.mudarBotao.play();
            }
            else{
                this.erro.play();
            }
        }
        else if((Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.W)) || Gdx.input.isKeyJustPressed(Input.Keys.DOWN) || Gdx.input.isKeyJustPressed(Input.Keys.S)){
            this.erro.play();
        }
    }

    //Chama metodo da respectiva opcao
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

    //Chama metodos baseados na entrada do usuario
    public void tratadorDeEntradas()
    {
        this.mudaOpcao(); //Referente a A D e as setas ESQUERDA DIREITA
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            this.selecao.play();
            this.opcaoSelecionada();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            this.fechaJogo();
        }
    }
    public void iniciaJogo(){

    }

    public void fechaJogo(){
        Gdx.app.exit();
    }

    public void abreOpcoes(){

    }
}

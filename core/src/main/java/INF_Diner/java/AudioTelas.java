package INF_Diner.java;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

//Classe que lida com toda parte sonora das telas de opcoes, tanto musica quanto efeitos sonoros
public class AudioTelas {
    private final Music musica;
    private final Sound mudarBotao;
    private final Sound selecao;
    private final Sound erro;
    private float volume;

    //Construtor
    AudioTelas(){
        this.musica = Gdx.audio.newMusic(Gdx.files.internal("MusicaMenu.mp3"));
        this.mudarBotao  = Gdx.audio.newSound(Gdx.files.internal("MudarBotao.mp3"));
        this.selecao  = Gdx.audio.newSound(Gdx.files.internal("SelecaoMenu.mp3"));
        this.erro = Gdx.audio.newSound(Gdx.files.internal("ErroMenu.mp3"));
        musica.setLooping(true); //Faz com que a musica rode infinitamente
        this.volume = 1.0f;
    }

    //Getter e setter de volume
    public float getVolume() {
        return volume;
    }
    public void setVolume(float volume) {
        this.volume = volume;
        this.musica.setVolume(volume);
    }

    //Rotina de encerramento da parte sonora
    public void dispose(){
        this.musica.dispose();
        this.mudarBotao.dispose();
        this.selecao.dispose();
        this.erro.dispose();
    }

    //Toca a musica indefinidamente
    public void tocarMusica(){
        this.musica.play();
    }

    //Para a musica
    public void pararMusica(){
        this.musica.stop();
    }
    //Efeito sonoro a ser usado na hora das trocas de opcao
    public void efeitoMudarBotao(){
        this.mudarBotao.play(this.volume);
    }

    //Efeito sonoro a ser usado ao apertar ENTER em uma tela de opcao
    public void efeitoSelecao(){
        this.selecao.play(this.volume);
    }

    //Efeito sonoro a ser usado quando qualquer tipo de comportamento invalido for tentado
    public void efeitoErro(){
        this.erro.play(this.volume);
    }
}

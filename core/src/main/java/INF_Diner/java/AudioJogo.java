package INF_Diner.java;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

//Classe que lida com toda parte sonora do jogo em si, tanto musica quanto efeitos sonoros
public class AudioJogo {
    //Atributos
    private final Music musica;
    private final Sound descartar;
    private final Sound interagirIngredientes;
    private final Sound cozinhar;
    private final Sound nao;
    private final Sound oi;
    private final Sound tchau;
    private final Sound valeu;
    private float volume;

    //Construtor
    AudioJogo(){
        //Atributos
        musica = Gdx.audio.newMusic(Gdx.files.internal("MusicaJogo.mp3"));
        descartar = Gdx.audio.newSound(Gdx.files.internal("Descartar.mp3"));
        interagirIngredientes =  Gdx.audio.newSound(Gdx.files.internal("InteragirIngredientes.mp3"));
        cozinhar = Gdx.audio.newSound(Gdx.files.internal("Cozinhar.mp3"));
        nao = Gdx.audio.newSound(Gdx.files.internal("Nao.mp3"));
        oi = Gdx.audio.newSound(Gdx.files.internal("Oi.mp3"));
        tchau = Gdx.audio.newSound(Gdx.files.internal("Tchau.mp3"));
        valeu = Gdx.audio.newSound(Gdx.files.internal("Valeu.mp3"));
        musica.setLooping(true); //Faz com que a musica rode infinitamente
        this.volume = 1.0f;
    }

    //Getters e Setters
    public float getVolume() {
        return volume;
    }
    public void setVolume(float volume) {
        this.volume = volume;
        this.musica.setVolume(volume);
    }
    public Music getMusica() {
        return musica;
    }
    public Sound getDescartar() {
        return descartar;
    }
    public Sound getInteragirIngredientes() {
        return interagirIngredientes;
    }
    public Sound getCozinhar() {
        return cozinhar;
    }
    public Sound getNao() {
        return nao;
    }
    public Sound getOi() {
        return oi;
    }
    public Sound getTchau() {
        return tchau;
    }
    public Sound getValeu() {
        return valeu;
    }

    //Outros metodos

    //Toca a musica indefinidamente
    public void tocarMusica(){
        this.musica.play();
    }

    //Para a musica
    public void pararMusica(){
        this.musica.stop();
    }

    //Efeito sonoro de quando o jogador interage com a lixeira
    public void efeitoDescartar(){
        this.descartar.play(this.volume);
    }

    //Efeito sonoro de quando o jogador pega ingredientes ou os coloca
    public void efeitoInteragirIngredientes(){
        this.interagirIngredientes.play(this.volume);
    }

    //Efeito sonoro de quando o jogador cozinha algo
    public void efeitoCozinhar(){
        this.cozinhar.play(this.volume);
    }

    //Efeito sonoro de quando o jogador nao satisfaz o pedido dos clientes corretamente
    public void efeitoNao(){
        this.nao.play(this.volume);
    }

    //Efeito sonoro tocado quando um cliente surgir
    public void efeitoOi(){
        this.oi.play(this.volume);
    }

    //Efeito sonoro tocado quando um cliente sumir
    public void efeitoTchau(){
        this.tchau.play(this.volume);
    }

    //Efeito sonoro tocado quando um cliente recebe um prato e atualiza o pedido
    public void efeitoValeu(){
        this.valeu.play(this.volume);
    }

    //Rotina de encerramento
    public void dispose(){
        musica.dispose();
        descartar.dispose();
        interagirIngredientes.dispose();
        cozinhar.dispose();
    }
}

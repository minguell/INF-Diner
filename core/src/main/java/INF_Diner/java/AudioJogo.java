package INF_Diner.java;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

//Classe que lida com toda parte sonora do jogo em si, tanto musica quanto efeitos sonoros
public class AudioJogo {
    private final Music musica;
    private final Sound descartar;
    private final Sound interagirIngredientes;
    private final Sound cozinhar;
    private float volume;

    //Construtor
    AudioJogo(){
        musica = Gdx.audio.newMusic(Gdx.files.internal("MusicaJogo.mp3"));
        descartar = Gdx.audio.newSound(Gdx.files.internal("Descartar.mp3"));
        interagirIngredientes =  Gdx.audio.newSound(Gdx.files.internal("InteragirIngredientes.mp3"));
        cozinhar = Gdx.audio.newSound(Gdx.files.internal("Cozinhar.mp3"));
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

    //Rotina de encerramento
    public void dispose(){
        musica.dispose();
        descartar.dispose();
        interagirIngredientes.dispose();
        cozinhar.dispose();
    }

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

}

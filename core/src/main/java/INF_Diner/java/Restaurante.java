package INF_Diner.java;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//Classe central do fluxo do jogo em si e exibe a cozinha, com suas funcionalidades
public class Restaurante {
    private boolean mostrarRestaurante; //Inicia a exibicao desta tela
    private final SpriteBatch batch;
    private final Texture chao = new Texture("Chao.png");
    private final Texture grama = new Texture("Grama.png");
    private final Texture balcao = new Texture("Armario.png");
    private final Texture arvore = new Texture("Arvore.png");
    private final AudioJogo audioJogo;
    private final Jogador jogador;
    private final int MATRIZ_X = 8;
    private final int MATRIZ_Y = 6;
    private final int ESCALA_X = 1920 / MATRIZ_X;
    private final int ESCALA_Y = 1080 / MATRIZ_Y;
    private final int TEXTO_Y = 50; //Altura do texto do inventario
    private final int TAM_IMAGEM_INVENTARIO = 50; //Altura da imagem do inventario
    private final int TEXTO_X_PALAVRA1 = 0; //X da primeira palavra do inventario
    private final int IMAGEM_X = TEXTO_X_PALAVRA1 + 260; //X da imagem do inventario
    private final int TEXTO_X_PALAVRA2 = IMAGEM_X + TAM_IMAGEM_INVENTARIO; //X da segunda palavra do inventario
    private final int[][] mapa = { //Matriz usada para desenhho do mapa, cada numero representa um tipo de "tile"
        {3, 2, 2, 2, 2, 2, 2, 3},
        {1, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 1}
    };

    //Construtor
    public Restaurante(Jogador jogador, AudioJogo audioJogo, SpriteBatch batch) {
        this.jogador = jogador;
        this.mostrarRestaurante = false;
        this.audioJogo = audioJogo;
        this.batch = batch;
    }

    //Getter e Setter de MostraRestaurante
    public boolean getMostrarRestaurante() {
        return mostrarRestaurante;
    }
    public void setMostrarRestaurante(boolean mostrarRestaurante) {
        this.mostrarRestaurante = mostrarRestaurante;
    }

    //Getter de AudioJogo
    public AudioJogo getAudioJogo() {
        return audioJogo;
    }

    //Principal metodo de exibicao e operacao do restaurante
    public void render(int dificuldade) {
        desenhaRestaurante();
        this.jogador.movimentar();
        this.jogador.render(batch);
        this.mostrarRestaurante = !this.jogador.saiuRestaurante();
    }

    //Desenha na tela toda a cozinha
    public void desenhaRestaurante(){
        int pratoIndex = 0;
        int ingredienteIndex = 0;
        //Percorre toda a matriz e desenha cada espaco
        for (int i = 0; i < MATRIZ_Y; i++) {
            for (int j = 0; j < MATRIZ_X; j++) {
                //DESENHA O CHAO
                switch(mapa[i][j]){
                    //CASO BALCAO
                    case 1:
                        this.batch.draw(chao, j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                        this.batch.draw(balcao, j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                        break;
                    //CASO GRAMA
                    case 2:
                        this.batch.draw(grama, j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                        break;
                    //CASO ARVORE
                    case 3:
                        this.batch.draw(grama, j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                        this.batch.draw(arvore, j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                        break;
                    //CASO CHAO
                    default:
                        this.batch.draw(chao, j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                        break;
                }
            }
        }
    }

    //Trata as entradas do jogador
    public void tratadorDeEntradas(){
        this.jogador.movimentar();
    }


    //Rotina de encerramento
    public void dispose(){
        this.batch.dispose();
        this.balcao.dispose();
        this.chao.dispose();
        this.grama.dispose();
        this.arvore.dispose();
    }
}

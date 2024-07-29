package INF_Diner.java;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.*;

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
    private float contador;
    private final int MATRIZ_X = 8;
    private final int MATRIZ_Y = 6;
    private final int ESCALA_X = 1920 / MATRIZ_X;
    private final int ESCALA_Y = 1080 / MATRIZ_Y;
    private final int MAX_PROFESSORES = 6;
    private final int[][] mapa = { //Matriz usada para desenhho do mapa, cada numero representa um tipo de "tile"
        {3, 2, 2, 2, 2, 2, 2, 3},
        {1, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 1}
    };
    List<Cliente> professores = new ArrayList<>();

    //Construtor
    public Restaurante(Jogador jogador, AudioJogo audioJogo, SpriteBatch batch) {
        this.jogador = jogador;
        this.mostrarRestaurante = false;
        this.audioJogo = audioJogo;
        this.batch = batch;
        for (int i = 0; i < MAX_PROFESSORES; i++) {
            //this.professores.add(new Professor(System.currentTimeMillis(), i + 1));
            this.professores.add(null);
        }
        this.contador = 0f;
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
    public void render() {
        desenhaRestaurante();
        this.jogador.movimentar();
        this.jogador.render(batch);
        atualizaProfessores();
        this.mostrarRestaurante = !this.jogador.saiuRestaurante();
    }

    //Vai atualizando professores, mesmo quando jogador nao estiver na tela
    public void atualizaProfessores(){
        boolean valido;
        contaTempo();
        for(int i = 0; i < MAX_PROFESSORES; i++){
            if(professores.get(i) != null){
                if(mostrarRestaurante){
                    batch.draw(professores.get(i).getSkin(), professores.get(i).getPosX(), professores.get(i).getPosY());
                }
                valido = professores.get(i).atualiza(batch);
                if(!valido){
                    professores.set(i, null);
                }
            }
        }
        geracaoClientes();
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            interagirClientes();
        }
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

    public void geracaoClientes(){
        if(this.contador > (60f / (Configuracoes.dificuldadeAtual + 1))) {
            this.contador = 0;
            ArrayList<Integer> indices = new ArrayList<>();
            for (int x = 0; x < MAX_PROFESSORES; x++) {
                indices.add(x);
            }
            Collections.shuffle(indices);
            for (int x = 0; x < indices.size(); x++) {
                if (professores.get(indices.get(x)) == null) {
                    Random rand = new Random();
                    int valor = rand.nextInt(7);
                    if (Configuracoes.dificuldadeAtual == 2 && valor == 6) {
                        professores.set(indices.get(x), new Diretor(indices.get(x) + 1));
                    } else if (valor > 3 && Configuracoes.dificuldadeAtual == 2) {
                        professores.set(indices.get(x), new ProfessorRico(indices.get(x) + 1));
                    } else if (valor > 4 && Configuracoes.dificuldadeAtual == 1) {
                        professores.set(indices.get(x), new ProfessorRico(indices.get(x) + 1));
                    } else {
                        professores.set(indices.get(x), new Professor(indices.get(x) + 1));
                    }
                    x = indices.size();
                }
            }
        }
    }

    public void interagirClientes(){
        if(this.jogador.getPosY() == this.jogador.getMIN_Y()){
            int indiceProfessor = (int)Math.floor((this.jogador.getPosX() - ((double) ESCALA_X / 2)) / ESCALA_X);
            if(this.professores.get(indiceProfessor) != null && this.professores.get(indiceProfessor).isParado()){
                if(this.jogador.getCaregando() == Jogador.Carregado.RECEITA &&
                    jogador.getReceitaCarregada().getTipoPrato() == this.professores.get(indiceProfessor).getPedido().getTipoPrato()){
                    this.jogador.descartaCarregado();
                    if(this.professores.get(indiceProfessor).satisfeito()){
                        this.jogador.recebeDinheiro(this.professores.get(indiceProfessor).pagaPedido());
                    }
                    else{
                        this.professores.get(indiceProfessor).atualizaPedido();
                    }
                }
                else{
                    this.audioJogo.efeitoNao();
                }
            }
        }
    }

    public void contaTempo(){
        this.contador += Gdx.graphics.getDeltaTime();
    }

    //Rotina de encerramento
    public void dispose(){
        this.balcao.dispose();
        this.chao.dispose();
        this.grama.dispose();
        this.arvore.dispose();
        for(Cliente professor: professores){
            if(professor != null){
                professor.dispose();
            }
        }
    }
}

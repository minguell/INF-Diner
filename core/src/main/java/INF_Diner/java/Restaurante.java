package INF_Diner.java;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.*;

//Classe da tela do restaurante, controla a geracao dos clientes e com suas funcionalidades
public class Restaurante {
    //Atributos
    private boolean mostrarRestaurante; //Inicia a exibicao desta tela
    private boolean forcarSpawn;
    private final SpriteBatch batch;
    private final Texture chao = new Texture("Chao.png");
    private final Texture grama = new Texture("Grama.png");
    private final Texture balcao = new Texture("Armario.png");
    private final Texture arvore = new Texture("Arvore.png");
    private final AudioJogo audioJogo;
    private final Jogador jogador;
    private float contador;
    private float INTERVALO_GERACAO = 30f;
    private final int MAX_PROFESSORES = 6;
    private final int MATRIZ_X = 8;
    private final int MATRIZ_Y = 6;
    private final int ESCALA_X = INF_Diner.TELA_X / MATRIZ_X;
    private final int ESCALA_Y = INF_Diner.TELA_Y / MATRIZ_Y;
    List<Cliente> professores = new ArrayList<>();
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
        for (int i = 0; i < MAX_PROFESSORES; i++) {
            this.professores.add(null);
        }
        this.contador = 0f;
        this.forcarSpawn = true;
    }

    //Getters e Setters

    public boolean isForcarSpawn() {
        return forcarSpawn;
    }
    public void setForcarSpawn(boolean forcarSpawn) {
        this.forcarSpawn = forcarSpawn;
    }
    public void setMostrarRestaurante(boolean mostrarRestaurante) {
        this.mostrarRestaurante = mostrarRestaurante;
    }
    public AudioJogo getAudioJogo() {
        return audioJogo;
    }
    public float getINTERVALO_GERACAO() {
        return INTERVALO_GERACAO;
    }
    public void setINTERVALO_GERACAO(float INTERVALO_GERACAO) {
        this.INTERVALO_GERACAO = INTERVALO_GERACAO;
    }
    public boolean getMostrarRestaurante() {
        return mostrarRestaurante;
    }
    public Texture getChao() {
        return chao;
    }
    public Texture getGrama() {
        return grama;
    }
    public Texture getBalcao() {
        return balcao;
    }
    public Texture getArvore() {
        return arvore;
    }
    public Jogador getJogador() {
        return jogador;
    }
    public float getContador() {
        return contador;
    }
    public void setContador(float contador) {
        this.contador = contador;
    }
    public int getMAX_PROFESSORES() {
        return MAX_PROFESSORES;
    }
    public int getMATRIZ_X() {
        return MATRIZ_X;
    }
    public int getMATRIZ_Y() {
        return MATRIZ_Y;
    }
    public int getESCALA_X() {
        return ESCALA_X;
    }
    public int getESCALA_Y() {
        return ESCALA_Y;
    }
    public List<Cliente> getProfessores() {
        return professores;
    }
    public void setProfessores(List<Cliente> professores) {
        this.professores = professores;
    }
    public int[][] getMapa() {
        return mapa;
    }

    //Outros Metodos

    //Principal metodo de exibicao e operacao do restaurante
    public void render() {
        desenhaRestaurante();
        this.jogador.movimentar();
        this.jogador.render(batch);
        atualizaProfessores();
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

    //Vai atualizando professores, mesmo quando jogador nao estiver na tela
    public void atualizaProfessores(){
        boolean valido;
        contaTempo();
        for(int i = 0; i < MAX_PROFESSORES; i++){
            if(professores.get(i) != null){
                if(mostrarRestaurante){ //Desenha cada professor
                    batch.draw(professores.get(i).getSkin(), professores.get(i).getPosX(), professores.get(i).getPosY());
                }
                valido = professores.get(i).atualiza(batch); //Verifica se o professor ainda e valido apos o atualizar
                if(!valido){ //Limpa invalidos
                    professores.set(i, null);
                }
            }
        }
        geracaoClientes();
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            interagirClientes();
        }
    }

    //Conta tempo para os ciclos de geracao
    public void contaTempo(){
        this.contador += Gdx.graphics.getDeltaTime();
    }

    //Gera Clientes, se ainda ha espaco, a cada intervalo de tempo, com tipos baseados na dificuldade
    public void geracaoClientes(){
        if(this.contador > INTERVALO_GERACAO || this.forcarSpawn) { //ForcarSpawn serve pra spawnar um cliente imediatamento e testes
            this.forcarSpawn = false;
            this.contador = 0;
            //Randomizacao do spanw
            ArrayList<Integer> indices = new ArrayList<>();
            for (int x = 0; x < MAX_PROFESSORES; x++) {
                indices.add(x); //Cria uma lista de indices
            }
            Collections.shuffle(indices); //Ordena em ordem aleatoria
            for (int x = 0; x < indices.size(); x++) {
                if (professores.get(indices.get(x)) == null) { //Procura o primeiro indice baseado nessa ordem vazio
                    if(mostrarRestaurante){
                        this.audioJogo.efeitoOi();
                    }
                    Random rand = new Random();
                    //Regras de chances de spawn baseadas em dificuldade
                    //Brutal: 2/8 Diretor 3/8 Professor Rico 3/8 Professor
                    //Medio: 3/8 Professor Rico 5/8 Professor
                    //Facil: 8/8 Professor
                    int valor = rand.nextInt(8);
                    if (Configuracoes.dificuldadeAtual == 2 && valor > 5) {
                        professores.set(indices.get(x), new Diretor(indices.get(x) + 1));
                    } else if (valor > 3 && Configuracoes.dificuldadeAtual == 2) {
                        professores.set(indices.get(x), new ProfessorRico(indices.get(x) + 1));
                    } else if (valor > 4 && Configuracoes.dificuldadeAtual == 1) {
                        professores.set(indices.get(x), new ProfessorRico(indices.get(x) + 1));
                    } else {
                        professores.set(indices.get(x), new Professor(indices.get(x) + 1));
                    }
                    x = indices.size(); //Encerra loop
                }
            }
        }
    }

    //Permite a interacao do usuario com os clientes, a entrega ou recusa da receita e a atualizacao dos clientes com isto
    public void interagirClientes(){
        if(this.jogador.getPosY() == this.jogador.getMIN_Y()){
            //Calcula o indice do cliente com quem o jogador interage
            int indiceProfessor = (int)Math.floor((this.jogador.getPosX() - ((double) ESCALA_X / 2)) / ESCALA_X);
            //Se o professor existir e for valido para interacao
            if(this.professores.get(indiceProfessor) != null && this.professores.get(indiceProfessor).isParado()){
                //Se jogador cumpriu o pedido
                if(this.jogador.getCaregando() == Jogador.Carregado.RECEITA &&
                    jogador.getReceitaCarregada().getTipoPrato() == this.professores.get(indiceProfessor).getPedido().getTipoPrato()){
                    //Entrega pedido
                    this.jogador.descartaCarregado();
                    if(this.professores.get(indiceProfessor).satisfeito()){ //Se ele satisfaz cliente vai embora
                        this.audioJogo.efeitoTchau();
                        this.jogador.recebeDinheiro(this.professores.get(indiceProfessor).pagaPedido());
                    }
                    else{ //Se nao satisfaz atualiza com o proximo
                        this.audioJogo.efeitoValeu();
                        this.professores.get(indiceProfessor).atualizaPedido();
                    }
                }
                else{ //Recusa se o jogador nao corresponder o desejo
                    this.audioJogo.efeitoNao();
                }
            }
        }
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

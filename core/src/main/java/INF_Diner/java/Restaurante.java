package INF_Diner.java;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//Classe central do fluxo do jogo em si e exibe a cozinha, com suas funcionalidades
public class Restaurante {
    private boolean mostrarRestaurante; //Inicia a exibicao desta tela
    private final SpriteBatch batch = new SpriteBatch();
    private final Texture mesa = new Texture("Balcao.png");
    private final Texture chao = new Texture("Chao.png");
    private final Texture grama = new Texture("Grama.png");
    private final Texture balcao = new Texture("Armario.png");
    private final Texture prato = new Texture("Prato.png");
    private final  ArrayList<Ingrediente> caixas = new ArrayList<>();
    private  ArrayList<Ingrediente> prato1 = new ArrayList<>();
    private  ArrayList<Ingrediente> prato2 = new ArrayList<>();
    private final Jogador jogador = new Jogador();
    private final AudioJogo audioJogo = new AudioJogo();
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
            {4, 4, 4, 4, 4, 4, 4, 4},
            {5, 0, 0, 0, 0, 0, 0, 5},
            {5, 0, 0, 0, 0, 0, 0, 5},
            {5, 5, 5, 5, 5, 5, 5, 5},
            {5, 0, 0, 0, 0, 0, 0, 5},
            {5, 0, 0, 0, 0, 0, 0, 5}
    };

    //Construtor
    public Restaurante() {
        caixas.add(new Ingrediente("Bife", true, 1, new Texture("Bife.png")));
        caixas.add(new Ingrediente("Arroz", true, 2, new Texture("Arroz.png")));
        caixas.add(new Ingrediente("Feijao", true, 3, new Texture("Feijao.png")));
        caixas.add(new Ingrediente("Macarrao", true, 4, new Texture("Macarrao.png")));
        caixas.add(new Ingrediente("Pao", false, 5, new Texture("Pao.png")));
        caixas.add(new Ingrediente("Peixe", false, 6, new Texture("Peixe.png")));
        caixas.add(new Ingrediente("Ovo", false, 6, new Texture("Ovo.png")));
        caixas.add(new Ingrediente("Tomate", false, 6, new Texture("Tomate.png")));
        this.mostrarRestaurante = false;
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

    //Principal metodo de exibicao e operacao de cozinha
    public void render(int dificuldade) {
        this.audioJogo.tocarMusica();
        tratadorDeEntradas();
        verificarTransicaoCozinha();
        ScreenUtils.clear(1f, 1f, 1f, 1f);
        this.batch.begin();
        desenhaRestaurante();
        desenhaInventario();
        this.jogador.render(batch);
        this.batch.end();
    }

    private void verificarTransicaoCozinha() {
        if (jogador.getPosY() > 1300) {
            // Transição para a tela ou estado da cozinha
            setMostrarRestaurante(false);
            // Aqui você poderia chamar um método para mudar a tela ou o estado do jogo
            // como um método `mudarParaCozinha()` que você teria que implementar
           // Cozinha.setmostrarCozinha(false);
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
                this.batch.draw(chao, j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                switch(mapa[i][j]){
                    //CASO mesa COM INGREDIENTES
                    case 1:
                        this.batch.draw(balcao, j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                        this.batch.draw(mesa, j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                        this.batch.draw(caixas.get(ingredienteIndex).getTextura(), j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                        ingredienteIndex++;
                        break;
                    //CASO PANELA
                    case 2:
                        this.batch.draw(balcao, j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                        break;
                    //CASO PRATO
                    case 3:
                        this.batch.draw(balcao, j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                        this.batch.draw(prato, j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                        //Desenha o conteudo de cada prato
                        if(pratoIndex == 0){
                            pratoIndex++;
                            for (Ingrediente ingrediente : prato1) {
                                this.batch.draw(ingrediente.getTextura(), j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                            }
                        }
                        else if (pratoIndex == 1){
                            pratoIndex++;
                            for (Ingrediente ingrediente : prato2) {
                                this.batch.draw(ingrediente.getTextura(), j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                            }
                        }
                        break;
                    //CASO LIXEIRA
                    case 4:
                        this.batch.draw(balcao, j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                        this.batch.draw(grama, j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                        break;
                    //CASO mesa SEM INGREDIENTES
                    case 5:
                        this.batch.draw(balcao, j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    //Desenha as informacoes do inventario na tela
    public void desenhaInventario(){
        BitmapFont font = new BitmapFont();
        font.getData().setScale(4);
        font.draw(this.batch, "Inventario:", TEXTO_X_PALAVRA1, TEXTO_Y);
        if(this.jogador.getCaregando() == Jogador.Carregado.INGREDIENTE){
            this.batch.draw(this.jogador.getIngredienteCarregado().getTextura(),  IMAGEM_X, 0, TAM_IMAGEM_INVENTARIO, TAM_IMAGEM_INVENTARIO);
            font.draw(this.batch, this.jogador.getIngredienteCarregado().getNome(), TEXTO_X_PALAVRA2, TEXTO_Y);
        }
        else if(this.jogador.getCaregando() == Jogador.Carregado.RECEITA){
            this.batch.draw(this.jogador.getReceitaCarregada().getTextura(),  IMAGEM_X, 0, TAM_IMAGEM_INVENTARIO, TAM_IMAGEM_INVENTARIO);
            font.draw(this.batch, this.jogador.getReceitaCarregada().getNome(), TEXTO_X_PALAVRA2, TEXTO_Y);
        }
    }

    //Trata as entradas do jogador
   public void tratadorDeEntradas(){
        this.jogador.movimentar();
         interacaoUtensilios();
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            this.mostrarRestaurante= false;
            this.audioJogo.pararMusica();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.K)) {
            this.audioJogo.pararMusica();
        }
    }

    //Trata as entradas do jogador referentes aos utensilios da cozinha
    public void interacaoUtensilios(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            if (verificarProximidadeCliente()){
               // Professor.professor.fazPedido();
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            interacaoEntregarPratos();
        }
    }

  //Permite o jogador pegar o conteudo de um prato, gerando uma mistura e limpando o prato
    public void interacaoEntregarPratos(){
        if (verificarProximidadeCliente()) {
            entregarItemAoCliente();
        } 
    }

    public boolean verificarProximidadeCliente() {
        int posYJogador = this.jogador.getPosY();
        int posXJogador = this.jogador.getPosX();
        // Supondo que a matriz mapa tem a informação dos clientes
        // e que o valor 7 representa um cliente
        if (mapa[posYJogador][posXJogador] == 7) {
            return true;
        }
        return false;
    }

    public void entregarItemAoCliente() {
        if (this.jogador.getCaregando() != Jogador.Carregado.NADA) {
            System.out.println("Item entregue ao cliente!");
          //  this.jogador.setCarregado(Jogador.Carregado.NADA);
        }
    }

    //Rotina de encerramento
    public void dispose(){
        this.batch.dispose();
        this.balcao.dispose();
        this.mesa.dispose();
        this.chao.dispose();
        this.grama.dispose();
        this.prato.dispose();
        this.audioJogo.dispose();
        for (Ingrediente value : caixas) {
            value.dispose();
        }
        for (Ingrediente ingrediente : prato1) {
            ingrediente.dispose();
        }
        for (Ingrediente ingrediente : prato2) {
            ingrediente.dispose();
        }
    }
}



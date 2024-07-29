package INF_Diner.java;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//Classe central do fluxo do jogo em si e exibe a cozinha, com suas funcionalidades
public class Cozinha {
    private boolean mostrarCozinha; //Inicia a exibicao desta tela
    private final SpriteBatch batch = new SpriteBatch();
    private final Texture panela = new Texture("Panela.png");
    private final Texture caixa = new Texture("Caixa.png");
    private final Texture chao = new Texture("Chao.png");
    private final Texture armario = new Texture("Armario.png");
    private final Texture prato = new Texture("Prato.png");
    private final Texture lixeira = new Texture("Lixeira.png");
    private final Texture fogao = new Texture("Fogao.png");
    private final  ArrayList<Ingrediente> caixas = new ArrayList<>();
    private  ArrayList<Ingrediente> prato1 = new ArrayList<>();
    private  ArrayList<Ingrediente> prato2 = new ArrayList<>();
    private final Jogador jogador = new Jogador();
    private final AudioJogo audioJogo = new AudioJogo();
    private final TodasReceitas todasReceitas = new TodasReceitas();
    private static float tempo = 0f;
    private final int MATRIZ_X = 8;
    private final int MATRIZ_Y = 6;
    private final int DESLOCA_X = -60;
    private final int DESLOCA_Y = -50;
    private final float ESCALA2 = 1.5f;
    private final int TELA_X = 1920;
    private final int TELA_Y = 1080;
    private final int ESCALA_X = TELA_X / MATRIZ_X;
    private final int ESCALA_Y = TELA_Y / MATRIZ_Y;
    private final int TEXTO_Y = 50; //Altura do texto do inventario
    private final int TAM_IMAGEM_INVENTARIO = 50; //Altura da imagem do inventario
    private final int TEXTO_X_PALAVRA1 = 0; //X da primeira palavra do inventario
    private final int IMAGEM_X = TEXTO_X_PALAVRA1 + 260; //X da imagem do inventario
    private final int TEXTO_X_PALAVRA2 = IMAGEM_X + TAM_IMAGEM_INVENTARIO; //X da segunda palavra do inventario
    private final int PONTUACAO_X = 1400; //X do texto Pontuacao
    private final int CRONOMETRO_X = 900;
    private final Restaurante restaurante = new Restaurante(jogador, audioJogo, batch);
    private final int[][] mapa = { //Matriz usada para desenhho do mapa, cada numero representa um tipo de "tile"
            { 1, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 1 },
            {5, 4, 3, 2, 2, 3, 4, 5 }
    };

    //Construtor
    public Cozinha() {
        caixas.add(new Ingrediente("Bife", true, 1, new Texture("Bife.png")));
        caixas.add(new Ingrediente("Arroz", true, 2, new Texture("Arroz.png")));
        caixas.add(new Ingrediente("Feijao", true, 3, new Texture("Feijao.png")));
        caixas.add(new Ingrediente("Macarrao", true, 4, new Texture("Macarrao.png")));
        caixas.add(new Ingrediente("Pao", false, 5, new Texture("Pao.png")));
        caixas.add(new Ingrediente("Queijo", false, 6, new Texture("Queijo.png")));
        caixas.add(new Ingrediente("Ovo", true, 7, new Texture("Ovo.png")));
        caixas.add(new Ingrediente("Tomate", false, 8, new Texture("Tomate.png")));
        caixas.add(new Ingrediente("Alface", false, 9, new Texture("Alface.png")));
        caixas.add(new Ingrediente("Peixe", true, 10, new Texture("Peixe.png")));
        this.mostrarCozinha = false;
    }

    //Getter e Setter de MostraCozinha
    public boolean getMostrarCozinha() {
        return mostrarCozinha;
    }
    public void setMostrarCozinha(boolean mostrarCozinha) {
        this.mostrarCozinha = mostrarCozinha;
    }

    //Getter e Setter de Tempo
    public static float getTempo() {
        return tempo;
    }
    public static void setTempo(float tempo) {
        Cozinha.tempo = tempo;
    }

    //Getter de AudioJogo
    public AudioJogo getAudioJogo() {
        return audioJogo;
    }

    //Principal metodo de exibicao e operacao do jogo
    public void render() {
        this.audioJogo.tocarMusica();
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            this.mostrarCozinha = false;
            this.audioJogo.pararMusica();
        }
        //Mostra todas as receitas disponiveis se R for apertado
        if(Gdx.input.isKeyJustPressed(Input.Keys.R)){
            this.todasReceitas.setMostrarReceitas(!this.todasReceitas.isMostrarReceitas());
        }
        this.batch.begin();
        if(this.todasReceitas.isMostrarReceitas()){
            this.todasReceitas.desenhaReceitas();
        }
        else {
            ScreenUtils.clear(1f, 1f, 1f, 1f);
            Cozinha.tempo += Gdx.graphics.getDeltaTime();
            if (this.restaurante.getMostrarRestaurante()) { //Mostra o restaurante
                this.restaurante.render();
            }
            else { //Senao mostra a cozinha normalmente, e atualiza o estado dos professores
                restaurante.atualizaProfessores();
                loopCozinha();
            }
        }
        desenhaInventario();
        this.batch.end();
    }

    //Principal metodo de exibicao e operacao de cozinha
    public void loopCozinha(){
        desenhaCozinha();
        this.jogador.movimentar();
        this.jogador.render(batch);
        interacaoUtensilios();
        this.restaurante.setMostrarRestaurante(this.jogador.saiuCozinha());
    }

    //Desenha na tela toda a cozinha
    public void desenhaCozinha(){
        int pratoIndex = 0;
        int ingredienteIndex = 0;
        //Percorre toda a matriz e desenha cada espaco
        for (int i = 0; i < MATRIZ_Y; i++) {
            for (int j = 0; j < MATRIZ_X; j++) { // se o mapa[i][j] for 2, desenha uma panela
                //DESENHA O CHAO
                this.batch.draw(chao, j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                switch(mapa[i][j]){
                    //CASO CAIXA COM INGREDIENTES
                    case 1:
                        this.batch.draw(armario, j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                        this.batch.draw(caixa, j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                        this.batch.draw(caixas.get(ingredienteIndex).getTextura(), j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                        ingredienteIndex++;
                        break;
                    //CASO PANELA
                    case 2:
                        this.batch.draw(armario, j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                        this.batch.draw(fogao, j * ESCALA_X + DESLOCA_X, i * ESCALA_Y + DESLOCA_Y, ESCALA_X * ESCALA2, ESCALA_Y * ESCALA2);
                        this.batch.draw(panela, j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                        break;
                    //CASO PRATO
                    case 3:
                        this.batch.draw(armario, j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
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
                        this.batch.draw(armario, j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                        this.batch.draw(lixeira, j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
                        break;
                    //CASO CAIXA SEM INGREDIENTES
                    case 5:
                        this.batch.draw(armario, j * ESCALA_X, i * ESCALA_Y, ESCALA_X, ESCALA_Y);
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
        font.draw(this.batch, "Pontuacao:" + this.jogador.getDinheiro(), PONTUACAO_X, TEXTO_Y);
        font.draw(this.batch, "" + Cozinha.tempo , CRONOMETRO_X, TEXTO_Y);
    }

    //Trata as entradas do jogador referentes aos utensilios da cozinha
    public void interacaoUtensilios(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            interacaoPegarCaixas();
            if(!this.jogador.isSentidoY() && this.jogador.getPosY() == this.jogador.getMAX_Y()){
                interacaoLixeiras();
                interacaoColocarPratos();
                interacaoPanela();
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            interacaoPegarPratos();
        }
    }

    //Permite o jogador pegar ingredientes da caixa que ele estiver do lado caso nao esteja levando nada
    public void interacaoPegarCaixas(){
        //Caixas da esquerda
        if(this.jogador.getPosX() == this.jogador.getMIN_X() && this.jogador.isSentidoX()){
            this.jogador.setIngredienteCarregado(caixas.get(((this.jogador.getPosY() + ESCALA_Y /2) / ESCALA_Y) * 2));
            this.audioJogo.efeitoInteragirIngredientes();
        }
        //Caixas da direita
        else if(this.jogador.getPosX() == this.jogador.getMAX_X() && !this.jogador.isSentidoX()){
            this.jogador.setIngredienteCarregado(caixas.get((((this.jogador.getPosY() + ESCALA_Y /2) / ESCALA_Y) * 2) + 1));
            this.audioJogo.efeitoInteragirIngredientes();
        }
    }

    //Permite o jogador descartar qualquer coisa que esteja carregando
    public void interacaoLixeiras(){
        //Cuida da lixeira da esquerda e da direita
        if((this.jogador.getPosX() >= ESCALA_X && this.jogador.getPosX() < ESCALA_X * 1.5) ||
            (this.jogador.getPosX() >= ESCALA_X * 5.5 && this.jogador.getPosX() < ESCALA_X * 6.5)){
            this.jogador.descartaCarregado();
            this.audioJogo.efeitoDescartar();
        }
    }

    //Permite o jogador colocar ingredientes no prato, limpando seu inventario depois
    public void interacaoColocarPratos(){
        //Prato da esquerda
        if((this.jogador.getPosX() >= ESCALA_X * 1.5 && this.jogador.getPosX() < ESCALA_X * 2.5)) {
            if (this.jogador.getCaregando() == Jogador.Carregado.INGREDIENTE) {
                this.prato1.add(this.jogador.getIngredienteCarregado());
                this.jogador.descartaCarregado();
                this.audioJogo.efeitoInteragirIngredientes();
            }
            //Caso for uma receita, so funciona se for a gororoba
            else if (this.jogador.getCaregando() == Jogador.Carregado.RECEITA  && this.jogador.getReceitaCarregada().getTipoPrato() == 0) {
                this.prato1.addAll(this.jogador.getReceitaCarregada().getIngredientes());
                this.jogador.descartaCarregado();
                this.audioJogo.efeitoInteragirIngredientes();
            }
        }
        //Prato da direita
        else if(this.jogador.getPosX() >= ESCALA_X * 4.5 && this.jogador.getPosX() < ESCALA_X * 5.5){
                if(this.jogador.getCaregando() == Jogador.Carregado.INGREDIENTE){
                    this.prato2.add(this.jogador.getIngredienteCarregado());
                    this.jogador.descartaCarregado();
                    this.audioJogo.efeitoInteragirIngredientes();
                }
                //Caso for uma receita, so funciona se for a gororoba
                else if(this.jogador.getCaregando() == Jogador.Carregado.RECEITA && this.jogador.getReceitaCarregada().getTipoPrato() == 0){
                    this.prato2.addAll(this.jogador.getReceitaCarregada().getIngredientes());
                    this.jogador.descartaCarregado();
                    this.audioJogo.efeitoInteragirIngredientes();
                }
        }
    }

    //Permite o jogador pegar o conteudo de um prato, gerando uma mistura e limpando o prato
    public void interacaoPegarPratos(){
        if(this.jogador.getCaregando() == Jogador.Carregado.NADA){
            //Prato da esquerda
            if(this.jogador.getPosX() >= ESCALA_X * 1.5 && this.jogador.getPosX() < ESCALA_X * 2.5){
                if(prato1.size() == 1){
                    this.jogador.setIngredienteCarregado(prato1.get(0));
                    this.audioJogo.efeitoInteragirIngredientes();
                }
                else if(prato1.size() >= 2){
                    this.jogador.setReceitaCarregada(new Receita(prato1));
                    this.audioJogo.efeitoInteragirIngredientes();
                }
                this.prato1 = new ArrayList<>();
            }
            //Prato da direita
            else if(this.jogador.getPosX() >= ESCALA_X * 4.5 && this.jogador.getPosX() < ESCALA_X * 5.5){
                if(prato2.size() == 1){
                    this.jogador.setIngredienteCarregado(prato2.get(0));
                    this.audioJogo.efeitoInteragirIngredientes();
                }
                if(prato2.size() >= 2){
                    this.jogador.setReceitaCarregada(new Receita(prato2));
                    this.audioJogo.efeitoInteragirIngredientes();
                }
                this.prato2 = new ArrayList<>();
            }
        }
    }

    //Permite o jogador cozinhar um ingrediente ou cozinhar uma mistura. Para outras coisas, gera cinzas
    public void interacaoPanela(){
        //Panelas da esquerda e direita
        if(this.jogador.getPosX() >= ESCALA_X * 2.5 && this.jogador.getPosX() < ESCALA_X * 4.5){
            //Caso ingrediente
            if(this.jogador.getCaregando() == Jogador.Carregado.INGREDIENTE){
                this.audioJogo.efeitoCozinhar();
                if(this.jogador.getIngredienteCarregado().isPrecisaCozinhar()){
                    this.jogador.getIngredienteCarregado().cozinha();
                }
                else{
                    this.jogador.descartaCarregado();
                    this.jogador.setIngredienteCarregado(new Ingrediente("Cinzas", false, 0, new Texture("Cinzas.png")));

                }
            }
            //Caso receita
            else if(this.jogador.getCaregando() == Jogador.Carregado.RECEITA){
                this.audioJogo.efeitoCozinhar();
                if(this.jogador.getReceitaCarregada().getTipoPrato() == 0){
                    Receita cozinhado = TodasReceitas.cozinhar(this.jogador.getReceitaCarregada().getIngredientes());
                    //Easter Egg
                    if(cozinhado.getTipoPrato() == 2){
                        this.jogador.setSkin(new Sprite(new Texture("Demonio.png")));
                    }
                    this.jogador.descartaCarregado();
                    this.jogador.setReceitaCarregada(cozinhado);
                }
                else{
                    this.jogador.descartaCarregado();
                    this.jogador.setIngredienteCarregado(new Ingrediente("Cinzas", false, 0, new Texture("Cinzas.png")));
                }
            }
        }
    }

    //Rotina de encerramento
    public void dispose(){
        this.batch.dispose();
        this.armario.dispose();
        this.panela.dispose();
        this.caixa.dispose();
        this.chao.dispose();
        this.prato.dispose();
        this.lixeira.dispose();
        this.fogao.dispose();
        this.audioJogo.dispose();
        this.restaurante.dispose();
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

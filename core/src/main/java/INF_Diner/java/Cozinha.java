package INF_Diner.java;

import


    com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Cozinha {

    private boolean mostrarCozinha;
    private final SpriteBatch batch = new SpriteBatch();
    private final Texture panela = new Texture("panela.png");
    private final Texture caixa = new Texture("caixa.png");
    private final Texture chao = new Texture("chao.png");
    private final Texture armario = new Texture("armario.png");
    private final Texture prato = new Texture("prato.png");
    private final Texture lixeira = new Texture("lixeira.png");
    private final Texture fogao = new Texture("fogao.png");
    private final  ArrayList<Ingrediente> caixas = new ArrayList<Ingrediente>();
    private final Jogador jogador = new Jogador();
    private int ingredienteIndex = 0;
    private final int deslocax = -60;
    private final int deslocay = -50;
    private final float escala2 = 1.5f;
    private final int escalax = 1920 / 8;
    private final int escalay = 1080 / 6;

    private final int[][] mapa = {
            { 0, 0, 0, 5, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 4 },
            { 0, 0, 3, 3, 3, 3, 0, 0 },
            { 1, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 1, 1, 2, 2, 1, 1, 1 }
    };

    public Cozinha() {
        caixas.add(new Ingrediente("Bife", true, 1, new Texture("bife.png")));
        caixas.add(new Ingrediente("Arroz", true, 2, new Texture("arroz.png")));
        caixas.add(new Ingrediente("Feijao", true, 3, new Texture("feijao.png")));
        caixas.add(new Ingrediente("Macarrao", true, 4, new Texture("macarrao.png")));
        caixas.add(new Ingrediente("Pão", false, 5, new Texture("pão.png")));
        caixas.add(new Ingrediente("Queijo", false, 6, new Texture("queijo.png")));
        caixas.add(new Ingrediente("Ovo", true, 7, new Texture("ovo.png")));
        caixas.add(new Ingrediente("Tomate", false, 8, new Texture("tomate.png")));
        caixas.add(new Ingrediente("Alface", false, 9, new Texture("alface.png")));
        caixas.add(new Ingrediente("Peixe", true, 10, new Texture("peixe.png")));
        this.mostrarCozinha = false;
    }

    public boolean getMostrarCozinha() {
        return mostrarCozinha;
    }

    public void setMostrarCozinha(boolean mostrarCozinha) {
        this.mostrarCozinha = mostrarCozinha;
    }

    public void render() {
        // renderiza a cozinha
        ScreenUtils.clear(1f, 1f, 1f, 1f);
        this.batch.begin();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) { // se o mapa[i][j] for 2, desenha uma panela
                this.batch.draw(chao, j * escalax, i * escalay, escalax, escalay);
                if (mapa[i][j] == 2) {
                    this.batch.draw(armario, j * escalax, i * escalay, escalax, escalay);
                    this.batch.draw(fogao, j * escalax + deslocax, i * escalay + deslocay, escalax * escala2, escalay * escala2);
                    this.batch.draw(panela, j * escalax, i * escalay, escalax, escalay);
                } // se for 1, desenha uma caixa
                else if (mapa[i][j] == 1) {
                    this.batch.draw(armario, j * escalax, i * escalay, escalax, escalay);
                    this.batch.draw(caixa, j * escalax, i * escalay, escalax, escalay);
                    this.batch.draw(caixas.get(ingredienteIndex).getTextura(), j * escalax, i * escalay, escalax,escalay);
                    ingredienteIndex++;
                    if (ingredienteIndex == caixas.size()) {
                        ingredienteIndex = 0;
                    }
                } else if (mapa[i][j] == 3) {
                    this.batch.draw(armario, j * escalax, i * escalay, escalax, escalay);
                    this.batch.draw(prato, j * escalax, i * escalay, escalax, escalay);
                } else if (mapa[i][j] == 4) {
                    this.batch.draw(lixeira, j * escalax, i * escalay, escalax, escalay);
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            this.mostrarCozinha = false;
        }
        this.jogador.movimentar();
        this.jogador.render(batch);
        this.batch.end();

    }

    public void dispose(){
        this.batch.dispose();
        this.armario.dispose();
        this.panela.dispose();
        this.caixa.dispose();
        this.chao.dispose();
        this.prato.dispose();
        this.lixeira.dispose();
        this.fogao.dispose();
        for (int i = 0; i < 10; i++) {
            this.caixas.get(i).dispose();
        }

    }
}

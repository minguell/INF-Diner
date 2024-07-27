package INF_Diner.java;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Cozinha {

    private boolean mostrarCozinha;
    private SpriteBatch batch = new SpriteBatch();
    private final Texture panela1 = new Texture("panela.png");
    private final Texture panela2 = new Texture("panela.png");
    private final Texture caixa = new Texture("caixa.png"); //para aplicar ainda
    private ArrayList<Ingrediente> caixas = new ArrayList<Ingrediente>();

    // como declaro uma matriz de mapa da cozinha?

    private final int[][] mapa = {
            { 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0 },
            { 1, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 1, 1, 2, 2, 1, 1, 1 }
    }; //no fim nao sei se vou usar isso

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
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        this.batch.begin();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                if (mapa[i][j] == 2) {
                    this.batch.draw(panela1, 2, 2);
                    this.batch.draw(panela2, 400, 2);
                }// else if (mapa[i][j] == 1) {
                //    for (int k = 0; k < caixas.size(); k++) {
                          //  this.batch.draw(caixas.get(k).getTextura(), 2, 2);
                        
            //        }
                //}  FALTA AQUI AINDA (PROVAVELMENTE VOU TER QUE USAR FORÇA BRUTA E FAZER UM INGREDIENTE DE CADA VEZ)
            }}
            
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            this.mostrarCozinha = false;
        }
        this.batch.end();

    }
}

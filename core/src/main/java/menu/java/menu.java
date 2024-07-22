package menu.java;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class menu extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private int opcao = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("logo.png");
    }

    @Override
    public void render() {
        this.tratadorDeEntradas();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(image, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }

    //Permite mudar o botao selecionado no menu principal com as setas ou WS
    public void mudaOpcao(){
        if((Gdx.input.isKeyJustPressed(Input.Keys.DOWN) || Gdx.input.isKeyJustPressed(Input.Keys.S)) && this.opcao < 2){
            this.opcao += 1;
        }
        else if((Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.W)) && this.opcao > 0){
            this.opcao -= 1;
        }
    }

    //Chama metodo da respectiva opcao
    public void opcaoSelecionada(){
        switch(this.opcao){
            case 0:
                this.iniciaJogo();
                break;

            case 1:
                this.abreOpcoes();
                break;

            case 2:
                this.fechaJogo();
                break;
        }
    }

    //Chama metodos baseados na entrada do usuario
    public void tratadorDeEntradas()
    {
        this.mudaOpcao(); //Referente a W S e as setas CIMA BAIXO
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            this.opcaoSelecionada();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            this.fechaJogo();
        }
    }
    public void iniciaJogo(){

    }

    public void fechaJogo(){

    }

    public void abreOpcoes(){

    }
}

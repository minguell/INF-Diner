package INF_Diner.java;

import com.badlogic.gdx.ApplicationAdapter;
/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */

//Classe na qual a execucao e efeitvamente iniciada. Create e a inicializacao de tudo, render e o loop de execucao e dispose e a rotina de encerramento do jogo
public class INF_Diner extends ApplicationAdapter{
    private MenuPrincipal menu;

    //Carrega o menu
    @Override
    public void create() {
        this.menu = new MenuPrincipal();
    }

    //Fica em loop e constitui a interface interativa do usuario
    @Override
    public void render() {
        this.menu.gameLoop();
    }

    //Libera a memoria apos o uso
    @Override
    public void dispose() {
        this.menu.dispose();
    }
}

package INF_Diner.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JogadorTest {

    private Jogador jogador;

    @BeforeEach
    public void setUp() {
        jogador = new Jogador(true);
    }

    @Test
    public void testSetReceitaCarregada() {
        // Atribui uma receita ao jogador
        Receita receita = new Receita(9, null);
        jogador.setReceitaCarregada(receita);
        // Verifica se o jogador carrega uma receita
        assertEquals(receita.getTipoPrato(), jogador.getReceitaCarregada().getTipoPrato(), "Tipo de prato das duas receitas nao e o mesmo");
        assertEquals(Jogador.Carregado.RECEITA, jogador.getCaregando(), "Jogador nao foi indicado como carregando");

        // Cria receita diferente
        Receita novaReceita = new Receita(5, null);
        jogador.setReceitaCarregada(novaReceita);
        // Verifica que a receita carregada não foi alterada, pois não é possível carregar outra receita já carregando uma
        assertNotEquals(novaReceita.getTipoPrato(), jogador.getReceitaCarregada().getTipoPrato(), "Jogador nao deveria pegar outra receita enquanto carrega uma");
        assertNotEquals(Jogador.Carregado.NADA, jogador.getCaregando(), "Jogador foi indicado como nao carregando");
    }

    @Test
    public void testSetIngredienteCarregado() {

        // Cria ingrediente e atribui ao jogador
        Ingrediente ingrediente = new Ingrediente("Pao", false, 5, null);
        jogador.setIngredienteCarregado(ingrediente);
        // Verifica se o ingrediente carregado pelo jogador
        assertEquals(ingrediente.getNome(), jogador.getIngredienteCarregado().getNome(), ingrediente.getNome() + " diferente de " + jogador.getIngredienteCarregado().getNome());
        // Verifica se o jogador está carregando um ingrediente
        assertEquals(Jogador.Carregado.INGREDIENTE, jogador.getCaregando(), "Jogador nao foi indicado como carregando");

        // Verifica que não é possível carregar um ingrediente se já estiver carregando outro
        Ingrediente novoIngrediente = new Ingrediente("Queijo", false, 6, null);
        jogador.setIngredienteCarregado(novoIngrediente);
        assertNotEquals(novoIngrediente.getNome(), jogador.getIngredienteCarregado().getNome(), "Ingrediente nao deveria ter sido substituido");
        assertNotEquals(Jogador.Carregado.NADA, jogador.getCaregando(), "Jogador foi indicado como nao carregando");
    }

    @Test
    public void testSequencial(){
        //Jogador recebe ingrediente
        Ingrediente ingrediente = new Ingrediente("Peixe", true, 10, null);
        jogador.setIngredienteCarregado(ingrediente);
        //Verifica se o esta carregando
        assertEquals(Jogador.Carregado.INGREDIENTE, jogador.getCaregando(), "Jogador nao foi indicado como carregando ingrediente");
        //Verifica se esta carregando o ingrediente 1 depois de receber um 2
        Ingrediente ingrediente2 = new Ingrediente("Cinzas", false, 0, null);
        jogador.setIngredienteCarregado(ingrediente2);
        assertEquals(ingrediente.getNome(), jogador.getIngredienteCarregado().getNome(), "Ingrediente nao deveria ter sido substituido");
        Receita receita = new Receita(13, null);
        jogador.setReceitaCarregada(receita);
        assertEquals(Jogador.Carregado.INGREDIENTE, jogador.getCaregando(), "Jogador nao foi indicado como carregando ingrediente");
        //Descarta para testar o contrario
        jogador.descartaCarregado();
        jogador.setReceitaCarregada(receita);
        assertEquals(Jogador.Carregado.RECEITA, jogador.getCaregando(), "Jogador nao foi indicado como carregando receita");
        jogador.setIngredienteCarregado(ingrediente2);
        assertEquals(Jogador.Carregado.RECEITA, jogador.getCaregando(), "Jogador nao foi indicado como carregando receita");
    }

    @Test
    public void testDescartaCarregado() {
        // Cria receita e atribui ao jogador
        Receita receita = new Receita(3, null);
        jogador.setReceitaCarregada(receita);

        // Descarta receita
        jogador.descartaCarregado();
        // Realiza 3 verificações de que o jogador não está carregando nada
        assertNull(jogador.getReceitaCarregada(), "Jogador nao descartou receita");
        assertNull(jogador.getIngredienteCarregado(), "Jogador nao descartou ingrediente");
        assertEquals(Jogador.Carregado.NADA, jogador.getCaregando(), "Jogador esta indicado como carregando algo");
    }

    @Test
    public void testSaiuCozinha() {
        jogador.setPosY(jogador.getMIN_Y());
        assertTrue(jogador.saiuCozinha(), "Posicao de saiuCozinha errada");
        assertEquals(900, jogador.getPosY());
        assertEquals(910, jogador.getMAX_Y());
        assertEquals(720, jogador.getMIN_Y());
    }

    @Test
    public void testSaiuRestaurante() {
        jogador.setPosY(jogador.getMAX_Y());
        assertTrue(jogador.saiuRestaurante(), "Posicao de saiuRestaurante errada");
        assertEquals(0, jogador.getPosY());
        assertEquals(720, jogador.getMAX_Y());
        assertEquals(-10, jogador.getMIN_Y());
    }
}

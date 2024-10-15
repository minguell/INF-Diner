package INF_Diner.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class RestauranteTest {

    private Restaurante restaurante;
    private Jogador jogador;

    @BeforeEach
    public void setUp() {
        jogador = new Jogador(true);
        restaurante = new Restaurante(jogador);
    }

    @Test
    public void testResetContador() {
        //Testa se o contador reinicia com a geracao de clientes
        restaurante.setContador(31f);
        restaurante.geracaoClientes(true);
        assertEquals(0f, restaurante.getContador(), "Contador nao reiniciou com a geracao de clientes");
    }

    @Test
    public void testGeracaoClientesSpawnExcedeIntervaloGeracao() {
        //Prepara o contador pra gerar um cliente
        restaurante.setContador(31f);
        restaurante.geracaoClientes(true);
        //Procura se algum cliente foi gerado
        boolean temCliente = false;
        for (Cliente cliente : restaurante.getProfessores()) {
            if(cliente != null){
                temCliente = true;
                break;
            }
        }
        assertTrue(temCliente, "Cliente nao gerado com tempo condizente");
    }

    @Test
    public void testGeracaoClientesForcarSpawn() {
        //Muda flag e gera cliente
        restaurante.setForcarSpawn(true);
        restaurante.geracaoClientes(true);
        //Procura se algum cliente foi gerado
        boolean temCliente = false;
        for (Cliente cliente : restaurante.getProfessores()) {
            if(cliente != null){
                temCliente = true;
                break;
            }
        }
        assertTrue(temCliente, "Cliente nao gerado quando forcado");
    }

    @Test
    public void testGeracaoClientesUntilListIsFull() {
        // Adiciona clientes até a capacidade máxima
        int maxProfessores = 6;
        for (int i = 0; i < maxProfessores; i++) {
            restaurante.geracaoClientes(true);
        }
        List<Cliente> professores = restaurante.getProfessores();
        assertEquals(maxProfessores, professores.size(), "Professores adicionais ou a menos foram gerados");

        // Tenta adicionar mais um cliente
        restaurante.geracaoClientes(true);
        assertEquals(maxProfessores, professores.size(), "Professores adicionais ou a menos foram gerados");
    }
    @Test
    public void testGeracaoClientesUmPorVez() {
        // Verifica que os clientes são adicionados um por vez
        int initialSize = restaurante.getProfessores().size();
        restaurante.geracaoClientes(true);
        List<Cliente> professores = restaurante.getProfessores();
        assertEquals(initialSize, professores.size(), "Professores nao foram gerados corretamente") ;
    }
}

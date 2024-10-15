package INF_Diner.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReceitaTest {

    @Test
    public void testanomePrato() {
        Receita receita = new Receita(0, null);
        assertEquals("Mistura", receita.nomePorPrato(0), "Nome nao condiz com esperado");
        assertEquals("Gororoba", receita.nomePorPrato(1), "Nome nao condiz com esperado");
        assertEquals("Pizza Demoniaca", receita.nomePorPrato(2), "Nome nao condiz com esperado");
        assertEquals("Pizza", receita.nomePorPrato(3), "Nome nao condiz com esperado");
        assertEquals("A La Minuta", receita.nomePorPrato(4), "Nome nao condiz com esperado");
        assertEquals("Pao Com Ovo", receita.nomePorPrato(5), "Nome nao condiz com esperado");
        assertEquals("Macarrao Com Queijo", receita.nomePorPrato(6), "Nome nao condiz com esperado");
        assertEquals("Macarrao Com Peixe", receita.nomePorPrato(7), "Nome nao condiz com esperado");
        assertEquals("Macarrao Bolonhesa", receita.nomePorPrato(8), "Nome nao condiz com esperado");
        assertEquals("Hamburguer", receita.nomePorPrato(9), "Nome nao condiz com esperado");
        assertEquals("Marmita Simples", receita.nomePorPrato(10), "Nome nao condiz com esperado");
        assertEquals("Salada", receita.nomePorPrato(11), "Nome nao condiz com esperado");
        assertEquals("Batida De Proteina", receita.nomePorPrato(12), "Nome nao condiz com esperado");
        assertEquals("Sanduiche De Peixe", receita.nomePorPrato(13), "Nome nao condiz com esperado");

    }

}

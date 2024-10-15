package INF_Diner.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IngredienteTest {

    @Test
    public void naoPrecisaCozinhar() {

        //gera um ingrediente
        Ingrediente ingrediente = new Ingrediente("Feijao", true, 3, null);

        // cozinha o ingrediente
        ingrediente.cozinha();

        // testa se a flag é alterada para false e se o nome foi alterado
        assertFalse(ingrediente.isPrecisaCozinhar(), "precisaCozinhar deve ser false após método cozinha()");
    }

    @Test
    public void ingredienteCozido() {

        //gera um ingrediente
        Ingrediente ingrediente = new Ingrediente("Feijao", true, 3, null);

        // cozinha o ingrediente
        ingrediente.cozinha();

        // testa se o nome do ingrediente é alterado
        assertEquals("Feijao (Cozido)", ingrediente.getNome(), "O nome do ingrediente deve ser atualizado para 'Feijao (Cozido)' após método cozinha()");
    }
}

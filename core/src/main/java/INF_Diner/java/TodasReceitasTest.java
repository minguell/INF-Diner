package INF_Diner.java;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TodasReceitasTest {
    @Test
    void testCozinhar() {

        // Cria lista de ingredientes
        ArrayList<Ingrediente> ingredientes = new ArrayList<>(Arrays.asList(
            new Ingrediente("Pao", false, 5, null),
            new Ingrediente("Tomate", false, 8, null),
            new Ingrediente("Queijo", false, 6, null)
            ));

        // "Cria" uma receita com base em outra existente (Pizza)
        Receita receita = new Receita(3, null); // Pizza
        receita.setIngredientes(ingredientes);

        // adiciona receita em TodasReceitas
        TodasReceitas.setReceitas(new ArrayList<>());
        TodasReceitas.getReceitas().add(receita);

        // Confere se os ingredientes correspondem a receita
        Receita result = TodasReceitas.cozinhar(ingredientes);
        assertEquals(receita.getNome(), result.getNome(), "Nome da receita cozinhada nao condiz com o nome da desejada");
    }
}

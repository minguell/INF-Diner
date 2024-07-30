package INF_Diner.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProfessorRicoTest {
    ProfessorRico professor = new ProfessorRico(3, true);
    @BeforeEach
    void setUp() {
        professor = new ProfessorRico(3, true);
    }

    @Test
    void vaiEmbora() {
        //Testa se ele ainda nao foi embora antes da hora
        this.professor.setParado(true);
        this.professor.setPosY(-264);
        assertTrue(this.professor.vaiEmbora(), "Professor nao deveria ter ido embora ainda");
        assertEquals(this.professor.getPosY(), -266, "Professor nao deveria ter ido embora ainda");
        assertFalse(this.professor.isParado(), "Professor deveria estar se movimentando");
        assertTrue(this.professor.vaiEmbora(), "Professor nao deveria ter ido embora ainda");
    }

    @Test
    void satisfeito() { //Deve ser verdade quando ele nao quer repetir
        assertFalse(professor.satisfeito(), "Professor Rico ainda tem pedido");
        professor.setDeveRepetir(false);
        assertTrue(professor.satisfeito(), "Professor rico nao tem pedidos");
    }
}

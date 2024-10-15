package INF_Diner.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProfessorTest {
    Professor professor = new Professor(3, true);
    @BeforeEach
    void setUp() {
        Professor professor = new Professor(3, true);
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
    void satisfeito() { //Deve ser sempre verdade
        assertTrue(professor.satisfeito(), "Professor nao tem pedido");
    }
}

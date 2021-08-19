import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestEspiral {

    @Test
    public void testInicializacao(){
        Espiral espiral = new Espiral();
        assertEquals(" - ", espiral.getNomeDoProduto(),
            "Ao inicializar uma espiral ela deve conter o simbólo ' - ' para representar que não há produto definido");
        assertEquals(0, espiral.getQuantidade(),
            "Ao inicializar uma espiral a quantidade de produtos deve vir zerada");
        assertEquals(0, espiral.getPreco(),
            "Ao inicializar uma espiral o preco do produto na espiral deve vir zaerado");
    }

    @Test
    public void testAlterandoEspiral(){
        Espiral espiral = new Espiral();
        espiral.setNomeDoProduto("m&m");
        espiral.setPreco(3.25);
        espiral.setQuantidade(5);
        assertEquals("m&m", espiral.getNomeDoProduto(),
            "Deve ser possível alterar o nome do produto que está na espiral");
        assertEquals(3.25, espiral.getPreco(),
            "Deve ser possível alterar o preço do produto que está na espiral");
        assertEquals(5, espiral.getQuantidade(),
            "Deve ser possível alterar a quantidade de produtos que está na espiral");
    }
}
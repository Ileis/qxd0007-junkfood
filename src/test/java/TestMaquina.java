import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMaquina {
    
    @Test
    public void testInicializacao(){
        Maquina maquina = new Maquina(3, 5);
        assertEquals(3, maquina.getSizeEspirais(),
            "Ao iniciar uma maquina a quantidade de esperiais deve ser igual ao informada no construtor.");
        assertEquals(5, maquina.getMaximoProdutos(),
            "Ao iniciar uma maquina a quantidade máxima de produtos em uma espiral deve ser igual ao informado no construtor.");
        assertEquals(0, maquina.getFaturamento(),
            "Ao iniciar uma maquina o fataturamento deve vir zerado.");
        assertEquals(0, maquina.getSaldoCliente(),
            "Ao iniciar uma maquina o saldo do cliente deve vir zerado.");
    }

    @Test
    public void testPegarEspiralComIndiceValido(){
        Maquina maquina = new Maquina(2, 1);
        Espiral espiralRetornada = maquina.getEspiral(1);
        assertEquals(" - ", espiralRetornada.getNomeDoProduto(),
            "Deve ser possível retornar uma espiral se o índice for valído.");
        assertEquals(0, espiralRetornada.getQuantidade(),
            "Deve ser possível retornar uma espiral se o índice for valído.");
        assertEquals(0, espiralRetornada.getPreco(),
            "Deve ser possível retornar uma espiral se o índice for valído.");
    }

    @Test
    public void testPegarEspiralComIndiceInvalido(){
        Maquina maquina = new Maquina(3, 4);
        assertTrue(maquina.alterarEspiral(2, "Guaraná-Antártica", 2, 4.75),
            "Deve ser possível alterar as informações da espiral com as descrições do produto se o índice estiver correto.");
        assertTrue(maquina.alterarEspiral(1, "Coca-Cola", 1, 3.25),
            "Deve ser possível alterar as informações da espiral com as descrições do produto se o índice estiver correto.");
        Espiral espiral = maquina.getEspiral(3);
        assertNull(espiral, "Não deve ser possível pegar um espiral com índice inválido.");
    }

    @Test
    public void testAlterarEspiralCorretamente(){
        Maquina maquina = new Maquina(3, 5);
        assertTrue(maquina.alterarEspiral(2, "Ruffles", 4, 4.50),
            "Deve ser possível alterar as informações da espiral com as descrições do produto se o índice estiver correto.");
        Espiral espiral = maquina.getEspiral(2);
        assertEquals("Ruffles", espiral.getNomeDoProduto(),
            "Deve ser possível alterar o nome do produto na espiral correspondente ao índice informado.");
        assertEquals(4, espiral.getQuantidade(),
            "Deve ser possível alterar a quantidade de produto na espiral correspondente ao índice informado.");
        assertEquals(4.50, espiral.getPreco(),
            "Deve ser possível alterar o preço do produto na espiral correspondente ao índice informado.");
    }

    @Test 
    public void testAlterarEspiralComLimiteExcedido(){
        Maquina maquina = new Maquina(4, 2);
        assertFalse(maquina.alterarEspiral(1, "Doritos", 5, 5.00),
            "Não deve ser possível alterar as informações da espiral se a quantidade de produtos excede o limite máximo da maquina");
        assertFalse(maquina.alterarEspiral(3, "Coca-Cola", 3, 8.00),
            "Não deve ser possível alterar as informações da espiral se a quantidade de produtos excede o limite máximo da maquina");
    }

    @Test
    public void testAlterarEspiralComIndiceIncorreto(){
        Maquina maquina = new Maquina(2, 3);
        assertFalse(maquina.alterarEspiral(4, "Doritos", 2, 5.00),
            "Não deve ser possível alterar as informações da espiral se o indice informado estiver incorreto.");
        assertFalse(maquina.alterarEspiral(-1, "Coca-Cola", 3, 8.00),
            "Não deve ser possível alterar as informações da espiral se o indice informado estiver incorreto.");
    }

    @Test
    public void testLimparEspiralCorretamente(){
        Maquina maquina = new Maquina(3, 4);
        assertTrue(maquina.alterarEspiral(1, "Ruffles", 4, 4.50),
            "Deve ser possível alterar as informações da espiral com as descrições do produto se o índice estiver correto.");
        assertTrue(maquina.alterarEspiral(2, "Coca-Cola", 3, 8.00),
            "Deve ser possível alterar as informações da espiral com as descrições do produto se o índice estiver correto.");
        assertTrue(maquina.limparEspiral(1),
            "Deve ser possível limpar da espiral as descrições dos produtos se o índice estiver correto.");
        Espiral espiral = maquina.getEspiral(1);
        assertEquals(" - ", espiral.getNomeDoProduto(),
            "Deve ser possível alterar o nome do produto na espiral para o status vazio.");
        assertEquals(0, espiral.getQuantidade(),
            "Deve ser possível alterar a quantidade de produtos na espira para zero.");
        assertEquals(0.0, espiral.getPreco(),
            "Deve ser possível alterar o preço do produto na espiral para zero.");
    }

    @Test
    public void testLimparEspiralComIndiceIncorreto(){
        Maquina maquina = new Maquina(3, 4);
        assertTrue(maquina.alterarEspiral(1, "Ruffles", 4, 4.50),
            "Deve ser possível alterar as informações da espiral com as descrições do produto se o índice estiver correto.");
        assertTrue(maquina.alterarEspiral(2, "Coca-Cola", 3, 8.00),
            "Deve ser possível alterar as informações da espiral com as descrições do produto se o índice estiver correto.");
        assertFalse(maquina.limparEspiral(3),
            "Não deve ser possível limpar as informações da espiral se o índice informado estiver incorreto.");
        assertFalse(maquina.limparEspiral(-1),
            "Não deve ser possível limpar as informações da espiral se o índice informado estiver incorreto.");
    }

    @Test
    public void testAdicionarDinheiro(){
        Maquina maquina = new Maquina(3, 4);
        assertTrue(maquina.inserirDinheiro(4.75),
            "Deve ser possível adicionar dinheiro na máquina se o valor for maior que 0.");
        assertTrue(maquina.inserirDinheiro(5.25), 
            "Deve ser possível adicionar dinheiro na máquina se o valor for maior que 0.");
        assertFalse(maquina.inserirDinheiro(-3.35),
            "Não deve ser possível adicionar dinheiro na máquina se o valor for menor ou igual a 0");
        assertEquals(10.00, maquina.getSaldoCliente(),
            "Deve ser possível um cliente inserir dinheiro na maquina.");
    }

    @Test
    public void testReceberTroco(){
        Maquina maquina = new Maquina(3, 4);
        maquina.inserirDinheiro(7.32);
        maquina.inserirDinheiro(6.68);
        assertEquals(14.00, maquina.getSaldoCliente(),
            "Deve ser possível um cliente inserir dinheiro na maquina.");
        assertEquals(14.00, maquina.receberTroco(),
            "Deve ser possível o cliente receber o troco da maquina.");
        assertEquals(0, maquina.getSaldoCliente(),
            "Ao receber o troco o saldo do cliente fica zerado.");
    }

    @Test
    public void testVenderProdutoComSucessoEReceberTroco(){
        Maquina maquina = new Maquina(3, 4);
        assertTrue(maquina.alterarEspiral(1, "Ruffles", 3, 4.50),
            "Deve ser possível alterar as informações da espiral com as descrições do produto se o índice estiver correto.");
        assertTrue(maquina.alterarEspiral(0, "Lays", 2, 5.00),
            "Deve ser possível alterar as informações da espiral com as descrições do produto se o índice estiver correto.");
        assertTrue(maquina.alterarEspiral(2, "Sensações", 2, 6.00),
            "Deve ser possível alterar as informações da espiral com as descrições do produto se o índice estiver correto.");
        assertTrue(maquina.inserirDinheiro(2.25),
            "Deve ser possível adicionar dinheiro na máquina se o valor for maior que 0.");
        assertTrue(maquina.inserirDinheiro(4.75),
            "Deve ser possível adicionar dinheiro na máquina se o valor for maior que 0.");
        assertTrue(maquina.vender(2),
            "Deve ser possível vender um produto da espiral se a máquina estiver com saldo sufieciente e se houver quantidade suficiente na espiral.");
        assertEquals(1.00, maquina.getEspiral(2).getQuantidade(),
            "Ao vender um item a quanditidade de produto será decrementada se ainda houver mais produtos na espiral.");
        assertEquals(1.00, maquina.receberTroco(),
            "Deve ser possível o cliente receber troco se houver troco resultante da compra.");
        assertEquals(6.00, maquina.getFaturamento(),
            "Ao vender um item o lucro é incrementado com o valor do produto que foi vendido da espiral.");
    }

    @Test
    public void testVenderProdutoComSucessoELimparEspiral(){
        Maquina maquina = new Maquina(3, 4);
        assertTrue(maquina.alterarEspiral(2, "Guaraná-Antártica", 2, 4.75),
            "Deve ser possível alterar as informações da espiral com as descrições do produto se o índice estiver correto.");
        assertTrue(maquina.alterarEspiral(1, "Coca-Cola", 1, 3.25),
            "Deve ser possível alterar as informações da espiral com as descrições do produto se o índice estiver correto.");
        maquina.inserirDinheiro(12.00);
        assertTrue(maquina.vender(2),
            "Deve ser possível vender um produto da espiral se a máquina estiver com saldo sufieciente e se houver quantidade suficiente na espiral.");
        assertTrue(maquina.vender(2),
            "Deve ser possível vender um produto da espiral se a máquina estiver com saldo sufieciente e se houver quantidade suficiente na espiral.");
        assertEquals(2.50, maquina.receberTroco(),
            "Deve ser possível o cliente receber troco se houver troco resultante da compra.");
        assertEquals(9.50, maquina.getFaturamento(),
            "Ao vender um item o lucro é incrementado com o valor do produto que foi vendido da espiral.");
        Espiral espiral = maquina.getEspiral(2);
        assertEquals(" - ", espiral.getNomeDoProduto(),
            "Deve ser possível alterar o nome do produto na espiral para o status vazio.");
        assertEquals(0, espiral.getQuantidade(),
            "Deve ser possível alterar a quantidade de produtos na espira para zero.");
        assertEquals(0.0, espiral.getPreco(),
            "Deve ser possível alterar o preço do produto na espiral para zero.");
    }

    @Test
    public void testVenderProdutoComIndiceErrado(){
        Maquina maquina = new Maquina(3, 4);
        assertTrue(maquina.alterarEspiral(2, "Guaraná-Antártica", 2, 4.75),
            "Deve ser possível alterar as informações da espiral com as descrições do produto se o índice estiver correto.");
        assertTrue(maquina.alterarEspiral(1, "Coca-Cola", 1, 3.25),
            "Deve ser possível alterar as informações da espiral com as descrições do produto se o índice estiver correto.");
        assertTrue(maquina.inserirDinheiro(5.25),
            "Deve ser possível adicionar dinheiro na máquina se o valor for maior que 0.");
        assertFalse(maquina.vender(3),
            "Não deve ser possível vender um produto se o indice não corresponder a nenhuma espiral.");
        assertFalse(maquina.vender(-2),
            "Não deve ser possível vender um produto se o indice não corresponder a nenhuma espiral.");
    }

    @Test
    public void testVenderProdutoSemDinheiroSuficiente(){
        Maquina maquina = new Maquina(3, 4);
        assertTrue(maquina.alterarEspiral(2, "Guaraná-Antártica", 2, 4.75),
            "Deve ser possível alterar as informações da espiral com as descrições do produto se o índice estiver correto.");
        assertTrue(maquina.alterarEspiral(1, "Coca-Cola", 1, 3.25),
            "Deve ser possível alterar as informações da espiral com as descrições do produto se o índice estiver correto.");
        maquina.inserirDinheiro(4.70);
        assertEquals(4.70, maquina.getSaldoCliente(),
            "Deve ser possível um cliente inserir dinheiro na maquina.");
        assertFalse(maquina.vender(2),
            "Não deve ser possível comprar um produto se o dinheiro inserido na máquina não for suficiente.");
        Espiral espiral = maquina.getEspiral(2);
        assertEquals(2, espiral.getQuantidade(),
            "Não deve ser possível alterar a quantidade de produtos se não foi possível vender o produto.");
    }

    @Test
    public void testVenderProdutoComDinheiroExato(){
        Maquina maquina = new Maquina(3, 4);
        assertTrue(maquina.alterarEspiral(2, "Guaraná-Antártica", 2, 4.75),
            "Deve ser possível alterar as informações da espiral com as descrições do produto se o índice estiver correto.");
        assertTrue(maquina.alterarEspiral(1, "Coca-Cola", 1, 3.25),
            "Deve ser possível alterar as informações da espiral com as descrições do produto se o índice estiver correto.");
        maquina.inserirDinheiro(3.25);
        assertEquals(3.25, maquina.getSaldoCliente(),
            "Deve ser possível um cliente inserir dinheiro na maquina.");
        assertTrue(maquina.vender(1),
            "Deve ser possível vender um produto da espiral se a máquina estiver com saldo sufieciente e se houver quantidade suficiente na espiral.");
        Espiral espiral = maquina.getEspiral(1);
        assertEquals(0, espiral.getQuantidade(),
            "Deve ser possível alterar a quantidade de produtos se o foi possível vender o produto.");
    }
}

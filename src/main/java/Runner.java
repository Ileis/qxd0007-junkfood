public class Runner {

    public static void main(String[] args) {

        Maquina maquina = new Maquina(3, 5);
        System.out.println(maquina);
        /*
            saldo: 0.0
            0[  -  : 0 U : 0.0 RS]
            1[  -  : 0 U : 0.0 RS]
            2[  -  : 0 U : 0.0 RS]
        */

        maquina.alterarEspiral(2, "Ruffles", 4, 5.25);
        maquina.alterarEspiral(1, "Doritos", 2, 4.50);
        maquina.alterarEspiral(0, "Sensações", 1, 7.00);
        System.out.println(maquina);
        /*
            saldo: 0.0
            0[ Sensações : 1 U : 7.0 RS]
            1[ Doritos : 2 U : 4.5 RS]  
            2[ Ruffles : 4 U : 5.25 RS] 
        */

        maquina.inserirDinheiro(6.00);
        if(!maquina.vender(0)){
            System.out.println("fail: não foi possível comprar o item, saldo insuficiente.");
            //fail: não foi possível comprar o item, saldo insuficiente.
        }

        maquina.inserirDinheiro(3.25);
        maquina.vender(0);
        System.out.println(maquina);
        /*
            saldo: 2.25
            0[  -  : 0 U : 0.0 RS]
            1[ Doritos : 2 U : 4.5 RS]
            2[ Ruffles : 4 U : 5.25 RS]
        */

        if(!maquina.vender(0)){
            System.out.println("fail: não foi possível comprar o item, espiral sem produtos.");
            //fail: não foi possível comprar o item, espiral sem produtos.
        }

        maquina.inserirDinheiro(7.50);
        maquina.vender(2);
        System.out.println(maquina);
        /*
            saldo: 4.50
            0[  -  : 0 U : 0.0 RS]
            1[ Doritos : 2 U : 4.5 RS]
            2[ Ruffles : 3 U : 5.25 RS]
        */

        if(!maquina.vender(4)){
            System.out.println("fail: não foi possível comprar o item, espiral não encontrada com o índice informado.");
            //fail: não foi possível comprar o item, espiral não encontrada com o índice informado.
        }

        double troco = maquina.receberTroco();
        System.out.println(troco);
        // 4.50

        double faturamento = maquina.getFaturamento();
        System.out.println(faturamento);
        // 12.25
    }  
}

import java.util.ArrayList;
import java.util.LinkedList;

public class Maquina {

    private final int QTD_ESPIRAIS;
    private final int MAXIMO_PRODUTOS;
    private ArrayList<Espiral> espirais;
    private double dinheiro;
    private double saldo;

    public Maquina(int qtdEspirais, int maximoProdutos){
        this.QTD_ESPIRAIS = qtdEspirais;
        this.MAXIMO_PRODUTOS = maximoProdutos;
        espirais = new ArrayList<Espiral>();
        inicializaEspirais();
    }

    private void inicializaEspirais(){
        for(int qtdEspirais = 0; qtdEspirais < QTD_ESPIRAIS; qtdEspirais++){
            espirais.add(new Espiral());
        }
    }

    public double getFaturamento() {
        return saldo;
    }

    public int getMaximoProdutos() {
        return MAXIMO_PRODUTOS;
    }

    public double getSaldoCliente() {
        return dinheiro;
    }

    public int getSizeEspirais(){
        return QTD_ESPIRAIS;
    }

    public Espiral getEspiral(int indice){
        if(indiceValido(indice))
            return espirais.get(indice);
        return null;
    }

    public boolean inserirDinheiro(double value){
        if(value > 0) {
            dinheiro += value;

            return true;
        }
        return false;
    }

    public double receberTroco(){
        double troco = dinheiro;
        dinheiro = 0;
        return troco;
    }

    public boolean alterarEspiral(int indice, String nome, int quantidade, double preco){
        if(indiceValido(indice)){
            if(quantidade <= MAXIMO_PRODUTOS){
                espirais.get(indice).setQuantidade(quantidade);
                espirais.get(indice).setPreco(preco);
                espirais.get(indice).setNomeDoProduto(nome);

                return true;
            }
            return false;
        }
        return false;
    }

    public boolean limparEspiral(int indice){
        if(indiceValido(indice)){
            espirais.set(indice, new Espiral());

            return true;
        }
        return false;
    }

    public boolean vender(int indice){
        if(indiceValido(indice)){
            if(qtdSuficiente(indice) && dinheiroSuficiente(indice)){
                espirais.get(indice).venderProduto();

                saldo += espirais.get(indice).getPreco();
                dinheiro -= espirais.get(indice).getPreco();

                if (espirais.get(indice).isEmpty())
                    limparEspiral(indice);

                return true;
            }
        }
        return false;
    }

    private boolean indiceValido(int indice){
        return (indice < QTD_ESPIRAIS && indice >= 0);
    }

    private boolean qtdSuficiente(int indice){
        return espirais.get(indice).getQuantidade() > 0;
    }

    private boolean dinheiroSuficiente(int indice){
        return (dinheiro >= espirais.get(indice).getPreco());
    }
}
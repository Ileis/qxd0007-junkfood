import java.util.ArrayList;

public class Maquina {

    private final int QTD_ESPIRAIS;
    private final int MAXIMO_PRODUTOS;
    private ArrayList<Espiral> espirais;
    private double saldoCliente;
    private double saldoMaquina;

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
        return saldoMaquina;
    }

    public int getMaximoProdutos() {
        return MAXIMO_PRODUTOS;
    }

    public double getSaldoCliente() {
        return saldoCliente;
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
            saldoCliente += value;

            return true;
        }
        return false;
    }

    public double receberTroco(){
        double troco = saldoCliente;
        saldoCliente = 0;
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

                receberSaldoMaquina(espirais.get(indice).getPreco());
                descontarSaldoCliente(espirais.get(indice).getPreco());

                if (espirais.get(indice).isEmpty())
                    limparEspiral(indice);

                return true;
            }
        }
        return false;
    }

    private void receberSaldoMaquina(double value){
        saldoMaquina += value;
    }

    private void descontarSaldoCliente(double value){
        saldoCliente -= value;
    }

    private boolean indiceValido(int indice){
        return (indice < QTD_ESPIRAIS && indice >= 0);
    }

    private boolean qtdSuficiente(int indice){
        return espirais.get(indice).getQuantidade() > 0;
    }

    private boolean dinheiroSuficiente(int indice){
        return (saldoCliente >= espirais.get(indice).getPreco());
    }
}